# Asignacion-14---Hashmap-Parte-1
# Proyecto HashMap - Estructura de Datos

# Descripción
Este proyecto implementa una Tabla Hash con Encadenamiento Separado desde cero en Java. La implementación incluye todas las operaciones fundamentales de un HashMap: inserción, búsqueda, eliminación y redimensionamiento dinámico.

# Características Principales
- Encadenamiento Separado: Manejo de colisiones mediante listas enlazadas
- Tipos Genéricos: Soporta cualquier tipo de clave y valor
- Redimensionamiento Automático: Se redimensiona cuando el factor de carga supera 0.75
- Documentación Javadoc Completa: Todos los métodos están documentados
- Pruebas Exhaustivas: Suite completa de tests incluida

# Estructura del Proyecto
HashMapProject/
    src/
        estructuras/
            Diccionario.java
            TablaHash.java
        test/
            TestTablaHash.java
README.md
.gitignore

# Compilación
Compilar todas las clases
javac -d bin src/estructuras/*.java test/*.java

O compilar individualmente
javac src/estructuras/Diccionario.java
javac src/estructuras/TablaHash.java
javac test/TestTablaHash.java

# Ejecución
Ejecutar las pruebas:
Si compilaste con -d bin
java -cp bin test.TestTablaHash

Si compilaste sin -d
java test.TestTablaHash

# Salida esperada:
PRUEBAS DE TABLA HASH CON ENCADENAMIENTO SEPARADO

TEST 1: Operaciones Básicas
Insertados 3 elementos
Size: 3
manzana: 5
pera: 3
naranja: 7
platano: null
Test 1 completado

...
TODAS LAS PRUEBAS COMPLETADAS

# Descripción de las Clases
1. Diccionario.java (Interfaz)
Define el contrato que debe cumplir cualquier implementación de diccionario:

- void put(K key, V value) - Insertar o actualizar
- V get(K key) - Obtener valor por clave
- V remove(K key) - Eliminar elemento
- boolean containsKey(K key) - Verificar existencia
- int size() - Obtener cantidad de elementos

2. TablaHash.java (Implementación)
Implementación completa de una tabla hash con las siguientes características:

Atributos Principales:
- tabla[]: Arreglo de listas enlazadas (buckets)
- size: Número de elementos almacenados
- capacidad: Tamaño del arreglo interno
- FACTOR_CARGA_MAX: Umbral para redimensionar (0.75)

Clase Interna Nodo<K, V>:
Representa un par clave-valor en la lista enlazada.

Métodos Principales:
put(K key, V value)
- Inserta o actualiza un par clave-valor
- Complejidad: O(1) promedio, O(n) si requiere resize
- Verifica automáticamente el factor de carga

get(K key)
- Recupera el valor asociado a una clave
- Complejidad: O(1) promedio
- Retorna null si la clave no existe

remove(K key)
- Elimina un par clave-valor
- Complejidad: O(1) promedio
- Retorna el valor eliminado o null

resize()
- Redimensiona la tabla cuando λ ≥ 0.75
- Duplica la capacidad y rehashea todos los elementos
- Complejidad: O(n)

3. TestTablaHash.java (Pruebas)
Suite completa de pruebas que valida:
1. Operaciones Básicas: Inserción y recuperación
2. Actualización: Modificación de valores existentes
3. Colisiones: Manejo correcto de colisiones
4. Eliminación: Operación remove
5. ContainsKey: Verificación de existencia
6. Resize: Redimensionamiento automático
7. Tipos Genéricos: Diferentes tipos de datos
8. Estrés: 1000 elementos para validar rendimiento
9. Visualización: Estructura interna de la tabla

# Ejemplo de Uso
// Crear una tabla hash
TablaHash<String, Integer> inventario = new TablaHash<>();

// Insertar elementos
inventario.put("manzana", 5);
inventario.put("pera", 3);
inventario.put("naranja", 7);

// Actualizar valor existente
inventario.put("manzana", 10);  // Ahora manzana tiene valor 10

// Recuperar valores
Integer cantidad = inventario.get("manzana");  // Retorna 10
System.out.println("Manzanas: " + cantidad);

// Verificar existencia
if (inventario.containsKey("pera")) {
    System.out.println("Tenemos peras!");
}

// Eliminar elemento
Integer eliminado = inventario.remove("naranja");
System.out.println("Eliminadas " + eliminado + " naranjas");

// Obtener tamaño
System.out.println("Total de productos: " + inventario.size());
