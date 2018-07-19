package com.kaxudodo.mysort;

import static com.kaxudodo.mysort.SortUtil.swapReferences;

/**
 * Created by aaron on 2018/7/16.
 */
public class MyQuickSelect {

    private static int CUTOFF = 10;

    private static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int left, int right, int k) {

        if (left + CUTOFF <= right) {

            AnyType pivot = median3(a, left, right);
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) < 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }
            swapReferences(a, i, right - 1);
            if (k <= i)
                quickSelect(a, left, i - 1, k);
            else if (k > i + 1)
                quickSelect(a, i + 1, right, k);

        } else {
            InsertSort.myInsertSort(a, left, right);
        }

    }


    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);
        // place pivot at position right-1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }
}
