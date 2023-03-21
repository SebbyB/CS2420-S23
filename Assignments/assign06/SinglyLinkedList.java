package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>, Iterable<E> {


    private Node head;
    private int size;

    /**
     * Utility class for the linked list.
     */
    private class Node{

        private E __data__;
        private Node __next__;
        Node(E data){
            setData(data);
            __next__ = null;
        }

        Node(E data,Node next){
            setData(data);
            setNext(next);
        }
        void setData(E data){
            __data__ = data;
        }

        void setNext(Node next){
            __next__ = next;
        }

        E getData(){
            return __data__;
        }

        Node next(){
            return __next__;
        }

    }

    /**
     * Default Constructor
     */
    public SinglyLinkedList(){
        size = 0;
        head = null;
    }

    /**
     * Constructor that uses a singly Linked List
     * @param list - list to be used
     */
    public SinglyLinkedList(SinglyLinkedList<E> list){
        size = list.size;
        head = list.head;
    }

    /**
     * Constructor that uses a stack
     * @param list stack to be used.
     */
    public SinglyLinkedList(LinkedListStack<E> list){
        size = list.backingList.size();
        head = list.backingList.head;
    }

    /**
     * Inserts something to the front of the Linked List
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        if (isEmpty()) {
            head = new Node(element);
        } else {
            Node newNode = new Node(element);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }


    /**
     * Inserts something at a index in the list.
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException - self explanitory.
     */
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Too far");
        }

        if (index == 0) {
            insertFirst(element);
            return;
        }

        Node n = head;
        for (int i = 0; i < index - 1; i++) {
            n = n.next();
        }

        Node newNode = new Node(element);
        newNode.setNext(n.next());
        n.setNext(newNode);
        size++;
    }


    /**
     * Gets the first item in the list
     * @return first item in the list.
     * @throws NoSuchElementException self explanatory
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException();}
        return head.getData();
    }

    /**
     * Gets the item at an index
     * @param index - the specified position
     * @return item
     * @throws IndexOutOfBoundsException self explanatory
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Too far.");
        }
        if (isEmpty()) throw new NoSuchElementException();


        Node n = head;
        if(index == 0){
            return head.getData();
        }
        for(int i = 0; i < index-1; i++){
            n = n.next();
        }

        if (n.next() == null){
            return null;
        }else {
        return n.next().getData();}
    }

    /**
     * Deletes the first item in the list
     * @return first item in the list.
     * @throws NoSuchElementException if there isn't anything in the list.
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException("you gotta stop doin this man... you do this every time.");
        }

        size--;
        E data = head.getData();
//        if (size == 1){
//            clear();
//            return data;}
        Node next = head.next();
        head = next;
        return data;
    }


    /**
     * Deletes the item at an index
     * @param index - the specified position
     * @return item deleted
     * @throws IndexOutOfBoundsException self explanatory
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (isEmpty()) throw new NoSuchElementException();

        E deletedValue;

        if (index == 0) {
            deletedValue = head.getData();
            head = head.next();
        } else {
            Node prev = head;
            Node curr = head.next();
            for (int i = 0; i < index-1; i++) {
                prev = curr;
                curr = curr.next();
            }
            deletedValue = curr.getData();
            prev.setNext(curr.next());
            if (curr == head) {
                head = prev;
            }
        }
        size--;
        return deletedValue;
    }

    /**
     * Contains an element.
     * @param element - element
     * @return true if its in the list, false otherwise.
     */
    public boolean contains(E element){
        Node n = head;
        while (n != null){
            if(n.getData().equals(element)) return true;
            n = n.next();
        }
        return false;
    }

    /**
     * Returns the index of an element in the list.
     * @param element - the element to search for
     * @return index of the element, -1 if it isn't in the list.
     */
    @Override
    public int indexOf(E element) {
        Node n = head;
        for (int i = 0; i < size; i++){
            if(n.getData().equals(element)) return i;
        }
        return -1;
    }

    /**
     * Size of the list.
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Empty status of the list.
     * @return true if empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     */
    @Override
    public void clear() {

        size = 0;
        head = null;
    }

    /**
     * Gives an array representation of the linked list.
     * @return array of elements from the list.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        Object[] arr = (E[])new Object[size];
        Node n = head;
        for (int i = 0; i < size; i++) {
            arr[i] = n.getData();
            n = n.next();
        }
        return (E[])arr;
    }

    /**
     * Iterator
     * @return new iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    /**
     * Iterator class.
     */
    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node current;
        private Node previous;
        private boolean canRemove;

        public SinglyLinkedListIterator() {
            current = head;
            previous = null;
            canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.getData();
            previous = current;
            current = current.next();
            canRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Cannot call remove() twice in a row");
            }
            if (current == null) {
                throw new IllegalStateException("Iterator out of sync");
            }
            if (previous == null) {
                head = current.next();
            } else {
                previous.setNext(current.next());
            }
            current = previous;
            canRemove = false;
            size--;
        }
    }


    }






