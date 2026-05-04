package listas;

/**
 * Implementación de una lista doblemente enlazada
 */
public class ListaDoble<T> {

	private NodoDoble<T> first;
	private NodoDoble<T> last;
	private int size;

	public ListaDoble() {
		first = null;
		last = null;
		size = 0;
	}

	public void addFirst(T value) {
		NodoDoble<T> newNode = new NodoDoble<>(value);

		if (isEmpty()) {
			first = last = newNode;
		} else {
			newNode.setNext(first);
			first.setPrevious(newNode);
			first = newNode;
		}

		size++;
	}

	public void addLast(T value) {
		NodoDoble<T> newNode = new NodoDoble<>(value);

		if (isEmpty()) {
			first = last = newNode;
		} else {
			last.setNext(newNode);
			newNode.setPrevious(last);
			last = newNode;
		}

		size++;
	}

	public boolean isEmpty() {
		return first == null;
	}
}