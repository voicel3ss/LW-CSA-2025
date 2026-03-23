package src;

import java.util.Arrays;
import java.util.Random;
import graph.Graph;

public class SortingLab {

    private static final int NUMBERS_RANGE = 10000;
    private static final int NUM_SORTS = 200;

    private static final int INITIAL_SIZE = 100;
    private static final int SIZE_INCREMENT = 40;
    /*
     * Generate a random array of @arraySize numbers, each in the range
     * [-NUMBER_RANGE, NUMBER_RANGE]
     */
    private static int[] createRandomArray(int arraySize) {
        int[] arr = new int[arraySize];
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(2 * NUMBERS_RANGE + 1) - NUMBERS_RANGE;
        }
        return arr;
    }

    /*
     * Generate a random array of @arraySize numbers. Array starts with
     * a random number in the range [-NUMBER_RANGE, NUMBER_RANGE] and each
     * number is larger than the previous by a random value in [0, 10] range.
     */
    private static int[] createSortedRandomArray(int arraySize) {
        int[] arr = new int[arraySize];
        Random rnd = new Random();
        arr[0] = (rnd.nextInt(2 * NUMBERS_RANGE + 1) - NUMBERS_RANGE) / 10;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + rnd.nextInt(11);
        }
        return arr;
    }

    /*
     * Sort the @arr array using java default Arrays.sort() method.
     * Returns the execution time.
     */
    public static double javaArraySort(int[] arr) {
        double result = System.nanoTime();
        Arrays.sort(arr);
        return System.nanoTime() - result;
    }

    /*
     * Sort the @arr array using selection sort method.
     * Returns the execution time.
     */
    public static double selectionSort(int[] arr) {
        double startTime = System.nanoTime();

        for (int i=0; i<arr.length-1; i++){
            int index = i;
            for (int j=i+1; j<arr.length-1; j++){
                if (arr[j] < arr[index]){
                    index = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        double latency = System.nanoTime() - startTime;
        return latency;
    }

    /*
     * Sort the @arr array using the insertion sort method.
     * Returns the execution time.
     */
    public static double insertionSort(int[] arr) {
        double startTime = System.nanoTime();

        for (int i=1; i<arr.length; i++){
            int k = arr[i];
            int j = i-1;

            while (j >= 0 && arr[j] > arr[i]){
                arr[j+1] = arr[j];
                j = j-1;
            }

            arr[j+1] = k;
        }

        double latency = System.nanoTime() - startTime;
        return latency;
    }

    /*
     * Run a sequence of experiments. Each experiment is executing a specific
     * sorting algorithm, NUM_SORTS times, over an array of increasing size.
     * The size of the array being sorted and the execution times are stored
     * in data arrays which are further plotted in a graph.
     */
    public static void main(String[] args) {
        double[] sizeOfArray = new double[NUM_SORTS];
        double[] timeArraySort = new double[NUM_SORTS];
        double[] timeSelection = new double[NUM_SORTS];
        double[] timeInsertion = new double[NUM_SORTS];

        for (int i = 0; i < NUM_SORTS; i++) {
            int[] arr = createRandomArray(INITIAL_SIZE + i * SIZE_INCREMENT);
            sizeOfArray[i] = arr.length;
            timeArraySort[i] = javaArraySort(arr.clone());
            timeSelection[i] = selectionSort(arr.clone());
            timeInsertion[i] = insertionSort(arr.clone());
        }

        Graph g1 = new Graph();
        g1.add("unsorted.java", sizeOfArray, timeArraySort);
        g1.add("unsorted.selection", sizeOfArray, timeSelection);
        g1.add("unsorted.insertion", sizeOfArray, timeInsertion);
        g1.plot();

        for (int i = 0; i < NUM_SORTS; i++) {
            int[] arr = createSortedRandomArray(INITIAL_SIZE + i * SIZE_INCREMENT);
            sizeOfArray[i] = arr.length;
            timeArraySort[i] = javaArraySort(arr.clone());
            timeSelection[i] = selectionSort(arr.clone());
            timeInsertion[i] = insertionSort(arr.clone());
        }

        Graph g2 = new Graph();
        g2.add("sorted.java", sizeOfArray, timeArraySort);
        g2.add("sorted.selection", sizeOfArray, timeSelection);
        g2.add("sorted.insertion", sizeOfArray, timeInsertion);
        g2.plot();
    }
}
