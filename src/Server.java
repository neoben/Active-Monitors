public class Server extends Thread {

    static int disponibili; //Numero di risorse disponibili
    static boolean libera[]; //Risorse libere
    final static int op1 = 0;
    final static int op2 = 1;
    final static int op3 = 2;
    final static int op4 = 3;
    static Entries e = new Entries(4);

    /* Costruttore classe Server */
    public Server(int n) {
        disponibili = n;
        libera = new boolean[n];
        for(int i = 0; i < n; i++)
            libera[i] = true;
    }

    /* op1(): richiede una singola risorsa */
    public static int op1() {
        e.call(op1);
	System.out.println();
        System.out.println("CLIENT CHIAMANTE: " + Thread.currentThread().getId());     
	System.out.println("Esecuzione metodo op1 :: Richiede una singola risorsa");
	int i = 0;
        while(!libera[i])
            i++;
        libera[i] = false;
        System.out.println("RISORSA RICHIESTA: " + i);
        disponibili--;
        System.out.println("RISORSE DISPONIBILI: " + disponibili);
        e.fineRendez_vous();
        return i;
    }

    /* op2(): rilascia una singola risorsa */
    public static void op2(int r) {
        e.call(op2);
	System.out.println();
        System.out.println("CLIENT CHIAMANTE: " + Thread.currentThread().getId());
	System.out.println("Esecuzione metodo op2 :: Rilascia una singola risorsa");
        libera[r] = true;
        System.out.println("RISORSA RILASCIATA: " + r);
        disponibili++;
        System.out.println("RISORSE DISPONIBILI: " + disponibili);
        e.fineRendez_vous();
    }

    /* op3(): richiede contemporaneamente due risorse */
    public static int[] op3() {
        e.call(op3);
	System.out.println();
        System.out.println("CLIENT CHIAMANTE: " + Thread.currentThread().getId());
	System.out.println("Esecuzione metodo op3 :: Richiede contemporaneamente due risorse");
        int app;
        int[]vet = new int[2];
        int i = 0;
        while(!libera[i])
            i++;
        libera[i] = false;
        System.out.println("RISORSA RICHIESTA: " + i);
        int j = 0;
        while(!libera[j])
            j++;
        libera[j] = false;
        System.out.println("RISORSA RICHIESTA: " + j);
        vet[0] = i;
        vet[1] = j;
        disponibili = disponibili - 2;
        System.out.println("RISORSE DISPONIBILI: " + disponibili);
        e.fineRendez_vous();
        return vet;
    }

    /* op4(): rilascia contemporaneamente due risorse */
    public static void op4(int[] r) {
        e.call(op4);
        int ind1;
        int ind2;
	System.out.println();
        System.out.println("CLIENT CHIAMANTE: " + Thread.currentThread().getId());
	System.out.println("Esecuzione metodo op4 :: Rilascia contemporaneamente due risorse");
        ind1 = r[0];
        ind2 = r[1];
        libera[ind1] = true;
        System.out.println("RISORSA RILASCIATA: " + ind1);
        libera[ind2] = true;
        System.out.println("RISORSA RILASCIATA: " + ind2);
        disponibili = disponibili + 2;
        System.out.println("RISORSE DISPONIBILI: " + disponibili);
        e.fineRendez_vous();
    }

    public void run() {
        int[] vet = new int[4];
        System.out.println();
	System.out.println("********************");
        System.out.println("SERVER ATTIVATO");
	System.out.println("********************");
        while(true) { //Ciclo infinito :: Server sempre attivo
            if(disponibili == 0) {
                vet[0] = op2;
                vet[1] = op4;
                e.accept(vet, 2);
                continue;
            }
            if(disponibili == 1) {
                vet[0] = op1;
                vet[1] = op2;
                vet[2] = op4;
                e.accept(vet, 3);
                continue;
            }
            if(disponibili >= 2) {
                vet[0] = op1;
                vet[1] = op2;
                vet[2] = op3;
                vet[3] = op4;
                e.accept(vet, 4);
                continue;
            }
        }
    }

    public static void main(String args[]) {
        if(args.length < 1) {
            System.out.println("COMANDO SERVER ERRATO");
            System.out.println("DIGITARE: java Server <numero_di_risorse>");
            return;
        }
        int risorse;
        risorse = Integer.parseInt(args[0]);
        Server s = new Server(risorse);
        s.start();
	Client1 cl1 = new Client1();
	cl1.start();
	Client2 cl2 = new Client2();
	cl2.start();
    }
}
