package Extra_alt_sistagongen.Extra_alt_sistagongen;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
