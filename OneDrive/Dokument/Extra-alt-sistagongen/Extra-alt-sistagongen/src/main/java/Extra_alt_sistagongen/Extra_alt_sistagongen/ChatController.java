package Extra_alt_sistagongen.Extra_alt_sistagongen;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/chat")
    public Message sendMessage(Message message) {
        return message;
    }
}
