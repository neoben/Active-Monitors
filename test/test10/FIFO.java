public class FIFO {

    /* Elemento della coda First In First Out */
    private class elem {
        elem succ;
        Thread t;
    }

    /* Testa della coda FIFO */
    private elem testa;

    /* Costruttore classe FIFO */
    public FIFO() {
        testa = null;
    }

    /* Inserimento in coda */
    public void insert(Thread id) {
        elem el = new elem();
        el.t = id;
        el.succ = null;
        if(testa == null)
            testa = el;
        else {	
            elem app;
            for(app = testa; app.succ != null; app = app.succ) {}
            app.succ = el;
        }
    }

    /* Estrazione in testa */
    public Thread extract() {
        Thread id;
        if(testa != null) {
            id = testa.t;
            testa = testa.succ;
            return id;
        }
        else
            return null;
    }
}
