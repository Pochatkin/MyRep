package com.exigenservices.lectures;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Михаил on 12.11.2015.
 */

@Stateless
public class Parser {

    // Полный костыль, сделать нормально через список.
    public char[] parser(String text) {
        char[] chars = new char[text.length()];

        if (text == null) {
            new NoSuchElementException("Text is empty");
        } else {
            int n = 1;
            boolean bool = true;

            chars[0] = text.charAt(0);
            char temp;
            for (int i = 0; i < text.length(); i++) {
                temp = text.charAt(i);
                for (int j = i; j > 0; j--) {
                    if (temp == chars[j]) {
                        bool = false;
                        break;
                    }
                }
                if (bool) {
                    n++;
                }
                bool = true;
            }
        }
        return chars;
    }

}
