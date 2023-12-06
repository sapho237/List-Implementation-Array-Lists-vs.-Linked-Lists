// Written by Sofia Kan, kan00022

public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] array;
    private int size;
    private boolean isSorted;

    public ArrayList() {
        array = (T[]) new Comparable[2];
        size = 0;
        isSorted = true;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        if (size == array.length) {
            resizeArray();
        }

        array[size] = element;
        size++;
        isSorted = false;

        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index > size) {
            return false;
        }
        if (size == array.length) {
            resizeArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        isSorted = false;
        size++;

        return true;
    }
    private boolean checkIsSorted() {
        for (int i = 0; i < size - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }


    private void resizeArray() {
        T[] newArray = (T[]) new Comparable[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void clear() {
        array = (T[]) new Comparable[2];
        size = 0;
        isSorted = true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[index];
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        for (int i = 1; i < size; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        isSorted = true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null; // Prevent memory leak
        size--;
        isSorted = false; // The list may no longer be sorted
        return removedElement;
    }

    @Override
    public void equalTo(T element) {
        if (element == null) {
            return;
        }
        int shift = 0;
        for (int i = 0; i < size; i++) {
            if (!element.equals(array[i])) {
                shift++;
            } else if (shift > 0) {
                array[i - shift] = array[i];
                array[i] = null;
            }
        }
        size -= shift;
        // Since equalTo does not change the order, isSorted remains unchanged
    }

    @Override
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            T temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
        isSorted = false; // The list is no longer sorted after reversal
    }

    @Override
    public void intersect(List<T> otherList) {
        if (otherList == null || otherList.size() == 0){
            clear();
            return;
        }
        boolean [] toKeep = new boolean[size];
        int newSize = 0;

        for (int i=0; i< size; i++){
            if (otherList.indexOf(array[i]) != -1){
                toKeep[i] = true;
                newSize++;
            }
            isSorted = false;
        }

        T[] newArray = (T[])  new Comparable [newSize];
        int newArrayIndex = 0;
        for (int i =0; i < size; i++){
            if (toKeep[i]) {
                newArray[newArrayIndex++] = array[i];
            }
        }

        array = newArray;
        size = newSize;

        isSorted = false;
    }

    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        T min = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] != null && array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

    @Override
    public T getMax() {
        if (size == 0) {
            return null;
        }

        T max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] != null && array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                sb.append(array[i].toString());
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
}
