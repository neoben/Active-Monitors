public class Entries {

    private Semaphore mutex; //Semaforo di mutua esclusione
    private Semaphore richiesta; //Semaforo condizione
    private int num_en; //Numero di entries
    boolean accettata[]; //Richiesta accettata sulla entry
    private Clients c[]; //Richieste effettuate sulle entries dai client

    /* Costruttore classe Entries */
    public Entries(int n) {
        accettata = new boolean[n];
        c = new Clients[n];
        mutex = new Semaphore(1);
        richiesta = new Semaphore(0);
        num_en = n;
        for(int i = 0; i < n; i++) {
            accettata[i] = false;
            c[i] = new Clients();
        }
    }

    /* Metodo call */
    public void call(int x) {
        mutex.p();
	System.out.println();
        System.out.println("CLIENT CHIAMANTE: " + Thread.currentThread().getId());
        System.out.println("Esecuzione metodo call");
        if(accettata[x]) {
            for(int i = 0; i < num_en; i++)
                accettata[i] = false;
	    mutex.v();
        }
        else {
            c[x].blocca();
	}
    }

    /* Metodo fineRendez_vous */
    public void fineRendez_vous() {
	System.out.println();
	System.out.println("CLIENT CHIAMANTE: " + Thread.currentThread().getId());
        System.out.println("Esecuzione metodo fineRendez_vous");	
	richiesta.v();
    }

    /* Metodo accept */
    public void accept(int[] vet, int n) {
	int indice;	
        int vuote = 0; //Numero di code di richieste sulle entries vuote
	mutex.p();
        for(int i = 0; i < n; i++) {
            indice = vet[i];
            if(c[indice].vuota())
                vuote++;
        }
        if(vuote == n) {
            for(int i = 0; i < n; i++) {
                indice = vet[i];
                accettata[indice] = true;
            }
        }
        else {
            for(int i = 0; i < n; i++) {
                indice = vet[i];
                if(c[indice].da_sbloccare())
                    break;
            }
	}
        mutex.v();
	System.out.println();
	System.out.println("SERVER");
	System.out.println("Esecuzione metodo accept :: Server in attesa di richieste");
        richiesta.p();
    }	

    private class Clients {

        private int bloccati; //Numero di clienti bloccati in coda
        private FIFO coda; //Coda FIFO dei client
        private Thread sbloccato; //Id del Thread sbloccato

        /* Costruttore classe Clients */
         public Clients() {
             coda = new FIFO();
             bloccati = 0;
             sbloccato = null;
         }

        /* blocca(): inserisce la richiesta client in coda */
        public synchronized void blocca() {
            coda.insert(Thread.currentThread());
            bloccati++;
	    mutex.v();
            while(sbloccato != Thread.currentThread()) {
                try {
                    wait();
                }
                catch(InterruptedException e){}
            }
            sbloccato = null;
	    notifyAll();
        }

        /* sblocca(): estrae la richiesta client dalla coda */
        public synchronized boolean da_sbloccare() {
	    mutex.v();
            while(sbloccato != null) {
                try {
                    wait();
                }
                catch(InterruptedException e){}
            }
            if(bloccati > 0) {
                sbloccato = coda.extract();
                bloccati--;
                notifyAll();
                return true;
            }
            return false;
        }

        public synchronized boolean vuota() {
            if(bloccati == 0)
                return true;
	    else
            	return false;
        }
    }
}
