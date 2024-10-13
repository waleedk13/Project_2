/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
import java.util.Iterator;

public class List<E> implements Iterable<E> {

    //Constants
    private static final int CAPACITY = 4;

    //Instance variables
    private E[] objects;
    private int size;


    public List() {
        this.objects = new E[CAPACITY];


    }


    //default constructor with an initial capacity of 4.
    private int find(E e) {

    }


    private void grow() {

    }


    public boolean contains(E e) {

    }


    public void add(E e) {

    }


    public void remove(E e) {

    }


    public boolean isEmpty() {

    }


    public int size() {

    }


    public Iterator<E> iterator() {

    }


    public E get(int index) {

    } //return the object at the index


    public void set(int index, E e) {

    } //put object e at the index


    public int indexOf(E e) {

    }
}//return the index of the object or return -1

    private class ListIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return false;// change this
        }//return false if it’s empty or end of list


        public E next() {

        } //return the next object in the list


    }
