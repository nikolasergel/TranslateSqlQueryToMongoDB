package by.sergel.intership;

import java.util.List;
import java.util.Map;

public class Printer {
    public static void printList(List list){
        if(!list.isEmpty()){
            for (Object k: list) {
                System.out.println(k.toString());
            }
        }
    }

    public static void printArray(String[] arr){
        if(arr != null && arr.length != 0){
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
    }

    public static void printMap(Map map){
        for(Object k : map.keySet()){
            System.out.println(k.toString().concat(" : ").concat(map.get(k).toString()));
        }
    }
}
