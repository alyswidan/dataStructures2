package aly.data.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ADMIN on 3/14/2017.
 */
public class BinaryHeap<T extends Comparable>  {

    private int x=0;
    private List<T>container;
    private Comparator<T>comparator = Comparator.naturalOrder();
    public BinaryHeap()
    {
        this.container = new ArrayList<>();
        this.container.add(null);
    }
    public BinaryHeap(Comparator<T> comparator){
        this();
        this.comparator = comparator;
    }
    public BinaryHeap(List<T>container)
    {
        this();
        this.container.addAll(container);
        build();
    }
    public BinaryHeap(List<T>container,Comparator<T>comparator){
        this();
        this.container.addAll(container);
        this.comparator = comparator;
        build();
    }

    public boolean isEmpty(){return container.size()==1;}
    public T extract(){

        T root = container.get(1);
        Collections.swap(container,1,container.size()-1);
        this.container.remove(container.size()-1);

        heapify(1);
        return root;
    }

    public void insert(T elem)
    {
        container.add(elem);
        int i = container.size()-1;
        while(comparator.compare(elem,container.get(i>>1))<1)
        {
            Collections.swap(container,i,i<<1);
            i<<=1;
        }
    }
    private void build()
    {
        int s = (container.size()-1)/2;

        for(int i=s;i>=1;i--)
            heapify(i);
    }
    private void heapify(int i)
    {

        int l = i<<1, r = l + 1,winner=i;

        if(l < container.size() && ( comparator.compare(container.get(l),container.get(i)) < 0 ) )
            winner = l;

        if(r < container.size() && ( comparator.compare(container.get(r),container.get(winner)) < 0) )
            winner = r;

        if(winner != i)
        {
            Collections.swap(this.container,i,winner);
            heapify(winner);
        }
    }

    @Override
    public String toString() {
        return container.toString();
    }
}
