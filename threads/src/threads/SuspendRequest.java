package threads;

// Clase que implementa dos métodos sincronizados para suspender y reanudar la ejecución
public class SuspendRequest {
	private boolean suspend; // Variable que controla la suspensión
	
	// Este método permanece en suspensión (usando wait()) mientras 'suspend' sea true
	public synchronized void waitForResume() throws InterruptedException {
		while (suspend)
			wait(); // Suspende el hilo hasta recibir notify() o notifyAll()
	}

	// Este método asigna un valor a 'suspend' y notifica a los hilos en espera
	public synchronized void set(boolean b) {
		suspend = b; // Permite el cambio de estado sobre el objeto
		notifyAll(); // Despierta los hilos en espera según el valor de 'suspend'
	}
}
