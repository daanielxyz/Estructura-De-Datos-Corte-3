package listas;

import java.util.Iterator;

/**
 * Implementación de una lista simplemente enlazada
 */
public class ListaSimple<T> implements Iterable<T> {

	private Nodo<T> first;
	private Nodo<T> last;
	private int size;

	public ListaSimple() {
		first = null;
		last = null;
		size = 0;
	}

	public void addFirst(T value) {
		Nodo<T> newNode = new Nodo<>(value);

		if (isEmpty()) {
			first = last = newNode;
		} else {
			newNode.setNext(first);
			first = newNode;
		}

		size++;
	}

	public void addLast(T value) {
		Nodo<T> newNode = new Nodo<>(value);

		if (isEmpty()) {
			first = last = newNode;
		} else {
			last.setNext(newNode);
			last = newNode;
		}

		size++;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void printList() {
		Nodo<T> current = first;

		while (current != null) {
			System.out.print(current.getValue() + "\t");
			current = current.getNext();
		}
		System.out.println();
	}

	public T remove(T value) {
		Nodo<T> current = first;
		Nodo<T> previous = null;

		while (current != null) {
			if (current.getValue().equals(value)) {

				if (previous == null) {
					first = current.getNext();
				} else {
					previous.setNext(current.getNext());
				}

				if (current == last) {
					last = previous;
				}

				size--;
				return value;
			}

			previous = current;
			current = current.getNext();
		}

		throw new RuntimeException("Elemento no encontrado");
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Nodo<T> current = first;

			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				T value = current.getValue();
				current = current.getNext();
				return value;
			}
		};
	}
}