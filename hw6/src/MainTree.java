import java.util.Random;
public class MainTree {
    public static void main(String[] args) {
        Tree tree = new TreeImpl();

        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            tree.add(new Person((random.nextInt(50) - 25), "Ivan", 10));
        }

        /*tree.add(new Person(60, "Ivan", 100));
        tree.add(new Person(50, "Ivan", 100));
        tree.add(new Person(40, "Ivan", 100));
        tree.add(new Person(55, "Ivan", 100));
        tree.add(new Person(31, "Ivan", 100));
        tree.add(new Person(45, "Ivan", 100));
        tree.add(new Person(57, "Ivan", 100));
        tree.add(new Person(66, "Ivan", 100));
        tree.add(new Person(70, "Ivan", 100));
        tree.add(new Person(67, "Ivan", 100));
        tree.add(new Person(81, "Ivan", 100));
        tree.add(new Person(62, "Ivan", 100));*/

        tree.traverse(TraverseMode.IN_ORDER);

        tree.display();

    }
}
