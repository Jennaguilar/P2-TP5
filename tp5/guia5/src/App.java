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
         ContadorInseguro contadorInseguro = new ContadorInseguro();
 
        Thread hiloInseguro1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                contadorInseguro.incrementar();
            }
        });
 
        Thread hiloInseguro2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                contadorInseguro.incrementar();
            }
        });

            hiloInseguro1.start();
            hiloInseguro2.start();
            hiloInseguro1.join(); // espera que hilo1 termine
            hiloInseguro2.join(); // espera que hilo2 termine


        System.out.println("Valor esperado:  20000");
        System.out.println("Valor real (posiblemente menor): " + contadorInseguro.contador);
        System.out.println("-- ¡Condición de carrera detectada!");

    }
}
