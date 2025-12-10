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
                int replacementKey = root.right.key;
                Node replacementNode = root.right;
                Node replacementParent = root;
                while(replacementNode.left != null) {
                    replacementParent = replacementNode;
                    replacementNode = replacementNode.left;
                }
                root.key = replacementKey;
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
                    int replacementKey = keyNode.right.key;
                    Node replacementNode = keyNode.right;
                    Node replacementParent = keyNode;
                    while(replacementNode.left != null) {
                        replacementParent = replacementNode;
                        replacementNode = replacementNode.left;
                    }
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
                    int replacementKey = keyNode.right.key;
                    Node replacementNode = keyNode.right;
                    Node replacementParent = keyNode;
                    while(replacementNode.left != null) {
                        replacementParent = replacementNode;
                        replacementNode = replacementNode.left;
                    }
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

    // public String toString(){
        
    // }

}