import java.io.*;
import java.net.*;

public class Server {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
    }

    public void start() throws IOException {
        System.out.println("Waiting for client connection...");
        try (Socket clientSocket = serverSocket.accept()) {
            System.out.println("Client connected!");

            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))
            ) {
                String command;
                while ((command = in.readLine()) != null) {
                    System.out.println("Client says: " + command);

                    if (command.equalsIgnoreCase("quit")) {
                        out.println("Goodbye! Server is shutting down.");
                        break;
                    } else if (command.equalsIgnoreCase("dir")) {
                        handleDir(out);
                    } else if (command.startsWith("get ")) {
                        String fileName = command.substring(4).trim();
                        handleGet(fileName, out);
                    } else {
                        handleCustomReply(serverInput, out);
                    }
                }
            }
        }
        stop();
    }

    private void handleDir(PrintWriter out) {
        File dir = new File(".");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    out.println(file.getName());
                }
            }
        }
        out.println("EOF");
    }

    private void handleGet(String fileName, PrintWriter out) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }
            } catch (IOException e) {
                out.println("Error reading file: " + e.getMessage());
            }
        } else {
            out.println("File not found.");
        }
        out.println("EOF");
    }

    private void handleCustomReply(BufferedReader serverInput, PrintWriter out) throws IOException {
        System.out.print("Enter reply to client: ");
        String reply = serverInput.readLine();
        out.println(reply);
        out.println("EOF");
    }

    public void stop() throws IOException {
        serverSocket.close();
        System.out.println("Server terminated.");
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(9090);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
