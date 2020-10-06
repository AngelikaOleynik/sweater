package sweater.repository;

import org.springframework.data.repository.CrudRepository;
import sweater.domain.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {  //позволяет найти полный список полей, либо найти их по id
    List<Message> findByTag(String tag);
}
