package services.datastructure;

public class ResponseData {
    private String key;
    private String regex;

    public ResponseData(){
    }

    public ResponseData(String key, String regex){
        this.key = key;
        this.regex = regex;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
