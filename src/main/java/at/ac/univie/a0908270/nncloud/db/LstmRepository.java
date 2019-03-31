package at.ac.univie.a0908270.nncloud.db;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LstmRepository  extends MongoRepository<Lstm, String> {
}
