public class Client1 extends Thread {

	public void run() {
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int i;
		i = ServerP.op1();
		ServerP.op2(i);
	}
}
