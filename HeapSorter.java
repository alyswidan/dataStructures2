package aly.data.sorting;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by ADMIN on 3/14/2017.
 */
public class HeapSorter<T extends Comparable> extends Sorter<T> {

    BinaryHeap<T> pq;
    @Override
    public void sort(List<T> list) {

         pq = new BinaryHeap<>(list);
         doSorting(list);
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {

       pq = new BinaryHeap<>(list,comparator);

        doSorting(list);
    }

    private void doSorting(List<T> list)
    {

        list.clear();
        while (!pq.isEmpty()) list.add(pq.extract());
    }
}


