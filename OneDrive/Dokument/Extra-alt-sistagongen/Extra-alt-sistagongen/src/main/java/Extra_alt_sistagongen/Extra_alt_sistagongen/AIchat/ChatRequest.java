package Extra_alt_sistagongen.Extra_alt_sistagongen.AIchat;

import java.util.ArrayList;
import java.util.List;

public class ChatRequest {

    private String model;
    private List<AiMessages> messages;
    private int n;

    public ChatRequest(String model, String systemMessage, String prompt, int n) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new AiMessages("system", systemMessage));
        this.messages.add(new AiMessages("user", prompt));
        this.n = n;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<AiMessages> getMessages() {
        return messages;
    }

    public void setMessages(List<AiMessages> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}