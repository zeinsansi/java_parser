import java.util.ArrayList;
import java.util.List;

public class Statement {

    private List<Token> tokens = new ArrayList<>();

    public Statement() {
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
