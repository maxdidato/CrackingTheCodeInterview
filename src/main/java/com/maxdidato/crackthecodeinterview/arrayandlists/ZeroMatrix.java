package com.maxdidato.crackthecodeinterview.arrayandlists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Write an algorithm such that if an element in an NxM matrix is 0, its entire row and column are set to zero
 *
 * Solving this with a brute force algorithm would require to copy the input matrix and insert zeros for the row and column
 * of each element that is zero.
 * We cannot do it in place otherwise the next cycles of the loop would find zeros and therefore the entire matrix
 * will be set to zero at some point.
 *
 * However if we loop through the matrix and find an element zero we can set row and col to zero in place and then
 * mark the row i and col j out. Meaning that while we proceed with the loop we will discard those row and col already
 * marked as zero.
 * This will result in a complexity of O(N*M) and space complexity of O(N+M)
 *
 * We could save the space to a O(1) by cheking the first row and col first, (setting to zero if necessary) and then
 * use those to store the cols and rows we need to exclude from the loop.
 *
 * The space saving is negligible and the resulting code would be more complex so we are happy with storing the cols
 * and rows in a separate data structure
 */

public final class ZeroMatrix
{
    public void zeroMatrix(int[][] a){
        //we use linkedhashset so to preserve the order
        LinkedHashSet<Integer> rows = new LinkedHashSet<>();
        LinkedHashSet<Integer> cols = new LinkedHashSet<>();
        for (int i = 0; i < a.length; i++){
            rows.add(i);//initialize all the rows
        }
        for (int i = 0; i < a[0].length; i++){
            cols.add(i);//initialize all the cols
        }
        //since we are going to remove elements from the rows set while looping over it we need to use an iterator
        Iterator<Integer> rowIterator = rows.iterator();

        while(rowIterator.hasNext()){
            int i = rowIterator.next();
            //for the columns we don't need an iterator since when we find the col with zero we just break the loop
            for (int j : cols){
                if (a[i][j] == 0){
                    //setting the row to zero
                    for (int r = 0; r< a.length; r++){
                        a[r][j] = 0;
                    }
                    //setting the column to zero
                    for (int c = 0; c< a[0].length; c++){
                        a[i][c] = 0;
                    }
                    rowIterator.remove();
                    cols.remove(j);
                    break;
                }
            }
        }


    }


    public static void main(String[] args)
    {
        int[][] a = new int[][]{{1,2,3,4,5}, {6,0,8,9,10}, {0,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        new ZeroMatrix().zeroMatrix(a);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j]);
                System.out.print("   ");
            }
            System.out.println(" ");
        }

    }
}


