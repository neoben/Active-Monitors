public class Client4 extends Thread {

	public void run() {
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int i;
		i = Server.op1();
	}
}
