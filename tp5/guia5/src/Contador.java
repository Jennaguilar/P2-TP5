public class Contador {
 private int copias = 0;
 private final Object lock = new Object();

    // ejercicio 3.1 sincronizar el metodo completo
    public synchronized void incrementarInseguro() {
        copias++;
    }
    // El hilo adquiere el monitor del objeto actual el (this)
    public synchronized void incrementarSyncMetodo(){
        copias++;
    }

    // Bloque sincronizado
    //solo bloqueamos la instruccion de practica
    public void incrementarSyncBloque(){
        synchronized (lock){
            copias++;
        }
    }

    // Método seguro para leer el valor
    public int getCopias() {
        synchronized (lock) {
            return copias;
        }
    }

    // para reiniciar el contador entre ejercicios
    public void reset(){
        copias=0;
    }

}
