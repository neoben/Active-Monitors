public class Client1 extends Thread {

	public void run() {
		
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int i;
		int j;
		i = Server.op1();
		Server.op2(i);
		j = Server.op1();
		Server.op2(j);
	}
}
