/**
 * Name         : Yap Kai Herng
 * Matric. No   : A0199729A
*/

import java.util.*;

public class Dictionary {
    private void run() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int q = sc.nextInt();
        abTree tree = new abTree();
        //read in the entries
        while(num > 0) {
            num--;
            String input = sc.next();
            tree.insert(input);
        }
        //read in the queries
        while(q > 0) {
            q--;
            String query = sc.next();
            System.out.println(tree.query(query));
        }
        sc.close();
    }

    public static void main(String args[]) {
        Dictionary runner = new Dictionary();
        runner.run();
    }
}

//abTree simulates a binary tree where traversing down to a leaf will build back the original entry
//the left branch represents 'a' and the right branch represents 'b'
//the root node of the tree acts as a controller to send queries left or right.
class abTree {
    abNode root = new abNode();
    private static char A = 'a';
    private static char B = 'b';

    class abNode {
        abNode a;
        abNode b;
        int size;

        private abNode() {
            this.size = 1;
        }

        //the tree will only call this method, so it will always start at the root node
        //this is an overloaded method to filter out empty strings and convert input strings
        //into a char[]
        //precond: string containing only 'a' or 'b'
        //postcond: passes off to insert(char[], int)
        private void insert(String s){
            if (s.equals("")) return;
            char[] arr = s.toCharArray();
            this.insert(arr, 0);
        }

        //travels along the tree, creating new nodes as it goes
        //precond: the array is not empty, insert(String s) deals with empty strings. n is guaranteed to be >0
        //postcond: the string represented by char[] arr is entered into the abTree
        private void insert(char[] arr, int n) {
            if (n == arr.length) return;
            if (arr[n] == A ) {
                if(a!=null) a.size++;
                abNode currA = a != null ? a: new abNode();
                this.a = currA;
                currA.insert(arr, n+1);
            } else {
                if(b!=null) b.size++;
                abNode currB = b != null ? b : new abNode();
                this.b = currB;
                currB.insert(arr, n+1);
            }
        }
        
        //the root node decides which tree to send the query
        //to either the "a" tree or "b" tree. Overloaded method to abstract out the 
        //conversion of the string s to char[]
        //precond: valid string is inputted
        //postcond: converts s to char[] and passes off to traverse(char[], int)
        private int traverse(String s) {
            char[] arr = s.toCharArray();
            return this.traverse(arr, -1);
        }

        //precond: n >= -1.  n indicates checking the (n+1)th element of the input arr 
        //postond: returns the number of entries in the treee that has the string represented
        //by the input array as the prefix
        private int traverse(char[] arr, int n) {
            if (n == arr.length-1) return size;
            abNode next = arr[n+1] == A ? a : b;
            if (next==null) return 0;
            else {
                return next.traverse(arr, n+1);
            }
        }


    }

    public void insert(String s) {
        root.insert(s);
    }

    public int query(String s) {
        return root.traverse(s);
    }
}

