package Extra_alt_sistagongen.Extra_alt_sistagongen.AIchat;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "https://coral-app-sym8r.ondigitalocean.app" })
public class AiChatController {

    @Autowired
    private AiChatService chatService;

    @PostMapping("/chat")
    public CompletableFuture<String> postChat(@RequestBody String prompt) {
        return CompletableFuture.supplyAsync(() -> {
            AiChatResponse response = chatService.sendChatResponse(prompt);
            return response.getChoices().get(0).getMessage().getContent();
        });
    }
}