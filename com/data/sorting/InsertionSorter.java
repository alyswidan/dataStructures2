package aly.data.sorting;

import java.util.List;

/**
 * Created by ADMIN on 3/17/2017.
 */
public class InsertionSorter<T extends Comparable<? extends T> > extends Sorter<T>{


    @Override
    public void sort(List<T> list) {

        for (int r=1; r < list.size(); r++)
        {
            T temp = list.get(r);
            list.remove(r);
            insert(list,r-1,temp);
        }
    }


    //insert p into sub array list[0..r]
    public void insert(List<T>list,int r,T p)
    {
        int i=0;
        while(i<=r && comparator.compare(list.get(i),p)<0)i++;
         list.add(i,p);

    }

}
