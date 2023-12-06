// Written by Sofia Kan, kan00022

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> head;
    private int size;
    private boolean isSorted;

    public LinkedList() {
        head = null;
        size = 0;
        isSorted = true;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        isSorted = false;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index > size) {
            return false;
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
        isSorted = false;
        return true;
    }

    private boolean checkIsSorted() {
        Node<T> current = head;
        while (current != null && current.getNext() != null) {
            if (current.getData().compareTo(current.getNext().getData()) > 0) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }


    @Override
    public void clear() {
        head = null;
        size = 0;
        isSorted = true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {
            return -1;
        }
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (element.equals(current.getData())) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        if (head == null || head.getNext() == null) {
            return; // List is already sorted or empty
        }

        boolean swapped;
        do {
            Node<T> current = head;
            Node<T> prev = null;
            swapped = false;

            while (current != null && current.getNext() != null) {
                Node<T> next = current.getNext();
                if (current.getData().compareTo(next.getData()) > 0) {
                    swapped = true;
                    if (prev != null) {
                        prev.setNext(next);
                    } else {
                        head = next;
                    }
                    current.setNext(next.getNext());
                    next.setNext(current);
                    prev = next;
                } else {
                    prev = current;
                    current = next;
                }
            }
        } while (swapped);
        isSorted = true;
    }


    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> current = head;
        Node<T> prev = null;

        if (index == 0) {
            head = current.getNext();
        } else {
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.getNext();
            }
            prev.setNext(current.getNext());
        }
        size--;
        isSorted = false;
        return current.getData();
    }

    @Override
    public void equalTo(T element) {
        Node<T> current = head;
        Node<T> prev = null;
        while (current != null) {
            if (!current.getData().equals(element)) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                    size--;
                } else {
                    head = head.getNext();
                    size--;
                }
            } else {
                prev = current;
            }
            current = current.getNext();
        }
        isSorted = false; // The list could be not sorted after removals
    }

    @Override
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
        isSorted = false;
    }

    @Override
    public void intersect(List<T> otherList) {
        if (otherList == null) {
            return;
        }
        Node<T> current = head;
        Node<T> prev = null;
        while (current != null) {
            if (otherList.indexOf(current.getData()) == -1) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                    size--;
                } else {
                    head = current.getNext();
                    size--;
                }
            } else {
                prev = current;
            }
            current = current.getNext();
        }
        isSorted = false; // The list could be not sorted after intersections
    }

    @Override
    public T getMin() {
        if (head == null) {
            return null;
        }
        T min = head.getData();
        Node<T> current = head.getNext();
        while (current != null) {
            if (current.getData().compareTo(min) < 0) {
                min = current.getData();
            }
            current = current.getNext();
        }
        return min;
    }

    @Override
    public T getMax() {
        if (head == null) {
            return null;
        }
        T max = head.getData();
        Node<T> current = head.getNext();
        while (current != null) {
            if (current.getData().compareTo(max) > 0) {
                max = current.getData();
            }
            current = current.getNext();
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
}
