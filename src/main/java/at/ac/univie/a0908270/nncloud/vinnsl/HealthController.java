package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(description = "Health of Service")
public class HealthController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;
	
	@Autowired
	MongoOperations mongoOperations;
	
	
	@GetMapping
	@ApiOperation(value = "Health of Service")
	/**
	 * Service Status of Service
	 * check if Web- and DBServices are running and healthy
	 */
	ResponseEntity<?> getServiceStatus() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/health");
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}
	
	
}
