public class Client extends Thread {

	public void run() {
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int i;
		int j;
		int[] x = new int[2];
		int[] y = new int[2];
		i = Server.op1();
		j = Server.op1();
		Server.op2(i);
		Server.op2(j);
		x = Server.op3();
		Server.op4(x);
		y= Server.op3();
		Server.op4(y);
	}
}
