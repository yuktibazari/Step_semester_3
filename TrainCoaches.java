class Node {
    String name;
    Node prev, next;

    Node(String name) {
        this.name = name;
    }
}

public class TrainCoaches {
    Node head, tail;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    void remove(String name) {
        Node temp = head;

        while (temp != null && !temp.name.equals(name))
            temp = temp.next;

        if (temp == null)
            return;

        if (temp.prev != null)
            temp.prev.next = temp.next;
        else
            head = temp.next;

        if (temp.next != null)
            temp.next.prev = temp.prev;
        else
            tail = temp.prev;
    }

    void printForward() {
        Node temp = head;
        System.out.print("Forward: ");

        while (temp != null) {
            System.out.print(temp.name);
            if (temp.next != null)
                System.out.print(" <-> ");
            temp = temp.next;
        }

        System.out.println();
    }

    void printBackward() {
        Node temp = tail;
        System.out.print("Backward: ");

        while (temp != null) {
            System.out.print(temp.name);
            if (temp.prev != null)
                System.out.print(" <-> ");
            temp = temp.prev;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        TrainCoaches train = new TrainCoaches();

        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}