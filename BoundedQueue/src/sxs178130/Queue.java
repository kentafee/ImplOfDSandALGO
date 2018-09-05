package sxs178130;
public interface Queue<T> {

    boolean offer (T data);
    T poll();
    T peek();
    boolean isEmpty();
    boolean clear();
    void toArray(T[] a);
    int size();

}
