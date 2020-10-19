/**
 * Name         : Yap Kai Herng
 * Matric. No   : A0199729A
*/

import java.util.*;

public class Diameter {
    private void run() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        Node[] nodes = new Node[num];
        for(int n = 0; n < num; n++) {
            nodes[n] = new Node();
            if (n==0) continue;
            nodes[n].setParent(nodes[sc.nextInt()-1]);
        }
        int max = 0;
        for(Node n : nodes) {
            max = Math.max(max, n.diameter());
        }
        System.out.println(max);
    }

    public static void main(String args[]) {
        Diameter runner = new Diameter();
        runner.run();
    }
}


class Node {
    private Node parent;
    private Node left;
    private Node right;

    public void setParent(Node n) {
        this.parent = n;
        if (n.left == null) n.left = this;
        else n.right = this;
    }

    public int height() {
        if (left ==null && right ==null) return 0;
        if (right == null) return left.height() + 1;
        else if (left == null) return right.height() +1;
        else return Math.max(left.height(), right.height()) + 1;
    }

    public int diameter() {
        int lh = 0, rh = 0, ld = 0, rd = 0, passThrough = 0;
        if (left != null) {
            lh = left.height();
            ld = left.diameter();
            passThrough += lh + 1;
        }
        if (right != null) {
            rh = right.height();
            rd = right.diameter();
            passThrough += rh +1;
        }
        int currMax = Math.max(passThrough, rd);
        currMax = Math.max(currMax, ld);
        return currMax;
    }
}

