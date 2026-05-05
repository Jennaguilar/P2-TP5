public class TareaRunnable implements Runnable {
    private String nombre;
 
    public TareaRunnable(String nombre) {
        this.nombre = nombre;
    }
 
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + nombre + " | hilo: "
                    + Thread.currentThread().getName() + "] = " + i);
        }
    }
}