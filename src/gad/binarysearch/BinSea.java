package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        return recursiveBinSea(sortedData, value, result, 0, sortedData.length - 1);
    }

    public static int recursiveBinSea(int[] sortedData, int value, Result result, int lowerBound, int upperBound) {
        int mid = (lowerBound + upperBound) / 2;
        result.addStep(mid);

        if (upperBound - lowerBound == 2)
            return mid;
        else if (upperBound == lowerBound)
            return upperBound;

        if (value < sortedData[mid]) {
            upperBound = mid;
            mid = recursiveBinSea(sortedData, value, result, lowerBound, upperBound);
        }
        else if (value > sortedData[mid]) {
            lowerBound = mid;
            mid = recursiveBinSea(sortedData, value, result, lowerBound, upperBound);
        }
        else if (value == sortedData[mid])
            return mid;
        return mid;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        return 0;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange, Result resultLower, Result resultHigher) {
        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 };
        int[] array2 = new int[] { 1, 3, 7, 15, 31, 63, 127, 255 };

        System.out.println(search(array2, 7, new StudentResult()));
        System.out.println(search(array2, 100, new StudentResult()));

        System.out.println(search(array, 7, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}