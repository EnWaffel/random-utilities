import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.io.HttpFile;

public class Test {

    public static void main(String[] args) {
        HttpFile file = new HttpFile("https://t6.rbxcdn.com/345a2d3438973c298fddd66d23497bc1");
        file.downloadContentToFile(FileOrPath.path("image.png"));
    }

}
