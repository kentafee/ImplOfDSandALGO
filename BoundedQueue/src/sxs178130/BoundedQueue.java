package sxs178130;

public class BoundedQueue<T> implements Queue<T>{

    private int start;
    private int end;
    private int noOfElements;
    private Object[] arr;
    BoundedQueue (int size)
    {
        this.noOfElements =0;
        this.arr = new Object[size];
    }

    @Override
    public boolean offer(T data) {
        if(noOfElements == arr.length)
            return false;

        if(end == arr.length-1){
            end = 0;
            arr[end] = data;
        }
        else
        {
            arr[++end] = data;
        }

        noOfElements++;
        return true;
    }

    @Override
    public T poll() {

        T data = peek();
        if(data!= null) {

            if (start == arr.length)
                start = 0;
            else
                start++;

            noOfElements--;
        }
        return data;
    }

    @Override
    public T peek() {

        if(isEmpty())
            return null;

        return (T)arr[start];

    }

    @Override
    public boolean isEmpty() {
        if(noOfElements == 0)
            return true;
        return false;
    }

    @Override
    public void clear() {
        this.start = this.end =0;
    }

    @Override
    public void toArray(T[] a) {
        if(isEmpty())
            return;

        int tempStart = start;
        int tempEnd = end;
        int index = 0;
        int sizeOfa = a.length;
        while(tempStart !=tempEnd || index>= sizeOfa)
        {
            a[index] = (T)arr[tempStart];
            index++;
            if(tempStart == arr.length)
                tempStart = 0;
            else
                tempStart++;
        }



    }

    @Override
    public int size() {
        return noOfElements;
    }


    public static void main(String[] args) {
        Queue queue = new BoundedQueue(10);
    }
}
