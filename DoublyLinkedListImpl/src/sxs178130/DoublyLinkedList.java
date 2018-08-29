package sxs178130;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {
    static class Entry<E> extends SinglyLinkedList.Entry<E> {
        Entry<E> prev;

        Entry(E x, Entry<E> nxt, Entry<E> prev) {
            super(x, nxt);
            this.prev = prev;
        }
    }


    DoublyLinkedList() {
        head = new Entry<>(null, null, null);
        tail = head;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new DLLIterator();
    }

    public Iterator<T> dllIterator() {
        return new DLLIterator();
    }
    public class DLLIterator extends SLLIterator {

        DLLIterator() {
        }

        public boolean hasPrev() {

            if (prev == head)
                return false;

            return ((Entry<T>) cursor).prev != null;

        }

        public T prev() {

            if(prev == cursor)
            {
                ((Entry<T>) (cursor.next)).prev = (Entry<T>)cursor;
                prev = ((Entry<T>) cursor).prev;
            }
            else
            {
                cursor = prev;
                prev = ((Entry<T>) prev).prev;
            }


            ready = true;
            return (T) cursor.element;
        }


    }

    @Override
    public void add(T x) {
        Entry<T> temp = new Entry(x, null, (Entry<T>)tail);
        add(temp);
    }



    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        DoublyLinkedList<Integer> dLLst = new DoublyLinkedList<>();
        for (int i = 1; i <= n; i++) {
            dLLst.add(Integer.valueOf(i+22));
        }


        Iterator<Integer> itr = dLLst.dllIterator();

        dLLst.printList();

        Scanner in = new Scanner(System.in);
        whileloop:
        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
                case 1:  // Move to next element and print it
                    if (itr.hasNext()) {
                        System.out.println(itr.next());
                    } else {
                        break whileloop;
                    }
                    break;
                case 2:  // Remove element
                    itr.remove();
                    dLLst.printList();
                    break;

                case 3:  // Move to next element and print it
                    if (((DoublyLinkedList.DLLIterator)itr).hasPrev()) {
                        System.out.println(((DoublyLinkedList.DLLIterator)itr).prev());
                    } else {
                        break whileloop;
                    }
                    break;
                default:  // Exit loop
                    break whileloop;
            }
        }
        dLLst.printList();


    }


}
