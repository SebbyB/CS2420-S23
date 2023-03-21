package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @param <Type>
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private BinaryNode<Type> root;
    private int numberOfNodes;

    public BinarySearchTree() {
        this(null, 0);
    }

    public BinarySearchTree(BinaryNode<Type> node, int size) {
        root = null;
        numberOfNodes = 0;
    }

    /**
     * Represents a generically-typed binary tree node. Each binary node contains
     * data, a reference to the left child, and a reference to the right child.
     *
     * @author Erin Parker and Josh Schell
     * @version March 18, 2022
     */
    public static class BinaryNode<Type> {

        private Type data;
        private BinaryNode<Type> leftChild;
        private BinaryNode<Type> rightChild;

        private BinaryNode<Type> parent;

        public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public BinaryNode(Type data) {
            this(data, null, null);
        }

        /**
         * @return the node data
         */
        public Type getData() {
            return data;
        }

        /**
         * @param data - the node data to be set
         */
        public void setData(Type data) {
            this.data = data;
        }

        /**
         * @return reference to the left child node
         */
        public BinaryNode<Type> getLeftChild() {
            return leftChild;
        }

        /**
         * @param leftChild - reference of the left child node to be set
         */
        public void setLeftChild(BinaryNode<Type> leftChild) {
            this.leftChild = leftChild;
            this.leftChild.parent = this;
        }

        /**
         * @return reference to the right child node
         */
        public BinaryNode<Type> getRightChild() {
            return rightChild;
        }

        /**
         * @param rightChild - reference of the right child node to be set
         */
        public void setRightChild(BinaryNode<Type> rightChild) {
            this.rightChild = rightChild;
            this.rightChild.parent = this;
        }

        /**
         * @return reference to the leftmost node in the binary tree rooted at this node
         */
        public BinaryNode<Type> getLeftmostNode() {
            if(leftChild == null)
                return this;
            return leftChild.getLeftmostNode();
        }

        /**
         * @return reference to the rightmost node in the binary tree rooted at this node
         */
        public BinaryNode<Type> getRightmostNode() {
            if(rightChild == null)
                return this;
            return rightChild.getRightmostNode();
        }

        /**
         * @return the height of the binary tree rooted at this node
         * The height of a tree is the length of the longest path to a leaf
         * node. Consider a tree with a single node to have a height of zero.
         */
        public int height() {
            if(leftChild == null && rightChild == null)
                return 0;
            else if (leftChild == null)
                return 1 + rightChild.height();
            else if (rightChild == null)
                return 1 + leftChild.height();
            else {
                return 1 + Math.max(leftChild.height(), rightChild.height());
            }
        }

        /**
         *
         * @return
         */
        public BinaryNode<Type> successor() {
            if(rightChild != null)
                return rightChild.getLeftmostNode();
            else if (leftChild != null)
                return leftChild.getRightmostNode();
            else return null;
        }
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        // Initialize a new node to be added.
        BinaryNode<Type> addNode = new BinaryNode<>(item);

        // Check if the tree is empty, if it is the node will be the first item in the tree / will be the root node.
        if (numberOfNodes == 0) {
            root = addNode;
            // Increase number of nodes.
            numberOfNodes++;
            return true;
        }

        BinaryNode<Type> pointerNode = root;
        while (true) {
            //If the pointerNode ever equals the item then it is already in the tree, so It should not be added.
            if (pointerNode.data.equals(item)) {
                return false;
            } else if (pointerNode.data.compareTo(item) > 0) {
                //Checks the left side of the tree
                if (pointerNode.leftChild == null) {
                    //If the left child ever ends up being null it will add it to the tree, increase the size, and return true.
                    pointerNode.setLeftChild(addNode);
                    numberOfNodes++;
                    return true;
                } else {
                    //moves on if the left child is null.
                    pointerNode = pointerNode.leftChild;
                }
            } else {
                //Checks the right side of the tree.
                if (pointerNode.rightChild == null) {
                    //If the right child ever ends up being null it will add it to the tree, increase the size, and return true.
                    pointerNode.setRightChild(addNode);
                    numberOfNodes++;
                    return true;
                } else {
                    //moves on if the right child is null.
                    pointerNode = pointerNode.rightChild;
                }
            }
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        for (Type item : items){
            boolean isAdd = add(item);
            if(!isAdd) return false;
        }
        return true;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.root = null;
        this.numberOfNodes = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        if (isEmpty()) {
            return false;
        }

        BinaryNode<Type> pointerNode = root;
        while (pointerNode != null) {
            if (pointerNode.data.equals(item)) {
                return true;
            } else if (pointerNode.data.compareTo(item) > 0) {
                pointerNode = pointerNode.leftChild;
            } else {
                pointerNode = pointerNode.rightChild;
            }
        }

        return false;
    }


    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for(var item : items) {
            if(!contains(item)) {return false;}
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("BST is empty");
        return root.getLeftmostNode().data;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return numberOfNodes == 0;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("BST is empty");
        return root.getRightmostNode().data;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        if (isEmpty()) {
            return false;
        }

        root = remove(root, item);
        numberOfNodes--;
        return true;
    }

    private BinaryNode<Type> remove(BinaryNode<Type> node, Type item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.data);
        if (cmp < 0) {
            node.leftChild = remove(node.leftChild, item);
        } else if (cmp > 0) {
            node.rightChild = remove(node.rightChild, item);
        } else {
            // Case 1: Node with only a right child
            if (node.leftChild == null) {
                return node.rightChild;
            }
            // Case 2: Node with only a left child
            else if (node.rightChild == null) {
                return node.leftChild;
            }

            // Case 3: Node with two children
            // Replace the node's data with its in-order successor's data
            node.data = minValue(node.rightChild);

            // Remove the in-order successor
            node.rightChild = remove(node.rightChild, node.data);
        }

        return node;
    }

    private Type minValue(BinaryNode<Type> node) {
        Type minValue = node.data;
        while (node.leftChild != null) {
            minValue = node.leftChild.data;
            node = node.leftChild;
        }
        return minValue;
    }


    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        for (Type item : items){
            if (!remove(item)) return false;
        }
        return true;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return numberOfNodes;
    }

    /**
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> arrayList = new ArrayList<>();
        inOrderTraversal(root, arrayList);
        return arrayList;
    }

    private void inOrderTraversal(BinaryNode<Type> node, ArrayList<Type> arrayList) {
        if (node != null) {
            inOrderTraversal(node.leftChild, arrayList);
            arrayList.add(node.data);
            inOrderTraversal(node.rightChild, arrayList);
        }
    }

}
