package by.sergel.intership;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Parser {
    public static final String SQL = "\\b\\s*((SELECT|select)\\b\\s+([A-Za-z0-9,\\s]+|[*]))(\\s+\\b(FROM|from)\\b\\s+" +
            "([A-Za-z0-9]+))(((\\s+(WHERE|where)\\s+[a-zA-Z0-9]+\\s*(<>|=||>|<)\\s*[a-zA-Z0-9\"']+" +
            "(\\s+(and|AND)\\s+[a-zA-Z0-9]+\\s*(<>|=|>|<)\\s*[a-zA-Z0-9\"']+)*)$)|(\\s+" +
            "(OFFSET|offset|LIMIT|limit)\\s+\\d+))*";
    public static final String KEYWORDS_SQL = "select|from|where|offset|limit";
    public static final String COMPARE_EXPRESSION = "[A-Za-z0-9\"]+\\s*[<>=]+\\s*[A-Za-z0-9\"']+";

    public static List<String> parseSqlToList(String sqlQuery, String regex){
        List<String> argHolder = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sqlQuery);

        int start = 0;

        while (matcher.find()){
            if(matcher.start() > 0){
                argHolder.add(sqlQuery.substring(start, matcher.start()).trim());
            }
            argHolder.add(matcher.group());
            start = matcher.end();
        }
        if(start != sqlQuery.length()){
            argHolder.add(sqlQuery.substring(start, sqlQuery.length()).trim());
        }
        return argHolder;
    }

    public static Map<String, String> parseSqlToMap(String sqlQuery, String regex){
        if(sqlQuery == null || regex == null){
            return null;
        }
        List<String> arguments = parseSqlToList(sqlQuery, regex);
        Map<String, String> map = new LinkedHashMap<>();

        for(int i = 0; i < arguments.size(); i += 2){
            map.put(arguments.get(i), arguments.get(i + 1));
        };
        return map;
    }
}
