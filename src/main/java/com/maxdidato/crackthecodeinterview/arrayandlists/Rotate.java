package com.maxdidato.crackthecodeinterview.arrayandlists;

import java.util.stream.IntStream;

/**
 * Given an image represented by an NxN matrix, where each pixel in th eimage is 4 bytes, write a method to rotate the
 * image by 90 degrees. Can you do this in place?
 */

public final class Rotate {

    /**
     * Each pixel is 4 bytes. So in Java we need to use a matrix of int (32 bits = 4 bytes)
     * The key concept is that we want to rotate the matrix 90 degrees clockwise.
     * It is kind of a transpose of the matrix, but with the columns flipped the other way.
     * In other words the row i must become the column n-i-1
     * Using a support matrix to copy over the values with the new index if quite straightforward. We loop through
     * the NxN elements of the matrix and the element a(i,j) becomes a(j, n-i-1)
     * This will take O(n2) and will take up O(n2) space since we need a same size matrix to do the copy
     *
     * If we want to do that in place we need to change approach. We can't swap the values using the previous formula
     * simply because we don't need to swap two values but actually rearrange them, so swapping the values will result
     * in wrong values
     *
     * One way to look at the problem is that a square NxN matrix is actually composed of layers which is the outer part
     * of the matrix
     * A A A A
     * A B B A         A = external layer
     * A B B A         B = inner layer
     * A A A A
     * <p>
     * In general a matrix NxN will have floor(N/2) layers
     * For each layer we can move each element on the right for a N-1 times.
     * Then we can move to the inner layers and do the same
     * so the complexity would be O(N/2 * N) = O(N2)
     *
     * Let's take the example of a 4x4 matrix
     * 1   2   3   4
     * 5   6   7   8
     * 9   10  11  12
     * 13  14  15  16
     *
     * we can replace all the elements of the outer layer (k = 0) by looping through from i-k to i<n-1-k -> from 0 to 2
     *
     * So a(0,0) = a(3,0)
     *    a(3,0) = a(3,3)
     *    a(3,3) = (0,3)
     *    a(0,3) = a(0,0) -> we need to store tmp = a(0,0) at the beginning otherwise this will result in the wrong value
     *
     *  We can then iterate through the next and apply the same logic and finally to the third element and do the same
     *
     *  At the end the first k cycle we will have rotated the outer layer.
     *  Then we move k to 1 and redo the same logic, only applied to the inner layer.
     *
     *  If we want to generalize the previously mentioned operation we need to express them in terms of the variables
     *  i,k and n. In general for each inner loop k, i we need to apply the following logic
    *               int tmp = a[k][i];
    *               a[k][i] = a[n - 1 - i][k];
    *               a[n - 1 - i ][k] = a[n - 1 -k][n - 1 - i];
    *               a[n - 1 -k][n - 1 - i] = a[i][n - 1 -k];
    *               a[i][n - 1 -k] = tmp;
     *
     */


    public void rotate(int[][] a) {
        int n = a.length;
        int numOfMatrixes = Math.floorDiv(n, 2);
        for (int k = 0; k < numOfMatrixes; k++) {
           for (int i = k; i < n-1-k; i++){
               int tmp = a[k][i];
               a[k][i] = a[n - 1 - i][k];
               a[n - 1 - i ][k] = a[n - 1 -k][n - 1 - i];
               a[n - 1 -k][n - 1 - i] = a[i][n - 1 -k];
               a[i][n - 1 -k] = tmp;
           }
        }

    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        new Rotate().rotate(a);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j]);
                System.out.print("   ");
            }
            System.out.println(" ");
        }
    }
}


