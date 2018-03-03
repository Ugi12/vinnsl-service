package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class VinnslController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;
	
	@Autowired
	MongoOperations mongoOperations;
	
	
	@GetMapping
	ResponseEntity<?> getServiceStatus() {
		Map<String, String> status = new HashMap<>();
		status.put("service", "up");
		return ResponseEntity.ok(status);
	}
	
	
}
