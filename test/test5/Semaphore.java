public class Semaphore {

    private FIFO coda; //Coda FIFO del semaforo
    private int valore; //Valore del semaforo
    private int bloccati; //Elementi bloccati in coda sul semaforo
    private Thread sbloccato; //Id del Thread sbloccato

    /* Costruttore classe Semaphore */
    public Semaphore(int s) {
        coda = new FIFO();
        valore = s;
        bloccati = 0;
        sbloccato = null;
    }

    /* p(): blocca la risorsa sul semaforo */
    public synchronized void p() {
        if(valore > 0) {
            valore--;
	}
        else {
            coda.insert(Thread.currentThread());
            bloccati++;
            while(Thread.currentThread() != sbloccato) {
                try {
                    wait();
                }
                catch(InterruptedException e){}
            }
            sbloccato = null;
            notifyAll();
        }
    }

    /* Sblocca la risorsa sul semaforo */
    public synchronized void v() {
        while(sbloccato != null) {
                try {
                    wait();
                }
                catch(InterruptedException e){}
        }
        if(bloccati == 0)
            valore++;
        else {
            sbloccato = coda.extract();
            bloccati--;
            notifyAll();
        }
    }
}
