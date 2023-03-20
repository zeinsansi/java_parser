import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private static final List<Regex> spec = new ArrayList<>();
    private String input;
    private int cursor;

    public Lexer(){
        cursor = 0;
        getSpec();
    }

    public Lexer(String input ) {
        this.input = input;
        cursor = 0;
        getSpec();
    }

    private void getSpec(){
        spec.add(new Regex("^\\s+","WHITESPACE"));
        //Numbers
        spec.add(new Regex("^[\\d]+", "NUMBER"));
        //String
        spec.add(new Regex("^[a-zA-Z]+", "STRING"));
        //symbols
        spec.add(new Regex("^[;]",";"));
    }

    public Token getNextToken() throws Exception {
        if(hasMoreTokens()  != true ){
            return null;
        }

        String nextToken = input.substring(cursor);
        for(Regex regex : spec) {
            Token token = new Token();
            token = match(nextToken, regex);
            if (token != null) {
                if (Objects.equals(token.getValue(), null)) {
                    continue;
                }
                if (Objects.equals(token.getType(), "WHITESPACE")) {
                    getNextToken();
                }
                return token;
            }
        }
        throw new Exception("Unexpected token:"+ nextToken);
    }

    private Token match(String nextToken, Regex regex){

        Token token = null;
        final Pattern pattern = Pattern.compile(regex.getPattern());
        final Matcher matcher = pattern.matcher(nextToken);
        if (matcher.find()){
            token = new Token(regex.getType(),matcher.group(0).toString());
            cursor += matcher.group(0).length();
        }
        return token;
    }

    private boolean hasMoreTokens(){
        if(cursor < input.length()){
            return true;
        }
        return false;
    }

    private boolean isEnd(){
        if(cursor == input.length()){
            return true;
        }
        return false;
    }
}
