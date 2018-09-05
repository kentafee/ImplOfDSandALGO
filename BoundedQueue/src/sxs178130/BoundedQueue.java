package sxs178130;

public class BoundedQueue<T> implements Queue<T>{
    @Override
    public boolean offer(T data) {
        return false;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public void toArray(T[] a) {

    }

    @Override
    public int size() {
        return 0;
    }
}
