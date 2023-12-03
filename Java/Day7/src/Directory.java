import java.util.ArrayList;

public class Directory {
    public String name;
    public int fileSize;
    public ArrayList<Directory> subDirectories = new ArrayList<>();
    public Directory upDirectory;

    public Directory() { }

    public Directory(String name, int fileSize, Directory upDirectory) {
        this.name = name;
        this.fileSize = fileSize;
        this.upDirectory = upDirectory;
    }
}
