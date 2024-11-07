package threads;

// Class that implements two synchronized methods to allow suspending and resuming execution
// Clase que implementa dos métodos sincronizados para suspender y reanudar la ejecución
public class SuspendRequest {
	private boolean suspend; // Variable to control suspension / Variable que controla la suspensión
	
	// This method waits in suspension (using wait()) while 'suspend' is true
	// Este método permanece en suspensión (usando wait()) mientras 'suspend' sea true
	public synchronized void waitForResume() throws InterruptedException {
		while (suspend)
			wait(); // Suspends the thread until receiving notify() or notifyAll() / Suspende el hilo hasta recibir notify() o notifyAll()
	}

	// This method sets the 'suspend' variable and notifies waiting threads
	// Este método asigna un valor a 'suspend' y notifica a los hilos en espera
	public synchronized void set(boolean b) {
		suspend = b; // Allows the object state to change / Permite el cambio de estado sobre el objeto
		notifyAll(); // Wakes up waiting threads based on 'suspend' value / Despierta los hilos en espera según el valor de 'suspend'
	}
}
