package estructuras;

import java.util.LinkedList;

/**
 * Implementación de tabla hash con encadenamiento separado.
 * Soporta tipos genéricos y redimensionamiento dinámico.
 * El factor de carga máximo es 0.75, tras lo cual se duplica la capacidad automáticamente.
 * Utiliza encadenamiento separado para manejar colisiones, donde cada posición del arreglo contiene 
 * una lista enlazada de nodos que comparten el mismo índice hash.
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 * @author Equipo 5
 */
public class TablaHash<K, V> implements Diccionario<K, V> {
    
    /**
     * Clase interna que representa un nodo en la tabla hash.
     * Almacena un par clave-valor.
     * @param <K> Tipo de la clave
     * @param <V> Tipo del valor
     */
    private class Nodo<K, V> {
        K key;
        V value;
        
        /**
         * Constructor del nodo.
         * @param key La clave del par
         * @param value El valor del par
         */
        public Nodo(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // Atributos principales
    private LinkedList<Nodo<K, V>>[] tabla;  // Arreglo de listas (buckets)
    private int size;                         // Cantidad de elementos almacenados (N)
    private int capacidad;                    // Tamaño del arreglo (M)
    private static final double FACTOR_CARGA_MAX = 0.75;
    
    /**
     * Constructor por defecto.
     * Inicializa la tabla con capacidad 11 (número primo) para mejorar la distribución de los elementos.
     */
    public TablaHash() {
        this.capacidad = 11;
        this.tabla = new LinkedList[capacidad];
        this.size = 0;
        
        // IMPORTANTE: Inicializar cada posición del arreglo
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }
    
    /**
     * Calcula el índice en el arreglo para una clave dada.
     * Maneja correctamente valores negativos del hashCode.
     * @param key La clave a hashear
     * @return Índice válido en el rango [0, capacidad-1]
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacidad;
    }
    
    /**
     * Inserta un par clave-valor en la tabla hash.
     * Si la clave ya existe, actualiza su valor.
     * Verifica automáticamente el factor de carga y redimensiona si es necesario.
     * Complejidad: O(1) promedio, O(n) si se requiere resize.
     * @param key La clave única del elemento (no puede ser null)
     * @param value El valor asociado a la clave
     * @throws NullPointerException si key es null
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("La clave no puede ser null");
        }
        
        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];
        
        // Buscar si la clave ya existe
        for (Nodo<K, V> nodo : lista) {
            if (nodo.key.equals(key)) {
                // Actualizar valor existente
                nodo.value = value;
                return;
            }
        }
        
        // La clave no existe, agregar nuevo nodo
        lista.add(new Nodo<>(key, value));
        size++;
        
        // Verificar factor de carga y redimensionar si es necesario
        double factorCarga = (double) size / capacidad;
        if (factorCarga >= FACTOR_CARGA_MAX) {
            resize();
        }
    }
    
    /**
     * Recupera el valor asociado a una clave.
     * Busca en la lista enlazada correspondiente al índice hash de la clave.
     * Complejidad: O(1) promedio.
     * @param key La clave a buscar
     * @return El valor asociado, o null si no existe
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        
        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];
        
        // Recorrer la lista buscando la clave
        for (Nodo<K, V> nodo : lista) {
            if (nodo.key.equals(key)) {
                return nodo.value;
            }
        }
        
        // No se encontró la clave
        return null;
    }
    
    /**
     * Elimina un par clave-valor de la tabla.
     * Busca el nodo en la lista correspondiente y lo elimina si existe.
     * Complejidad: O(1) promedio.
     * @param key La clave del elemento a eliminar
     * @return El valor eliminado, o null si la clave no existe
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        
        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];
        
        // Buscar y eliminar el nodo
        for (int i = 0; i < lista.size(); i++) {
            Nodo<K, V> nodo = lista.get(i);
            if (nodo.key.equals(key)) {
                lista.remove(i);
                size--;
                return nodo.value;
            }
        }
        
        // No se encontró la clave
        return null;
    }
    
    /**
     * Verifica si una clave existe en la tabla.
     * @param key La clave a verificar
     * @return true si existe, false en caso contrario
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    /**
     * Retorna la cantidad de elementos almacenados.
     * @return El tamaño actual de la tabla
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Redimensiona la tabla hash cuando el factor de carga supera el umbral.
     * Duplica la capacidad y reubica todos los elementos recalculando
     * sus índices hash con el nuevo tamaño.
     * Este proceso es necesario para mantener el rendimiento O(1) promedio
     * de las operaciones básicas.
     * Complejidad: O(n) donde n es el número de elementos.
     */
    private void resize() {
        // Guardar referencia al arreglo antiguo
        LinkedList<Nodo<K, V>>[] tablaVieja = tabla;
        
        // Duplicar la capacidad
        capacidad = capacidad * 2;
        tabla = new LinkedList[capacidad];
        
        // Inicializar cada posición del nuevo arreglo
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
        
        // Reinsertar todos los elementos (rehashing)
        size = 0; // Será incrementado por put()
        for (LinkedList<Nodo<K, V>> bucket : tablaVieja) {
            for (Nodo<K, V> nodo : bucket) {
                // Recalcula índices automáticamente
                put(nodo.key, nodo.value);
            }
        }
    }
    
    /**
     * Retorna la capacidad actual de la tabla (tamaño del arreglo interno).
     * @return La capacidad actual
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Calcula y retorna el factor de carga actual.
     * @return El factor de carga (size/capacidad)
     */
    public double getFactorCarga() {
        return (double) size / capacidad;
    }
    
    /**
     * Retorna una representación en cadena de la tabla hash mostrando todos los pares clave-valor.
     * @return Representación en cadena de la tabla
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TablaHash [size=").append(size)
          .append(", capacidad=").append(capacidad)
          .append(", factorCarga=").append(String.format("%.3f", getFactorCarga()))
          .append("]\n");
        
        for (int i = 0; i < capacidad; i++) {
            if (!tabla[i].isEmpty()) {
                sb.append("  tabla[").append(i).append("] -> ");
                for (Nodo<K, V> nodo : tabla[i]) {
                    sb.append("(").append(nodo.key).append(", ")
                      .append(nodo.value).append(") ");
                }
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
}
