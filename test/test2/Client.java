public class Client extends Thread {

	public void run() {
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int i;
		int j;
		int x;
		int y;
		i = Server.op1();
		j = Server.op1();
		x = Server.op1();
		y = Server.op1();
	}
}
