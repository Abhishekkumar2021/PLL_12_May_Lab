package Lab.Tree;

public class BST{
    private Node root;
    public BST(){
        root = null;
    }
    // Insertion in BST
    private Node insert(Node root, int data){
        if(root == null){
            root = new Node(data);
            return root;
        }
        if(data < root.data){
            root.left = insert(root.left, data);
        }
        else if(data > root.data){
            root.right = insert(root.right, data);
        }
        return root;
    }
    public void insert(int data){
        root = insert(root, data);
    }

    // Search in BST
    private boolean search(Node root, int data){
        if(root == null){
            return false;
        }
        if(root.data == data){
            return true;
        }
        if(data < root.data){
            return search(root.left, data);
        }
        else{
            return search(root.right, data);
        }
    }
    public boolean search(int data){
        return search(root, data);
    }

    // Deletion in BST
    private Node delete(Node root, int data){
        if(root == null){
            return null;
        }
        if(data < root.data){
            root.left = delete(root.left, data);
        }
        else if(data > root.data){
            root.right = delete(root.right, data);
        }
        else{
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            else{
                Node temp = root.right;
                while(temp.left != null){
                    temp = temp.left;
                }
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }
        return root;
    }
    public void delete(int data){
        root = delete(root, data);
    }

    // Inorder Traversal
    private void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
    public void inorder(){
        inorder(root);
    }

    // Preorder Traversal
    private void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }
    public void preorder(){
        preorder(root);
    }

    // Postorder Traversal
    private void postorder(Node root){
        if(root==null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }
    public void postorder(){
        postorder(root);
    }

    // Height of BST
    private int height(Node root){
        if(root == null){
            return 0;
        }
        int lheight = height(root.left);
        int rheight = height(root.right);
        return Math.max(lheight, rheight) + 1;
    }
    public int height(){
        return height(root);
    }

    // Print Level
    private void printLevel(Node root, int curr, int level){
        if(root == null){
            return;
        }
        if(curr == level){
            System.out.println(root.data);
        }
        printLevel(root.left, curr+1, level);
        printLevel(root.right, curr+1, level);
    }
    private void printLevel(Node root, int level){
        printLevel(root, 1, level);
    }

    // Level Order Traversal
    public void levelOrder(){
        int h = height(root);
        for(int i=1; i<=h; i++){
            printLevel(root, i);
        }
    }

    // Print Level reverse
    private void printLevelReverse(Node root, int curr, int level){
        if(root == null){
            return;
        }
        if(curr == level){
            System.out.println(root.data);
        }
        printLevelReverse(root.right, curr+1, level);
        printLevelReverse(root.left, curr+1, level);
    }
    public void printLevelReverse(int level){
        printLevelReverse(root, 1, level);
    }

    // Pretty print the tree
    public void prettyPrint() {
        prettyPrint(root, 0);
    }
  
    private void prettyPrint(Node node, int indent) {
        if (node == null)
            return;
      
        prettyPrint(node.right, indent + 4);
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(node.data);
      
        prettyPrint(node.left, indent + 4);
    }
};