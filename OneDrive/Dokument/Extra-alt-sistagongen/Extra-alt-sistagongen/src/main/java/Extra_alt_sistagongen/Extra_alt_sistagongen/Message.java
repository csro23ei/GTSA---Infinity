package Extra_alt_sistagongen.Extra_alt_sistagongen;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String sender;
    private String content;
    private MsgType type;
}
