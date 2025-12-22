import java.util.ArrayList;

class BST {

    Node root;

    // Precondition: None.
    // Postcondition: Initializes an empty BST (root == null).
    public BST()
    {
        root = null;
    }


    
    // Precondition: BST is properly initialized
    // Postcondition: inserts key into the BST
    void insert(int key){
        insert(key, root);
    }
    
    // Precondition: BST is properly initialized. 'prev' is the current node (may be null if tree empty).
    // Postcondition: Inserts 'key' into the subtree rooted at 'prev' (or sets root if prev is null). Duplicates are ignored.
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

    // Precondition: BST is properly initialized; 'myNode' is the current subtree root (may be null).
    // Postcondition: Returns true if 'key' exists in subtree rooted at 'myNode', false otherwise.
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
            // traverse until find a parent w left/right child has key
            while (prev != null) {
                if (prev.left != null && prev.left.key == key) {
                    Node keyNode = prev.left;
                    //no child
                    if (keyNode.left == null && keyNode.right == null){
                        prev.left = null;
                    }
                    //one child
                    else if (keyNode.left != null && keyNode.right == null){
                        prev.left = keyNode.left;
                    }
                    else if (keyNode.left == null && keyNode.right != null){
                        prev.left = keyNode.right;
                    }
                    //two child
                    else{
                        Node replacementNode = keyNode.right;
                        Node replacementParent = keyNode;
                        while(replacementNode.left != null) {
                            replacementParent = replacementNode;
                            replacementNode = replacementNode.left;
                        }
                        prev.left.key = replacementNode.key;
                        if (replacementParent == keyNode) {
                            prev.left.right = replacementNode.right;
                        } else {
                            replacementParent.left = replacementNode.right;
                        }
                    }
                    return true;
                } else if (prev.right != null && prev.right.key == key) {
                    Node keyNode = prev.right;
                    //no child
                    if (keyNode.left == null && keyNode.right == null){
                        prev.right = null;
                    }
                    //one child
                    else if (keyNode.left != null && keyNode.right == null){
                        prev.right = keyNode.left;
                    }
                    else if (keyNode.left == null && keyNode.right != null){
                        prev.right = keyNode.right;
                    }
                    //two child
                    else{
                        Node replacementNode = keyNode.right;
                        Node replacementParent = keyNode;
                        while(replacementNode.left != null) {
                            replacementParent = replacementNode;
                            replacementNode = replacementNode.left;
                        }
                        prev.right.key = replacementNode.key;
                        if (replacementParent == keyNode) {
                            prev.right.right = replacementNode.right;
                        } else {
                            replacementParent.left = replacementNode.right;
                        }
                    }
                    return true;
                } else {
                    if (key > prev.key) prev = prev.right;
                    else prev = prev.left;
                }
            }
        }
        return true;

    }

    // Precondition: BST is properly initialized.
    // Postcondition: Returns a string with each tree depth on its own line, nodes at each depth left-to-right.
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

    // Precondition: 'levels' is an ArrayList where index corresponds to depth level.
    // Postcondition: Appends node keys into 'levels' at their respective depth using in-order traversal.
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
    // Precondition: BST is properly initialized.
    // Postcondition: Returns true if tree satisfies the BST ordering property for all nodes.
    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Precondition: 'root' is current node; all keys in this subtree must be within [minValue, maxValue].
    // Postcondition: Returns true if subtree rooted at 'root' satisfies BST property within the given bounds.
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
 
    // Precondition: BST is properly initialized.
    // Postcondition: Prints a formatted ASCII representation of the tree to stdout.
    public void printTree(){
        printTree(root, null, false);
    }

    // Precondition: 'root' is current node; 'prev' is the chain of trunks from parent (may be null).
    // Postcondition: Prints the subtree rooted at 'root' using ASCII trunk formatting.
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

    // Precondition: 'prev' is the parent of 'subRoot' and not null; subRoot.left != null.
    // Postcondition: Performs an in-place right rotation at 'subRoot' and updates the parent's child pointer.
    private void rotateRight(Node subRoot, Node prev){
        boolean left = (prev.left != null && prev.left.key == subRoot.key);
        Node pivot = subRoot.left;

        subRoot.left = pivot.right;
        
        pivot.right = subRoot;
        if (left){
            prev.left = pivot;
        } else {
            prev.right = pivot;
        }
    }

    // Precondition: 'prev' is the parent of 'subRoot' and not null; subRoot.right != null.
    // Postcondition: Performs an in-place left rotation at 'subRoot' and updates the parent's child pointer.
    private void rotateLeft(Node subRoot, Node prev){
        boolean left = (prev.left != null && prev.left.key == subRoot.key);
        Node pivot = subRoot.right;

        subRoot.right = pivot.left;
        
        pivot.left = subRoot;
        if (left){
            prev.left = pivot;
        } else {
            prev.right = pivot;
        }
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Height & Balance/////////////////////////////////
    
    // Precondition: BST is properly initialized.
    // Postcondition: Returns the height of the tree (empty tree -> -1, single node -> 0).
    public int height(){
        return height(root);
    }

    // Precondition: 'node' is root of subtree (may be null).
    // Postcondition: Returns height of subtree, or -1 if null.
    private int height(Node node){
        if (node == null) return -1;
        if (node.left == null && node.right == null) return 0;
        if (node.left == null && node.right != null) return height(node.right)+1;
        if (node.left != null && node.right == null) return height(node.left)+1;
        return Math.max(height(node.left)+1, height(node.right)+1);
    }

    // Precondition: 'node' is root of subtree (may be null).
    // Postcondition: Returns balance factor = height(left) - height(right); returns 0 if node is null.
    private int balance(Node node){
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    //balance the nodes under the selected node.
    private void balanceTree(Node node, Node prev){
        if (node == null) return;
        //LL
        else if (balance(node) > 1 && balance(node.left) >= 0) {
            rotateRight(node, prev);
        } 
        //LR (double rotation)
        else if (balance(node) < -1 && balance(node.left) < 0) {
            rotateLeft(node.right, node);
            rotateRight(node, prev);
        }
        //RR
        else if (balance(node) > 1 && balance(node.right) >=0) {
            rotateLeft(node, prev);
        } 
        //RL (double rotation)
        else if (balance(node) < -1 && balance(node.right) < 0) {
            rotateRight(node.left, node);
            rotateLeft(node, prev);
        }
        return;
    }  

}