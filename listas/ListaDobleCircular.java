package listas;

/**
 * Implementación de una lista doblemente circular
 */
public class ListaDobleCircular<T> {

	private NodoDoble<T> first;
	private NodoDoble<T> last;
	private int size;

	public ListaDobleCircular() {
		first = null;
		last = null;
		size = 0;
	}

	public void addLast(T value) {
		NodoDoble<T> newNode = new NodoDoble<>(value);

		if (isEmpty()) {
			first = last = newNode;
			first.setNext(first);
			first.setPrevious(first);
		} else {
			newNode.setPrevious(last);
			newNode.setNext(first);
			last.setNext(newNode);
			first.setPrevious(newNode);
			last = newNode;
		}

		size++;
	}

	public boolean isEmpty() {
		return first == null;
	}
}