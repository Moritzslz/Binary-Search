package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        int lowerBound = 0;
        int upperBound = sortedData.length - 1;
        int mid = (lowerBound + upperBound) / 2;

        //Edge case: all values are the same
        if (sortedData[lowerBound] == sortedData[upperBound])
            return mid;

        //The maximum amount of iterations is half of the array length
        //That is the case when the searched index is at the most outer
        //edge of the array you get when splitting the given array in half.
        for (int i = 0; i < sortedData.length / 2; i++) {
            mid = (lowerBound + upperBound) / 2;

            //Termination Condition
            if (lowerBound == upperBound - 1) {
                if (sortedData[lowerBound] == value) {
                    result.addStep(lowerBound);
                    return lowerBound;
                } else if (sortedData[upperBound] == value) {
                    result.addStep(upperBound);
                    return upperBound;
                } else if (sortedData[lowerBound] < value && value < sortedData[upperBound]) {
                    //If the searched for value lies between lower bound and upper bound then the searched for value
                    //is not contained in the array
                    //-> return upperBound (next bigger value)
                    return upperBound;
                } else if (sortedData[lowerBound] > value) {
                    //If the lower bound is 0, and it's value is bigger than the searched for value then the searched
                    //for value is not contained in the array
                    //-> return lowerBound (next bigger value)
                    return lowerBound;
                } else if (sortedData[upperBound] < value) {
                    //If the upperBound bound is sortedDate.length - 1, and it's value is smaller than the searched
                    //for value then the searched for value is not contained in the array
                    //-> return upperBound (next smaller value)
                    return upperBound;
                }
            }

            result.addStep(mid);

            if (sortedData[mid] > value) {
                upperBound = mid;
            } else if (sortedData[mid] < value) {
                lowerBound = mid + 1;
            } else if (sortedData[mid] == value) {
                break;
            }
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