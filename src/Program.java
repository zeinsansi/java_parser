import com.google.gson.Gson;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private List<Statement> statementList = new ArrayList<>();

    public Program() {
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    @Override
    public String toString() {
        String output = "body\n" + new Gson().toJson(statementList) + "\n";
        return output;
    }
}
