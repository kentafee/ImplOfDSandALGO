package sxs178130;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListOperations {
    public static<T extends Comparable<? super T>>
    void intersect(List<T> l1, List<T> l2, List<T> outList) {
        // Return elements common to l1 and l2, in sorted order.
        // outList is an empty list created by the calling
        // program and passed as a parameter.
        // Function should be efficient whether the List is
        // implemented using ArrayList or LinkedList.
        // Do not use HashSet/Map or TreeSet/Map or other complex
        // data structures.
        Iterator<T> itr1 = l1.iterator();
        Iterator<T> itr2 = l2.iterator();

        T x1 = next(itr1);
        T x2 = next(itr2);

       while(x1!= null && x2!=null)
       {
           int comp = x1.compareTo(x2);
            if( comp == 0)
            {
                 outList.add(x1);
                 x1 = next(itr1);
                 x2 = next(itr2);
            }
            else if(comp >0)
            {
                x2= next(itr2);
            }
            else
            {
                x1 = next(itr1);
            }
       }

    }
    public static<T> T next(Iterator<T> it) {
       if( it.hasNext())
           return it.next();
       return null;
    }


    public static<T extends Comparable<? super T>>
    void union(List<T> l1, List<T> l2, List<T> outList) {
        // Return the union of l1 and l2, in sorted order.
        // Output is a set, so it should have no duplicates.

        Iterator<T> itr1 = l1.iterator();
        Iterator<T> itr2 = l2.iterator();

        T x1 = next(itr1);
        T x2 = next(itr2);

        while(x1!= null && x2!=null)
        {
            int comp = x1.compareTo(x2);
            if(comp == 0)
            {
                outList.add(x1);
                x1 = next(itr1);
                x2 = next(itr2);
            }
        }
    }

    public static<T extends Comparable<? super T>>
    void difference(List<T> l1, List<T> l2, List<T> outList) {
        // Return l1 - l2 (i.e, items in l1 that are not in l2), in sorted order.
        // Output is a set, so it should have no duplicates.
    }


    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(11);
        l1.add(103);
        l1.add(105);
        l1.add(106);
        l1.add(109);
        List<Integer> l2 = new ArrayList<>();
        l2.add(105);
        l2.add(109);
        l2.add(1011);
        l2.add(101111);
        l2.add(1011111);
        List<Integer> outlist = new ArrayList<>();
        ListOperations.intersect(l1,l2,outlist);

        for(Integer i : outlist)
        {
            System.out.println(i);
        }
    }
}
