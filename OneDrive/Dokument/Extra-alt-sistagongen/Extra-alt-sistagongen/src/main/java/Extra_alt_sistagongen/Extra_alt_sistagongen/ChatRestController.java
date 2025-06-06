package Extra_alt_sistagongen.Extra_alt_sistagongen;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRestController {

    private final MessageRepository messageRepository;

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @PostMapping("/message")
    public Message postMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }
}
