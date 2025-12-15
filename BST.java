import java.util.ArrayList;

class BST {

    Node root;

    public BST()
    {
        root = null;
    }


    
    // Precondition: BST is properly initialized
    // Postcondition: inserts key into the BST
    void insert(int key){
        insert(key, root);
    }
    
    private void insert(int key, Node prev){
        //empty tree case
        if (prev == null){
            root = new Node(key);
            return;
        }
        // key is bigger than prev (right)
        if (key > prev.key){
            if (prev.right == null){
                prev.right = new Node(key);
                return;
            }
            else {
                insert(key, prev.right);
            }
        }
        // key is smaller than prev (left)
        if (key <= prev.key){
            if (prev.left == null){
                prev.left = new Node(key);
                return;
            }
            else {
                insert(key, prev.left);
            }
        }
    }



    // Precondition: BST is properly initialized
    // Postcondition: returns true if key is found, false otherwise
    boolean search(int key){
        return search(key, root);

    }

    private boolean search(int key, Node myNode){
        if (myNode == null) return false;
        if (myNode.key == key) return true;
        if (key < myNode.key) return search(key, myNode.left);
        return search(key, myNode.right);
    }



    // Precondition: BST is properly initialized, root is not null.
    // Postcondition: returns true if key was found and removed, false otherwise
    boolean remove(int key){
        Node prev = root;
        if (!search(key)) return false;
        //root case
        if (root.key == key){
            //no child
            if (root.left == null && root.right == null){
                root = null;
            }
            //one child
            else if (root.left != null && root.right == null){
                root = root.left;
            }
            else if (root.left == null && root.right != null){
                root = root.right;
            }
            //two children
            else{
                
                Node replacementNode = root.right;
                Node replacementParent = root;
                while(replacementNode.left != null) {
                    replacementParent = replacementNode;
                    replacementNode = replacementNode.left;
                }
                
                root.key = replacementNode.key;
                if (replacementParent == root) {
                    root.right = replacementNode.right;
                } else {
                    replacementParent.left = replacementNode.right;
                }
            }
        }
        //not root cases
        else {
            Boolean x = true;
            while (x){
                if (prev.left.key == key || prev.right.key == key){
                    x = false;
                } else if (key > prev.key){
                    prev = prev.right;
                } else{
                    prev = prev.left;
                }
            }

            if (prev.left.key == key){
                Node keyNode = prev.left;
                //no child
                if (prev.left.left == null && prev.left.right == null){
                    prev.left = null;
                }
                //one child
                else if (prev.left.left != null && prev.left.right == null){
                    prev.left = prev.left.left;
                }
                else if (prev.left.left == null && prev.left.right != null){
                    prev.left = prev.left.right;
                }
                //two child
                else{
                    Node replacementNode = keyNode.right;
                    Node replacementParent = keyNode;
                    while(replacementNode.left != null) {
                        replacementParent = replacementNode;
                        replacementNode = replacementNode.left;
                    }
                    int replacementKey = keyNode.right.key;
                    prev.left.key = replacementKey;
                    if (replacementParent == keyNode) {
                        // replacementNode is the right of keyNode
                        prev.left.right = replacementNode.right;
                    } else {
                        // replacementNode is in the left of keyNode's right
                        replacementParent.left = replacementNode.right;
                    }
                }

            }
            if (prev.right.key == key){
                Node keyNode = prev.right;
                //no child
                if (prev.right.left == null && prev.right.right == null){
                    prev.right = null;
                }
                //one child
                else if (prev.right.left != null && prev.right.right == null){
                    prev.right = prev.right.left;
                }
                else if (prev.right.left == null && prev.right.right != null){
                    prev.right = prev.right.right;
                }
                //two child
                else{
                    Node replacementNode = keyNode.right;
                    Node replacementParent = keyNode;
                    while(replacementNode.left != null) {
                        replacementParent = replacementNode;
                        replacementNode = replacementNode.left;
                    }
                    int replacementKey = keyNode.right.key;
                    prev.right.key = replacementKey;
                    if (replacementParent == keyNode) {
                        // replacementNode is right child
                        prev.right.right = replacementNode.right;
                    } else {
                        // replacementNode is left of keyNode's right
                        replacementParent.left = replacementNode.right;
                    }
                }
            }
        }
        return true;

    }

    public String toString(){
        if (root == null) return "";
        ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
        toString(root, 0, levels);

        String s = "";
        for (int i = 0; i < levels.size(); i++) {
            ArrayList<Integer> lvl = levels.get(i);
            for (int j = 0; j < lvl.size(); j++) {
                if (j > 0) s += " ";
                s += lvl.get(j);
            }
            if (i < levels.size() - 1) s += "\n";
        }
        return s;
    }

    private void toString(Node node, int level, ArrayList<ArrayList<Integer>> levels) {
        if (node == null) return;
        toString(node.left, level + 1, levels);
        while (levels.size() <= level) {
            levels.add(new ArrayList<Integer>());
        }
        levels.get(level).add(node.key);
        toString(node.right, level + 1, levels);
    }






    //Add the following functions to your BST
 //Please use this code to verify your tree integrity
    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
        // check for root is not null or not
        if (root == null) {
            return true;
        }
        // check for current node value with left node value and right node value and recursively check for left sub tree and right sub tree
        if(root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key) && isBSTOrNot(root.right, root.key, maxValue)){
            return true;
        }
        return false;
    }

 

   // please use the following pieces of code to display your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public void printTree(){
        printTree(root, null, false);
    }

    private void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);
    }




////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Rotation/////////////////////////////////////

    private void rotateRight(Node subRoot, Node prev){
        boolean left = false;
        if (prev.left.key == subRoot.key){
            left = true;
        }
        Node pivot = subRoot.left;
        if (pivot.right != null){
            subRoot.left = pivot.right;
        }
        pivot.right = subRoot;
        if (left){
            prev.left = pivot;
        } else {
            prev.right = pivot;
        }
        
    }

    private void rotateLeft(Node subRoot, Node prev){
        boolean left = false;
        if (prev.left.key == subRoot.key){
            left = true;
        }
        Node pivot = subRoot.right;
        if (pivot.left != null){
            subRoot.right = pivot.left;
        }
        pivot.left = subRoot;
        if (left){
            prev.left = pivot;
        } else {
            prev.right = pivot;
        }
    }
}