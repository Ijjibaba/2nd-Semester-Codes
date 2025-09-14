import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public String host;
    public int port;
    public Socket socket;
    public PrintWriter out;
    public BufferedReader in;
    public Scanner scanner;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.scanner = new Scanner(System.in);
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Connected to server at " + host + ":" + port);
    }

    public void start() throws IOException {
        while (true) {
            System.out.print("Enter command (dir, get filename, quit): ");
            String command = scanner.nextLine();
            out.println(command);

            if (command.equalsIgnoreCase("quit")) {
                readAllFromServer();
                disconnect();
                break;
            }

            readResponse();
        }
    }

    private void readResponse() throws IOException {
        String response;
        while ((response = in.readLine()) != null) {
            if (response.equals("EOF")) break;
            System.out.println("Server: " + response);
        }
    }

    private void readAllFromServer() throws IOException {
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("Server: " + line);
        }
    }

    public void disconnect() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("Disconnected from server.");
    }

    public static void sendQuitCommand() {
        try (Socket socket = new Socket("localhost", 9090);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("quit");
        } catch (IOException e) {
            System.out.println("Could not send quit to server (server might not be running).");
        }
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter server IP address (or type 'quit' to exit): ");
                String host = inp.nextLine();
                if (host.equalsIgnoreCase("quit")) {
                    sendQuitCommand();
                    break;
                }

                System.out.print("Enter port number: ");
                int port = Integer.parseInt(inp.nextLine());

                Client client = new Client(host, port);
                client.connect();
                client.start();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        inp.close();
    }
}
