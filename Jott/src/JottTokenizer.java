import java.util.ArrayList;

public class JottTokenizer {
        public static void main(String[] args) {
            ArrayList<String> Tokens = new ArrayList<String>();
            String look="space";
            int spot=0;
            String previous="space";
            for (String token:args) {
                if(look.equals("quote")){


                }
                System.out.println(token);
                look=(tokenizer(token, previous, spot));
                if(look==null){
                    System.out.println("OH NO");
                    System.exit(0);
                }
                if(look.equals("quote")){
                    previous="quote";
                    spot+=1;
                    look=tokenizer(token, previous, spot);
                    if(look==null){
                        System.out.println("OH NO");
                        System.exit(0);
                    }
                    while(look.equals("quote")){
                        spot+=1;
                        if(spot==token.length()){
                            break;
                        }
                        look=tokenizer(token, previous, spot);
                        if(look.equals("string")){
                            Tokens.add(look);
                            previous="space";
                        }
                    }
                }
                spot=0;
            }
            for (String thing: Tokens) {
                System.out.println(thing);
            }
        }

        private static String tokenizer(String token, String previous, int spot) {
            if(previous.equals("space")) {
                if (token.charAt(spot) == '+' && token.length()==1) {
                    return "plus";
                }
                if (token.charAt(spot) == '-' && token.length()==1) {
                    return "minus";
                }
                if (token.charAt(spot) == '*' && token.length()==1) {
                    return "mult";
                }
                if (token.charAt(spot) == '/' && token.length()==1) {
                    return "divide";
                }
                if (token.charAt(spot) == '^'&& token.length()==1) {
                    return "power";
                }
                if (token.charAt(spot) == ')' && token.length()==1) {
                    return "end_parenthesis";
                }
                if (token.charAt(spot) == '(' && token.length()==1) {
                    return "start_parenthesis";
                }
                if (token.charAt(spot) == ';' && token.length()==1) {
                    return "end_stmt";
                }
                if (token.charAt(spot) == '=' && token.length()==1) {
                    return "assign";
                }
                if (token.charAt(spot) == ',' && token.length()==1){
                    return "comma";
                }
                if (token.charAt(spot) >= 48 && token.charAt(spot) <= 57) {
                    if(token.length()==spot+1) {
                        return "number";
                    }
                    else{
                        return tokenizer(token, "number", spot+1);
                    }
                }
                if (token.charAt(spot) == '.') {
                    return tokenizer(token, "period", spot+1);
                }
                if ((token.charAt(spot) >= 97 && token.charAt(spot) <= 122)||(token.charAt(spot)>=65 && token.charAt(spot)<=90)) {
                    return tokenizer(token, "keyword", spot+1);
                }
                if (token.charAt(spot) == '"') {
                    return "quote";
                }
                return null;
            }
            if(previous.equals("number")){
                if(token.length()==spot+1) {
                    return "number";
                }
                if (token.charAt(spot) == '.') {
                    return tokenizer(token, "period", spot+1);
                }
                else{
                    return tokenizer(token, "number", spot+1);
                }
            }
            if(previous.equals("period")){
                if(token.charAt(spot) >= 48 && token.charAt(spot) <= 57){
                    if(token.length()==spot+1){
                        return "number";
                    }
                    return tokenizer(token, "number", spot+1);
                }
            }
            if(previous.equals("quote")){
                if (token.charAt(spot) == '"' && token.length()==spot+1) {
                    return "string";
                }
                if (token.charAt(spot) == ' '||(token.charAt(spot) >= 48 && token.charAt(spot) <= 57)||
                        (token.charAt(spot)>=65 && token.charAt(spot)<=90)||(token.charAt(spot) >= 97 && token.charAt(spot) <= 122)) {
                    if(token.length()==spot+1){
                        return "quote";
                    }
                    return tokenizer(token, "quote", spot+1);
                }
            }
            if(previous.equals("keyword")){
                if (token.charAt(spot) == ' '||(token.charAt(spot) >= 48 && token.charAt(spot) <= 57)||
                        (token.charAt(spot)>=65 && token.charAt(spot)<=90)||(token.charAt(spot) >= 97 && token.charAt(spot) <= 122)) {
                    if(token.length()==spot+1){
                        return "keyword";
                    }
                    else{
                        return tokenizer(token, "keyword", spot+1);
                    }
                }
            }
            return null;
        }


    }

}
