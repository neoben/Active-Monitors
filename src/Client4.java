public class Client4 extends Thread {

	public void run() {
		try{
			Thread.sleep(3000);
		} 
		catch(InterruptedException e){}
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int[] x;
		int[] y;
		x = ServerP.op3();
		ServerP.op4(x);
		y = ServerP.op3();
		ServerP.op4(y);
	}
}
