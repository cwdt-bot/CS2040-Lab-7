/**
 * Name         : Yap Kai Herng
 * Matric. No   : A0199729A
*/

import java.util.*;

public class Lca {
    private void run() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int queries = sc.nextInt();
        Node[] nodes = new Node[num];
        for(int n = 0; n < num; n++) {
            nodes[n] = new Node(n+1,sc.nextInt());
        }
        for(int n = 0; n <num-1; n++) {
            String side = sc.next();
            Node curr = nodes[sc.nextInt()-1];
            Node toSet = nodes[sc.nextInt()-1];
            if (side.equals("L")) curr.setLeft(toSet);
            else curr.setRight(toSet);
        }
        Node head = nodes[0];
        while(queries-- > 0) {
            Node one = nodes[sc.nextInt()-1];
            Node two = nodes[sc.nextInt()-1];
            head.lca(one, two);
        }

    }

    public static void main(String args[]) {
        Lca runner = new Lca();
        runner.run();
    }
}


class Node {
    private Node left;
    private Node right;
    private int val;
    private int pos;

    public Node(int i,int n) {
        this.pos = i;
        this.val = n;
    }

    public void setLeft(Node n) {this.left = n;}
    public void setRight(Node n) {this.right = n;}

    public void lca(Node x, Node y) {
        if (this == x || this == y) {
            System.out.println(pos);
        } else if(val<x.val && val>y.val) {
            System.out.println(pos);
        } else if (val<y.val && val > x.val) {
            System.out.println(pos);
        } else if (x.val < val && y.val < val) {
            left.lca(x,y);
        } else {
            right.lca(x,y);
        }
    }
}
