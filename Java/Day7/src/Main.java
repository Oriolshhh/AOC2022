import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        String line;
        FileReader f = new FileReader("src\\puzzle7.txt");
        BufferedReader b = new BufferedReader(f);
        Directory mainDirectory = new Directory("/", 0, null);
        Directory currentDirectory = new Directory();

        while((line = b.readLine()) != null) {
            if(line.contains("$")) {
                String[] tokens = line.split(" ");
                if(tokens[1].equals("cd")) {
                    if(tokens[2].equals("..")) {
                        //go up one directory
                        currentDirectory = currentDirectory.upDirectory;
                    } else if (tokens[2].equals("/")) {
                        //go to main directory
                        currentDirectory = mainDirectory;
                    } else{
                        //we have cd "nameDirectory"
                        //create a temp directory with the new name
                        Directory temp = new Directory(tokens[2], 0, currentDirectory);
                        //add that temp directory to the subdirectories the currentDirectory has
                        currentDirectory.subDirectories.add(temp);
                        //update the current directory
                        currentDirectory = temp;
                    }
                } else{
                    //ls command that we can ignore
                }
            }else{
                String[] tokens = line.split(" ");
                if(tokens[0].matches("[0-9]+")) {
                    //we have a file
                    currentDirectory.fileSize = currentDirectory.fileSize + Integer.parseInt(tokens[0]);
                } else{
                    //dir command that we can ignore
                }
            }
        }

        //get all mainDirectory subDirectories
        ArrayList<Directory> secondDirectories = mainDirectory.subDirectories;
        System.out.println(secondDirectories.size());
        //System.out.println("SOLUTION 1: "+firstSolution(secondDirectories));
        System.out.println("SOLUTION 2: "+secondSolution(mainDirectory));

    }

    private static int min = Integer.MAX_VALUE;
    private static int secondSolution(Directory mainDirectory) {
        //get total size of the / directory
        int totalSize = getSizeFromDirectories(mainDirectory);

        //calculate needed size
        int neededSize = 30000000 - (70000000 - totalSize);

        //get the subdirectories of / directory
        ArrayList<Directory> secondDirectories = mainDirectory.subDirectories;

        for (int i = 0; i < secondDirectories.size(); i++) {
            //get on a temp Directory the indicate directory
            Directory temp = secondDirectories.get(i);

            //get the size of that directory + the size of their subdirectories which we will calculate recursively
            int size = getSizeFromDirectories(temp);

            //if the size of the directory is lower than the needed size
            if(size > neededSize) {
                //compare with min which was the lower directory fileSize found
                if(size < min) {
                    min = size;
                }
            }

            //add the subdirectories of the temp directory to also check their sizes
            for (int j = 0; j < temp.subDirectories.size(); j++) {
                secondDirectories.add(temp.subDirectories.get(j));
            }

        }

        //return the lower fileSize found
        return min;
    }

    private static int firstSolution(ArrayList<Directory> list) {
        int sumTotalSize = 0;

        for (int i = 0; i < list.size(); i++) {
            //get on a temp Directory
            Directory temp = list.get(i);

            //get the size of that directory + the size of their subdirectories which we will calculate recursively
            int size = getSizeFromDirectories(temp);

            //if the size of the directory is lower than 100000
            if(size <= 100000) {
                sumTotalSize += size;
            }

            //add the subdirectories of the temp directory to also check their sizes
            for (int j = 0; j < temp.subDirectories.size(); j++) {
                list.add(temp.subDirectories.get(j));
            }
        }

        return sumTotalSize;
    }

    private static int getSizeFromDirectories(Directory directory) {
        //if we only have one subDirectory
        if(directory.subDirectories.size() == 0) {
            return directory.fileSize;
        }

        //get firstly the fileSize of that directory (the files that the directory directly has)
        int fileSize = directory.fileSize;

        //get the size of each of their subDirectories
        for (int i = 0; i < directory.subDirectories.size(); i++) {
            fileSize  = fileSize + getSizeFromDirectories(directory.subDirectories.get(i));
        }

        return fileSize;
    }
}