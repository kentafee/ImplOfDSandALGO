package sxs178130;

import java.util.Scanner;

public class BoundedQueue<T> implements Queue<T>{

    private int start;
    private int end;
    private int noOfElements;
    private T[] arr;
    BoundedQueue (int size)
    {
        this.start = 0;
        this.end = -1;
        this.noOfElements =0;
        this.arr = (T[])(new Object[size]);
    }

    @Override
    public boolean offer(T data) {
        if(noOfElements == arr.length)
            return false;

        if(end == arr.length-1)
            end = 0;
        else
            end++;

        arr[end] = data;
        noOfElements++;
        return true;
    }

    @Override
    public T poll() {

        T data = peek();
        if(data!= null) {

            if (start == arr.length-1)
                start = 0;
            else if(start == end) {
                start = 0;
                end = -1;
            }
            else
                start++;

            noOfElements--;
        }
        else
        {
            start = 0;
            end = -1;
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
        this.start = 0;
        this.end = -1;
        noOfElements = 0;
    }

    @Override
    public void toArray(T[] a) {
        if(isEmpty())
            return;

        int tempStart = start;
        int tempEnd = end;
        int index = 0;
        int sizeOfa = a.length;
        while(tempStart !=tempEnd && index< sizeOfa)
        {
            a[index] = (T)arr[tempStart];
            index++;
            if(tempStart == arr.length-1)
                tempStart = 0;
            else
                tempStart++;
        }
        if(index< sizeOfa)
        a[index] = (T)arr[tempStart];



    }

    @Override
    public int size() {
        return noOfElements;
    }


    @Override
    public void printQueue()
    {
        if(noOfElements>0)
        {
            int index=start;
            while (index!=end)
            {

                System.out.print(arr[index]+" ");
                if(end<start &&index==arr.length-1)
                {
                    index=0;
                }
                else
                    index++;
            }
            System.out.print(arr[end]+"  ");
            System.out.println();

        }
        else
        {
            System.out.print("queue is empty");
        }
    }
    public static void main(String[] args) {
        int n = 5;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        int i =1;
        BoundedQueue<Integer> que= new BoundedQueue<>(5);
        for ( i = 1; i <= n; i++) {
            que.offer(Integer.valueOf(i));
        }

        que.printQueue();
        Scanner in = new Scanner(System.in);
        whileloop: while (in.hasNext()) {
            int com = in.nextInt();
            switch (com) {
                case 1: // Move to next element and print it
                    if (!que.isEmpty()) {
                        System.out.println(que.peek());
                        que.printQueue();
                    }
                    break;
                case 2: // Remove element
                    if (!que.isEmpty()) {
                        System.out.println("size before poll: "+que.size());
                        que.poll();
                        que.printQueue();
                        System.out.println("size after poll: "+que.size());
                    }
                    break;
                case 3: // Remove element by value
                    Integer[] a=new  Integer[10];
                    que.toArray(a);
                    for(Integer number:a)
                    {
                        System.out.print(number + " ");
                    }
                    break;
                case 4: // get the element by index
                    que.clear();
                    que.printQueue();
                    break;
                case 5:
                    que.offer(Integer.valueOf(i++));
                    que.printQueue();
                    // System.out.println(lst.get(14));
                    break;
                default: // Exit loop
                    break whileloop;
            }
        }
    }
}
