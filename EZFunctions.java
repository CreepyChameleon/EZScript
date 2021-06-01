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
}
