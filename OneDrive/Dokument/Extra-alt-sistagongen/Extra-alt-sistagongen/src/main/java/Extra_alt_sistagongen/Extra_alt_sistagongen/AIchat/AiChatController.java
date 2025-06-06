package Extra_alt_sistagongen.Extra_alt_sistagongen.AIchat;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiChatController {

    @PostMapping("/Aichat")
    public String postChta(@RequestBody String prompt) {
        return "Hej";
    }
}
