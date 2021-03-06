package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (isEmpty()) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void revert() {
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next = head.next;
        while (next != null) {
            current.next = previous;
            previous = current;
            current = next;
            next = current.next;
        }
        current.next = previous;
        head = current;
    }

    public T deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public T deleteLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> beforeTail = null;
        Node<T> tail = head;
        while (tail.next != null) {
            beforeTail = tail;
            tail = tail.next;
        }
        if (beforeTail != null) {
            beforeTail.next = null;
        } else {
            head = null;
        }
        return tail.value;
    }

    boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
