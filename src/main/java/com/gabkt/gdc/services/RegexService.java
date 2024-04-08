package com.gabkt.gdc.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexService {
    public static String removeSpecialCharacters(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group());
        }

        return result.toString();
    }
}
