public class Client2 extends Thread {

	public void run() {
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int[] x = new int[2];
		x = ServerP.op3();
		ServerP.op4(x);
	}
}
