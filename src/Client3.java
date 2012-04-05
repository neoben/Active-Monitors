public class Client3 extends Thread {

	public void run() {
		
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int i;
		int j;
		i = ServerP.op1();
		ServerP.op2(i);
		j = ServerP.op1();
		ServerP.op2(j);
	}
}
