package src;

import org.junit.Test;

import java.util.Arrays;

import org.junit.Assert;

public class UnitTests {

    @Test
    public void testJavaArraySort() {
        int[] input = new int[] { 10, 5, 8, -3, 11, 11, 1, -8, 12, 8, 15 };
        int[] output = new int[] { -8, -3, 1, 5, 8, 8, 10, 11, 11, 12, 15 };
        SortingLab.javaArraySort(input);
        Assert.assertEquals("javaArraySort(input, output): ", true, Arrays.equals(input, output));
    }

    @Test
    public void testInsertionSort() {
        // TODO: Replace line below witn your unit test code for SortingLab.insertionSort()
        Assert.fail();
    }

    @Test
    public void testSelectionSort() {
        // TODO: Replace line below with your unit test code for SortingLab.selectionSort()
        Assert.fail();
    }
}
