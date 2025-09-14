import java.io.*;
import java.net.*;

public class MultithreadedClientApp {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9090);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read messages from server in another thread
            new ReadThread(in).start();

            // Send user input to server
            String input;
            while ((input = userInput.readLine()) != null) {
                out.println(input);

                if (input.equalsIgnoreCase("QUIT")) {
                    System.out.println("You left the chat.");
                    break;
                }
            }

            socket.close();

        } catch (IOException e) {
            System.out.println("Could not connect to server.");
        }
    }
}

class ReadThread extends Thread {
    BufferedReader in;

    public ReadThread(BufferedReader in) {
        this.in = in;
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Disconnected from server.");
        }
    }
}