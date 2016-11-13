package com.homework.models;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by mikhail on 15.09.16.
 */
class Bases {
    private static volatile Bases instance;
    private HashMap<String, String> baseBooks;
    private HashMap<String, LinkedHashSet<String>> baseIndex;

    HashMap<String, String> getBaseBooks() {
        return baseBooks;
    }

    HashMap<String, LinkedHashSet<String>> getBaseIndex() {
        return baseIndex;
    }


    private Bases() {
        baseBooks = new HashMap<String, String>();
        baseIndex = new HashMap<String, LinkedHashSet<String>>();
        try {
            getBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getBase() throws IOException {
        if (baseIndex.isEmpty()) {
            for (Integer i = 1; i <= 10; i++) {
                initBases(i.toString() + ".txt");
            }
        }
    }

    private List<String> split(List<String> list) {
        Pattern p = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
        List<String> temp = new LinkedList<String>();
        for (String a : list) {
            Collections.addAll(temp, p.split(a));
        }
        ListIterator<String> iterator = temp.listIterator();
        while(iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }
        return temp;
    }

    private void initBases(String indexDocument) throws IOException {
        File f = new File("1.txt");
        Logger logger = Logger.getLogger(Bases.class.getName());
        logger.info(f.getAbsolutePath());
        List<String> arrWord = Files.readAllLines(Paths.get("res/" + indexDocument), StandardCharsets.UTF_8);
        String s = new String();
        for(String words : arrWord){
            s += words + " ";
        }
        arrWord = split(arrWord);
        for (String word : arrWord) {
            baseBooks.put(indexDocument, s);
            if (!baseIndex.containsKey(word)) {
                LinkedHashSet<String> temp = new LinkedHashSet<String>();
                temp.add(indexDocument);
                baseIndex.put(word, temp);
            } else {
                baseIndex.get(word).add(indexDocument);
            }
        }
    }

    static Bases getInstance() {
        if(instance == null)
            synchronized (Bases.class) {
                if (instance == null)
                    instance = new Bases();
            }
        return instance;
    }

}