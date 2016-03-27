
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Iterator;
/**
 * CSS 162 
 * Rob Nash
 * Author: Jack Lewis
 * ArrayList is a class that stores items in an Array that behaves the way
 * any list would. 
 */




public class ArrayList<T extends Comparable> implements Iterable<T>, Cloneable, Comparable {

	private Object[] data;
	private int numElements;

	//Constructor for ArrayList, uses generic type <T>
	public ArrayList(T[] dataArray) {
		int arraySize = 0;
		for (int i = 0; i < dataArray.length; i++) {
			if (dataArray[i] != null) { //check for null at every spot
				arraySize++;
			}
		}
		this.numElements = arraySize;
		this.data = new Object[arraySize];
		for (int i = 0; i < arraySize; i++) {
			this.data[i] = dataArray[i];//copies the arr into list
		}
	}

	//ArrayList Constructor
	//Takes in a desired array size
	//Will not accept anything bigger than 65,536
	public ArrayList(int arrayLength) {
		if (arrayLength < 65536) {
			this.numElements = 0;
			this.data = new Object[arrayLength];
		} else {
			throw new RuntimeException("The array you are trying to make is too large!");
		}
	}


	//Default, no-arg constructor
	//Default size is 100
	public ArrayList() {
		int defaultSize = 100;
		this.numElements = 0;
		this.data = new Object[defaultSize];
	}

	//copy constructor
	//verifies o is not null and is an instanceof ArrayList
	//creates a new ArrayList and then copies
	//all elements into it
	private ArrayList(T o) {
		if (o == null || !(o instanceof ArrayList)) {
			throw new IllegalArgumentException("ArrayList was null, or simply not an ArrayList.");
		} else {
			ArrayList<T> that = (ArrayList<T>) o;
			this.data = new Object[that.size()];
			for (int i = 0; i < that.size(); i++) {
				this.data[i] = (T) that.get(i); //make sure it's a "T"
			}
			this.numElements = that.size();
		}
	}

	//clone method
	//references this ArrayList's copy constructor
	public ArrayList<T> clone() {
		return new ArrayList<T>( (T) this);
	}

	//returns the number of elements
	//that the array currently holds
	public int size() {
		return this.numElements;
	}

	//if there are no elements in
	//the array, it's empty
	public boolean isEmpty() {
		return numElements == 0;
	}

	//if the number of elements in the array is equal to
	//(or greater than) the array's length, we're "full"
	private boolean isFull() {
		return numElements >= this.data.length; //added '>' just for safety
	}

	//creates a copyArray of the same size
	//then copies all elements into it
	//then re-creates data in correct size
	//then copies elements back into data
	private void resize(int newArraySize) {
		Object[] copyArr = new Object[newArraySize];
		for (int i = 0; i < this.numElements; i++) {
			copyArr[i] = this.data[i];
		}
		this.data = new Object[newArraySize];
		for (int i = 0; i < this.numElements; i++) {
			this.data[i] = copyArr[i];
		}
	}

	//shifts the array values to the right, at an index
	//provided. 
	private void arrayShiftRight(int shiftIndex) {
		for (int i = numElements-1; i >= shiftIndex; i--) {
			this.data[i+1] = this.data[i];
		}
	}


	//shifts the array to the left, at the shiftIndex location
	private void arrayShiftLeft(int shiftIndex) {
		for (int i = shiftIndex; i < this.data.length - 1; i++) {
			
			this.data[i] = this.data[i+1];
			
		}
	}

	//add a generic type at a particular index
	public void add(T objectToAdd, int index) {
		if (objectToAdd != null) {
			if (this.isFull()) {
				this.resize(numElements * 2); //make the array twice as big
				if (index < this.size()) { //if index is located in the middle of the array
					this.arrayShiftRight(index);
					this.data[index] = objectToAdd;
				}
				if (index >= this.size()) { //if the index is after the array
					this.resize(index + 1);
					this.data[index] = objectToAdd;
				}
			} else {
				if (index < this.size()) { //if index is located in the middle of the array
					this.arrayShiftRight(index);
					this.data[index] = objectToAdd;
				}
				if (index >= this.size()) { //if the item is placed after the array
					this.resize(index + 1);
					this.data[index] = objectToAdd;
				}
			}
			numElements++; //we need to increase the size
		}
	}

