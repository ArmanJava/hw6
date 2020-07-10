import java.util.Stack;
public class TreeImpl implements Tree {

    private Node root;
    private int size;
    private int treeMaxHeight = 6; // Максимальная глубина дерева
    private boolean balance = true;

    public TreeImpl() {
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void add(Person value) {
        Node node = new NodeImpl(value);
        if (root == null){

            root = node;
            root.setHeight(1);
            return;

        }

        Node parent = null;
        Node current = root;

        while (current != null){
            parent = current;
            current = current.getChildById(value.getId());
        }

        if (parent.isLeftChild(value.getId())){
            if (parent.getHeight() < treeMaxHeight){
                parent.setLeftChild(node);
            } else {
                System.out.println("Превышена максимальная глубина дерева");
            }
        } else {
            if (parent.getHeight() < treeMaxHeight){

                parent.setRightChild(node);
            } else {

                System.out.println("Превышена максимальная глубина дерева");
            }
        }
        size++;
        node.setHeight(parent.getHeight() + 1);
    }

    @Override
    public Person remove(int id) {
        Node current = root;
        Node parent = null;

        while(current != null){
            if (current.getKey() == id){
                break;
            }
            parent = current;
            current = current.getChildById(id);
        }

        if (current == null){
            return null;
        }

        if (current.isLeaf()){
            if (parent == null){
                root = null;
            } else if (parent.isLeftChild(id)){
                parent.setLeftChild(null);
            }
            else {
                parent.setRightChild(null);
            }
        }
        else if (current.getRightChild() == null || current.getLeftChild() == null){
            Node childNode = current.getLeftChild() != null ? current.getLeftChild():current.getRightChild();
            if (parent.isLeftChild(current.getKey())){
                parent.setLeftChild(childNode);
            }
            else {
                parent.setRightChild(childNode);
            }
        }
        else {
            Node successor = getSuccessor(current);
            if (current == root){
                root = successor;
            }
            else if (parent.isLeftChild(id)){
                parent.setLeftChild(successor);
            }
            else {
                parent.setRightChild(successor);
            }
        }


        size--;
        return current.getData();
    }

    private Node getSuccessor (Node node){
        Node successor = node;
        Node successorParent = node;
        Node current = node.getRightChild();

        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }
        if (successor != node.getRightChild()){
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(node.getRightChild());
        }

        return successor;
    }

    @Override
    public Person find(int id) {
        Node current = root;
        while (current!= null){

            if (current.getKey() == id){
                return current.getData();
            }
            current = current.getChildById(id);

        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void traverse(TraverseMode mode) {
        switch (mode){
            case IN_ORDER:
                inOrder (root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                default:
                    throw new IllegalArgumentException("Invalid mode = " + mode);
        }
    }

    private void postOrder(Node root) {
        if (root != null){
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
            root.display();
        }
    }

    private void preOrder(Node root) {
        if (root != null){
            root.display();
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
        }
    }

    private void inOrder(Node node) {
        if (node != null){
            inOrder(node.getLeftChild());
            node.display();
            inOrder((node.getRightChild()));
        }
    }

    @Override
    public boolean isBalancedTree(Node node){

        if ( node != null )
        {
            if (!isBalancedNode(node)){
                balance = false;
            }
            isBalancedTree(node.getLeftChild());
            isBalancedTree((node.getRightChild()));
        }
            return balance;
    }

    @Override
    public void display() {
        Stack<Node> globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getKey());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
        System.out.println("Глубина дерева: " + height(root));
        System.out.println("Tree is balanced: " + isBalancedTree(root));
    }


    @Override
    public boolean isBalancedNode(Node node) {
        if (node.getLeftChild() == null || node.getRightChild() == null){
            if (node.getHeight() < ((height(root)) - 1)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int height(Node node) {

        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }
}
