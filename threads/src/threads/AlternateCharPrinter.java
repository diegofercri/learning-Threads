package threads;

public class AlternateCharPrinter {

    public static char ch = 65; // The starting character, 'A' in ASCII

    // Method to create and start threads
    private static void createAndStartThreads(int count) {
        Object lock = new Object(); // Lock object used for synchronization between threads
        for (int i = 0; i < count; i++) {
            // Start new threads with different characters (A, B, C, etc.)
            new Thread(new AlternateCharRunner((char) (65 + i), lock)).start();
        }
    }

    public static void main(String[] args) {
        // Start 5 threads (printing A, B, C, D, E)
        createAndStartThreads(5);
    }

}

class AlternateCharRunner implements Runnable {

    private char ch; // The character this thread will print
    private Object lock; // Lock object for synchronization
    private static int runnerCount; // Static counter to track the number of threads

    // Constructor that initializes the character and lock object
    public AlternateCharRunner(char ch, Object lock) {
        this.ch = ch; // Set the character this thread will print
        this.lock = lock; // Set the lock object for synchronization
        runnerCount++; // Increment the static counter of running threads
    }

    @Override
    public void run() {
        // Print the thread name and the character it will start printing
        System.out.println(Thread.currentThread().getName() + " started with character: " + ch);

        while (true) {
            synchronized (lock) {
                // Wait until it's this thread's turn to print the character
                while (ch != AlternateCharPrinter.ch) {
                    try {
                        lock.wait(); // The thread waits to be notified
                    } catch (InterruptedException e) {
                        e.printStackTrace(); // Handle interruption
                    }
                }

                // Print the character assigned to this thread
                System.out.println(Thread.currentThread().getName() + " printing: " + ch);
                AlternateCharPrinter.ch++; // Increment 'ch' to move to the next character

                // If 'ch' exceeds the number of threads, reset to 'A'
                if (AlternateCharPrinter.ch == (65 + runnerCount)) {
                    AlternateCharPrinter.ch = 65; // Reset to 'A' (ASCII 65)
                }

                // Notify all threads to check if it's their turn to print
                lock.notifyAll(); 

                try {
                    Thread.sleep(500); // Adjust the time between prints (500ms)
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Handle interruption
                }
            }
        }
    }
}
