package sxs178130;

public class SortableList<T extends Comparable<? super T>>  extends SinglyLinkedList<T>{
    void merge(SortableList<T> otherList) {  // Merge this list with other list


    }
    void mergeSort() { //Sort this list
    }
    public static<T extends Comparable<? super T>> void mergeSort(SortableList<T> list) {
        list.mergeSort();
    }

    public static void main(String[] args) {

        SortableList<Integer> list = new SortableList<>();
        SortableList.mergeSort(list);
    }
}
