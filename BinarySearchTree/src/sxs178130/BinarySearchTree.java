/**
 * @author Binary search tree (starter code)
 **/

package sxs178130;

import java.util.*;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }

    Entry<T> root;
    int size;
    Stack<Entry<T>> stack;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }


    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {

        Entry<T> t = find(x);
        if (t == null || t.element.compareTo(x) != 0)
            return false;
        return true;
    }

    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) {
        Entry<T> t = find(x);
        if (t == null || t.element.compareTo(x) != 0)
            return null;
        return t.element;
    }

    /** TO DO: Add x to tree. 
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */
    public boolean add(T x) {
        if (size == 0) {
            root = new Entry<>(x, null, null);
            size++;
            return true;
        } else {
            Entry<T> t = find(x);

            if (t.element.compareTo(x) == 0) {
                t.element = x;
                return false;
            } else if (t.element.compareTo(x) > 0) {
                t.left = new Entry<>(x, null, null);
                size++;
            } else {
                t.right = new Entry<>(x, null, null);
                size++;
            }

        }

        return true;
    }

    /** TO DO: Remove x from tree. 
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {

        if (root == null)
            return null;

        Entry<T> t = find(x);

        if (t == null || t.element.compareTo(x) != 0)
            return null;

        T result = t.element;
        if (t.right == null || t.left == null) {
            bypass(t);
        }
        else {
                stack.push(t);
                Entry<T> successor = find(t.right,x);
                t.element = successor.element   ;
                bypass(successor);
        }

        size--;
        return result;
    }

    private void bypass(Entry<T> t) {

        Entry <T> parent = stack.peek();
        Entry<T> child = t.left == null ? t.right : t.left;
        if(parent == null)
            root = child;
        else
            if (parent.left == t)
                parent.left = child;
            else
                parent.right = child;
    }

    public T min() {
        if (size == 0)
            return null;
        Entry<T> t = root;

        while (t.left != null)
            t = t.left;
        return t.element;
    }

    public T max() {
        if (size == 0)
            return null;

        Entry<T> t = root;

        while (t.right != null)
            t = t.right;

        return t.element;
    }


    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        if (root == null)
            return arr;
        Entry<T> t = root;

        Stack<Entry<T>> stack = new Stack<>();
        int index = 0;
        // traverse the tree
        while (t != null || stack.size() > 0)
        {
            while (t !=  null)
            {
                stack.push(t);
                t = t.left;
            }


            t = stack.pop();

            arr[index++] = t.element;

            t = t.right;
        }
        return arr;
    }

//
//    public Comparable[] postFixToArray() {
//        Comparable[] arr = new Comparable[size];
//        if(root == null)
//            return null;
//
//        Entry <T> temp = root;
//
//        Stack<Entry<T>> stack = new Stack<>();
//        int index = 0;
//
//        while(temp!=null || stack.size()>0) {
//            while (temp != null) {
//                stack.push(temp);
//                temp = temp.left;
//            }
//
//            temp = stack.pop();
//            if(temp.right!=null)
//                temp = temp.right;
//            else {
//                arr[index++] = temp.element;
//                temp =stack.pop();
//                temp = temp.right;
//            }
//        }
//
//
//        return arr;
//    }


        public Comparable[] prefixToArray() {
        Comparable[] arr = new Comparable[size];
        if(root == null)
            return null;

        Entry <T> temp = root;

        Stack<Entry<T>> stack = new Stack<>();
        int index = 0;


        while(temp!=null || stack.size()>0) {

            while(temp!=null) {
                arr[index++] = temp.element;
                stack.push(temp);
                temp= temp.left;
            }

            temp = stack.pop().right;
        }



        return arr;
   }

    public Entry<T> find(T x) {

        stack = new Stack<>();
        stack.push(null);
        return find(this.root, x);

    }

    private Entry<T> find(Entry<T> tEntry, T x) {
        if (tEntry == null || (tEntry.element.compareTo(x) == 0))
            return tEntry;
        while (true) {
            if (tEntry.element.compareTo(x) > 0) {
                if (tEntry.left == null)
                    break;
                else {
                    stack.push(tEntry);
                    tEntry = tEntry.left;
                }
            } else if (tEntry.element.compareTo(x) == 0)
                break;
            else {
                if (tEntry.right == null)
                    break;
                else {
                    stack.push(tEntry);
                    tEntry = tEntry.right;
                }
            }

        }
        return tEntry;
    }
// Start of Optional problem 2

    /** Optional problem 2: Iterate elements in sorted order of keys
     Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
        return null;
    }

    // Optional problem 2.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem 2.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem 2.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem 2.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

// End of Optional problem 2

    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int x = in.nextInt();
            if (x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if (x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.prefixToArray();
                System.out.print("Final: ");
                for (int i = 0; i < t.size; i++) {
                    System.out.print(arr[i] + " ");
                }

                System.out.println("Max--->>>>>"+t.max());
                System.out.println("Min--->>>>>"+t.min());
                return;
            }
        }

    }


    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }

}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/