package rpis81.kobzareva.oop.model;

public class Node {
    Service value;
    Node next;
    Node previous;

    Node(Service element, Node previous, Node next) {
        this.value = element;
        this.next = next;
        this.previous = previous;
    }

    public Node(Service value) {
        this.previous = this;
        this.next = this;
        this.value = value;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Service getValue() {
        return value;
    }

    public void setValue(Service value) {
        this.value = value;
    }
}
