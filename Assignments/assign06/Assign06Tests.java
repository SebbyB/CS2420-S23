package assign06;

import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

public class Assign06Tests {

    int n = 10000;

    @Nested
    @DisplayName("SinglyLinkedList Class Tests")
    class SinglyLinkedMethods {


        /**
         * Tests that the constructors for the SinglyLinkedList class work as intended.
         */
        @Nested
        @DisplayName("Constructor Tests")
        class sllconstructors {

            @DisplayName("Default Constructor Test")
            @Test
            void emptyConstructor() {
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<Integer> intTest = new SinglyLinkedList<>();
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<String> stringTest = new SinglyLinkedList<>();
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<Object> objTest = new SinglyLinkedList<>();
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<URL> URLTest = new SinglyLinkedList<>();
                });

                SinglyLinkedList<Integer> intTest = new SinglyLinkedList<>();
                assertTrue(intTest.isEmpty());
            }

            @DisplayName("LinkedList Constructor Test")
            @Test
            void LinkedConstructor() {


                SinglyLinkedList<Integer> intTest = new SinglyLinkedList<>();
                SinglyLinkedList<String> stringTest = new SinglyLinkedList<>();
                SinglyLinkedList<Object> ObjTest = new SinglyLinkedList<>();
                SinglyLinkedList<URL> URLTest = new SinglyLinkedList<>();

                intTest.insertFirst(1);
                stringTest.insertFirst("hi");
                ObjTest.insertFirst(new Object());
                try {
                    URLTest.insertFirst(new URL("https://utah.instructure.com/courses/835657/assignments/11773080?module_item_id=19609851"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<Integer> i = new SinglyLinkedList<>(intTest);
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<String> s = new SinglyLinkedList<>(stringTest);
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<Object> o = new SinglyLinkedList<>(ObjTest);
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<URL> u = new SinglyLinkedList<>(URLTest);
                });


                SinglyLinkedList<Integer> i = new SinglyLinkedList<>(intTest);
                SinglyLinkedList<String> s = new SinglyLinkedList<>(stringTest);
                SinglyLinkedList<Object> o = new SinglyLinkedList<>(ObjTest);
                SinglyLinkedList<URL> u = new SinglyLinkedList<>(URLTest);
                assertEquals(i.getFirst(), intTest.getFirst());
                assertEquals(s.getFirst(), stringTest.getFirst());
                assertEquals(o.getFirst(), ObjTest.getFirst());
                assertEquals(u.getFirst(), URLTest.getFirst());


            }

            @DisplayName("LinkedListStack Constructor Test")
            @Test
            void llstackConstructor() {


                LinkedListStack<Integer> intTest = new LinkedListStack<>();
                LinkedListStack<String> stringTest = new LinkedListStack<>();
                LinkedListStack<Object> ObjTest = new LinkedListStack<>();
                LinkedListStack<URL> URLTest = new LinkedListStack<>();

                intTest.push(1);
                stringTest.push("hi");
                ObjTest.push(new Object());
                try {
                    URLTest.push(new URL("https://utah.instructure.com/courses/835657/assignments/11773080?module_item_id=19609851"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<Integer> i = new SinglyLinkedList<>(intTest);
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<String> s = new SinglyLinkedList<>(stringTest);
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<Object> o = new SinglyLinkedList<>(ObjTest);
                });
                assertDoesNotThrow(() -> {
                    SinglyLinkedList<URL> u = new SinglyLinkedList<>(URLTest);
                });


                SinglyLinkedList<Integer> i = new SinglyLinkedList<>(intTest);
                SinglyLinkedList<String> s = new SinglyLinkedList<>(stringTest);
                SinglyLinkedList<Object> o = new SinglyLinkedList<>(ObjTest);
                SinglyLinkedList<URL> u = new SinglyLinkedList<>(URLTest);
                assertEquals(i.getFirst(), intTest.peek());
                assertEquals(s.getFirst(), stringTest.peek());
                assertEquals(o.getFirst(), ObjTest.peek());
                assertEquals(u.getFirst(), URLTest.peek());


            }
        }

        /**
         * Tests that the insertFirst() method works as intended for the SinglyLinkedList class.
         */
        @Nested
        @DisplayName("InsertFirst Tests")
        class IF {
            @DisplayName("Integers")
            @Test
            void insertNInts() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    int inserting = i;
                    assertDoesNotThrow(() -> {
                        testList.insertFirst(inserting);
                    });
                    assertEquals(testList.getFirst(), i);
                    assertEquals(testList.size(), i + 1);
                }
            }

