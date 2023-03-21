package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack <E> implements Stack<E>{


    SinglyLinkedList<E> backingList;

    /**
     * Default constructor.
     */
    public LinkedListStack(){
        backingList = new SinglyLinkedList<>();
    }

    /**
     * Constructor that uses a singly linked list
     * @param list list
     */
    public LinkedListStack(SinglyLinkedList<E> list){
        backingList = list;
    }

    /**
     * Clears the stack
     */
    @Override
    public void clear() {
        backingList.clear();
    }

    /**
     * Checks if the stack is empty.
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return backingList.isEmpty();
    }

    /**
     * Shows first item in stack
     * @return - first item
     * @throws NoSuchElementException - if theres nothing in the stack
     */
    @Override
    public E peek() throws NoSuchElementException {
        return backingList.getFirst();
    }

    /**
     * Shows first item in stack, and removes it.
     * @return - first item.
     * @throws NoSuchElementException 0 if theres nothing in the stack
     */
    @Override
    public E pop() throws NoSuchElementException {
        return backingList.deleteFirst();
    }

    /**
     * puts something on the top of the stack
     * @param element - the element to be added
     */
    @Override
    public void push(E element) {

        backingList.insertFirst(element);
    }

    /**
     * Size of stack
     * @return - size
     */
    @Override
    public int size() {
        return backingList.size();
    }
}
