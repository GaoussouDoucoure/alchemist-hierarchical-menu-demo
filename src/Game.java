import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

class Menu{
    String path;
    ArrayList<String> items;
    Menu(String path){
        this.path = path;
        this.items = new ArrayList<String>();
        String fullPath = path + "index.txt";
        System.out.println(fullPath);
        try {
            Scanner s = new Scanner(new File(fullPath));
            while(s.hasNextLine()){
                this.items.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    void show(){
        while(true) {
            for(String item : this.items){
                System.out.println(item);
            }
            Scanner s = new Scanner(System.in);
            String command = s.nextLine().trim();
            System.out.println("received command: " + command);
            for (String item : this.items) {
                char c = command.charAt(0);
                if (item.charAt(0) == c) {
                    System.out.println("Command Found!!!" + item);
                    String[] parts = item.split(" - ");
                    if (c == 'x' || c == 'X') {
                        return; // exit loop
                    }
                    String path = ".\\data\\" + parts[1] + "\\";
                    String fullPath = path + "index.txt";
                    if(Files.exists(Path.of(fullPath))){
                        Menu child = new Menu(path);
                        child.show();
                    }else {
                        System.out.println("Handle Action: " + c);
                    }
                    break;
                }
            }
        }
    }
}
public class Game{
    public static void main(String[] args) {
        Menu m = new Menu(".\\data\\");
        m.show();
    }
}