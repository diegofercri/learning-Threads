package threads;

public class SolicitaSuspender {
	private boolean suspender;
	
	public synchronized void esperandoParaReanudar() throws InterruptedException {
		while (suspender)
			wait();
	}

	public synchronized void set(boolean b) {
		suspender = b;
		notifyAll();
	}
}