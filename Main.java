public class Main{

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // System.out.println(tree.search(25));
        // System.out.println(tree.search(70));

        // tree.remove(20);
        // System.out.println(tree.search(20));

        // tree.remove(30);
        // System.out.println(tree.search(20));

        tree.remove(50);
        // System.out.println(tree.search(50));
        // System.out.println(tree.search(30));
        // System.out.println(tree.search(70));
        System.out.println(tree);
        System.out.println("" + tree.height());
    }

}