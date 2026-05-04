package listas;

/**
 * Implementación de una lista simplemente circular
 */
public class ListaSimpleCircular<T> {

	private Nodo<T> first;
	private Nodo<T> last;
	private int size;

	public ListaSimpleCircular() {
		first = null;
		last = null;
		size = 0;
	}

	public void addFirst(T value) {
		Nodo<T> newNode = new Nodo<>(value);

		if (isEmpty()) {
			first = last = newNode;
			last.setNext(first);
		} else {
			newNode.setNext(first);
			first = newNode;
			last.setNext(first);
		}

		size++;
	}

	public void addLast(T value) {
		Nodo<T> newNode = new Nodo<>(value);

		if (isEmpty()) {
			first = last = newNode;
			last.setNext(first);
		} else {
			last.setNext(newNode);
			newNode.setNext(first);
			last = newNode;
		}

		size++;
	}

	public boolean isEmpty() {
		return first == null;
	}
}