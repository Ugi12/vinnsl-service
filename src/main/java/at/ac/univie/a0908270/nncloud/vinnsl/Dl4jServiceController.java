package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import at.ac.univie.a0908270.nncloud.db.Vinnsl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(description = "Deeplearning4J Data Service for Neural Networks")
public class Dl4jServiceController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;
	
	@Autowired
	MongoConverter mongoConverter;
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@GetMapping(value = "/dl4j/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Get Deeplearning4J Transformation Object of Neural Network")
	public ResponseEntity<String> getDl4JNetwork(@PathVariable("id") String id) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			return ResponseEntity.ok().body(result.nncloud.getDl4jNetwork());
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(value = "/dl4j/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Put Deeplearning4J Transformation Object of Neural Network")
	public ResponseEntity pullDl4JConfiguration(@PathVariable("id") String id, @RequestBody String dl4J) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			result.nncloud.setDl4jNetwork(dl4J);
			nnRepository.save(result);
			
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
