public interface Node {

    void setData(Person value);
    Person getData();

    int getKey();

    Node getLeftChild();

    Node getRightChild();

    void setLeftChild(Node leftChild);

    void setRightChild(Node rightChild);

    Node getChildById (int key);

    boolean isLeftChild(int key);

    boolean isLeaf();

    void display();

    int getHeight();

    void setHeight(int height);
}
