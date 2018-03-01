package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import at.ac.univie.a0908270.nncloud.db.Vinnsl;
import at.ac.univie.a0908270.nncloud.util.NnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/status")
public class VinnslStatusController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;
	
	@Autowired
	MongoOperations mongoOperations;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getStatus(@PathVariable String id) {
		Map<String, NnStatus> status = new HashMap<>();
		
		Query query = new Query();
		query.fields().include("_id");
		query.fields().include("nncloud");
		
		Vinnsl v = mongoOperations.findOne(query, Vinnsl.class);
		status.put(v.identifier, v.nncloud.getStatus());
		
		return ResponseEntity.ok(status);
	}
	
	@RequestMapping(value = "/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> updateStatus(@PathVariable String id, @PathVariable NnStatus status) {
		Vinnsl vinnsl = nnRepository.findOne(id);
		vinnsl.nncloud.setStatus(status);
		nnRepository.save(vinnsl);
		
		return ResponseEntity.ok().build();
	}
	
}
