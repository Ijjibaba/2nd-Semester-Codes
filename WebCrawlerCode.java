import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerCode {


    private Set<String> visitedLinks = new HashSet<>();

    
    public void crawl(String startUrl) {
        if (visitedLinks.contains(startUrl)) {
            return;
        }

        System.out.println("Crawling: " + startUrl);
        visitedLinks.add(startUrl);

        try {
            URL url = new URL(startUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuilder html = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                html.append(line);
            }
            reader.close();


            Pattern pattern = Pattern.compile("<a\\s+[^>]*href=[\"']([^\"'>\\s]+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(html.toString());

            while (matcher.find()) {
                String link = matcher.group(1);

                // Print only absolute URLs starting with http or https
                if (link.startsWith("http")) {
                    System.out.println("  Found: " + link);
                }
            }

        } catch (Exception e) {
            System.err.println("Failed to crawl: " + startUrl + " ->>> " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String startingUrl = "https://uok.edu.pk/faculties/computerscience/ubit.php";
        WebCrawlerCode crawler = new WebCrawlerCode();
        crawler.crawl(startingUrl);
    }
}