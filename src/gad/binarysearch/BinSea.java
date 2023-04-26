package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
       return recursiveBinarySearch(sortedData, value, 0, (sortedData.length-1), result);
    }

    public static int recursiveBinarySearch(int[] sortedData, int value, int lowerBound, int upperBound, Result result) {
        int index = -1;
        int mid = -1;

        if (upperBound - lowerBound <= 2) {
            return (upperBound - lowerBound) / 2 + lowerBound + 1;
        }

        mid = (upperBound - lowerBound) / 2 + lowerBound;
        result.addStep(mid);

        if(value == sortedData[mid]) {
            return mid;
        } else if(value < sortedData[mid]) {
            upperBound = mid;
            index =  recursiveBinarySearch(sortedData, value, lowerBound, upperBound, result);
        } else if (value > sortedData[mid]) {
            lowerBound = mid;
            index =  recursiveBinarySearch(sortedData, value, lowerBound, upperBound, result);
        }
        return index;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        return 0;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange, Result resultLower, Result resultHigher) {
        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 };

        System.out.println(search(array, 7, new StudentResult()));
        System.out.println(search(array, 100, new StudentResult()));

        System.out.println(search(array, 7, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}