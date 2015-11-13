package com.azim.syllabification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by otkurbiz on 11/10/15.
 */
public class UyghurSyllabification extends AbstractSyllabification{

    //vowel character
    private  String[] UYGHUR_VOWELS = {"a","e","i","é","o","u","ö","ü"};

    //consonants character
    private  String[] UYGHUR_CONSONANTS = {"b","p","t","j","ch", "x","d","r","z","zh", "s","sh","f","gh","q", "k","g","ng","l","m", "w","y","h","n",};

    //hemze
    private static String HEMZE = "'";



    /**
     * Singleton Mode
     */
    private static UyghurSyllabification SINGLE = null;
    public static UyghurSyllabification getInstance(){
        if (SINGLE == null){
            SINGLE = new UyghurSyllabification();
        }
        return  SINGLE;
    }

    /**
     * Construction Method
     * init variables
     */
    private UyghurSyllabification(){
        super();
        setMathRule(null,false);
        setVowelCharacterSet(null);
        setConsonantCharacterSet(null);
    }

    /**
     * is vowel character
     * @param str
     * @return
     */
    protected boolean isVolwels(String str){
        for (int i=0; i<VOWELS.length; i++){
            if(VOWELS[i].equals(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * is consonant character
     * @param str
     * @return
     */
    protected boolean isConsonants(String str){
        for (int i=0; i<CONSONANTS.length; i++){
            if(CONSONANTS[i].equals(str)){
                return true;
            }
        }
        return false;
    }


    /**
     * get the number of Syllabification
     * @param source
     * @return
     */
    public int numberOfSyllabification(String source){
        int count = 0;
        for (int i = 0;i<source.length() ;i++){
            String sub = source.substring(i, i + 1);
            if (isVolwels(sub)){
                count++;
            }
        }
        return count;
    }

    /**
     * get the format of source code
     * @param source
     * @return
     */
    protected String getFormatString(String source){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<source.length();i++){
            String sub = source.substring(i, i + 1);
            String key = sub;

            //jude the double char character
            if ((sub.equals("z") || sub.equals("c") || sub.equals("s") || sub.equals("g")) && i < source.length()-1){
                String next = source.substring(i+1,i+2);
                if (next.equals("h")){
                    key = sub + next;
                    i++;
                }
            }else if(sub.equals("n") && i < source.length()-1){
                String next = source.substring(i+1,i+2);
                if (next.equals("g")){
                    key = sub + next;
                    i++;
                }
            }

            //store the original character
            charList.add(key);

            //match keys
            if (isVolwels(key)){
                stringBuilder.append("V");
            }else if(isConsonants(key)){
                stringBuilder.append("C");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * get the syllabification list of source string(only the word)
     * @param source
     * @param syllabList
     */
    public void Syllabication(String source,List<String> syllabList){
        String formatString = getFormatString(source);
        int begin = 0;
        int end = 1;
        while (end <= formatString.length()){
            String format = formatString.substring(begin,end);
            if (matchRule.containsKey(format)){
                int index = matchRule.get(format);
                //String out = formatString.substring(begin,begin+index);
                String syllab = getSyllab(begin, begin + index);
                begin += index;
                end = begin;
                syllabList.add(syllab);
            }
            if (end == formatString.length()){
                String syllab = getSyllab(begin, end);
                syllabList.add(syllab);
            }
            end++;
        }

    }

    /**
     * get one syllabification in original word
     * @param begin
     * @param end
     * @return
     */
    private String getSyllab(int begin,int end){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = begin;i < end ; i++){
            stringBuilder.append(charList.get(i));
        }
        return stringBuilder.toString();
    }

    /**
     * Set rule for syllabification
     *
     * @param match
     * V is represents vowel
     * C is represents consonants
     * Key for map is match rule
     * value for map is divide position for given rule
     */
    public void setMathRule(Map<String,Integer> match,boolean isMerge){

        //default match rule rule for uyghur language
        matchRule =new HashMap();
        matchRule.put("VCCV",2);
        matchRule.put("CVCCV",3);
        matchRule.put("CVCCVC",3);
        matchRule.put("CCVCCVC",4);

        matchRule.put("VCV",1);
        matchRule.put("CVCV",2);
        matchRule.put("VCVC",1);
        matchRule.put("CVCVC",2);
        matchRule.put("CVCVCC",2);
        matchRule.put("CCVCV",3);

        matchRule.put("VCCCV",3);
        matchRule.put("CVCCCV",3);
        //matchRule.put("VCCCVC",2);
        //matchRule.put("CVCCCVC",2);
        matchRule.put("VCCCCV",3);
        matchRule.put("CVVCCV",4);
        if (match != null){
            if (!isMerge){
                matchRule = match;
            }else{
                for (Map.Entry entry:match.entrySet()){
                    matchRule.put(entry.getKey().toString(),Integer.parseInt(entry.getValue().toString()));
                }
            }
        }
    }

    public void setVowelCharacterSet(String[] vowels){
        if (vowels != null){
            VOWELS = vowels;
        }else{
            VOWELS = UYGHUR_VOWELS;
        }
    }

    public void setConsonantCharacterSet(String[] consonants){
        if (consonants != null){
            CONSONANTS = consonants;
        }else{
            CONSONANTS = UYGHUR_CONSONANTS;
        }
    }



}
