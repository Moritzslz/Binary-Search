package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        int lowerBound = 0;
        int upperBound = sortedData.length - 1;
        int mid = (lowerBound + upperBound) / 2;

        if (sortedData[lowerBound] == sortedData[upperBound])
            return mid;

        for (int i = 0; i < sortedData.length - 1; i++) {
            mid = (lowerBound + upperBound) / 2;
            //System.out.println("Lower: " + lowerBound);
            //System.out.println("Upper: " + upperBound);

            if (lowerBound == upperBound) {
                if (sortedData[mid] == value)
                    result.addStep(mid);
                break;
            } else if (lowerBound == upperBound - 1) {
                if (sortedData[lowerBound] == value) {
                    result.addStep(lowerBound);
                    return lowerBound;
                } else if (sortedData[upperBound] == value) {
                    result.addStep(upperBound);
                    return upperBound;
                } else
                    result.addStep(upperBound);
                    return upperBound;
            }

            result.addStep(mid);

            if(value < sortedData[mid])
                upperBound = mid - 1;
            else if(value > sortedData[mid])
                lowerBound = mid + 1;
            else if(value == sortedData[mid])
                break;
        }
        return mid;
    }

    public static int recursiveBinSea(int[] sortedData, int value, Result result, int lowerBound, int upperBound) {
        int mid = (lowerBound + upperBound) / 2;

        if (lowerBound > upperBound) {
            return lowerBound;
        } else if (lowerBound == upperBound) {
            if (value == sortedData[mid])
                result.addStep(mid);
            return lowerBound;
        }

        result.addStep(mid);

        if (value < sortedData[mid]) {
            upperBound = mid - 1;
            return recursiveBinSea(sortedData, value, result, lowerBound, upperBound);
        } else if (value > sortedData[mid]) {
            lowerBound = mid + 1;
            return recursiveBinSea(sortedData, value, result, lowerBound, upperBound);
        }
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
        int[] array3 = new int[] { 2, 4, 8, 16, 32, 64, 128, 256 };
        int[] array4 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2 };

        System.out.println(search(array, 7, new StudentResult()));
        System.out.println(search(array, 100, new StudentResult()));

        System.out.println(search(array, 1, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}