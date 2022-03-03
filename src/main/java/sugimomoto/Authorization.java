package sugimomoto;

public class Authorization {
    
    private String code;

    private String state;

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
