package util;

import java.util.Iterator;

/**
 * A simple implementation of a resizable array-based list.
 * Provides methods to add, remove, and access elements, as well as to iterate through the list.
 *
 * @param <E> The type of elements in this list.
 * @author Waleed Khalid, Rehan Baig
 */
public class List<E> {
    private E[] objects;  // Array to store elements
    private int size;  // Number of elements in the list
    private static final int NOT_FOUND = -1;  // Constant for element not found
    private static final int CAPACITY = 4;  // Initial capacity of the list

    /**
     * Default constructor for the List class.
     * Initializes the array with a capacity of 4 and sets the initial size to 0.
     */
    public List() {
        this.objects = (E[]) new Object[CAPACITY];
        this.size = 0;
    }

    /**
     * Clears the list by resetting the array and size.
     */
    public void clear() {
        this.objects = (E[]) new Object[CAPACITY];  // Reset to the initial size
        this.size = 0;  // Reset the size counter
    }

    /**
     * Finds the index of the specified element in the list.
     *
     * @param e The element to search for.
     * @return The index of the element if found, or -1 if not found.
     */
    private int find(E e) {
        int i = 0;
        while (i < size) {
            if (objects[i] != null && objects[i].equals(e)) {
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    /**
     * A helper method to increase the capacity of the array by 4.
     * This method is called when the original array becomes full.
     */
    private void grow() {
        E[] newObjects = (E[]) new Object[objects.length + 4];
        int i = 0;
        while (i < size) {
            newObjects[i] = objects[i];
            i++;
        }
        this.objects = newObjects;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param e The element to check.
     * @return true if the element is found, false otherwise.
     */
    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }

    /**
     * Adds a new element to the list.
     * If the array is full, it increases the array size.
     *
     * @param e The element to add.
     */
    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size] = e;
        size++;
    }

    /**
     * Removes the specified element from the list.
     *
     * @param e The element to remove.
     * @return true if the element was removed, false otherwise.
     */
    public boolean remove(E e) {
        int index = find(e);
        if (index == NOT_FOUND) {
            return false;
        }
        objects[index] = objects[size - 1];  // Replace with the last element
        objects[size - 1] = null;  // Nullify the last element
        size--;
        return true;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current size of the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return An iterator for the list.
     */
    public Iterator<E> iterator() {
        return new ListIterator<>();
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index The index of the element to return.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            String errorMessage = ("Invalid Index: " + index + ", the size is: " + size);
            throw new IndexOutOfBoundsException(errorMessage);
        }
        return objects[index];
    }

    /**
     * Replaces the element at the specified index with the specified element.
     *
     * @param index The index of the element to replace.
     * @param e     The element to be stored at the specified position.
     */
    public void set(int index, E e) {
        objects[index] = e;
    }

    /**
     * An inner class that provides an iterator for the List class.
     *
     * @param <E> The type of elements returned by this iterator.
     */
    private class ListIterator<E> implements Iterator<E> {
        private int activeIndex = 0;  // Index of the next element to return

        /**
         * Determines if additional elements exist in the list.
         *
         * @return true if more elements are available, false otherwise.
         */
        public boolean hasNext() {
            return activeIndex < size && objects[activeIndex] != null;
        }

        /**
         * Returns the next element in the list.
         *
         * @return The next element.
         * @throws IllegalStateException If no further elements are available.
         */
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException("No further elements available");
            }
            E element = (E) objects[activeIndex];
            activeIndex++;
            return element;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Add and Get Elements
        List<String> stringList = new List<>();
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Cherry");

        System.out.println("Size after adding elements: " + stringList.size());
        assert stringList.size() == 3 : "Size should be 3";

        System.out.println("Element at index 0: " + stringList.get(0));
        assert stringList.get(0).equals("Apple") : "Element at index 0 should be Apple";
        System.out.println("Element at index 1: " + stringList.get(1));
        assert stringList.get(1).equals("Banana") : "Element at index 1 should be Banana";

        // Test Case 2: Remove an Element
        boolean removed = stringList.remove("Banana");
        System.out.println("Remove Banana: " + removed);
        assert removed : "Banana should be removed successfully";

        System.out.println("Size after removing Banana: " + stringList.size());
        assert stringList.size() == 2 : "Size should be 2";

        boolean contains = stringList.contains("Banana");
        assert !contains : "List should not contain Banana";

        // Test Case 3: Check Contains
        boolean containsApple = stringList.contains("Apple");
        System.out.println("Contains Apple: " + containsApple);
        assert containsApple : "List should contain Apple";

        boolean containsGrape = stringList.contains("Grape");
        System.out.println("Contains Grape: " + containsGrape);
        assert !containsGrape : "List should not contain Grape";

        // Test Case 4: Test isEmpty()
        List<String> emptyList = new List<>();
        System.out.println("Empty list is empty: " + emptyList.isEmpty());
        assert emptyList.isEmpty() : "List should be empty initially";

        stringList.add("Apple");
        System.out.println("Non-empty list is empty: " + stringList.isEmpty());
        assert !stringList.isEmpty() : "List should not be empty after adding an element";

        // Test Case 5: Test set() Method
        stringList.set(1, "Orange");
        System.out.println("Element at index 1 after set: " + stringList.get(1));
        assert stringList.get(1).equals("Orange") : "Element at index 1 should be Orange";

        // Test Case 6: Iterator
        stringList.add("Banana"); // Re-add banana to test iteration
        System.out.println("Iterating through the list:");
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // Expected output: Apple, Orange, Banana

        // Final size check after all operations
        System.out.println("Final size: " + stringList.size());
        assert stringList.size() == 3 : "Final size should be 3";
    }
}
