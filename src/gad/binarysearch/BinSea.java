package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        int[] indices = binarySearch(sortedData, value, result);
        int mid = indices[0];
        int lowerBound = indices[1];
        int upperBound = indices[2];

        if(lowerBound == upperBound)
            return mid;

        if(mid != -1)
            return mid;
        else if (sortedData[lowerBound] < value && value < sortedData[upperBound])
            return upperBound;
        else if (lowerBound != -1)
            return lowerBound;
        else
            return upperBound;
    }

    public static int[] binarySearch(int[] sortedData, int value, Result result) {
        int lowerBound = 0;
        int upperBound = sortedData.length - 1;
        int mid = (lowerBound + upperBound) / 2;
        int[] indices = new int[3];
        indices[0] = mid;
        indices[1] = lowerBound;
        indices[2] = upperBound;

        //Edge case: all values are the same
        if (sortedData[indices[1]] == sortedData[indices[2]])
            return indices;

        //The maximum amount of iterations is half of the array length
        //That is the case when the searched index is at the most outer
        //edge of the array you get when splitting the given array in half.
        for (int i = 0; i < sortedData.length / 2; i++) {
            indices[0] = (indices[1] + indices[2]) / 2;

            //Termination Condition
            if (indices[1] == indices[2]) {
                if (sortedData[indices[2]] == value)
                    result.addStep(indices[2]);
                return indices;
            } else if (indices[1] == indices[2] - 1) {
                if (sortedData[indices[1]] == value) {
                    result.addStep(indices[1]);
                    indices[0] = -1;
                    indices[2] = -1;
                    return indices;
                } else if (sortedData[indices[2]] == value) {
                    result.addStep(indices[2]);
                    indices[0] = -1;
                    indices[1] = -1;
                    return indices;
                } else if (sortedData[indices[1]] < value && value < sortedData[indices[2]]) {
                    //If the searched for value lies between lower bound and upper bound then the searched for value
                    //is not contained in the array
                    //-> return indices[2] (next bigger value)
                    result.addStep(indices[2]);
                    indices[0] = -1;
                    return indices;
                } else if (sortedData[indices[1]] > value) {
                    //If the lower bound is 0, and it's value is bigger than the searched for value then the searched
                    //for value is not contained in the array
                    //-> return indices[1] (next bigger value)
                    result.addStep(indices[1]);
                    indices[2] = indices[1];
                    indices[0] = -1;
                    return indices;
                } else if (sortedData[indices[2]] < value) {
                    //If the indices[2] bound is sortedDate.length - 1, and it's value is smaller than the searched
                    //for value then the searched for value is not contained in the array
                    //-> return indices[2] (next smaller value)
                    result.addStep(indices[2]);
                    indices[1] = indices[2];
                    indices[0] = -1;
                    return indices;
                }
            }

            result.addStep(indices[0]);

            if (sortedData[indices[0]] > value) {
                indices[2] = indices[0] - 1;
            } else if (sortedData[indices[0]] < value) {
                indices[1] = indices[0] + 1;
            } else if (sortedData[indices[0]] == value) {
                break;
            }
        }
        return indices;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        return 0;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange, Result resultLower, Result resultHigher) {
        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 }; //7
        int[] array2 = new int[] { 1, 3, 7, 15, 31, 63, 127, 255 }; //7
        int[] array3 = new int[] { 2, 4, 8, 16, 32, 64, 128, 256 }; //7
        int[] array4 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2 }; //8

        System.out.println(search(array, 7, new StudentResult()));
        System.out.println(search(array, 100, new StudentResult()));

        System.out.println(search(array, 1, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}