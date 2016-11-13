package com.homework.models;

import java.util.*;

/**
 * Created by mikhail on 13.09.16.
 */
public class InvertIndex {

    private HashMap<String, String> baseBooks;
    private HashMap<String, LinkedHashSet<String>> baseIndex;


    public InvertIndex() {
        Bases base = Bases.getInstance();
        baseBooks = base.getBaseBooks();
        baseIndex = base.getBaseIndex();
    }

    public String[] getDocNames(String searchText) {
        searchText = searchText.toLowerCase();
        String[] searchWords = searchText.split(" ");

        HashSet<String> currentDocs = new HashSet<String>(baseBooks.keySet());
        for (String word : searchWords) {
            if (baseIndex.get(word) != null) {
                currentDocs = intersection(currentDocs, baseIndex.get(word));
            } else {
                currentDocs = new HashSet<String>();
            }
        }
        return currentDocs.toArray(new String[currentDocs.size()]);
    }

    public String[] getTextInDocs(String searchText, String[] docsName) {
        List<String[]> result = new ArrayList<String[]>();
        for(String doc : docsName) {
            String text = baseBooks.get(doc);
            System.out.println(baseIndex.get(searchText));
            String[] searchWords = searchText.split(" ");
            List<String> elementText = new ArrayList<String>();
            for(String word : searchWords)
                try {
                    elementText.add(text.substring(prevIndexWord(text, word), nextIndexWord(text, word)));
                } catch (Exception e) {
                    System.out.println("prev: " + prevIndexWord(text, word));
                    System.out.println("next: " + nextIndexWord(text, word));
                    e.printStackTrace();
                }
            result.add(elementText.toArray(new String[elementText.size()]));
        }
        return makeFinalString(result);

    }

    private Integer nextIndexWord(String text, String word) {
        Integer result;
        result = text.indexOf(" " + word + " ");
        if (result == -1)
            result = text.indexOf(" " + word + ",");
        if (result == -1)
            result = text.indexOf(" " + word + ".");
        if (result == -1)
            result = text.indexOf(word);
        int k = 0;
        while(k < 2) {
            if (text.charAt(result) == ' ')
                k++;
            result++;
        }
        return --result;

    }

    private Integer prevIndexWord(String text, String word) {
        Integer result;
        result = text.indexOf(" " + word +  " ");
        if (result == -1)
            result = text.indexOf(" " + word + ",");
        if (result == -1)
            result = text.indexOf(" " + word + ".");
        if (result == -1)
            result = text.indexOf(word);
        int k = 0;
        while(k < 2) {
            if(text.charAt(result) == ' ')
                k++;
            result--;
            if (result <= 0)
                return 0;
        }
        return ++result;
    }

    private String[] makeFinalString(List<String[]> result) {
        List<String> returns = new ArrayList<String>();
        for(String[] arrText : result) {
            String s = "...";
            for(String text : arrText) {
                s += text + "... ";
            }
            returns.add(s);
        }
        return returns.toArray(new String[returns.size()]);
    }

    private HashSet<String> intersection(HashSet<String> a, LinkedHashSet<String> b) {

        HashSet<String> temp = new HashSet<String>();
        for(String s : b) {
            if(a.contains(s)) {
                temp.add(s);
            }
        }
        return temp;
    }

}
