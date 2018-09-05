package sxs178130;

import java.util.Iterator;

public interface DoublyLinkedListIterator<T> extends Iterator<T> {
    boolean hasNext();

    boolean hasPrev();

    T next();

    T previous();

    void add(T x);

}
