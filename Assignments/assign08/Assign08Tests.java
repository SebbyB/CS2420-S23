package assign08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sebastian Barney
 * @version March 17, 2022
 */
public class Assign08Tests {

    int n = 10;

    /**
     * Tests BinarySearchTree Class.
     */
    @Nested
    @DisplayName("Binary Search Tree Tests")
    class BSTTests{

        /**
         * Tests BinarySearchTree's contains(Type) method.
         */
        @Nested
        @DisplayName("Add Tests")
        class addTests{

            /**
             * Adds integers to a BST and checks that it contains them.
             */
            @Test
            @DisplayName("Ordered List of Integers")
            void intList(){
                //Creates a testList of Integers to compare stuff to.
                BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                for(int i = 0; i < n; i++){
                    //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                    assertEquals(testTree.size(),i);
                    assertFalse(testTree.contains(i));
                    //Adds the item.
                    assertTrue(testTree.add(i));
                    //Checks that the item is in the list and that the size has changed.
                    assertTrue(testTree.contains(i));
                    assertEquals(testTree.size(),i+1);
                }
            }
            /**
             * Adds Strings to a BST and checks that it contains them.
             */
            @Test
            @DisplayName("Ordered List of Strings")
            void stringList(){
                //Creates a testList of Integers to compare stuff to.
                BinarySearchTree<String> testTree = new BinarySearchTree<>();
                for(int i = 0; i < n; i++){
                    //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                    assertEquals(testTree.size(),i);
                    assertFalse(testTree.contains(Integer.toString(i)));
                    //Adds the item.
                    assertTrue(testTree.add(Integer.toString(i)));
                    //Checks that the item is in the list and that the size has changed.
                    assertTrue(testTree.contains(Integer.toString(i)));
                    assertEquals(testTree.size(),i+1);
                }
            }

            /**
             * Adds integers to a BST and checks that it contains them.
             */
            @Test
            @DisplayName("UnOrdered List of Integers")
            void unIntList(){
                //Creates a testList of Integers to compare stuff to.
                BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                int j = 0;
                for(int i = n; i > 0; i--){
                    //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                    assertEquals(testTree.size(),j);
                    assertFalse(testTree.contains(i));
                    //Adds the item.
                    assertTrue(testTree.add(i));
                    //Checks that the item is in the list and that the size has changed.
                    assertTrue(testTree.contains(i));
                    j++;
                    assertEquals(testTree.size(),j);
                }
            }
            /**
             * Adds Strings to a BST and checks that it contains them.
             */
            @Test
            @DisplayName("UnOrdered List of Strings")
            void unStringList(){
                //Creates a testList of Integers to compare stuff to.
                BinarySearchTree<String> testTree = new BinarySearchTree<>();
                int j = 0;
                for(int i = n; i > 0; i--){
                    //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                    assertEquals(testTree.size(),j);
                    assertFalse(testTree.contains(Integer.toString(i)));
                    //Adds the item.
                    assertTrue(testTree.add(Integer.toString(i)));
                    //Checks that the item is in the list and that the size has changed.
                    assertTrue(testTree.contains(Integer.toString(i)));
                    j++;
                    assertEquals(testTree.size(),j);
                }
            }

        }

        /**
         * Tests BinarySearchTree's addAll(Collection<Type>) method.
         */
        @Nested
        @DisplayName("AddAll Tests")
        class addAllTests{

