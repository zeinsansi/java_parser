import java.util.ArrayList;
import java.util.List;

public class Parser {

    private String input = "";
    private Token currenToken = null;

    private Lexer lexer = new Lexer();

    public Parser() {
    }

    public Program parse(String input) throws Exception {
        this.input = input;
        lexer = new Lexer(input);
        currenToken = lexer.getNextToken();
        Program program = new Program();
        program.setStatementList(statementList(null));
        return program;
    }

    private List<Statement> statementList(String stopper) throws Exception {
        List<Statement> statements = new ArrayList<>();
        while (currenToken != null && stopper == null){
            statements.add(getStatement());
        }
        return statements;
    }

    private Statement getStatement() throws Exception {
        Statement statement = literal();
        return statement;
    }

    private Statement literal() throws Exception {
        Statement statement = new Statement();
        List<Token> tokens = new ArrayList<>();
        Token token = new Token();
        if(currenToken != null){
            switch (currenToken.getType()){
                case "STRING":
                    token = stringLiteral();
                    break;
                case "NUMBER":
                    token = numberLiteral();
                    break;
            }
            tokens.add(token);
            statement.setTokens(tokens);
            if(token.getType() == "null"){
                currenToken = null;
            }
            return statement;
        }
        return null;
    }

    private Token stringLiteral() throws Exception {
        Token token = consume("STRING");
        return token;
    }


    private Token numberLiteral() throws Exception {
        Token token = consume("NUMBER");
        return token;
    }

    private Token consume(String tokenType) throws Exception {
        Token token = this.currenToken;
        if (token == null) {
            throw new Exception("Unexpected end of input, expected: "+ tokenType);
        }
        if (token.getType() != tokenType) {
            throw new Exception("Unexpected token :" + token.getValue() + "expected: "+ tokenType);
        }
        this.currenToken = this.lexer.getNextToken();
        return token;
    }

}
