package sxs178130;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Enhanced singlyLinkedList with additional operations
 * like addFirst(x), removeFirst(), remove(x).
 *
 * removeFirst() removes the first element of the list.
 * remove(x) deletes and returns the first occurrence of x from the list.  If x is not found, it should throw a
 * "No such element" exception.


 */

public class SinglyLinkedList2<T extends Comparable<? super T>> extends SinglyLinkedList {

    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        Entry<T> first = head.next;

        if (head.next == tail) {
            head.next = null;
            tail = head;
        }

        else
        {
            head.next = first.next;
        }

        return first.element;
    }


    public void addFirst(T data)
    {
        if(size == 0)
        {
            add(data);
        }
        else
        {
            size++;
            Entry<T> newEntry = new Entry<>(data,head.next);
            head.next = newEntry;

        }

    }

public boolean remove(T data)
{

    Iterator<T> itr = this.iterator();

    while(itr.hasNext())
    {
        if(data.compareTo(itr.next()) == 0)
        {
            itr.remove();
            return true;
        }
    }

    return false;

}


    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        SinglyLinkedList2<Integer> lst = new SinglyLinkedList2<>();
        for (int i = 1; i <= n; i++) {
            lst.add(Integer.valueOf(i+20));
        }
    }


}

