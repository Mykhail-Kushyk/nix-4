package ua.com.alevel;

import java.util.*;
import java.util.function.Consumer;

public class OrderedList<T> implements List<T> {

    private int size;
    private T[] array;
    private final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public OrderedList(Collection<? extends T> c) {
        Object[] arr = c.toArray();
        size = arr.length;
        array = (T[]) Arrays.copyOf(arr, size, Object[].class);
    }

    public OrderedList(int capacity) {
        this.capacity = capacity;
    }

    @SuppressWarnings("unchecked")
    public OrderedList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    static <T> T elementAt(Object[] es, int index) {
        return (T) es[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(Object o) {
        modCount++;
        if (size == capacity) {
            extendCapacity(size, 1);
        }
        array[size] = (T) o;
        size++;
        Arrays.sort(array);
        return true;
    }

    private void extendCapacity(int oldCapacity, int totalNewElements) {
        capacity = (oldCapacity + oldCapacity >> 1) + totalNewElements;
        array = Arrays.copyOf(array, capacity);
    }

    @Override
    public boolean remove(Object o) {
        modCount++;
        return o.equals(remove(indexOf(o)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        modCount++;
        Object[] newArray = c.toArray();
        if ((size + newArray.length) >= capacity) {
            extendCapacity(capacity, newArray.length);
        }
        for (int i = 0, j = size; i < newArray.length; i++, j++) {
            array[j] = (T) newArray[i];
        }
        size += newArray.length;
        Arrays.sort(array);
        return true;
    }

    @Deprecated
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return addAll(c);
    }

    @Override
    public void clear() {
        modCount++;
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Deprecated
    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, Object element) {
        add(element);
        return (T) element;
    }

    @Deprecated
    @Override
    public void add(int index, Object element) {
        add(element);
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldElement = array[index];
        Object[] arr = array;
        fastRemove(arr, index);
        return oldElement;
    }

    private void fastRemove(Object[] arr, int index) {
        modCount++;
        if (size - 1 - index >= 0) System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        arr[--size] = null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Such element is absent!");
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0 ; i--) {
                if (array[i] == null)
                    return i;
            }
        } else {
            for (int i = size - 1; i >= 0 ; i--) {
                if (array[i].equals(o))
                    return i;
            }
        }
        throw new IllegalArgumentException("Such element is absent!");
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException();
        return new ListItr(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> list = new OrderedList<T>();
        list.addAll(Arrays.asList(array).subList(fromIndex, toIndex));
        return list;
    }

    @Override
    public boolean retainAll(Collection c) {
        for (T t : array) {
            if (!c.contains(t)) {
                remove(t);
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] arr = c.toArray();
        if (c.size() != 0) {
            for (int i = 0; i < c.size(); i++) {
                remove(arr[i]);
            }
        }
        cutToSize();
        return false;
    }

    @SuppressWarnings("unchecked")
    private void cutToSize() {
        modCount++;
        if (size == 0) {
            array = (T[]) new Object[]{};
        } else if (size < array.length) {
            array = Arrays.copyOf(array, size);
        }
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] arr = c.toArray();
        for (Object o : arr) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] arr = OrderedList.this.array;
            if (i >= arr.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) arr[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                OrderedList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            final int size = OrderedList.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] es = array;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept(elementAt(es, i));
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public T previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] arr = OrderedList.this.array;
            if (i >= arr.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) arr[lastRet = i];
        }

        public void set(T t) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                OrderedList.this.set(lastRet, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(T t) {
            checkForComodification();

            try {
                int i = cursor;
                OrderedList.this.add(i, t);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}