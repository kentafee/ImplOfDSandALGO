
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
        int length = (int) Math.ceil((stringLength + 1 / Log_10B) + 1);
        this.len = stringLength;
        arr = new long[length];
        for (int i = 0; i < stringLength; i++) {
            arr[i] = Character.getNumericValue(s.charAt(s.length() - 1 - i));
        }


    }

    public Num(long x) {
    }

    public Num(int size) {
        arr = new long[size];
        len = 0;


    }

    public Num(Num a) {

        this.len = a.len;
        this.arr = new long[a.arr.length];

        for(int i =0; i<len; i++)
        {
            this.arr[i] = a.arr[i];
        }

    }

    public static Num add(Num a, Num b) {

        return null;
    }


    public static Num subtract(Num a, Num b) {


        Num result;

        if (b.isNegative && !a.isNegative)
            return add(a, b);
        else if (a.isNegative && b.isNegative)
            return subtract(b, a);
        else if (a.isNegative && !b.isNegative)
            return add(a, b);


        if (a.len > b.len) {
            result = minus(a, b);
            result.isNegative = false;
        }
        else if (a.len < b.len){
            result = minus(b,a);
            result.isNegative = true;
        }
        else
        {
            int index = a.len-1;
            while(index >=0 && a.arr[index]==b.arr[index])
                index--;
            if(a.arr[index]>b.arr[index]){
                result = minus(a,b);
                result.isNegative = false;
            }
            else if(a.arr[index]<b.arr[index]){
                result = minus(b,a);
                result.isNegative = true;
            }

            else
            {
                result = new Num("0");
            }
        }

        return result;
    }

    private static Num minus(Num a, Num b) {
        boolean borrow = false;
        Num result = new Num( a.arr.length);
        result.len = a.len;
        for (int i = 0; i < a.len; i++) {
            if (i < b.len ) {
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


        while(result.len + 1 >0 && result.arr[result.len-1]==0)
            result.len--;
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
        Num start = new Num("0");
        Num end = new Num(a);
        while (true) {
            Num temp = add(start, end);
            Num mid = add(start, temp.by2());

            int comp = a.compareTo(product(mid, b));
            if (comp == 0) {
                return mid;
            } else if (comp < 0) {
                if (b.compareTo(subtract(product(mid, b), a)) > 0)
                    end = mid;
                else
                    return subtract(mid, new Num(1));

            } else {
                if (b.compareTo(subtract(a, product(mid, b))) > 0)
                    start = mid;
                else
                    return mid;
            }
        }

    }


    // return a%b
    public static Num mod(Num a, Num b) {

        Num start = new Num("0");
        Num end = new Num(a);
        while (true) {
            Num temp = add(start, end);
            Num mid = add(start, temp.by2());

            int comp = a.compareTo(product(mid, b));
            if (comp == 0) {
                return new Num("0");
            } else if (comp < 0) {
                if (b.compareTo(subtract(product(mid, b), a)) > 0)
                    end = mid;
                else
                    return subtract(product(mid, b), a);

            } else {
                if (b.compareTo(subtract(a, product(mid, b))) > 0)
                    start = mid;
                else
                    return subtract(a, product(mid, b));
            }
        }


    }

    // Use binary search
    public static Num squareRoot(Num a) {
        Num start = new Num("0");
        Num end = new Num(a);
        while (true) {
            Num temp = add(start, end);
            Num mid = add(start, temp.by2());

            int comp = a.compareTo(product(mid, mid));
            if (comp == 0) {
                return mid;
            } else if (comp > 0) {
                if (mid.compareTo(subtract(a,product(mid, mid))) > 0)
                    start = mid;
                else
                    return mid;

            } else {
                if (mid.compareTo(subtract( product(mid, mid),a)) > 0)
                    end = mid;
                else
                    return subtract(mid, new Num(1));
            }
        }


    }


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
        if (this.isNegative && !other.isNegative) {
            return -1;
        } else if (!this.isNegative && other.isNegative) {
            return 1;
        } else if (!this.isNegative && !other.isNegative) {
            if (this.len > other.len) {
                return 1;
            } else if (this.len < other.len) {
                return -1;
            } else {
                int i = this.len - 1;
                while (this.arr[i] == other.arr[i]) {
                    if (i == 0) {
                        return 0;
                    }
                    i--;
                }
                return (this.arr[i] > other.arr[i]) ? 1 : -1;
            }
        } else {
            this.isNegative = false;
            other.isNegative = false;
            int result = this.compareTo(other);
            this.isNegative = true;
            other.isNegative = true;
            if (result == 1) {
                return -1;
            } else if (result == -1) {
                return 1;
            } else {
                return 0;
            }
        }

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
        boolean borrow = false;
        Num result = new Num(this.arr.length);
        result.len = this.len;
        int j = this.len - 1;
        for (int i = this.len - 1; i >= 0; i--) {
            if (borrow) {
                result.arr[j] = (this.arr[i] + base) / 2;
                if ((this.arr[i] + base) % 2 == 1) {
                    borrow = true;
                } else {
                    borrow = false;
                }
                j--;
            } else {
                result.arr[j] = this.arr[i] / 2;
                if (this.arr[i] % 2 == 1) {
                    borrow = true;
                } else {
                    borrow = false;
                }
                j--;
            }

        }
        while(result.len >1 && result.arr[result.len-1]==0)
            result.len--;
        return result;
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
        Num x = new Num("1");
        Num y = new Num("1000");
       Num b = x.by2();
        Num z = Num.subtract(x,y);
        System.out.println(z.arr[0] + "---------" + z.arr[1]);
        Num a = Num.power(x, 8);
        System.out.println(a);
        if (z != null) z.printList();
    }
}



