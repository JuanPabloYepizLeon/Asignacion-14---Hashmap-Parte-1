package test;

import estructuras.TablaHash;

/**
 * Clase de prueba para la implementación de TablaHash.
 * Contiene casos de prueba exhaustivos para validar todas las operaciones.
 * @author Equipo 5
 */
public class TestTablaHash {
    
    /**
     * Prueba las operaciones básicas de inserción y recuperación.
     */
    public static void testOperacionesBasicas() {
        System.out.println("TEST 1: Operaciones Basicas");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        // Insertar elementos
        mapa.put("manzana", 5);
        mapa.put("pera", 3);
        mapa.put("naranja", 7);
        
        System.out.println("Insertados 3 elementos");
        System.out.println("Size: " + mapa.size()); // Esperado: 3
        
        // Recuperar elementos
        System.out.println("manzana: " + mapa.get("manzana")); // Esperado: 5
        System.out.println("pera: " + mapa.get("pera"));       // Esperado: 3
        System.out.println("naranja: " + mapa.get("naranja")); // Esperado: 7
        
        // Buscar elemento inexistente
        System.out.println("platano: " + mapa.get("platano")); // Esperado: null
        
        System.out.println("Test 1 completado\n");
    }
    
    /**
     * Prueba la actualización de valores existentes.
     */
    public static void testActualizacion() {
        System.out.println("TEST 2: Actualizacion de Valores");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        mapa.put("manzana", 5);
        System.out.println("Valor inicial de manzana: " + mapa.get("manzana")); // 5
        
        // Actualizar valor
        mapa.put("manzana", 10);
        System.out.println("Valor actualizado de manzana: " + mapa.get("manzana")); // 10
        System.out.println("Size (debe seguir siendo 1): " + mapa.size()); // 1
        
        System.out.println("Test 2 completado\n");
    }
    
    /**
     * Prueba el manejo de colisiones con el encadenamiento separado.
     */
    public static void testColisiones() {
        System.out.println("TEST 3: Manejo de Colisiones");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        // Insertar elementos que potencialmente colisionan
        mapa.put("gato", 1);
        mapa.put("casa", 7);
        mapa.put("sol", 3);
        mapa.put("luna", 9);
        
        System.out.println("Insertados 4 elementos con posibles colisiones");
        System.out.println("gato: " + mapa.get("gato"));   // 1
        System.out.println("casa: " + mapa.get("casa"));   // 7
        System.out.println("sol: " + mapa.get("sol"));     // 3
        System.out.println("luna: " + mapa.get("luna"));   // 9
        
        System.out.println("Test 3 completado\n");
    }
    
    /**
     * Prueba la operación de eliminación.
     */
    public static void testRemove() {
        System.out.println("TEST 4: Eliminacion de Elementos");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        mapa.put("manzana", 5);
        mapa.put("pera", 3);
        mapa.put("naranja", 7);
        
        System.out.println("Size inicial: " + mapa.size()); // 3
        
        // Eliminar elemento existente
        Integer valorEliminado = mapa.remove("pera");
        System.out.println("Valor eliminado (pera): " + valorEliminado); // 3
        System.out.println("Size despues de eliminar: " + mapa.size()); // 2
        System.out.println("Buscar pera despues de eliminar: " + mapa.get("pera")); // null
        
        // Eliminar elemento inexistente
        Integer noExiste = mapa.remove("platano");
        System.out.println("Intentar eliminar inexistente: " + noExiste); // null
        System.out.println("Size (sin cambios): " + mapa.size()); // 2
        
        System.out.println("Test 4 completado\n");
    }
    
    /**
     * Prueba el método containsKey.
     */
    public static void testContainsKey() {
        System.out.println("TEST 5: ContainsKey");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        mapa.put("manzana", 5);
        mapa.put("pera", 3);
        
        System.out.println("Contiene 'manzana' " + mapa.containsKey("manzana")); // true
        System.out.println("Contiene 'pera' " + mapa.containsKey("pera"));       // true
        System.out.println("Contiene 'platano' " + mapa.containsKey("platano")); // false
        
        System.out.println("Test 5 completado\n");
    }
    
