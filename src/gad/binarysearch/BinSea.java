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
        int maxLen = sortedData.length - 1;
        if (lowerBound) {
            //Smallest index where the value is bigger or equal to the searched value
            while (idx < maxLen && sortedData[idx] < value)
                idx++;
            while (idx > 0 && sortedData[idx - 1] == sortedData[idx] && sortedData[idx] == value)
                idx--;
            if (idx == 0 && sortedData[idx] < value)
                return -1;
            if (idx == maxLen && sortedData[idx] < value)
                return -1;
        } else {
            //Biggest index where the value is smaller or equal to the searched value
            while (idx > 0 && sortedData[idx] > value)
                idx--;
            while (idx < maxLen && sortedData[idx + 1] < value)
                idx++;
            while (idx < maxLen && sortedData[idx] == sortedData[idx + 1] && sortedData[idx] == value)
                idx++;
            if (idx == 0 && sortedData[idx] > value)
                return -1;
        }
        return idx;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRage, Result resultLower, Result resultHigher) {
        if(sortedData.length == 0)
            return Interval.EmptyInterval.getEmptyInterval();

        int lowerIntervalBound = search(sortedData, valueRage.getFrom(), true, resultLower);
        if (valueRage.getFrom() < sortedData[0])
            lowerIntervalBound = 0;
        if (valueRage.getFrom() > sortedData[sortedData.length - 1])
            return Interval.EmptyInterval.getEmptyInterval();
        if (lowerIntervalBound == -1)
            return Interval.EmptyInterval.getEmptyInterval();

        int higherIntervalBound = search(sortedData, valueRage.getTo(), false, resultHigher);
        if(valueRage.getTo() < sortedData[0])
            return Interval.EmptyInterval.getEmptyInterval();
        if (higherIntervalBound == -1)
            return Interval.EmptyInterval.getEmptyInterval();

        return new NonEmptyInterval(lowerIntervalBound, higherIntervalBound);
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 };
        int[] array2 = new int[] { 7, 7, 7, 7, 7, 7, 7 };
        //System.out.println(search(array, 7, new StudentResult()));
        //System.out.println(search(array, 100, new StudentResult()));

        //System.out.println(search(array, 7, false, new StudentResult()));
        //System.out.println(search(array, 100, false, new StudentResult()));
        System.out.println(search(array2, 6, true, new StudentResult()));
        //System.out.println(search(array, 9002, false, new StudentResult()));
        //System.out.println(search(array, 1, false, new StudentResult()));

        //System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        //System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
        System.out.println(search(array2, new NonEmptyInterval(6, 7), new StudentResult(), new StudentResult()));
    }
}