public class Main{

    public static void main(String[] args) {
        BST tree = new BST();

        tree.AVLinsert(50);
        tree.AVLinsert(30);
        tree.AVLinsert(20);
        tree.AVLinsert(10);
        tree.AVLinsert(5);
        tree.AVLinsert(60);
        tree.AVLinsert(80);
        tree.AVLinsert(55);
        tree.AVLinsert(12);
        tree.AVLinsert(7);
        tree.AVLremove(30);
        System.out.println(tree);
        // tree.AVLinsert(40);
        // tree.AVLinsert(70);
        // tree.AVLinsert(60);
        // tree.AVLinsert(80);
        // System.out.println(tree.search(25));
        // System.out.println(tree.search(70));

        // tree.remove(20);
        // System.out.println(tree.search(20));

        // tree.remove(30);
        // System.out.println(tree.search(20));

        // tree.remove(50);
        // System.out.println(tree.search(50));
        // System.out.println(tree.search(30));
        // System.out.println(tree.search(70));
        // System.out.println(tree);
        // System.out.println("" + tree.height());
    }

}