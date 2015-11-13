package com.azim.syllabification;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by otkurbiz on 11/10/15.
 */
public interface Syllabify {

    void Syllabication(String source, List<String> syllabList);

    void setMathRule(Map<String, Integer> match, boolean isMerge);

    void setVowelCharacterSet(String[] vowels);

    void setConsonantCharacterSet(String[] consonants);
}
