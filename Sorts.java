package sort;

import java.util.Random;

public class Sorts {

    public static boolean isSorted(Comparable[] comparables){
        for (int i = 0; i < comparables.length-1; i++) {
            if (!less(comparables[i],comparables[i+1]))
                return false;
        }
        return true;
    }

    public static void selectionSort(Comparable[] comparables){
        for (int i = 0; i < comparables.length; i++) {
            int min = i;
            for (int j = min; j < comparables.length; j++) {
                if (less(comparables[j], comparables[min]))
                    min = j;
            }
            exch(comparables,i,min);
        }
    }

    public static void insertionSort(Comparable[] comparables){
        for (int i = 0; i < comparables.length; i++) {
            for (int j = i; j > 0; j--) {
                if(less(comparables[j],comparables[j-1]))
                    exch(comparables,j,j-1);
                else
                    break;
            }
        }
    }

    // h = 3X+1
    public static void shellSort(Comparable[] comparables) {
        int N = comparables.length;
        int h = 1;
        while(h < N/3)
            h = 3*h+1;
        //3X+1 h-sort
        while(h >= 1){
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j-=h) {
                    if(less(comparables[j],comparables[j-h]))
                        exch(comparables, j, j-h);
                    else
                        break;
                }
            }
            h = (h-1)/3;
        }
    }

    private static void merge(Comparable[] comparables, Comparable[] copy, int lo, int mid, int hi){
        for (int i = lo; i < hi+1; i++)
            copy[i] = comparables[i];
        int i = lo;
        int j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid+1)
                comparables[k] = copy[j++];
            else if(j == hi+1)
                comparables[k] = copy[i++];
            else if (less(copy[i], copy[j]))
                comparables[k] = copy[i++];
            else
                comparables[k] = copy[j++];
        }
    }

    private static void sort(Comparable[] comparables, Comparable[] copy, int lo, int hi){
        if (hi <= lo)
            return ;
        int mid = lo + (hi-lo)/2;
        sort(comparables,copy,lo,mid);
        sort(comparables,copy,mid+1,hi);
        merge(comparables,copy, lo, mid, hi);
    }

    public static void mergeSort(Comparable[] comparables) {
        Comparable[] copy = new Comparable[comparables.length];
        sort(comparables, copy,0, comparables.length-1);
    }

    public static void mergeSortBottomToUp(Comparable[] comparables) {
        Comparable[] copy = new Comparable[comparables.length];
        int N = comparables.length;
        for (int sz = 1; sz <= N; sz+=sz) {
            for (int i = 0;i < N-sz;i += 2*sz){
                merge(comparables, copy , i,i+sz-1 , Math.min(i+2*sz-1, N-1));
            }
        }
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(11);
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();


        mergeSortBottomToUp(a);

        for (int i = 0; i < 10; i++) {
            System.out.print(a[i] + " ");
        }

    }
}
