package Lab.File_Handling;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadWrite {
    public void writeToFile(String fileName, String data) {
        File file = new File("./Java/Lab/File_Handling/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
        
    }

    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File("./Java/Lab/File_Handling/" + fileName);
        if (!file.exists()) {
            System.out.println("File does not exist");
        } else {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    data.add(sc.nextLine());
                }
                sc.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return data;
    }
    public static void main(String[] args) {
        ReadWrite rw = new ReadWrite();
        rw.writeToFile("Output.txt", "Hello World");
        ArrayList<String> data = rw.readFromFile("Input.txt");
        for (String s : data) {
            System.out.println(s);
        }
    }
}
