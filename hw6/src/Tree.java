public interface Tree {

    void add (Person value);

    Person remove (int id);

    Person find (int id);

    boolean isEmpty();

    boolean isBalancedTree(Node node);

    void display();

    int getSize();

    void traverse (TraverseMode mode);

    boolean isBalancedNode(Node node);

    int height(Node node);

}
