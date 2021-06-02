import java.util.ArrayList;

public class EZFunctions {
    public static String BuildString(ArrayList<String> arr){
        String newString = String.join(" ", arr);
        if(newString.startsWith("\"")){
            newString = newString.substring(1);
        }
        if(newString.endsWith("\"")){
            newString = newString.substring(0, (newString.length() - 1));
        }
        return newString;
    }

    public static boolean ifCondition(ArrayList<String> wordList){
        if(wordList.get(2).toLowerCase().equals("variable")){ //variable comparison
            String obj1Val = Interpreter.globalVariables.get(wordList.get(3))[1];
            String obj1Type = Interpreter.globalVariables.get(wordList.get(3))[0];

            String obj2Val = Interpreter.globalVariables.get(wordList.get(9))[1];
            String obj2Type = Interpreter.globalVariables.get(wordList.get(9))[0];
        }
        
        return false;
    }
}
