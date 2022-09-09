import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.http.HttpClient;

public class Test {

    public static void main(String[] args) {
        HttpClient client = HttpClient.get(new Properties().set("redirect_policy", java.net.http.HttpClient.Redirect.ALWAYS));
        client.getAsync("https://assets.bierfrust.de");
    }

}
