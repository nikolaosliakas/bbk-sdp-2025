package main;
import java.util.*;

public class Challenge {

    public static void main(String[] args){
        ArrayList<String> strList = new ArrayList<>(Arrays.asList(
                "Hello", "Hello", "Goodbye", "Goodbye", "Morning", "Afternoon"));
        ArrayList<Integer> IntList = new ArrayList<>(Arrays.asList(1,2,3,4,5,5,5,555, 6, 8));

        ArrayList<String> dedupeString = elimDupes(strList);
        ArrayList<Integer> dedupeIntegers = elimDupes(IntList);
        System.out.println(dedupeString);
        System.out.println(dedupeIntegers);

        ArrayList<String> remString = removeDupes(strList);
        ArrayList<Integer> remIntegers = removeDupes(IntList);


        System.out.println(remString);
        System.out.println(remIntegers);

    }
    public static <E> ArrayList<E> elimDupes(ArrayList<E> list){
        ArrayList<E> returnList = new ArrayList<>();
        for(E element:list){
            if(!returnList.contains(element))
                returnList.add(element);
        }
        return returnList;
    }
    public static <E extends Comparable<E>> ArrayList<E> removeDupes(ArrayList<E> list){
        boolean found = false;
        if(list.isEmpty())
            return list;
        ArrayList<E> newList = new ArrayList<>();
        newList.add(list.getFirst());
        for(int i = 1; i<list.size(); i++){
            for(int j=0; j<newList.size(); j++){
                if(list.get(i).compareTo(newList.get(j))==0)
                {found = true;
                    break;
                }
            }
            if(!found)
                newList.add(list.get(i));
            found = false;
        }
        return newList;
    }

}


