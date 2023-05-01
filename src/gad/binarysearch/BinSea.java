package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        if (sortedData[0] == sortedData[sortedData.length - 1])
                return (sortedData.length - 1) / 2;
        return recursiveBinSea(sortedData, value, result, 0, sortedData.length - 1);
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
        int idx = search(sortedData, value, result);
        System.out.println("Idx: " + idx);
        if (lowerBound) {
            if(idx == sortedData.length - 1 && sortedData[idx] < value)
                return -1;
            if(idx == 0 && sortedData[idx] < value)
                return -1;
            while (value > sortedData[idx])
                idx++;
            while (idx > 0 && sortedData[idx-1] == sortedData[idx])
                idx--;
        } else {
            if(idx == sortedData.length - 1 && sortedData[idx] > value)
                return -1;
            if(idx == 0 && sortedData[idx] > value)
                return -1;
            while (value < sortedData[idx])
                idx--;
            while (idx < sortedData.length - 1 && sortedData[idx] == sortedData[idx+1])
                idx++;
        }
        return idx;
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