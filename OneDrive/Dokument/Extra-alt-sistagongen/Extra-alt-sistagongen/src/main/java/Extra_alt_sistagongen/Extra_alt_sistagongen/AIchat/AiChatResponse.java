package Extra_alt_sistagongen.Extra_alt_sistagongen.AIchat;

import java.util.List;

public class AiChatResponse {

    private List<Choice> choices;

    public AiChatResponse() {
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        private AiMessages message;

        public Choice() {
        }

        public AiMessages getMessage() {
            return message;
        }

        public void setMessage(AiMessages message) {
            this.message = message;
        }
    }
}