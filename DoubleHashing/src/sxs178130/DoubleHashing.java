package sxs178130;

public class DoubleHashing<T> {

    T[] table;
    int[] flag;


    private int find(T x)
    {

        int i = x.hashCode();
        int k = i;
        int xspot;

        while(true)
        {
            if(table[k] == x || flag[k]==0)
                return k;
            else if(flag[k]==2)
                break;
            else
                k++;
        }

        xspot = k;

        while(true)
        {

        }
    }

}
