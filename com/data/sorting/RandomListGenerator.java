package aly.data.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ADMIN on 3/17/2017.
 */
public class RandomListGenerator {


    public List<Integer>get(int n,int min,int max)
    {
        ArrayList<Integer>list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(ThreadLocalRandom.current().nextInt(min, max + 1));
        return list;
    }



}
