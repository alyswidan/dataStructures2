package aly.data.sorting;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ADMIN on 3/14/2017.
 */
public class MergeSorter<T extends Comparable> extends Sorter<T> {
    List<T>t1,t2;
    int n1,n2,i,j,k;
    public MergeSorter(){

        t1=new ArrayList<T>();t2=new ArrayList<T>();

    }

    @Override
    public  void sort(List<T> list)
    {
            mergeSort(list,0,list.size()-1);
    }

    private void mergeSort(List<T>L,int lo,int hi)
    {
        if(hi<=lo)return;
        int mid = (lo+hi)/2;


        mergeSort(L,lo,mid);
        mergeSort(L,mid+1,hi);
        merge(L,lo,hi,mid);
    }
    private void merge(List<T> L, int lo,int hi,int mid)
    {

        t1.clear();
        t2.clear();
        for( i=lo;i<=mid;i++)t1.add(L.get(i));
        for( i = mid+1;i<=hi;i++)t2.add(L.get(i));
        n1 = t1.size();
        n2 = t2.size();
        i=j=0;
        k=lo;

        while(i<n1 && j<n2)
            if(comparator.compare(t1.get(i),t2.get(j))<=0)
                L.set(k++,t1.get(i++));
            else
                L.set(k++,t2.get(j++));

        while(i<n1)L.set(k++,t1.get(i++));

        while(j<n2)L.set(k++,t2.get(j++));

    }







}
