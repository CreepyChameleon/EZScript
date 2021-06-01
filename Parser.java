import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
To do
check types of values
*/

public class Parser
{
    public static void main(String[] args){
        boolean boot = true;
        while(boot == true){
            boot = false;
            File file = new File("testFile.EZ");
            parse(file);
        }
    }

    public static void parse(File file){
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                //System.out.println("NEW-LINE");
                System.out.println(data);
                Interpreter.interpret(data);
            }
            scanner.close();
        } catch (FileNotFoundException e){ //if file does not exist an exception will be thrown
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}