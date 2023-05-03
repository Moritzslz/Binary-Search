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

        while (lowerBound < upperBound) {
            mid = (lowerBound + upperBound) / 2;
            result.addStep(mid);
            if (sortedData[mid] < value)
                lowerBound = mid + 1;
            else if (sortedData[mid] > value)
                upperBound = mid - 1;
            else if (sortedData[mid] == value)
                return mid;
        }

        if (value < sortedData[0])
            return 0;
        else if (value > sortedData[sortedData.length - 1])
            return sortedData.length - 1;

        return upperBound;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        int idx = search(sortedData, value, result);
        if(lowerBound) {
            if(idx == 0 && sortedData[idx] > value)
                return - 1;
            while (idx < sortedData.length - 1 && sortedData[idx] < value)
                idx++;
            while (idx > 0 && sortedData[idx - 1] == sortedData[idx])
                idx--;
        } else {
            if(idx == sortedData.length - 1 && sortedData[idx] < value)
                return - 1;
            while (idx > 0 && sortedData[idx] > value)
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

        System.out.println(search(array, 7, new StudentResult()));
        System.out.println(search(array, 100, new StudentResult()));

        System.out.println(search(array, 7, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));
        System.out.println(search(array, 9002, false, new StudentResult()));
        System.out.println(search(array, 1, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}