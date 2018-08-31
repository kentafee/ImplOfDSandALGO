package sxs178130;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This implementation of Linked list supports additional operations such as
 * get(index), set(index, x), add(index, x), remove(index)
 * All operations throw "No such element" exception if the list is too short.
 * get(index) returns the element at index (first element is at index 0).
 * set(index, x) replaces the element at given index to be x.
 * add(index, x) adds x as a new element at given index.
 * remove(index) deletes and returns element at index
 */

public class SinglyLinkedList3<T> extends SinglyLinkedList2 {



    private IteratorAndData getItrAtIndex(int index) {

        if(index < 0 || index >size-1)
            throw new NoSuchElementException();
        int ptr = -1;
        T data = null;
        Iterator<T> itr = this.iterator();
        while (itr.hasNext()) {
            ptr++;
            data = itr.next();
            if (ptr == index)
                break;
        }

        return new IteratorAndData(itr, data);



    }

    public T get(int index) {

        return (T)getItrAtIndex(index).getData();

    }
//    public boolean set(int index, T data) {
//
//        IteratorAndData itrData = getItrAtIndex(index);
//
//    }
//
//    public boolean add(int index, T data) {
//
//        IteratorAndData itrData = getItrAtIndex(index);
//
//    }

    public T remove(int index) {

        IteratorAndData itrData = getItrAtIndex(index);
        itrData.getItr().remove();
        return (T)itrData.getData();



    }

    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        SinglyLinkedList3<Integer> lst = new SinglyLinkedList3<>();
        for (int i = 1; i <= n; i++) {
            lst.add(Integer.valueOf(i+20));
        }

        lst.printList();

        System.out.println(lst.get(2));
        System.out.println(lst.get(9));
//        System.out.println(lst.get(11));
//        System.out.println(lst.get(1));

        lst.remove(2);
        lst.remove(0);
        lst.remove(7);
        lst.printList();


    }

    class IteratorAndData<T> {

        private Iterator itr;
        private T data;

        IteratorAndData(Iterator<T> itr, T data) {
            this.data = data;
            this.itr = itr;
        }

        public Iterator getItr() {
            return itr;
        }

        public T getData() {
            return data;
        }


    }

}

