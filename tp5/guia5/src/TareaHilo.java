public class TareaHilo extends Thread {
    private String nombre;

    public TareaHilo(String nombre){
        this.nombre = nombre;
    }



    @Override 
    //Thread.currentThread().getName() devuelve el nombre interno del hilo.
    public void run(){
         for (int i = 1; i <= 5; i++) {
            System.out.println("[" + nombre + " | hilo: "
                    + Thread.currentThread().getName() + "] = " + i);
        }
    }
}