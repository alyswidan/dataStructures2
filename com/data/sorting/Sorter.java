package aly.data.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ADMIN on 3/14/2017.
 */
public abstract class Sorter<T extends Comparable> {

   Comparator<T>comparator = Comparator.naturalOrder();
   public abstract  void sort(List<T> list);

   public void sort(List<T>list,Comparator<T>comparator)
   {
      this.comparator = comparator;
      sort(list);
   }


}
