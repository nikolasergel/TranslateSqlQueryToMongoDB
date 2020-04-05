package by.sergel.intership;


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator{
    public String translate(String sqlQuery){
        if(sqlQuery != null) {
            StringBuilder mongo = new StringBuilder();
            Pattern pattern = Pattern.compile(Parser.SQL);
            Matcher matcher = pattern.matcher(sqlQuery);

            if (matcher.matches()) {
                mongo.append("db");
                Map<String, String> mapSqlQuery = Parser.parseSqlToMap(sqlQuery, Parser.KEYWORDS_SQL);
                for (String k : mapSqlQuery.keySet()) {
                    switch (k.toLowerCase()) {
                        case "select": {
                            mongo.append(".find({}".concat(select(mapSqlQuery.get(k))).concat(")"));
                            break;
                        }
                        case "from": {
                            mongo.insert(mongo.indexOf(".find"), from(mapSqlQuery.get(k)));
                            break;
                        }
                        case "where": {
                            mongo.insert(mongo.indexOf("{}") + 1, where(mapSqlQuery.get(k)));
                            break;
                        }
                        case "offset": {
                            mongo.append(skip(mapSqlQuery.get(k)));
                            break;
                        }
                        case "limit": {
                            mongo.append(limit(mapSqlQuery.get(k)));
                            break;
                        }
                    }
                }
                return mongo.toString();
            }
        }
        return "Enter the correct SQL request!";
    }

    private String select(String arg){
        if(arg != null) {
            String[] arguments = Spliter.splitIntoWords(arg);
            StringBuilder ret = new StringBuilder(", {");
            if (arguments.length > 1) {
                for (String k : arguments) {
                    ret.append(k.concat(": 1, "));
                }
                ret.delete(ret.length() - 2, ret.length());
            } else {
                if (arguments[arguments.length - 1].equals("*")) {
                    return "";
                }
                ret.append(arguments[arguments.length - 1].concat(": 1"));
            }
            ret.append("}");
            return ret.toString();
        }
        return "";
    }

    private static String from(String arg){
        if(arg != null){
            String[] arguments = Spliter.splitIntoWords(arg);
            if(arguments.length == 1){

                return "." + arguments[0];
            }
        }
        return "";
    }

    private String where(String arg) {
        if(arg != null) {
            String[] arguments = Parser.parseSqlToList(arg, Parser.COMPARE_EXPRESSION).toArray(new String[0]);
            String key = "";

            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i].toLowerCase().equals("and")) {
                    if (!key.equals("and")) {
                        arguments[i - 1] = "$and: [{".concat(arguments[i - 1]);
                    }
                    key = arguments[i].toLowerCase();
                    arguments[i] = ("}, {");
                } else if (arguments[i].matches(Parser.COMPARE_EXPRESSION)) {
                    String buff = compOperatorToMongo(arguments[i]);
                    if (i == arguments.length - 1 && key.equals("and")) {
                        buff = buff.concat("}]");
                    }
                    arguments[i] = buff;
                }

            }
            return arrToString(arguments);
        }
        return "";
    }

    private String skip(String arg){
        if(arg != null){
            return ".skip(".concat(arg).concat(")");
        }
        return "";
    }

    private String limit(String arg){
        if(arg != null){
            return ".limit(".concat(arg).concat(")");
        }
        return "";
    }

    private String compOperatorToMongo(String arg){
        if(arg != null){
            String[] buffArguments = Spliter.splitIntoWords(arg);
            switch (buffArguments[1]) {
                case "=": {
                    return buffArguments[0] + ": " + buffArguments[2];
                }
                case "<": {
                    return buffArguments[0] + ": { $lt: " + buffArguments[2] + " }";
                }
                case ">": {
                    return buffArguments[0] + ": { $gt : " + buffArguments[2] + " }";
                }
                case "<>": {
                    return buffArguments[0] + ": { $ne : " + buffArguments[2] + " }";
                }
            }
        }
        return "";
    }

    public String arrToString(String[] arg){
        StringBuilder ret = new StringBuilder();
        if(arg != null) {
            for (String i : arg) {
                ret.append(i);
            }
        }
        return ret.toString();
    }
}
