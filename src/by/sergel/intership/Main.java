package by.sergel.intership;


public class Main {
    public static void main (String[] args){
        Translator translator = new Translator();
        if(args.length != 0){
            System.out.println(translator.translate(args[0]));
        }
    }
}
