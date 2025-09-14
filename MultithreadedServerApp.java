import java.io.*;
import java.net.*;

public class MultithreadedServerApp {

    // Max number of clients the server will accept
    static final int MAX_CLIENTS = 20;

    // Array to hold client threads
    static ClientHandler[] clients = new ClientHandler[MAX_CLIENTS];

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);

                // Add new client to the first empty slot
                for (int i = 0; i < MAX_CLIENTS; i++) {
                    if (clients[i] == null) {
                        clients[i] = new ClientHandler(socket, clients, i);
                        clients[i].start();
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

// Thread that handles each client
class ClientHandler extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    ClientHandler[] clients;
    int index;
    String name;

    public ClientHandler(Socket socket, ClientHandler[] clients, int index) {
        this.socket = socket;
        this.clients = clients;
        this.index = index;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error setting up streams.");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void run() {
        try {
            out.println("Enter your name:");
            name = in.readLine();
            System.out.println(name + " joined the chat.");
            broadcast(name + " has joined the chat.");

            String msg;
            while ((msg = in.readLine()) != null) {
                if (msg.equalsIgnoreCase("QUIT")) {
                    break;
                }
                System.out.println(name + ": " + msg);
                broadcast(name + ": " + msg);
            }

            // Disconnect
            System.out.println(name + " left the chat.");
            broadcast(name + " has left the chat.");
            clients[index] = null;
            socket.close();

        } catch (IOException e) {
            System.out.println("Client disconnected unexpectedly.");
        }
    }

    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            if (client != null && client != this) {
                client.sendMessage(message);
            }
        }
    }
}