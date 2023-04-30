package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        int lowerBound = 0;
        int upperBound = sortedData.length - 1;
        int mid = upperBound / 2;
        result.addStep(mid);

        while(lowerBound != upperBound) {
            if (value == sortedData[mid]) {
                return mid;
            } else if (value < sortedData[mid]) {
                upperBound = mid-1;
                if (lowerBound != upperBound)
                    mid = upperBound / 2;
                else
                    break;
            } else if (value > sortedData[mid]) {
                lowerBound = mid+1;
                if (lowerBound != upperBound)
                    mid = (lowerBound + upperBound) / 2;
                else
                    break;
            }
            result.addStep(mid);
        }
        if(lowerBound == upperBound)
            return upperBound;
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

        System.out.println(search(array, 7, new StudentResult()));
        System.out.println(search(array, 100, new StudentResult()));

        System.out.println(search(array, 7, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}