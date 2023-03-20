import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        final Parser parser = new Parser();
        final String input = " ";
        final Program output =  parser.parse(input);
        System.out.print(output.toString());

    }
}