public class Client extends Thread {

	public void run() {
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int[] x = new int[2];
		x = Server.op3();
	}
}