            /**
             * Tests the ArrayList Collection.
             */
            @DisplayName("ArrayLists")
            @Nested
            class ArrayListTests {
                /**
                 * Adds an entire ArrayList of Integers to a BST.
                 */
                @Test
                @DisplayName("ArrayList of Integers")
                void intArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    ArrayList<Integer> testList = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(i));
                        //Adds the item.
                        assertTrue(testList.add(i));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(i));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size());
                    //Checks that the tree contains every item in the list. 
                    for (Integer num : testList) {
                        assertTrue(testTree.contains(num));
                    }
                }

                /**
                 * Adds an entire ArrayList of Integers to a BST after adding a bunch.
                 */
                @Test
                @DisplayName("ArrayList of Integers PreExisting Tree")
                void intMiddleArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    ArrayList<Integer> testList = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(i));
                        //Adds the item.
                        assertTrue(testList.add(i));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(i));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with n items that aren't in the list.
                    int j = 0;
                    for (int i = n; i < 2*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(i));
                        //Adds the item.
                        assertTrue(testTree.add(i));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(i));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size()+n);
                    //Checks that the tree contains every item in the list. 
                    for (Integer num : testList) {
                        assertTrue(testTree.contains(num));
                    }
                    //Populates the tree with n items that aren't in the list.
                    j += n;
                    for (int i = 2*n; i < 3*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(i));
                        //Adds the item.
                        assertTrue(testTree.add(i));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(i));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Checks that the tree contains every item in the list. 
                    for (Integer num : testList) {
                        assertTrue(testTree.contains(num));
                    }
                }

                /**
                 * Adds an entire ArrayList of Strings to a BST.
                 */
                @Test
                @DisplayName("ArrayList of Strings")
                void stringArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    ArrayList<String> testList = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testList.add(Integer.toString(i)));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(Integer.toString(i)));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size());
                    //Checks that the tree contains every item in the list. 
                    for (String str : testList) {
                        assertTrue(testTree.contains(str));
                    }
                }

                /**
                 * Adds an entire ArrayList of Strings to a BST after adding a bunch.
                 */
                @Test
                @DisplayName("ArrayList of Strings PreExisting Tree")
                void stringMiddleArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    ArrayList<String> testList = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testList.add(Integer.toString(i)));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(Integer.toString(i)));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with n items that aren't in the list.
                    int j = 0;
                    for (int i = n; i < 2*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testTree.add(Integer.toString(i)));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(Integer.toString(i)));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size()+n);
                    //Checks that the tree contains every item in the list. 
                    for (String str : testList) {
                        assertTrue(testTree.contains(str));
                    }
                    //Populates the tree with n items that aren't in the list.
                    j += n;
                    for (int i = 2*n; i < 3*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testTree.add(Integer.toString(i)));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(Integer.toString(i)));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Checks that the tree contains every item in the list. 
                    for (String str : testList) {
                        assertTrue(testTree.contains(str));
                    }
                }
                
            }
            
            /**
             * Tests the Stack Collection.
             */
            @DisplayName("Stacks")
            @Nested
            class StackTests {
                /**
                 * Adds an entire Stack of Integers to a BST.
                 */
                @Test
                @DisplayName("Stack of Integers")
                void intArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    Stack<Integer> testList = new Stack<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(i));
                        //Adds the item.
                        assertTrue(testList.add(i));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(i));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size());
                    //Checks that the tree contains every item in the list. 
                    for (Integer num : testList) {
                        assertTrue(testTree.contains(num));
                    }
                }

                /**
                 * Adds an entire Stack of Integers to a BST after adding a bunch.
                 */
                @Test
                @DisplayName("Stack of Integers PreExisting Tree")
                void intMiddleArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    Stack<Integer> testList = new Stack<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(i));
                        //Adds the item.
                        assertTrue(testList.add(i));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(i));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with n items that aren't in the list.
                    int j = 0;
                    for (int i = n; i < 2*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(i));
                        //Adds the item.
                        assertTrue(testTree.add(i));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(i));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size() + n);
                    //Checks that the tree contains every item in the list. 
                    for (Integer num : testList) {
                        assertTrue(testTree.contains(num));
                    }
                    //Populates the tree with n items that aren't in the list.
                    j += n;
                    for (int i = 2*n; i < 3*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(i));
                        //Adds the item.
                        assertTrue(testTree.add(i));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(i));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Checks that the tree contains every item in the list. 
                    for (Integer num : testList) {
                        assertTrue(testTree.contains(num));
                    }
                }

                /**
                 * Adds an entire Stack of Strings to a BST.
                 */
                @Test
                @DisplayName("Stack of Strings")
                void stringArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    Stack<String> testList = new Stack<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testList.add(Integer.toString(i)));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(Integer.toString(i)));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size());
                    //Checks that the tree contains every item in the list. 
                    for (String str : testList) {
                        assertTrue(testTree.contains(str));
                    }
                }

                /**
                 * Adds an entire Stack of Strings to a BST after adding a bunch.
                 */
                @Test
                @DisplayName("Stack of Strings PreExisting Tree")
                void stringMiddleArrList() {
                    //Creates a testList of Integers to compare stuff to.
                    Stack<String> testList = new Stack<>();
                    for (int i = 0; i < n; i++) {
                        //Checks that the size of the list before adding and that it doesn't contain the item to be added.
                        assertEquals(testList.size(), i);
                        assertFalse(testList.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testList.add(Integer.toString(i)));
                        //Checks that the item is in the list and that the size has changed.
                        assertTrue(testList.contains(Integer.toString(i)));
                        assertEquals(testList.size(), i + 1);
                    }
                    //Creates a testTree to use.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    //Checks that the tree is empty before tests.
                    assertEquals(testTree.size(), 0);
                    assertTrue(testTree.isEmpty());
                    //Populates the tree with n items that aren't in the list.
                    int j = 0;
                    for (int i = n; i < 2*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testTree.add(Integer.toString(i)));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(Integer.toString(i)));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Populates the tree with the addAll Method.
                    testTree.addAll(testList);
                    //Checks that the size has changed.
                    assertFalse(testTree.isEmpty());
                    assertEquals(testTree.size(), testList.size()+n);
                    //Checks that the tree contains every item in the list. 
                    for (String str : testList) {
                        assertTrue(testTree.contains(str));
                    }
                    //Populates the tree with n items that aren't in the list.
                    j += n;
                    for (int i = 2*n; i < 3*n; i++) {
                        //Checks that the size of the Tree before adding and that it doesn't contain the item to be added.
                        assertEquals(testTree.size(), j);
                        assertFalse(testTree.contains(Integer.toString(i)));
                        //Adds the item.
                        assertTrue(testTree.add(Integer.toString(i)));
                        //Checks that the item is in the Tree and that the size has changed.
                        assertTrue(testTree.contains(Integer.toString(i)));
                        j++;
                        assertEquals(testTree.size(), j);
                    }
                    //Checks that the tree contains every item in the list. 
                    for (String str : testList) {
                        assertTrue(testTree.contains(str));
                    }
                }

            }
            
            
        }

        /**
         * Tests BinarySearchTree's contains(Type) method.
         */
        @Nested
        @DisplayName("Contains Tests")
        class containsTests{

            /**
             * Tests that the contains method works on Integers.
             */
            @DisplayName("Integers")
            @Test
            void ints(){
                //Creates a testTree
                BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                for (int i = n*-1; i < n; i++){
                    //Checks that the item isn't in the tree.
                    assertFalse(testTree.contains(i));
                    //Adds it to the list.
                    testTree.add(i);
                    //Checks that the item is in the tree.
                    assertTrue(testTree.contains(i));
                }
            }
            /**
             * Tests that the contains method works on Strings.
             */
            @DisplayName("Strings")
            @Test
            void stgs(){
                //Creates a testTree
                BinarySearchTree<String> testTree = new BinarySearchTree<>();
                for (int i = n*-1; i < n; i++){
                    //Checks that the item isn't in the tree.
                    assertFalse(testTree.contains(Integer.toString(i)));
                    //Adds it to the list.
                    testTree.add(Integer.toString(i));
                    //Checks that the item is in the tree.
                    assertTrue(testTree.contains(Integer.toString(i)));
                }
            }
            
        }

        /**
         * Tests BinarySearchTree's containsAll(Collection<Type>) method.
         */
        @Nested
        @DisplayName("ContainsAll Tests")
        class containsAllTests{

            /**
             * Tests that the ContainsAll(Collection<Type>) method for a BST works with Primitives.
             */
           @Nested
            @DisplayName("Integers")
            class contAllInts{

                /**
                 * Checks that the method works with an Array List
                 */
                @DisplayName("ArrayLists")
                @Test
                void ArrayListTest(){
                   
                    //Creates a testTree and populates it with a bunch of items.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                    }
                    //Creates a testList and populates it with a bunch of items.
                    ArrayList<Integer> testList = new ArrayList<>();
                    for (int i = n; i < 2*n; i++){
                        testList.add(i);
                    }
                    //Tests that the list is not in the tree.
                    assertFalse(testTree.containsAll(testList));
                    //Adds them to the tree.
                    testTree.addAll(testList);
                    //Tests that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Adds a bunch more items to the tree.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(i);
                    }
                    //Tests that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
               }

                /**
                 * Checks that the method works with a Linked List
                 */
                @DisplayName("LinkedLists")
                @Test
                void LinkedListTest(){

                    //Creates a testTree and populates it with a bunch of items.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                    }
                    //Creates a testList and populates it with a bunch of items.
                    LinkedList<Integer> testList = new LinkedList<>();
                    for (int i = n; i < 2*n; i++){
                        testList.add(i);
                    }
                    //Tests that the list is not in the tree.
                    assertFalse(testTree.containsAll(testList));
                    //Adds them to the tree.
                    testTree.addAll(testList);
                    //Tests that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Adds a bunch more items to the tree.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(i);
                    }
                    //Tests that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                }

                /**
                 * Checks that the method works with a Stack
                 */
                @DisplayName("Stacks")
                @Test
                void StackTest(){

                    //Creates a testTree and populates it with a bunch of items.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                    }
                    //Creates a testList and populates it with a bunch of items.
                    Stack<Integer> testList = new Stack<>();
                    for (int i = n; i < 2*n; i++){
                        testList.add(i);
                    }
                    //Tests that the list is not in the tree.
                    assertFalse(testTree.containsAll(testList));
                    //Adds them to the tree.
                    testTree.addAll(testList);
                    //Tests that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Adds a bunch more items to the tree.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(i);
                    }
                    //Tests that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                }
           }

            /**
             * Tests that the ContainsAll(Collection<Type>) method for a BST works with Strings and other objects.
             */
            @Nested
            @DisplayName("Integers")
            class contAllString{

                /**
                 * Checks that the method works with an Array List
                 */
                @DisplayName("ArrayLists")
                @Test
                void ArrayListTest(){

                    //Creates a testTree and populates it with a bunch of items.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Creates a testList and populates it with a bunch of items.
                    ArrayList<String> testList = new ArrayList<>();
                    for (int i = n; i < 2*n; i++){
                        testList.add(Integer.toString(i));
                    }
                    //Tests that the list is not in the tree.
                    assertFalse(testTree.containsAll(testList));
                    //Adds them to the tree.
                    testTree.addAll(testList);
                    //Tests that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Adds a bunch more items to the tree.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Tests that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                }

                /**
                 * Checks that the method works with a Linked List
                 */
                @DisplayName("LinkedLists")
                @Test
                void LinkedListTest(){

                    //Creates a testTree and populates it with a bunch of items.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Creates a testList and populates it with a bunch of items.
                    LinkedList<String> testList = new LinkedList<>();
                    for (int i = n; i < 2*n; i++){
                        testList.add(Integer.toString(i));
                    }
                    //Tests that the list is not in the tree.
                    assertFalse(testTree.containsAll(testList));
                    //Adds them to the tree.
                    testTree.addAll(testList);
                    //Tests that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Adds a bunch more items to the tree.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Tests that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                }

                /**
                 * Checks that the method works with a Stack
                 */
                @DisplayName("Stacks")
                @Test
                void StackTest(){

                    //Creates a testTree and populates it with a bunch of items.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Creates a testList and populates it with a bunch of items.
                    Stack<String> testList = new Stack<>();
                    for (int i = n; i < 2*n; i++){
                        testList.add(Integer.toString(i));
                    }
                    //Tests that the list is not in the tree.
                    assertFalse(testTree.containsAll(testList));
                    //Adds them to the tree.
                    testTree.addAll(testList);
                    //Tests that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Adds a bunch more items to the tree.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Tests that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                }
            }
        }

        /**
         * Tests BinarySearchTree's remove(Type) method.
         */
        @Nested
        @DisplayName("Remove Tests")
        class rem{

            /**
             * Shows remove works for primitives
             */
            @DisplayName("Ints")
            @Test
            void remInts(){

                //Creates a testTree and populates it with a bunch of items.
                BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                for (int i = 0; i < n; i++){
                    testTree.add(i);
                }
                //Tests that the tree is populated.
                assertFalse(testTree.isEmpty());
                int j = n;
                for (int i = 0; i < n; i++){
                    //Tests that the item is in the tree, tests the size
                    assertTrue(testTree.contains(i));
                    assertEquals(testTree.size(),j);
                    //Removes it.
                    assertTrue(testTree.remove(i));
                    //Tests that the item is no longer in the tree and the size has changed.
                    j--;
                    assertEquals(testTree.size(),j);
                    assertFalse(testTree.contains(i));
                }
                assertTrue(testTree.isEmpty());
            }

            /**
             * Shows remove works for objects and strings.
             */
            @DisplayName("Strings")
            @Test
            void remStgs(){

                //Creates a testTree and populates it with a bunch of items.
                BinarySearchTree<String> testTree = new BinarySearchTree<>();
                for (int i = 0; i < n; i++){
                    testTree.add(Integer.toString(i));
                }
                //Tests that the tree is populated.
                assertFalse(testTree.isEmpty());
                int j = n;
                for (int i = 0; i < n; i++){
                    //Tests that the item is in the tree, tests the size
                    assertTrue(testTree.contains(Integer.toString(i)));
                    assertEquals(testTree.size(),j);
                    //Removes it.
                    assertTrue(testTree.remove(Integer.toString(i)));
                    //Tests that the item is no longer in the tree and the size has changed.
                    j--;
                    assertEquals(testTree.size(),j);
                    assertFalse(testTree.contains(Integer.toString(i)));
                }
                assertTrue(testTree.isEmpty());
            }
        }

        /**
         * Tests BinarySearchTree's removeAll(Collection<Type>) method.
         */
        @Nested
        @DisplayName("RemoveAll Tests")
        class remAll{
            
            @Nested
            @DisplayName("Primitives")
            class inties{
                
                @DisplayName("ArrayLists")
                @Test
                void arraylists(){
                    
                    //Creates a test Tree and populates it with items.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                    }
                    //Creates a test List and populates it with items, then adds it to the tree.
                    ArrayList<Integer> testList = new ArrayList<>();
                    for (int i = n; i < 2*n; i++){
                        testTree.add(i);
                    }
                    testTree.addAll(testList);
                    //shows that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Populates the tree with more stuff.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(i);
                    }
                    //shows that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Removes the list from the tree.
                    assertTrue(testTree.removeAll(testList));
                    //Shows that the list is no longer in the tree.
                    assertFalse(testTree.containsAll(testList));
                }

                @DisplayName("LinkedLists")
                @Test
                void linkedlists(){

                    //Creates a test Tree and populates it with items.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                    }
                    //Creates a test List and populates it with items, then adds it to the tree.
                    LinkedList<Integer> testList = new LinkedList<>();
                    for (int i = n; i < 2*n; i++){
                        testTree.add(i);
                    }
                    testTree.addAll(testList);
                    //shows that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Populates the tree with more stuff.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(i);
                    }
                    //shows that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Removes the list from the tree.
                    assertTrue(testTree.removeAll(testList));
                    //Shows that the list is no longer in the tree.
                    assertFalse(testTree.containsAll(testList));
                }

                @DisplayName("Stacks")
                @Test
                void stackos(){

                    //Creates a test Tree and populates it with items.
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                    }
                    //Creates a test List and populates it with items, then adds it to the tree.
                    Stack<Integer> testList = new Stack<>();
                    for (int i = n; i < 2*n; i++){
                        testTree.add(i);
                    }
                    testTree.addAll(testList);
                    //shows that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Populates the tree with more stuff.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(i);
                    }
                    //shows that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Removes the list from the tree.
                    assertTrue(testTree.removeAll(testList));
                    //Shows that the list is no longer in the tree.
                    assertFalse(testTree.containsAll(testList));
                }
            }

            @Nested
            @DisplayName("Non-Primitives")
            class objays{

                @DisplayName("ArrayLists")
                @Test
                void arraylists(){

                    //Creates a test Tree and populates it with items.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Creates a test List and populates it with items, then adds it to the tree.
                    ArrayList<String> testList = new ArrayList<>();
                    for (int i = n; i < 2*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    testTree.addAll(testList);
                    //shows that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Populates the tree with more stuff.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //shows that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Removes the list from the tree.
                    assertTrue(testTree.removeAll(testList));
                    //Shows that the list is no longer in the tree.
                    assertFalse(testTree.containsAll(testList));
                }

                @DisplayName("LinkedLists")
                @Test
                void linkedlists(){

                    //Creates a test Tree and populates it with items.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Creates a test List and populates it with items, then adds it to the tree.
                    LinkedList<String> testList = new LinkedList<>();
                    for (int i = n; i < 2*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    testTree.addAll(testList);
                    //shows that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Populates the tree with more stuff.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //shows that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Removes the list from the tree.
                    assertTrue(testTree.removeAll(testList));
                    //Shows that the list is no longer in the tree.
                    assertFalse(testTree.containsAll(testList));
                }

                @DisplayName("Stacks")
                @Test
                void stackos(){

                    //Creates a test Tree and populates it with items.
                    BinarySearchTree<String> testTree = new BinarySearchTree<>();
                    for (int i = 0; i < n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //Creates a test List and populates it with items, then adds it to the tree.
                    Stack<String> testList = new Stack<>();
                    for (int i = n; i < 2*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    testTree.addAll(testList);
                    //shows that the list is in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Populates the tree with more stuff.
                    for (int i = 2*n; i < 3*n; i++){
                        testTree.add(Integer.toString(i));
                    }
                    //shows that the list is still in the tree.
                    assertTrue(testTree.containsAll(testList));
                    //Removes the list from the tree.
                    assertTrue(testTree.removeAll(testList));
                    //Shows that the list is no longer in the tree.
                    assertFalse(testTree.containsAll(testList));
                }
            }
        }

        /**
         * Tests BinarySearchTree's first() and last() methods.
         */
        @Nested
        @DisplayName("First and Last")
        class fnl{
            @Nested
            @DisplayName("First")
            class fst{

                @DisplayName("First Changes")
                @Test
                void fc(){

                    //Creates a testTree, shows its empty, shows first throws...
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    assertTrue(testTree.isEmpty());
                    assertThrows(NoSuchElementException.class, testTree::first);
                    for (int i = n; i > 0; i--){
                        testTree.add(i);
                        assertDoesNotThrow(testTree::first);
                        assertEquals(testTree.first(),i);
                    }
                }

                @DisplayName("First Doesn't change")
                @Test
                void fnc(){

                    //Creates a testTree, shows its empty, shows first throws...
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    assertTrue(testTree.isEmpty());
                    assertThrows(NoSuchElementException.class, testTree::first);
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                        assertDoesNotThrow(testTree::first);
                        assertEquals(testTree.first(),0);
                    }
                }
            }

            @Nested
            @DisplayName("Last")
            class lst{

                @DisplayName("Last Changes")
                @Test
                void lc(){

                    //Creates a testTree, shows its empty, shows first throws...
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    assertTrue(testTree.isEmpty());
                    assertThrows(NoSuchElementException.class, testTree::last);
                    for (int i = 0; i < n; i++){
                        testTree.add(i);
                        assertDoesNotThrow(testTree::last);
                        assertEquals(testTree.last(),i);
                    }
                }

                @DisplayName("Last Doesn't change")
                @Test
                void lnc(){

                    //Creates a testTree, shows its empty, shows first throws...
                    BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                    assertTrue(testTree.isEmpty());
                    assertThrows(NoSuchElementException.class, testTree::last);
                    for (int i = n; i > 0; i--){
                        testTree.add(i);
                        assertDoesNotThrow(testTree::last);
                        assertEquals(testTree.last(),n);
                    }
                }
            }
        }

        /**
         * Tests BinarySearchTree's size() and isEmpty() methods.
         */
        @Nested
        @DisplayName("Size and Empty")
        class snl{

            @DisplayName("Is Empty")
            @Test
            void em_tee(){
                //Shows that an empty tree is definitely empty.
                assertTrue(new BinarySearchTree<Integer>().isEmpty());
            }
            @DisplayName("Size changes")
            @Test
            void sizey(){

                //Creates an empty tree.
                BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
                assertTrue(testTree.isEmpty());
                for(int i = 0; i < n; i++){
                    //checks the size then adds the item to the tree.
                    assertEquals(testTree.size(), i);
                    testTree.add(i);
                    //shows that the size has changed.
                    assertEquals(testTree.size(), i+1);
                    //Show that the tree is no longer empty.
                    assertFalse(testTree.isEmpty());
                }
                for (int i = n-1; i >=0; i--){
                    //checks the size then removes the item from the tree.
                    assertEquals(testTree.size(), i+1);
                    testTree.remove(i);
                    //shows that the size has changed.
                    assertEquals(testTree.size(), i);
                }
                //Shows that the tree is empty now.
                assertTrue(testTree.isEmpty());

            }
        }

        /**
         * Tests BinarySearchTree's toArrayList() method.
         */
        @DisplayName("ToArrayList")
        @Test
        void tolist(){

            //Creates a testTree that gets populated with a List.
            BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
            ArrayList<Integer> testList = new ArrayList<>(), checklist;
            for(int i = 0; i < n; i++){
                testList.add(i);
            }
            testTree.addAll(testList);
            //Makes the tree into a list.
            checklist = testTree.toArrayList();
            assertEquals(testList, checklist);
        }
    }



}
