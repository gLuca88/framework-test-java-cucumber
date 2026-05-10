package com.gianluca.framework.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingUtils {


    public static boolean isAlphabeticallyAscending(List<String> actualList) {
        List<String> sortedList = new ArrayList<>(actualList);
        Collections.sort(sortedList);
        return actualList.equals(sortedList);
    }

    public static boolean isAlphabeticallyDescending(List<String> actualList) {

        List<String> sortedList = new ArrayList<>(actualList);

        sortedList.sort(Collections.reverseOrder());

        return actualList.equals(sortedList);
    }

    public static boolean isPriceAscending(List<Double> actualList) {

        List<Double> sortedList = new ArrayList<>(actualList);

        Collections.sort(sortedList);

        return actualList.equals(sortedList);
    }

    public static boolean isPriceDescending(List<Double> actualList) {

        List<Double> sortedList = new ArrayList<>(actualList);

        sortedList.sort(Collections.reverseOrder());

        return actualList.equals(sortedList);
    }

}
