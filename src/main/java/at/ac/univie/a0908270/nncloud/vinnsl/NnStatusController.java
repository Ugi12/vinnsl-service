package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import at.ac.univie.a0908270.nncloud.db.Vinnsl;
import at.ac.univie.a0908270.nncloud.util.NnStatus;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(description = "Processing Status Service for Neural Networks")
@RequestMapping("/status")
public class NnStatusController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;
	
	@Autowired
	MongoOperations mongoOperations;
	
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Status of all Neural Networks")
	ResponseEntity<?> getAllStatus() {
		Map<String, NnStatus> status = new HashMap<>();
		
		Query query = new Query();
		
		query.fields().include("_id");
		query.fields().include("nncloud");
		
		List<Vinnsl> vinnsl = mongoOperations.find(query, Vinnsl.class);
		
		for (Vinnsl v : vinnsl) {
			status.put(v.identifier, v.nncloud.getStatus());
		}
		
		return ResponseEntity.ok(status);
	}
	@GetMapping(value = "/iris", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Status of specific Neural Networks")
	ResponseEntity<?> getStatusForIrisNetwork() {
		Map<String, NnStatus> status = new HashMap<>();

		String des = "iris";

		Query query = new Query();
		query.fields().include("_id");
		query.fields().include("nncloud");

		query.addCriteria(Criteria.where("description.metadata.description").regex(des, "i"));

		List<Vinnsl> vinnsl = mongoOperations.find(query, Vinnsl.class);

		for(Vinnsl v : vinnsl){
			status.put(v.identifier, v.nncloud.getStatus());
		}
		return ResponseEntity.ok(status);
	}
	@GetMapping(value = "/mnist", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Status of specific Neural Networks")
	ResponseEntity<?> getStatusForMnistNetwork() {
		Map<String, NnStatus> status = new HashMap<>();

		String des = "mnist";

		Query query = new Query();
		query.fields().include("_id");
		query.fields().include("nncloud");

		query.addCriteria(Criteria.where("description.metadata.description").regex(des, "i"));

		List<Vinnsl> vinnsl = mongoOperations.find(query, Vinnsl.class);

		for(Vinnsl v : vinnsl){
			status.put(v.identifier, v.nncloud.getStatus());
		}
		return ResponseEntity.ok(status);
	}
	@GetMapping(value = "/wine", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Status of specific Neural Networks")
	ResponseEntity<?> getStatusForWineNetwork() {
		Map<String, NnStatus> status = new HashMap<>();

		String des = "wine";

		Query query = new Query();
		query.fields().include("_id");
		query.fields().include("nncloud");

		query.addCriteria(Criteria.where("description.metadata.description").regex(des, "i"));

		List<Vinnsl> vinnsl = mongoOperations.find(query, Vinnsl.class);

		for(Vinnsl v : vinnsl){
			status.put(v.identifier, v.nncloud.getStatus());
		}
		return ResponseEntity.ok(status);
	}

	@GetMapping(value = "/lstm", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Status of specific Neural Networks")
	ResponseEntity<?> getStatusForLSTMNetwork() {
		Map<String, NnStatus> status = new HashMap<>();

		String des = "LSTM";

		Query query = new Query();
		query.fields().include("_id");
		query.fields().include("nncloud");

		query.addCriteria(Criteria.where("description.metadata.description").regex(des, "i"));

		List<Vinnsl> vinnsl = mongoOperations.find(query, Vinnsl.class);

		for(Vinnsl v : vinnsl){
			status.put(v.identifier, v.nncloud.getStatus());
		}
		return ResponseEntity.ok(status);
	}

	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Status of Neural Network")
	ResponseEntity<?> getStatus(@PathVariable String id) {
		Map<String, NnStatus> status = new HashMap<>();
		
		Query query = new Query();
		query.fields().include("_id");
		query.fields().include("nncloud");
		
		Vinnsl v = mongoOperations.findOne(query, Vinnsl.class);
		status.put(v.identifier, v.nncloud.getStatus());
		
		return ResponseEntity.ok(status);
	}
	
	
	@PutMapping(value = "/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Set Status of a Neural Network")
	ResponseEntity<?> updateStatus(@PathVariable String id, @PathVariable NnStatus status) {
		Vinnsl vinnsl = nnRepository.findOne(id);
		vinnsl.nncloud.setStatus(status);
		nnRepository.save(vinnsl);
		
		return ResponseEntity.ok().build();
	}
	
}
