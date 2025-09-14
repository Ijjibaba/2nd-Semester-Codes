import java.io.*;
import java.net.*;

class WebPageDownloader {
    private String url;
    private String filePath;

    // Constructor
    public WebPageDownloader(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }

    // Method to download webpage content
    public String fetchContent() throws IOException {
        StringBuilder content = new StringBuilder();

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", 
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/119.0.0.0 Safari/537.36");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // Method to save content to file
    public void saveToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    // Method to perform full download and save
    public void download() {
        try {
            String content = fetchContent();
            saveToFile(content);
            System.out.println("Webpage saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

public class WebServer {
    public static void main(String[] args) {
        // Example usage
        String url = "https://en.wikipedia.org/wiki/Kumar_Sangakkara";
        String filePath = "file.html";   // relative path

        WebPageDownloader downloader = new WebPageDownloader(url, filePath);
        downloader.download();
    }
}