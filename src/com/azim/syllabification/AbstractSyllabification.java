package com.azim.syllabification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by otkurbiz on 11/11/15.
 */
public abstract class AbstractSyllabification implements Syllabify{

    //vowel character
    protected   String[] VOWELS ;

    //consonants character
    protected   String[] CONSONANTS ;

    //Map to store Syllabification rules
    protected Map<String,Integer> matchRule;

    //List to store original characters
    protected List<String> charList;

    protected AbstractSyllabification(){
        charList = new ArrayList<>();
        setMathRule(null,false);
        setVowelCharacterSet(null);
        setConsonantCharacterSet(null);
    }


    abstract protected  boolean isVolwels(String str);

    abstract protected boolean isConsonants(String str);

    abstract protected String getFormatString(String source);

    @Override
    public void Syllabication(String source, List<String> syllabList) {
    }

    @Override
    public void setMathRule(Map<String, Integer> match, boolean isMerge) {
        matchRule = new HashMap<>();
    }

    @Override
    public void setVowelCharacterSet(String[] vowels) {
        VOWELS = new String[]{};
    }

    @Override
    public void setConsonantCharacterSet(String[] consonants) {
        CONSONANTS = new String[]{};
    }
}