	//a simple facade method
	//that calls the larger 'add' with an index at the end of the array
	public void add(T objectToAdd) { 
		this.add(objectToAdd, this.numElements);
	}

	//remove an item from a particular place in the array 
	//return it and shift the array left one place to cover up the missing spot
	public T remove(int removeIndex) {
		
		if (removeIndex >= this.numElements) { 
			throw new NullPointerException("Tried to remove from an incorrect place in the array."); 
		} else {
			T retVal = (T) this.data[removeIndex];
			this.arrayShiftLeft(removeIndex); //move the array over to cover up the hole
			this.data[this.data.length-1] = null; 
			numElements--; //one less item in array now
			return retVal;
		}
	}

	//compares ArrayList lengths
	//if equal, then calls equals on each individual
	//object in the ArrayLists, returns true if all equal. 
	public boolean equals(T o) { 
		if (o == null || !(o instanceof ArrayList)) {
			throw new IllegalArgumentException ("Tried to call ArrayList's equals with either a null Array or something that wasn't an array.");
		}
		ArrayList that = (ArrayList) o;
		if (this.data.length == that.data.length) {
			for (int i = 0; i < this.data.length; i++) {
				if (!this.data[i].equals(that.data[i])) {
					return false;
				}
			}
		}
		return true;
	}

	//ArrayList's compareTo function that 
	//increases whenever an item in this > that and decreases
	//whenever an item in this < that. 
	public int compareTo(Object o) {
		int retVal = 0;
		if (o == null || !(o instanceof ArrayList)) {
			throw new IllegalArgumentException("Tried to use ArrayList's compareTo() method with either a null or something that wasn't an ArrayList.");
		}
		ArrayList that = (ArrayList) o;
		for (int i = 0; i < numElements; i++) {
			if (this.get(i).compareTo(that.get(i)) > 0) retVal++; //increment
			else if (this.get(i).compareTo(that.get(i)) < 0) retVal--; //decrement
			else retVal = retVal;
		}
		return retVal;
	}

	//returns T object 
	//at specified index
	public T get(int index) {
		if (index < this.data.length) { //verify that the index is legit and not out-of-bounds
			return (T) this.data[index]; //return the "casted" Object.  
		}
		return (T) "This is an error message."; //If we've made it here, there was a problem, so we cast a String as a T and return it. 
	}

	//searches for a particular T Object
	//and once it finds it, it returns the Object's index
	//if Object isn't found, returns -1
	public int indexOf(T o) {
		if (o == null) {
			throw new IllegalArgumentException("Called indexOf with a null value.");
		} else {
			for (int i = 0; i < this.data.length; i++) {
				if (this.data[i].equals(o)) {
					return i;
				}
			}
		}
		return -1;
	}

	//Iterator method
	public Iterator<T> iterator() {
		Iterator<T> myIterator = new Iterator<T>() {
			private int iterIndex;

			//confirms that there is still more data in the ArrayList.
			@Override
			public boolean hasNext() {
				return iterIndex < numElements && data[iterIndex] != null;
			}
			//returns the data Object located at
			//the iterator's current index.
			@Override
			public T next() {
				return (T) data[iterIndex++];
			}
			
			@Override
			public void remove() {
			}
		};
		return myIterator;
	}

	//Literally goes through the entire ArrayList
	//and calls remove() on each element, clearing it. 
	public void clear() {
		for (int i = numElements-1; i >= 0; i--) {
			this.remove(i);
		}
	}

	//Standard toString() method
	@Override
	public String toString() {
		String retVal = "[";
		for (int i = 0; i < this.data.length; i++) {
			if (this.data[i] != null) { //null check at each element
				retVal += this.data[i] + ", ";
			}
		}
		return retVal + "]";
	}
}

