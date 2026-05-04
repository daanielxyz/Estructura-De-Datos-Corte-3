package Arboles;

import colas.Cola;

public class ArbolBinario<T extends Comparable<T>> {

    private Nodo<T> raiz;
    private int peso;

    public ArbolBinario() {
        this.raiz = null;
        this.peso = 0;
    }

    // ─── Operaciones básicas ───────────────────────────────────────────

    public boolean estaVacio() {
        return raiz == null;
    }

    public int obtenerPeso() {
        return peso;
    }

    // ─── Agregar ──────────────────────────────────────────────────────

    public void agregar(T dato) {
        raiz = agregarRecursivo(raiz, dato);
        peso++;
    }

    private Nodo<T> agregarRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<>(dato);
        }
        if (dato.compareTo(nodo.getDato()) < 0) {
            nodo.setHijoIzquierdo(agregarRecursivo(nodo.getHijoIzquierdo(), dato));
        } else if (dato.compareTo(nodo.getDato()) > 0) {
            nodo.setHijoDerecho(agregarRecursivo(nodo.getHijoDerecho(), dato));
        }
        return nodo;
    }

    // ─── Existe ───────────────────────────────────────────────────────

    public boolean existe(T dato) {
        return existeRecursivo(raiz, dato);
    }

    private boolean existeRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) return false;
        if (dato.compareTo(nodo.getDato()) == 0) return true;
        if (dato.compareTo(nodo.getDato()) < 0)
            return existeRecursivo(nodo.getHijoIzquierdo(), dato);
        return existeRecursivo(nodo.getHijoDerecho(), dato);
    }

    // ─── Altura ───────────────────────────────────────────────────────

    public int obtenerAltura() {
        return obtenerAlturaRecursivo(raiz);
    }

    private int obtenerAlturaRecursivo(Nodo<T> nodo) {
        if (nodo == null) return 0;
        int alturaIzq = obtenerAlturaRecursivo(nodo.getHijoIzquierdo());
        int alturaDer = obtenerAlturaRecursivo(nodo.getHijoDerecho());
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    // ─── Nivel de un dato ─────────────────────────────────────────────

    public int obtenerNivel(T dato) {
        return obtenerNivelRecursivo(raiz, dato, 0);
    }

    private int obtenerNivelRecursivo(Nodo<T> nodo, T dato, int nivel) {
        if (nodo == null) return -1; // No encontrado
        if (dato.compareTo(nodo.getDato()) == 0) return nivel;
        if (dato.compareTo(nodo.getDato()) < 0)
            return obtenerNivelRecursivo(nodo.getHijoIzquierdo(), dato, nivel + 1);
        return obtenerNivelRecursivo(nodo.getHijoDerecho(), dato, nivel + 1);
    }

    // ─── Contar hojas ─────────────────────────────────────────────────

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo<T> nodo) {
        if (nodo == null) return 0;
        if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) return 1;
        return contarHojasRecursivo(nodo.getHijoIzquierdo())
                + contarHojasRecursivo(nodo.getHijoDerecho());
    }

    // ─── Mayor ────────────────────────────────────────────────────────

    public T obtenerMayor() {
        if (estaVacio()) return null;
        Nodo<T> actual = raiz;
        while (actual.getHijoDerecho() != null) {
            actual = actual.getHijoDerecho();
        }
        return actual.getDato();
    }

    // ─── Menor ────────────────────────────────────────────────────────

    public T obtenerMenor() {
        if (estaVacio()) return null;
        Nodo<T> actual = raiz;
        while (actual.getHijoIzquierdo() != null) {
            actual = actual.getHijoIzquierdo();
        }
        return actual.getDato();
    }

    // ─── Recorridos en profundidad ────────────────────────────────────

    public void recorrerInOrden() {
        inOrden(raiz);
        System.out.println();
    }

    private void inOrden(Nodo<T> nodo) {
        if (nodo == null) return;
        inOrden(nodo.getHijoIzquierdo());
        System.out.print(nodo.getDato() + " ");
        inOrden(nodo.getHijoDerecho());
    }

    public void recorrerPreOrden() {
        preOrden(raiz);
        System.out.println();
    }

    private void preOrden(Nodo<T> nodo) {
        if (nodo == null) return;
        System.out.print(nodo.getDato() + " ");
        preOrden(nodo.getHijoIzquierdo());
        preOrden(nodo.getHijoDerecho());
    }

    public void recorrerPostOrden() {
        postOrden(raiz);
        System.out.println();
    }

    private void postOrden(Nodo<T> nodo) {
        if (nodo == null) return;
        postOrden(nodo.getHijoIzquierdo());
        postOrden(nodo.getHijoDerecho());
        System.out.print(nodo.getDato() + " ");
    }

    // ─── Recorrido en amplitud (por niveles) ──────────────────────────

    public void imprimirAmplitud() {
        if (estaVacio()) return;

        Cola<Nodo<T>> cola = new Cola<>();
        cola.encolar(raiz);

        while (!cola.estaVacia()) {
            Nodo<T> actual = cola.desencolar();
            System.out.print(actual.getDato() + " ");

            if (actual.getHijoIzquierdo() != null)
                cola.encolar(actual.getHijoIzquierdo());
            if (actual.getHijoDerecho() != null)
                cola.encolar(actual.getHijoDerecho());
        }
        System.out.println();
    }

    public Nodo<T> getRaiz() { return raiz; }

    // ─── Borrar árbol ─────────────────────────────────────────────────

    public void borrarArbol() {
        raiz = null;
        peso = 0;
    }

// ─── Eliminar dato ────────────────────────────────────────────────

    public void eliminar(T dato) {
        if (existe(dato)) {
            raiz = eliminarRecursivo(raiz, dato);
            peso--;
        }
    }

    private Nodo<T> eliminarRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) return null;

        if (dato.compareTo(nodo.getDato()) < 0) {
            nodo.setHijoIzquierdo(eliminarRecursivo(nodo.getHijoIzquierdo(), dato));

        } else if (dato.compareTo(nodo.getDato()) > 0) {
            nodo.setHijoDerecho(eliminarRecursivo(nodo.getHijoDerecho(), dato));

        } else {
            // Caso 1: nodo hoja
            if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
                return null;
            }
            // Caso 2: un solo hijo
            if (nodo.getHijoIzquierdo() == null) return nodo.getHijoDerecho();
            if (nodo.getHijoDerecho() == null)   return nodo.getHijoIzquierdo();

            // Caso 3: dos hijos → reemplazar con el menor del subárbol derecho
            T menorDerecha = obtenerMenorDesde(nodo.getHijoDerecho());
            nodo.setDato(menorDerecha);
            nodo.setHijoDerecho(eliminarRecursivo(nodo.getHijoDerecho(), menorDerecha));
        }
        return nodo;
    }

    private T obtenerMenorDesde(Nodo<T> nodo) {
        while (nodo.getHijoIzquierdo() != null) {
            nodo = nodo.getHijoIzquierdo();
        }
        return nodo.getDato();
    }
}