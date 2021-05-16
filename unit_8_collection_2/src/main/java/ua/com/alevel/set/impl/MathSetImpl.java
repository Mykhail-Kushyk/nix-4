package ua.com.alevel.set.impl;

import ua.com.alevel.set.MathSet;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

public class MathSetImpl<N extends Number> implements MathSet<N> {

    private int size;
    private N[] array;
    private final int DEFAULT_CAPACITY = 10;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MathSetImpl() {
        this.array = (N[]) new Number[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    @SuppressWarnings("unchecked")
    public MathSetImpl(int capacity) {
        this.capacity = capacity;
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can not be less than 1");
        }
        array = (N[]) new Number[this.capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MathSetImpl(N[] numbers) {
        this.capacity = numbers.length;
        this.size = 0;
        this.array = (N[]) new Number[capacity];
        add(numbers);
    }

    @SuppressWarnings("unchecked")
    public MathSetImpl(N[] ... numbers) {
        this.capacity = numbers.length;
        this.size = 0;
        this.array = (N[]) new Number[capacity];
    }

    public MathSetImpl(MathSet<N> numbers) {
        this.capacity = numbers.size();
        this.size = numbers.size();
        this.array = numbers.toArray();
    }

    @SuppressWarnings("unchecked")
    public MathSetImpl(MathSet<N> ... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            this.size += numbers[i].size();
        }
        this.capacity = this.size;
        this.array = (N[]) new Number[this.capacity];
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].size(); j++) {
                this.array[index++] = numbers[i].get(j);

            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(N n) {
//        if (contains(n)) {
//            removeElement(array, n);
//        }
        if (size == capacity) {
            extendCapacity(size, 1);
        }
        array[size++] = n;
    }

    private void extendCapacity(int oldCapacity, int totalNewElements) {
        capacity = (oldCapacity + oldCapacity >> 1) + totalNewElements;
        array = copyArray(array, capacity);
    }

    @SuppressWarnings("unchecked")
    private N[] copyArray(N[] arr, int capacity) {
        N[] newArray = (N[]) new Number[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    private boolean contains(N element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    private void removeElement(N[] arr, N element) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(element)) {
                fastRemove(arr, i);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void fastRemove(N[] arr, int index) {
        if (size - 1 - index >= 0) System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        arr[--size] = null;
    }

    @Override
    public void add(N... n) {
        for (int i = 0; i < n.length; i++) {
            add(n[i]);
        }
    }

    @Override
    public void join(MathSet<N> ms) {
        if (!ms.isEmpty()) {
            int newCapacity = size + ms.size();
            extendCapacity(capacity, newCapacity - capacity);
            for (int i = 0; i < ms.size(); i++) {
                add(ms.get(i));
            }
        }
    }

    @Override
    public void join(MathSet<N>... ms) {
        for (int i = 0; i < ms.length; i++) {
            join(ms[i]);
        }
    }

    @Override
    public void sortDesc() {
        recursiveQuickSortDesc(0, size - 1);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        recursiveQuickSortDesc(firstIndex, lastIndex);
    }

    @Override
    public void sortDesc(N value) {
        if (indexOf(value) == -1) {
            throw new IllegalArgumentException("Such value does not exist!");
        }
        sortDesc(indexOf(value), size - 1);
    }

    private int indexOf(N value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sortAsc() {
        recursiveQuickSortAsc(0, size - 1);
    }

    private void recursiveQuickSortAsc(int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            N pivot = array[right];
            int partition = partitionItAsc(left, right, pivot);
            recursiveQuickSortAsc(left, partition - 1);
            recursiveQuickSortAsc(partition + 1, right);
        }
    }

    private void recursiveQuickSortDesc(int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            N pivot = array[right];
            int partition = partitionItDesc(left, right, pivot);
            recursiveQuickSortDesc(left, partition - 1);
            recursiveQuickSortDesc(partition + 1, right);
        }
    }

    private int partitionItAsc(int left, int right, N pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (array[++leftPtr].doubleValue() < pivot.doubleValue())
                ;
            while (rightPtr > 0 && array[--rightPtr].doubleValue() > pivot.doubleValue())
                ;
            if (leftPtr >= rightPtr)
                break;
            else {
                swap(leftPtr, rightPtr);
            }
        }
            swap(leftPtr, right);
            return leftPtr;
    }

    private int partitionItDesc(int left, int right, N pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (array[++leftPtr].doubleValue() > pivot.doubleValue())
                ;
            while (rightPtr > 0 && array[--rightPtr].doubleValue() < pivot.doubleValue())
                ;
            if (leftPtr >= rightPtr)
                break;
            else {
                swap(leftPtr, rightPtr);
            }
        }
        swap(leftPtr, right);
        return leftPtr;
    }

    private void swap(int firstIndex, int secondIndex) {
        N temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        recursiveQuickSortAsc(firstIndex, lastIndex);
    }

    @Override
    public void sortAsc(N value) {
        if (indexOf(value) == -1) {
            throw new IllegalArgumentException("Such value does not exist!");
        }
        sortAsc(indexOf(value), size - 1);
    }

    @Override
    public N get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    @Override
    public N getMax() {
        int nElements = size;
        N maxValue = array[0];
        for (int i = 1, j = (nElements / 2) + 1; j < nElements; i++, j++) {
            if (array[i].doubleValue() > maxValue.doubleValue()) {
                maxValue = array[i];
            }
            if (array[j].doubleValue() > maxValue.doubleValue()) {
                maxValue = array[j];
            }
        }
        return maxValue;
    }

    @Override
    public N getMin() {
        int nElements = size;
        N minValue = array[0];
        for (int i = 1, j = (nElements / 2) + 1; j < nElements; i++, j++) {
            if (array[i].doubleValue() < minValue.doubleValue()) {
                minValue = array[i];
            }
            if (array[j].doubleValue() < minValue.doubleValue()) {
                minValue = array[j];
            }
        }
        return minValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public N getAverage() {
        if (array[0].getClass() == Integer.class ||
                array[0].getClass() == Byte.class ||
                array[0].getClass() == Long.class ||
                array[0].getClass() == Short.class) {
            BigInteger sum = new BigInteger(array[0].toString());
            for (int i = 1; i < size; i++) {
                sum = sum.add(new BigInteger(array[i].toString()));
            }
            return (N) sum.divide(BigInteger.valueOf(size));
        }
        if (array[0].getClass() == Float.class ||
                array[0].getClass() == Double.class) {
            BigDecimal sum = new BigDecimal(array[0].toString());
            for (int i = 1; i < size; i++) {
                sum = sum.add(new BigDecimal(array[i].toString()));
            }
            return (N) sum.divide(BigDecimal.valueOf(size), RoundingMode.HALF_UP);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public N getMedian() {
        sortAsc();
        if (size % 2 == 1) {
            return array[size / 2];
        } else {
            if (array[0].getClass() == Integer.class ||
                    array[0].getClass() == Byte.class ||
                    array[0].getClass() == Long.class ||
                    array[0].getClass() == Short.class) {
                int index = size / 2;
                BigInteger sum = new BigInteger(array[index].toString());
                sum = sum.add(new BigInteger(array[--index].toString()));
                return (N) sum.divide(BigInteger.valueOf(2));
            }
            if (array[0].getClass() == Float.class ||
                    array[0].getClass() == Double.class) {
                int index = size / 2;
                BigDecimal sum = new BigDecimal(array[index].toString());
                sum = sum.add(new BigDecimal(array[--index].toString()));
                return (N) sum.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
            }
        }
        return null;
    }

    @Override
    public N[] toArray() {
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public N[] toArray(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        int sizeArray = lastIndex - firstIndex;
        N[] newArray = (N[]) new Number[sizeArray];
        for (int i = 0, j = firstIndex; i < lastIndex; i++, j++) {
            newArray[i] = array[j];
        }
        return newArray;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MathSet<N> squash(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        int arraySize = (lastIndex - firstIndex) + 1;
        N[] numbers = (N[]) new Number[arraySize];
        for (int i = 0, j = firstIndex; j <= lastIndex; i++, j++) {
            numbers[i] = array[j];
        }
        MathSet<N> set = new MathSetImpl<N>(numbers);
        return set;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    @Override
    public void clear(N[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            removeElement(array, numbers[i]);
        }
    }
}