            @DisplayName("Strings")
            @Test
            void insertNStrings() {
                SinglyLinkedList<String> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    String inserting = Integer.toString(i);
                    assertDoesNotThrow(() -> {
                        testList.insertFirst(inserting);
                    });
                    assertEquals(testList.getFirst(), Integer.toString(i));
                    assertEquals(testList.size(), i + 1);
                }
            }

            @DisplayName("URLS")
            @Test
            void insertNURLS() {
                SinglyLinkedList<URL> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    try {
                        URL inserting = new URL("https://utah.instructure.com/courses/835657/assignments/11773080?module_item_id=19609851" + Integer.toString(i));
                        assertDoesNotThrow(() -> {
                            testList.insertFirst(inserting);
                        });
                        assertEquals(testList.size(), i + 1);
                        assertEquals(testList.getFirst(), inserting);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }

        /**
         * Tests that the insert() method works as intended for the SinglyLinkedList class.
         */
        @Nested
        @DisplayName("Insert Tests")
        class INS {

            @DisplayName("Insert Index")
            @Test
            void insertAtIndex() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) testList.insertFirst(i);
                assertNotEquals(testList.get(3), 420);
                assertEquals(testList.size(), n);
                assertDoesNotThrow(() -> testList.insert(3, 420));
                assertEquals(testList.get(3), 420);
                assertEquals(testList.size(), n + 1);
            }

            @DisplayName("Insert Throws")
            @Test
            void insertThrowsIndex() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) testList.insertFirst(i);
                assertNotEquals(testList.get(3), 420);
                assertEquals(testList.size(), n);
                assertDoesNotThrow(() -> testList.insert(3, 420));
                assertEquals(testList.get(3), 420);
                assertEquals(testList.size(), n + 1);
                assertThrows(IndexOutOfBoundsException.class, () -> testList.insert(n + 2, 3));
            }

            @DisplayName("Insert 0")
            @Test
            void insert0Index() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                testList.insert(0, 1);
                assertEquals(testList.getFirst(), 1);
                assertEquals(testList.size(), 1);
                testList.insert(0, 2);
                assertEquals(testList.getFirst(), 2);
                assertEquals(testList.size(), 2);
                testList.insert(0, 3);
                assertEquals(testList.getFirst(), 3);
                assertEquals(testList.size(), 3);
                testList.insert(0, 4);
                assertEquals(testList.getFirst(), 4);
                assertEquals(testList.size(), 4);
            }


        }

        /**
         * Tests that the getFirst() method works as intended for the SinglyLinkedList class.
         */
        @Nested
        @DisplayName("GetFirst Tests")
        class GF {
            @DisplayName("GetFirst N Items")
            @Test
            void getFirstListN() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    testList.insertFirst(i);
                    assertEquals(testList.getFirst(), i);
                }
            }

            @DisplayName("GetFirst N Empty")
            @Test
            void gfthrows() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    assertThrows(NoSuchElementException.class, testList::getFirst);
                }
            }
        }


        /**
         * Tests that the get() method works as intended for the SinglyLinkedList class.
         */
        @Nested
        @DisplayName("Get Tests")
        class GET {

            @DisplayName("Get Index 0")
            @Test
            void get0() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();

                for (int i = 0; i < n; i++) {
                    testList.insert(i, i);
                    assertEquals(i + 1, testList.size());
                    int testVal = testList.get(i);
                    assertEquals(testVal, i);
                }
                assertEquals(testList.get(0), testList.getFirst());
            }

            @DisplayName("Get N Items")
            @Test
            void getListN() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();

                for (int i = 0; i < n; i++) {
                    testList.insert(i, i);
                    assertEquals(i + 1, testList.size());
                    int testVal = testList.get(i);
                    assertEquals(testVal, i);
                }
            }

            @DisplayName("Get N Empty")
            @Test
            void gEthrows() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    assertThrows(NoSuchElementException.class, testList::getFirst);
                }
            }

            @DisplayName("Get N Too Far")
            @Test
            void gthrows() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    testList.insertFirst(i);
                    int ins = i;
                    assertEquals(i + 1, testList.size());
                    assertThrows(IndexOutOfBoundsException.class, () -> testList.get(ins + 14));
                    assertThrows(IndexOutOfBoundsException.class, () -> testList.get(-1));

                }
            }
        }

        /**
         * Tests that the deleteFirst() method works as intended for the SinglyLinkedList class.
         */
        @Nested
        @DisplayName("DeleteFirst Tests")
        class DELFIRST {
            @DisplayName("Delete First N Items")
            @Test
            void delFirstListN() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    testList.insertFirst(i);
                    assertEquals(testList.getFirst(), i);
                    assertEquals(testList.size(), 1);
                    assertFalse(testList.isEmpty());
                    assertEquals(testList.getFirst(), testList.deleteFirst());
                    assertEquals(testList.size(), 0);
                    assertTrue(testList.isEmpty());

                }

            }

            @DisplayName("Del First N Empty")
            @Test
            void dfthrows() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    assertThrows(NoSuchElementException.class, testList::deleteFirst);
                }
            }
        }

        /**
         * Tests that the delete() method works as intended for the SinglyLinkedList class.
         */
        @Nested
        @DisplayName("Del Tests")
        class DEL {
            @DisplayName("Del Index 0")
            @Test
            void get0() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();

                for (int i = 0; i < n; i++) {
                    testList.insert(i, i);
                    assertEquals(i + 1, testList.size());
                    int testVal = testList.get(i);
                    assertEquals(testVal, i);
                }
                assertEquals(testList.size(), n);
                assertEquals(testList.getFirst(), testList.delete(0));
                assertEquals(testList.size(), n - 1);


            }

            @DisplayName("Del N Items")
            @Test
            void delListN() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();

                for (int i = 0; i < n; i++) {
                    testList.insert(i, i);
                    assertFalse(testList.isEmpty());
                }
                for (int j = n - 2; j >= 0; j--) {
                    assertTrue(testList.contains(j));
                    int testVal = testList.get(j);
                    int expval = testList.delete(j);
                    assertEquals(testVal, expval);
                    assertFalse(testList.contains(j));
                }


            }

            @DisplayName("Del N Empty")
            @Test
            void DEthrows() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    assertThrows(IndexOutOfBoundsException.class, () -> testList.delete(3));
                }
            }

            @DisplayName("Get N Too Far")
            @Test
            void delthrows() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 0; i < n; i++) {
                    testList.insertFirst(i);
                    int ins = i;
                    assertEquals(i + 1, testList.size());
                    assertThrows(IndexOutOfBoundsException.class, () -> testList.delete(ins + 14));
                    assertThrows(IndexOutOfBoundsException.class, () -> testList.delete(-1));
                }
            }
        }

        @Nested
        @DisplayName("Contains and IndexOf Tests")
        class contains {
            @DisplayName("Does Contain")
            @Test
            void contain() {

                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 1; i <= n; i++) {
                    assertFalse(testList.contains(i));
                    testList.insertFirst(i);
                    assertTrue(testList.contains(i));
                }

                for (int i = n; i > 0; i--) {
//                    System.out.println(Arrays.toString(testList.toArray()));
                    assertTrue(testList.contains(i));
                    testList.deleteFirst();
//                    System.out.println(testList.size());
                    assertFalse(testList.contains(i));
                }
            }

            @DisplayName("index")
            @Test
            void index() {

                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                for (int i = 1; i <= n; i++) {
                    assertEquals(-1, testList.indexOf(i));
                    testList.insertFirst(i);
                    assertNotEquals(-1, testList.indexOf(i));
                }

                for (int i = n; i > 0; i--) {
//                    System.out.println(Arrays.toString(testList.toArray()));
                    assertNotEquals(testList.indexOf(i), -1);
                    testList.deleteFirst();
//                    System.out.println(testList.size());
                    assertEquals(testList.indexOf(i), -1);
                }
            }
        }

        @Nested
        @DisplayName("Size, Clear,  and Empty Tests")
        class sizeEmpty {
            @DisplayName("Changing Size")
            @Test
            void size() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();

                for (int i = 0; i < n; i++) {
                    assertEquals(testList.size(), i);
                    testList.insertFirst(i);
                    assertEquals(testList.size(), i + 1);
                }
                testList.deleteFirst();
                assertEquals(testList.size(), n - 1);
            }

            @DisplayName("Cleared List and Empty")
            @Test
            void clear() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();

                assertTrue(testList.isEmpty());
                for (int i = 0; i < n; i++) {
                    assertEquals(testList.size(), i);
                    testList.insertFirst(i);
                    assertEquals(testList.size(), i + 1);
                }
                assertFalse(testList.isEmpty());
                testList.clear();
                assertTrue(testList.isEmpty());

            }
        }

        @Nested
        @DisplayName("To Array")
        class ToArray {
            @DisplayName("To Array")
            @Test
            void toarr() {
                SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
                Integer[] arr = new Integer[n];
                assertTrue(testList.isEmpty());
                for (int i = 0; i < n; i++) {
                    assertEquals(testList.size(), i);
                    testList.insert(i, i);
                    assertEquals(testList.size(), i + 1);
                    arr[i] = i;
                }
                assertArrayEquals(arr, testList.toArray());

            }

        }


    }

    @Nested
    @DisplayName("LinkedListStack Class Tests")
    class LinkedStackMethods {


        @Nested
        @DisplayName("Constructors")
        class constructors {


            @DisplayName("Default Constructor Test")
            @Test
            void emptyConstructor() {
                assertDoesNotThrow(() -> {
                    LinkedListStack<Integer> intTest = new LinkedListStack<>();
                });
                assertDoesNotThrow(() -> {
                    LinkedListStack<String> stringTest = new LinkedListStack<>();
                });
                assertDoesNotThrow(() -> {
                    LinkedListStack<Object> objTest = new LinkedListStack<>();
                });
                assertDoesNotThrow(() -> {
                    LinkedListStack<URL> URLTest = new LinkedListStack<>();
                });

                LinkedListStack<Integer> intTest = new LinkedListStack<>();
                assertTrue(intTest.isEmpty());
            }

            @DisplayName("LinkedList Constructor Test")
            @Test
            void LinkedConstructor() {
                SinglyLinkedList<Integer> intList = new SinglyLinkedList<>();
                SinglyLinkedList<String> stringList = new SinglyLinkedList<>();
                SinglyLinkedList<Object> objList = new SinglyLinkedList<>();
                SinglyLinkedList<URL> URLList = new SinglyLinkedList<>();

                for (int i = 1; i <= n; i++) {
                    intList.insertFirst(i);
                    stringList.insertFirst(Integer.toString(i));
                    objList.insertFirst(new Object());
                    try {
                        URLList.insertFirst(new URL("http://www.example.com/" + i));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }

                assertDoesNotThrow(() -> {
                    LinkedListStack<Integer> i = new LinkedListStack<>(intList);
                });
                assertDoesNotThrow(() -> {
                    LinkedListStack<String> s = new LinkedListStack<>(stringList);
                });
                assertDoesNotThrow(() -> {
                    LinkedListStack<Object> o = new LinkedListStack<>(objList);
                });
                assertDoesNotThrow(() -> {
                    LinkedListStack<URL> u = new LinkedListStack<>(URLList);
                });

                LinkedListStack<Integer> i = new LinkedListStack<>(intList);
                LinkedListStack<String> s = new LinkedListStack<>(stringList);
                LinkedListStack<Object> o = new LinkedListStack<>(objList);
                LinkedListStack<URL> u = new LinkedListStack<>(URLList);

                assertEquals(n, i.size());
                assertEquals(n, s.size());
                assertEquals(n, o.size());
                assertEquals(n, u.size());

                assertEquals(n, i.peek());
                assertNotNull(o.peek());
                assertEquals("http://www.example.com/" + Integer.toString(n), u.peek().toString());
            }
        }


        @Test
        void clear() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            for (int i = 0; i < n; i++) {
                stack.push(i);
            }
            assertFalse(stack.isEmpty());
            stack.clear();
            assertTrue(stack.isEmpty());
        }

        @Test
        void isEmpty() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            assertTrue(stack.isEmpty());
            stack.push(42);
            assertFalse(stack.isEmpty());
        }

        @Test
        void peek() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            for (int i = 0; i < n; i++) {
                stack.push(i);
                assertEquals(i, stack.peek());
            }
            assertEquals(n - 1, stack.peek());
        }

        @Test
        void peekEmpty() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            assertThrows(NoSuchElementException.class, stack::peek);
        }

        @Test
        void pop() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            for (int i = 0; i < n; i++) {
                stack.push(i);
            }
            for (int i = n - 1; i >= 0; i--) {
                assertEquals(i, stack.pop());
            }
            assertTrue(stack.isEmpty());
        }

        @Test
        void popEmpty() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            assertThrows(NoSuchElementException.class, stack::pop);
        }

        @Test
        void push() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            for (int i = 0; i < n; i++) {
                stack.push(i);
                assertEquals(i, stack.peek());
            }
        }

        @Test
        void size() {
            LinkedListStack<Integer> stack = new LinkedListStack<>();
            for (int i = 0; i < n; i++) {
                assertEquals(i, stack.size());
                stack.push(i);
                assertEquals(i + 1, stack.size());
            }
            for (int i = n; i > 0; i--) {
                assertEquals(i, stack.size());
                stack.pop();
            }
            assertEquals(0, stack.size());
        }
    }
    @Nested
    @DisplayName("WebBrowser Class Tests")
    class WebBrowserTest {

        private WebBrowser webBrowser;

        @BeforeEach
        void setUp() {
            webBrowser = new WebBrowser();
        }
        @Nested
        @DisplayName("Constructors")
        class constr {

            @DisplayName("Default Constructor Test")
            @Test
            void emptyConstructor() {
                assertDoesNotThrow(() -> new WebBrowser());
            }

            @DisplayName("History Constructor Test")
            @Test
            void historyConstructor() {
                SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();
                try {
                    historyList.insertFirst(new URL("https://www.google.com"));
                    historyList.insertFirst(new URL("https://www.github.com"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                assertDoesNotThrow(() -> new WebBrowser(historyList));
            }
        }

        @DisplayName("Visit Test")
        @Test
        void visit() {
            try {
                URL url = new URL("https://www.google.com");
                webBrowser.visit(url);
                assertEquals(url, webBrowser.history().getFirst());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @DisplayName("Back Test")
        @Test
        void back() {
            assertThrows(NoSuchElementException.class, () -> webBrowser.back());
            try {
                URL url1 = new URL("https://www.google.com");
                URL url2 = new URL("https://www.github.com");
                webBrowser.visit(url1);
                webBrowser.visit(url2);
                assertEquals(url1, webBrowser.back());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @DisplayName("Forward Test")
        @Test
        void forward() {
            assertThrows(NoSuchElementException.class, () -> webBrowser.forward());
            try {
                URL url1 = new URL("https://www.google.com");
                URL url2 = new URL("https://www.github.com");
                webBrowser.visit(url1);
                webBrowser.visit(url2);
                webBrowser.visit(url1);
                webBrowser.visit(url2);
                webBrowser.back();
                assertEquals(url2,webBrowser.forward());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @DisplayName("History Test")
        @Test
        void history() {
            try {
                URL url1 = new URL("https://www.google.com");
                URL url2 = new URL("https://www.github.com");
                webBrowser.visit(url1);
                webBrowser.visit(url2);
                SinglyLinkedList<URL> historyList = webBrowser.history();
                assertEquals(url2, historyList.deleteFirst());
                assertEquals(url1, historyList.getFirst());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
