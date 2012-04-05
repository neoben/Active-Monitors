public class Client1 extends Thread {

	public void run() {
		System.out.println();
        	System.out.println("CLIENT " + Thread.currentThread().getId() + " ATTIVATO");
		int[] x = new int[2];
		int i;
		int j;
		x = Server.op3();
		i = Server.op1();
		try{
			Thread.sleep(4000);
		} 
		catch(InterruptedException e){}
		Server.op2(i);		
	}
}
