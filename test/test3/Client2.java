public class Client2 extends Thread {

	public void run() {
		try{
			Thread.sleep(3000);
		} 
		catch(InterruptedException e){}
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int[] x;
		int[] y;
		x = Server.op3();
		y = Server.op3();
	}
}
