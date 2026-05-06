public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===== SECCIÓN 1: Implementación de Tareas (Creación de Hilos) =====\n");
        System.out.println("-- Ejercicio 1.1: extends Thread --");
        TareaHilo h1 = new TareaHilo("Hilo-A");
        TareaHilo h2 = new TareaHilo("Hilo-B");
        TareaHilo h3 = new TareaHilo("Hilo-C");

        //crea un hilo y ejecuta run() 
        h1.start();
        h2.start();
        h3.start();

        // el join() bloquea el hilo actual (main) hasta que ese hilo finalice.
        h1.join();
        h2.join();
        h3.join();

        System.out.println("\n-- Ejercicio 1.2: implements Runnable --");
        TareaRunnable tarea = new TareaRunnable("Tarea");

        // new Thread(tarea) → crea un hilo que ejecutará tarea.run()
        Thread t1 = new Thread(tarea);
        Thread t2 = new Thread(tarea);
 
        t1.start();
        t2.start();
 
        t1.join();
        t2.join();
    

         System.out.println("\n===== SECCIÓN 2: Condición de Carrera (SIN sincronización) =====\n");
         System.out.println("-- Ejercicio 2.1: anomalía sin sincronización --");
         Contador contador = new Contador();
 
        Thread hiloInseguro1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                contador.incrementarInseguro();
            }
        });
 
        Thread hiloInseguro2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                contador.incrementarInseguro();
            }
        });

            hiloInseguro1.start();
            hiloInseguro2.start();
            hiloInseguro1.join(); // espera que hilo1 termine
            hiloInseguro2.join(); // espera que hilo2 termine

        System.out.println("Valor esperado:  20000");
        System.out.println("Valor real (posiblemente menor): " + contador.getCopias());
        System.out.println("-- ¡Condición de carrera detectada!");
        contador.reset(); // reiniciamos el contador para las siguientespruebas


        System.out.println("\n===== SECCIÓN 3: Solución con Sincronización =====\n");
        System.out.println("-- Ejercicio 3.1: método synchronized --");
        
        Thread hiloSeguro1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                contador.incrementarSyncMetodo(); // primera solucion
            }
        });

        Thread hiloSeguro2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                contador.incrementarSyncMetodo(); // Segunda solucion
        }
    });

            hiloSeguro1.start();
            hiloSeguro2.start();
            hiloSeguro1.join();
            hiloSeguro2.join();

        System.out.println("Valor esperado:  20000");
        System.out.println("Valor real: " + contador.getCopias());
        contador.reset();


        System.out.println("\n-- Ejercicio 3.2: bloque synchronized --");
        Thread hiloBloque1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                contador.incrementarSyncBloque(); // Solución 2
            }
        });

        Thread hiloBloque2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                contador.incrementarSyncBloque(); // Solución 2
            }
        });

        hiloBloque1.start();
        hiloBloque2.start();
        hiloBloque1.join();
        hiloBloque2.join();

        System.out.println("Valor esperado:  20000");
        System.out.println("Valor real: " + contador.getCopias());
    }
}
