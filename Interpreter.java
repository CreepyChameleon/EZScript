import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
    public static boolean looping = false;
    public static boolean firstRun = true;

    public static boolean lastLineIfFalse = false;
    public static boolean lastLineIfStatement = false;

    public static HashMap<String, String[]> globalVariables = new HashMap<String, String[]>();
    public static Scanner input = new Scanner(System.in);
    // globalVariables.put(a, b);
    // globalVariables.get(a);

    public static int interpret(String data){
        // Scanner input = new Scanner(System.in);
        //if a comment
        if(data.startsWith("//")){
            System.out.println("comment");
            ;
            return 0;
        }
        //if an empty line
        if(data.equals("")){
            System.out.println("EMPTY");
            ;
            return 0;
        }
        String[] sW = data.split(" "); //split sentence into array of words
        ArrayList<String> scriptWords = new ArrayList<String>();
        for(String i : sW){
            scriptWords.add(i);
        }

        //if to only be run on the first pass(while looping through the entire program)
        if(scriptWords.get(0).toLowerCase().equals("firstrun")){
            if(firstRun){ //only runs once
                scriptWords.remove(0);
            }
            else{
                ;
            }
        }

        //if beginning a loop
        if(scriptWords.get(0).toLowerCase().equals("loop")){
            looping = true;
        }
        //end loop
        else if(scriptWords.get(0).toLowerCase().equals("stop")){
            looping = false;
            ;
            return 0;
        }

        //if an if statement
        if(scriptWords.get(0).toLowerCase().equals("if")){
            boolean ifCondition = EZFunctions.ifCondition(scriptWords);
            if(ifCondition){
                lastLineIfStatement = true;
                lastLineIfFalse = false;
                ;
                return 1;
            }
            else if(!ifCondition){
                lastLineIfStatement = true;
                lastLineIfFalse = true;
                ;
                return 1;
            }
            else{
                ;//exception?
            }
        }
        else if(scriptWords.get(0).toLowerCase().equals("then")){
            if(lastLineIfStatement && !lastLineIfFalse){
                scriptWords.remove(0); //reads input after "then"
            }
            else{
                ;
                return 0;
            }
        }
        //elif an else statement
        else if(scriptWords.get(0).toLowerCase().equals("else")){
            if(lastLineIfFalse){ //if the if statement was false
                lastLineIfStatement = true;
                lastLineIfFalse = false;
            }
            else{
                lastLineIfStatement = false;
            }
        }
        //if not an if or else statement(neccesary to stop searching for an else)
        else{
            lastLineIfStatement = false;
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
                        System.out.print(EZFunctions.BuildString(scriptWords));
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
            //if printing a var
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
            //if printing a value
            else if(scriptWords.get(1).toLowerCase().equals("the") && !scriptWords.get(2).toLowerCase().equals("variable")){
                if(scriptWords.get(2).toLowerCase().equals("string")){
                    for(int i=0;i<3;i++){
                        scriptWords.remove(0);
                    }
                    System.out.println(EZFunctions.BuildString(scriptWords));
                }
                else if(scriptWords.get(2).toLowerCase().equals("integer")){
                    System.out.println(Integer.parseInt(scriptWords.get(3)));
                }
                else if(scriptWords.get(2).toLowerCase().equals("boolean")){
                    System.out.println(Boolean.parseBoolean(scriptWords.get(3)));
                }
                else if(scriptWords.get(2).toLowerCase().equals("double")){
                    System.out.println(Double.parseDouble(scriptWords.get(3)));
                }
            }
        }
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