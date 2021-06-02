import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
    public static boolean looping = false;
    public static boolean lastLineIfFalse = false;
    public static HashMap<String, String[]> globalVariables = new HashMap<String, String[]>();
    // globalVariables.put(a, b);
    // globalVariables.get(a);

    public static int interpret(String data){
        Scanner input = new Scanner(System.in);
        //if a comment
        if(data.startsWith("//")){
            System.out.println("comment");
            input.close();
            return 0;
        }
        //if an empty line
        if(data.equals("")){
            System.out.println("EMPTY");
            input.close();
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
        
        //if an if statement
        if(scriptWords.get(0).toLowerCase().equals("if")){
            boolean ifCondition = EZFunctions.ifCondition(scriptWords);

            if(ifCondition){

            }
            else if(!ifCondition){

            }
            else{
                ;//exception?
            }
        }            
        //elif an else statement
        else if(scriptWords.get(0).toLowerCase().equals("else")){
            ;
        }
        //if not an if or else statement(neccesary to stop searching for an else)
        else{

        }

        //if variable function
        if(scriptWords.get(0).toLowerCase().equals("the") && scriptWords.get(1).toLowerCase().equals("variable")){
            //if setting a variable
            if(scriptWords.get(3).toLowerCase().equals("is") && scriptWords.get(4).toLowerCase().equals("equal") && scriptWords.get(5).toLowerCase().equals("to")){
                //if a random
                if(scriptWords.get(7).toLowerCase().equals("random")){
                    //random integer
                    if(scriptWords.get(8).toLowerCase().equals("integer")){
                        String vName = scriptWords.get(2);
                        String vType = "integer";
                        //bound 1 == sw(10)
                        int b1 = Integer.parseInt(scriptWords.get(10));
                        //bound 2 == sw(12)
                        int b2 = Integer.parseInt(scriptWords.get(12));

                        int rand = (int) (Math.random() * ((b2 - b1)) + 1) + b1;
                        setVar(vName, vType, String.valueOf(rand));
                    }
                    //random double
                    if(scriptWords.get(8).toLowerCase().equals("double")){
                        String vName = scriptWords.get(2);
                        String vType = "double";
                        //bound 1 == sw(10)
                        double b1 = Double.parseDouble(scriptWords.get(10));
                        //bound 2 == sw(12)
                        double b2 = Double.parseDouble(scriptWords.get(12));

                        double rand = (double) (Math.random() * (b2 - b1)) + b1;
                        setVar(vName, vType, String.valueOf(rand));
                    }
                }
                //if a String
                if(scriptWords.get(7).toLowerCase().equals("string")){
                    if(scriptWords.get(8).toLowerCase().equals("input")){
                        String vName = scriptWords.get(2);
                        String vType = "string";
                        for(int i=0;i<9;i++){
                            scriptWords.remove(0);
                        }
                        System.out.println(EZFunctions.BuildString(scriptWords));
                        String inp = input.nextLine();
                        setVar(vName, vType, inp);
                    }
                    else{
                        String vName = scriptWords.get(2);
                        String vType = "string";
                        for(int i=0;i<8;i++){
                            scriptWords.remove(0);
                        }
                        String s = EZFunctions.BuildString(scriptWords);
                        setVar(vName, vType, s);
                    }
                }
                else if(scriptWords.get(7).toLowerCase().equals("integer")){
                    if(scriptWords.get(8).toLowerCase().equals("input")){
                        String vName = scriptWords.get(2);
                        String vType = "integer";
                        for(int i=0;i<9;i++){
                            scriptWords.remove(0);
                        }
                        System.out.println(EZFunctions.BuildString(scriptWords));
                        int inp = input.nextInt();
                        setVar(vName, vType, String.valueOf(inp));
                    }
                    else{
                        setVar(scriptWords.get(2), scriptWords.get(7), scriptWords.get(8));
                    }
                }
                else if(scriptWords.get(7).toLowerCase().equals("double")){
                    if(scriptWords.get(8).toLowerCase().equals("input")){
                        String vName = scriptWords.get(2);
                        String vType = "double";
                        for(int i=0;i<9;i++){
                            scriptWords.remove(0);
                        }
                        System.out.println(EZFunctions.BuildString(scriptWords));
                        double inp = input.nextDouble();
                        setVar(vName, vType, String.valueOf(inp));
                    }
                    else{
                        setVar(scriptWords.get(2), scriptWords.get(7), scriptWords.get(8));
                    }
                }
                else if(scriptWords.get(7).toLowerCase().equals("boolean")){
                    if(scriptWords.get(8).toLowerCase().equals("input")){
                        String vName = scriptWords.get(2);
                        String vType = "boolean";
                        for(int i=0;i<9;i++){
                            scriptWords.remove(0);
                        }
                        System.out.println(EZFunctions.BuildString(scriptWords));
                        boolean inp = input.nextBoolean();
                        setVar(vName, vType, String.valueOf(inp));
                    }
                    else{
                        setVar(scriptWords.get(2), scriptWords.get(7), scriptWords.get(8));
                    }
                }
            }
        }
        //if a print function
        if(scriptWords.get(0).toLowerCase().equals("print")){
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
                //if a double
                if(getVar(scriptWords.get(3))[0].equals("double")){
                    System.out.println(Double.parseDouble(getVar(scriptWords.get(3))[1]));
                }
            }
        }
        input.close();
        return 1;
    }

    public static void setVar(String vName, String vType, String s){
        //check vType against acceptable types and return error if not
        String[] arr = {vType, s};
        globalVariables.put(vName, arr);
    }

    public static String[] getVar(String vName){
        return globalVariables.get(vName);
    }
}