package estructuras;

/**
 * Interfaz que define las operaciones básicas de un diccionario.
 * Un diccionario almacena pares clave-valor donde cada clave es única.
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 * @author Equipo 5
 */
public interface Diccionario<K, V> {
    
    /**
     * Inserta un par clave-valor en el diccionario.
     * Si la clave ya existe, actualiza su valor.
     * @param key La clave única del elemento
     * @param value El valor asociado a la clave
     */
    void put(K key, V value);
    
    /**
     * Recupera el valor asociado a una clave.
     * @param key La clave a buscar
     * @return El valor asociado, o null si no existe
     */
    V get(K key);
    
    /**
     * Elimina un par clave-valor del diccionario.
     * @param key La clave del elemento a eliminar
     * @return El valor eliminado, o null si la clave no existe
     */
    V remove(K key);
    
    /**
     * Verifica si una clave existe en el diccionario.
     * @param key La clave a verificar
     * @return true si existe, false en caso contrario
     */
    boolean containsKey(K key);
    
    /**
     * Retorna la cantidad de elementos almacenados.
     * @return El tamaño actual del diccionario
     */
    int size();
}
