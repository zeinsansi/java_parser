public class Token {

    private String type;
    private String value;

    public Token (){

    }
    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        String result = "type:" + " "+type +
                        "\nvalue:"+" "+value;
        return result;
    }
}
