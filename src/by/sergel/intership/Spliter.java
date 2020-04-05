package by.sergel.intership;


import java.util.regex.Pattern;

public class Spliter {
    public static String DELIMITERS = "\\s*(\\s|,|\\.)\\s*";

    public static String[] splitIntoWords(String str){
        Pattern pattern = Pattern.compile(DELIMITERS);
        return pattern.split(str);
    }
}
