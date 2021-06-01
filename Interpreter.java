import java.util.HashMap;
import java.util.ArrayList;

public class Interpreter {
    public boolean looping = false;
    public static HashMap<String, String[]> globalVariables = new HashMap<String, String[]>();
    // globalVariables.put(a, b);
    // globalVariables.get(a);

    public static int interpret(String data){
        //if a comment
        if(data.startsWith("//")){
            System.out.println("comment");
            return 0;
        }
        //if an empty line
        if(data.equals("")){
            System.out.println("EMPTY");
            return 0;
        }
        String[] sW = data.split(" "); //split sentence into array of words
        ArrayList<String> scriptWords = new ArrayList<String>();
        for(String i : sW){
            scriptWords.add(i);
        }
        // for(int i=0; i<scriptWords.length; i++){
        //     System.out.println(scriptWords[i]);
        // }


        //if variable function
        if(scriptWords.get(0).toLowerCase().equals("the") && scriptWords.get(1).toLowerCase().equals("variable")){
            //if setting a variable
            if(scriptWords.get(3).toLowerCase().equals("is") && scriptWords.get(4).toLowerCase().equals("equal") && scriptWords.get(5).toLowerCase().equals("to")){
                System.out.println("SETTING VAR");
                //if an input

                //if a random

                //if a String
                if(scriptWords.get(7).toLowerCase().equals("string")){
                    String vName = scriptWords.get(2);
                    String vType = "string";
                    for(int i=0;i<8;i++){
                        scriptWords.remove(0);
                    }
                    String s = EZFunctions.BuildString(scriptWords);
                    setVar(vName, vType, s);
                }
            }
        }
        //if a print function
        else if(scriptWords.get(0).toLowerCase().equals("print")){
            System.out.println("PRINT");
            if(scriptWords.get(1).toLowerCase().equals("the") && scriptWords.get(2).toLowerCase().equals("variable")){
                //if a string
                if(getVar(scriptWords.get(3))[0].equals("string")){
                    System.out.println(getVar(scriptWords.get(3))[1]);
                }
                //if an integer
                if(getVar(scriptWords.get(3))[0].equals("integer")){
                    System.out.println(Integer.parseInt(getVar(scriptWords.get(3))[1]));
                }
                //if a boolean
                if(getVar(scriptWords.get(3))[0].equals("boolean")){
                    System.out.println(Boolean.parseBoolean(getVar(scriptWords.get(3))[1]));
                }
                //if a float
                if(getVar(scriptWords.get(3))[0].equals("float")){
                    System.out.println(Float.parseFloat(getVar(scriptWords.get(3))[1]));
                }
            }
        }

        return 1;
    }

    public static void setVar(String vName, String vType, String s){
        String[] arr = {vType, s};
        globalVariables.put(vName, arr);
    }

    public static String[] getVar(String vName){
        return globalVariables.get(vName);
    }
}
