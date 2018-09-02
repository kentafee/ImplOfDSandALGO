package sxs178130;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListProblems<T> extends SinglyLinkedList3{


    public T kFromEnd(int k)
    {
    return (T)get(size -k);
    }

//    public void reverse()
//    {
//        ListProblems revList;
//        Iterator
//
//    }

    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        ListProblems<Integer> lst = new ListProblems<>();
        for (int i = 1; i <= n; i++) {
            lst.add(Integer.valueOf(i + 20));
        }

        System.out.println(lst.kFromEnd(2));
        System.out.println(lst.kFromEnd(3));
        System.out.println(lst.kFromEnd(11));
        System.out.println(lst.kFromEnd(-11));
        System.out.println(lst.kFromEnd(1));
    }

}