    /**
     * Prueba el redimensionamiento automático de la tabla.
     */
    public static void testResize() {
        System.out.println("TEST 6: Redimensionamiento Dinamico");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        System.out.println("Capacidad inicial: " + mapa.getCapacidad()); // 11
        System.out.println("Factor de carga inicial: " + 
                          String.format("%.3f", mapa.getFactorCarga())); // 0.000
        
        // Insertar suficientes elementos para forzar resize
        System.out.println("\nInsertando 9 elementos para forzar resize...");
        for (int i = 1; i <= 9; i++) {
            mapa.put("clave" + i, i * 10);
        }
        
        System.out.println("Size: " + mapa.size()); // 9
        System.out.println("Capacidad despues de resize: " + mapa.getCapacidad()); // 22
        System.out.println("Factor de carga despues de resize: " + 
                          String.format("%.3f", mapa.getFactorCarga()));
        
        // Verificar que todos los elementos siguen accesibles
        System.out.println("\nVerificando que todos los elementos son accesibles:");
        for (int i = 1; i <= 9; i++) {
            Integer valor = mapa.get("clave" + i);
            System.out.println("clave" + i + ": " + valor);
        }
        
        System.out.println("Test 6 completado\n");
    }
    
    /**
     * Prueba con diferentes tipos de datos.
     */
    public static void testTiposGenericos() {
        System.out.println("TEST 7: Tipos Genericos");
        
        // TablaHash con Integer como clave
        TablaHash<Integer, String> mapaInt = new TablaHash<>();
        mapaInt.put(1, "uno");
        mapaInt.put(2, "dos");
        mapaInt.put(3, "tres");
        
        System.out.println("TablaHash<Integer, String>:");
        System.out.println("1: " + mapaInt.get(1));
        System.out.println("2: " + mapaInt.get(2));
        System.out.println("3: " + mapaInt.get(3));
        
        // TablaHash con String como clave y valor
        TablaHash<String, String> mapaStr = new TablaHash<>();
        mapaStr.put("ES", "Espana");
        mapaStr.put("MX", "Mexico");
        mapaStr.put("AR", "Argentina");
        
        System.out.println("\nTablaHash<String, String>:");
        System.out.println("ES: " + mapaStr.get("ES"));
        System.out.println("MX: " + mapaStr.get("MX"));
        System.out.println("AR: " + mapaStr.get("AR"));
        
        System.out.println("Test 7 completado\n");
    }
    
    /**
     * Prueba de estrés con muchos elementos.
     */
    public static void testEstres() {
        System.out.println("TEST 8: Prueba de Estres");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        int numElementos = 1000;
        System.out.println("Insertando " + numElementos + " elementos...");
        
        // Insertar
        for (int i = 0; i < numElementos; i++) {
            mapa.put("elemento" + i, i);
        }
        
        System.out.println("Size: " + mapa.size());
        System.out.println("Capacidad: " + mapa.getCapacidad());
        System.out.println("Factor de carga: " + 
                          String.format("%.3f", mapa.getFactorCarga()));
        
        // Verificar algunos elementos aleatorios
        System.out.println("\nVerificando elementos aleatorios:");
        System.out.println("elemento0: " + mapa.get("elemento0"));
        System.out.println("elemento500: " + mapa.get("elemento500"));
        System.out.println("elemento999: " + mapa.get("elemento999"));
        
        // Eliminar algunos elementos
        System.out.println("\nEliminando 100 elementos...");
        for (int i = 0; i < 100; i++) {
            mapa.remove("elemento" + i);
        }
        System.out.println("Size despues de eliminar: " + mapa.size());
        
        System.out.println("Test 8 completado\n");
    }
    
    /**
     * Muestra la estructura interna de la tabla.
     */
    public static void testVisualizacion() {
        System.out.println("TEST 9: Visualizacion de Estructura");
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        mapa.put("gato", 1);
        mapa.put("casa", 7);
        mapa.put("sol", 3);
        mapa.put("luna", 9);
        mapa.put("mar", 5);
        
        System.out.println(mapa.toString());
        System.out.println("Test 9 completado\n");
    }
    
    /**
     * Método principal que ejecuta todas las pruebas.
     */
    public static void main(String[] args) {
        System.out.println("PRUEBAS DE TABLA HASH CON ENCADENAMIENTO SEPARADO\n");

        testOperacionesBasicas();
        testActualizacion();
        testColisiones();
        testRemove();
        testContainsKey();
        testResize();
        testTiposGenericos();
        testEstres();
        testVisualizacion();
        
        System.out.println("TODAS LAS PRUEBAS COMPLETADAS");
    }
}
