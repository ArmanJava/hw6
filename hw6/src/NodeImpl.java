public class NodeImpl implements Node {

    private Person value;

    private Node leftChild;
    private Node rightChild;
    private int height;

    public NodeImpl(Person value) {
        this.value = value;
        this.height = 0;
    }

    @Override
    public void setData(Person value) {
        this.value = value;
    }

    @Override
    public Person getData() {
        return value;
    }

    @Override
    public int getKey() {
        return value.getId();
    }

    @Override
    public Node getLeftChild() {
        return leftChild;
    }

    @Override
    public Node getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public Node getChildById(int key) {
        if (isLeftChild(key)){
            return getLeftChild();
        }
        else {
            return getRightChild();
        }
    }

    @Override
    public boolean isLeftChild(int key) {
        return key < getKey();
    }

    @Override
    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }

    @Override
    public void display() {
        System.out.println(getData());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
