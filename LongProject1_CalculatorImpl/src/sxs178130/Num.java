
// Starter code for lp1.
// Version 1.0 (8:00 PM, Wed, Sep 5).

package sxs178130;

public class Num implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
    static long base = defaultBase;  // Change as needed
    long[] arr;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in arr[0..len-1]

    public Num(String s) {

        isNegative = (s.charAt(0) == '-') ? true : false;
        double Log_10B = Math.log10(this.base);
        int stringLength = (isNegative) ? s.length() - 1 : s.length();
        len = (int) Math.ceil((stringLength + 1 / Log_10B) + 1);
        arr = new long[len];
        for (int i = 0; i < stringLength; i++) {
            arr[i] = Character.getNumericValue(s.charAt(s.length() - 1 - i));
        }


    }

    public Num(long x) {
    }

    public Num() {
        arr = new long[200];


    }

    public static Num add(Num a, Num b) {

        return null;
    }


    public static Num subtract(Num a, Num b) {


      Num result =  minus(a,b);

//        if (b.isNegative && !a.isNegative)
//            return add(a, b);
//        else if (a.isNegative && b.isNegative)
//            return subtract(b, a);
//        else if (a.isNegative && !b.isNegative)
//            return add(a, b);
//

        if (a.len > b.len) {

        } else {

        }


        return result;
    }

    private static Num minus(Num a, Num b) {
        boolean borrow = false;
        Num result = new Num();
        for (int i = 0; i < a.len - 2; i++) {
            if (i < b.len - 2) {
                if (borrow) {
                    a.arr[i]--;

                }

                long temp = a.arr[i] - b.arr[i];
                if (temp >= 0) {
                    result.arr[i] = temp;
                    borrow = false;
                } else {
                    result.arr[i] = temp + base;
                    borrow = true;
                }


            } else {
                if (borrow) {
                    a.arr[i]--;
                    if (a.arr[i] < 0) {
                        result.arr[i] = a.arr[i] + base;
                    } else {
                        result.arr[i] = a.arr[i];
                        borrow = false;
                    }
                } else {
                    result.arr[i] = a.arr[i];
                }


            }
        }
        return result;
    }

    public static Num product(Num a, Num b) {
        return null;
    }

    // Use divide and conquer
    public static Num power(Num a, long n) {
        return null;
    }

    // Use binary search to calculate a/b
    public static Num divide(Num a, Num b) {
        return null;
    }

    // return a%b
    public static Num mod(Num a, Num b) {
        return null;
    }

    // Use binary search
    public static Num squareRoot(Num a) {
        return null;
    }


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
        return 0;
    }

    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    public void printList() {
    }

    // Return number to a string in base 10
    public String toString() {
        return null;
    }

    public long base() {
        return base;
    }

    // Return number equal to "this" number, in base=newBase
    public Num convertBase(int newBase) {
        return null;
    }

    // Divide by 2, for using in binary search
    public Num by2() {
        return null;
    }

    // Evaluate an expression in postfix and return resulting number
    // Each string is one of: "*", "+", "-", "/", "%", "^", "0", or
    // a number: [1-9][0-9]*.  There is no unary minus operator.
    public static Num evaluatePostfix(String[] expr) {
        return null;
    }

    // Evaluate an expression in infix and return resulting number
    // Each string is one of: "*", "+", "-", "/", "%", "^", "(", ")", "0", or
    // a number: [1-9][0-9]*.  There is no unary minus operator.
    public static Num evaluateInfix(String[] expr) {
        return null;
    }


    public static void main(String[] args) {
        Num x = new Num("100");
        Num y = new Num("5");
        Num z = Num.subtract(x, y);
        System.out.println(z.arr[0] + "---------" +z.arr[1]);
        Num a = Num.power(x, 8);
        System.out.println(a);
        if (z != null) z.printList();
    }
}