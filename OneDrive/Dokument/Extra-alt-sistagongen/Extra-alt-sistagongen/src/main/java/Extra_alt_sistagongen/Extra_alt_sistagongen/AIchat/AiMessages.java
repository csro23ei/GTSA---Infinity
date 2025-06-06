package Extra_alt_sistagongen.Extra_alt_sistagongen.AIchat;

public class AiMessages {
    
    private String role;
    private String content;

    
    public AiMessages(String role, String content) {
        this.role = role;
        this.content = content;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    
}
