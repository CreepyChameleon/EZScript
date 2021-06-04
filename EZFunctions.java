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
        //if the variable VAR1 is equal to the variable VAR2
        if(wordList.get(2).toLowerCase().equals("variable")){ //variable comparison
            String obj1Val = Interpreter.globalVariables.get(wordList.get(3))[1];
            String obj1Type = Interpreter.globalVariables.get(wordList.get(3))[0];

            if(wordList.get(8).toLowerCase().equals("variable")){ //var to var
                String obj2Val = Interpreter.globalVariables.get(wordList.get(9))[1];
                String obj2Type = Interpreter.globalVariables.get(wordList.get(9))[0];
            }
            else if(wordList.get(8).toLowerCase().equals("string")){
                if(obj1Type.equals("string")){
                    String val2 = wordList.get(9);
                    if(obj1Val.equals(val2)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    ;//raise type error
                }
            }
            else if(wordList.get(8).toLowerCase().equals("integer")){
                if(obj1Type.equals("integer")){
                    int val2 = Integer.parseInt(wordList.get(9));
                    if(Integer.parseInt(obj1Val) == val2){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    ;//raise type error
                }
            }
            else if(wordList.get(8).toLowerCase().equals("double")){
                if(obj1Type.equals("double")){
                    double val2 = Double.parseDouble(wordList.get(9));
                    if(Double.parseDouble(obj1Val) == val2){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    ;//raise type error
                }
            }
            else if(wordList.get(8).toLowerCase().equals("boolean")){
                if(obj1Type.equals("boolean")){
                    boolean val2 = Boolean.parseBoolean(wordList.get(9));
                    if(Boolean.parseBoolean(obj1Val) == val2){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    ;//raise type error
                }
            }
            else{
                ;//raise type error
            }


            
        }
        else if(wordList.get(2).toLowerCase().equals("string")){
            String obj1Val = wordList.get(3);
            if(wordList.get(8).toLowerCase().equals("variable")){
                String varVal = Interpreter.globalVariables.get(wordList.get(9))[1];
                String varType = Interpreter.globalVariables.get(wordList.get(9))[0];
                if(varType.equals("string")){
                    if(obj1Val.equals(varVal)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    ;//raise type error
                }
            }
            else if(wordList.get(8).toLowerCase().equals("string")){
                if(obj1Val.equals(wordList.get(9))){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                ;//raise type error
            }
        }
        else if(wordList.get(2).toLowerCase().equals("integer")){
            int obj1Val = Integer.parseInt(wordList.get(3));
            if(wordList.get(8).toLowerCase().equals("variable")){
                int varVal = Integer.parseInt(Interpreter.globalVariables.get(wordList.get(9))[1]);
                String varType = Interpreter.globalVariables.get(wordList.get(9))[0];
                if(varType.equals("integer")){
                    if(obj1Val == varVal){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    ;//raise type error
                }
            }
            else if(wordList.get(8).toLowerCase().equals("integer")){
                if(obj1Val == Integer.parseInt(wordList.get(9))){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                ;//raise type error
            }
        }
        else if(wordList.get(2).toLowerCase().equals("double")){

        }
        else if(wordList.get(2).toLowerCase().equals("boolean")){

        }
        return false;
    }
}
