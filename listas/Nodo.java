package listas;

/**
 * Nodo genérico para listas simplemente enlazadas
 * @param <T> tipo de dato almacenado
 */
public class Nodo<T> {

	private Nodo<T> next;
	private T value;

	/**
	 * Constructor con valor
	 */
	public Nodo(T value) {
		this.value = value;
		this.next = null;
	}

	/**
	 * Constructor con referencia al siguiente nodo
	 */
	public Nodo(T value, Nodo<T> next) {
		this.value = value;
		this.next = next;
	}

	public Nodo<T> getNext() {
		return next;
	}

	public void setNext(Nodo<T> next) {
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}