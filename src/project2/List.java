package project2;


import java.util.Iterator;

public class List<E> {
    private E[] objects;
    private int size;
    private static final int NOT_FOUND = -1;
    private static final int CAPACITY = 4;


    /**
     * Default constructor for the List class.
     * Sets the array with a capacity of 4 and sets the initial size to 0.
     */
    public List() {
        this.objects = (E[]) new Object[CAPACITY];
        this.size = 0;

    }

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
     * A helper method to increase the capacity array by 4
     * This method is called when the orignal array becomes full
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
     * Checks to see if the appointment exists in the array
     *
     * @param e The appointment to check.
     * @return true if the appointment is there and false if it does not exist
     */
    public boolean contains(E e) {
        int locate = find(e);
        if (locate == NOT_FOUND) {
            return false;
        }
        return true;
    }


    /**
     * Adds a new appointment to the array.
     * If the appointment already exists and/or the array is full,
     * it will handle accordingly.
     *
     * @param e The appointment to add.
     */
    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size] = e;
        size++;
    }


    /**
     * Removes an appointment from the array.
     * If the appointment does not exist it prints an error message
     *
     * @param e The appointment to remove.
     * @return The details of the removed appointment and if not found will
     * print null
     */

    public boolean remove(E e) {
        int index = find(e);
        if (index == NOT_FOUND) {
            return false;
        }
        objects[index] = objects[size - 1];
        objects[size - 1] = null;
        size--;
        return true;
    }


    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false if it is not empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    //is this the getter method?
    public int size() {
        return size;

    }

    public Iterator<E> iterator() {
        return new ListIterator<>();

    }


    /**
     * Returns the element located at the given index.
     *
     * @param index the position of the element to be returned
     * @return the element at the specified index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            String errorMessage = ("Invalid Index: " + index + ", " +
                    "the size is: " + size);
            throw new IndexOutOfBoundsException(errorMessage);
        }
        return objects[index];
    }


    public void set(int index, E e) {
        objects[index] = e;

    }

    private class ListIterator<E> implements Iterator<E> {
        private int activeIndex = 0;


        /**
         * Determines if additional elements exist in the list.
         *
         * @return true if more elements are available, false if not
         */

        public boolean hasNext() {
            if (activeIndex >= size) {
                return false;
            }
            return objects[activeIndex] != null;
        }


        /**
         * Returns the next element in the list.
         *
         * @return the next element
         */
        public E next() {
            if (hasNext() == false) {
                throw new IllegalStateException
                        ("No further elements available");
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