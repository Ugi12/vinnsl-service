package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a00908270.vinnsl.schema.*;
import at.ac.univie.a0908270.nncloud.db.*;
import at.ac.univie.a0908270.nncloud.db.Process;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(description = "ViNNSL Neural Network Service")
@RestController
public class VinnslServiceController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;

	@Autowired
	StatisticRepository statisticRepository;

	@Autowired
	ProcessRepository processRepository;
	
	@Autowired
	MongoConverter mongoConverter;
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@GetMapping(value = "/vinnsl", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List all Neural Networks")
	public ArrayList<Vinnsl> getAllVinnslNetworks() {
		ArrayList<Vinnsl> nnList = new ArrayList<>();
		
		for (Vinnsl nn : nnRepository.findAll()) {
			nnList.add(nn);
		}
		return nnList;
	}
	
	@PostMapping(value = "/vinnsl", consumes = {MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Import a new ViNNSL XML Defintion")
	ResponseEntity<?> addXml(@RequestBody Vinnsl vinnsl) {
		nnRepository.insert(vinnsl);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(vinnsl.identifier).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(value = "/vinnsl/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Get Neural Network Object")
	public ResponseEntity<Vinnsl> getVinnslNetwork(@PathVariable("id") String id) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			return ResponseEntity.ok().body(nnRepository.findOne(id));
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(value = "/vinnsl/{id}/definition", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Add/Replace ViNNSL Definition of Neural Network")
	ResponseEntity<?> addDefinitionToVinnsl(@PathVariable("id") String id, @RequestBody Definition def) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			result.definition = def;
			nnRepository.save(result);
			return ResponseEntity.ok().body(nnRepository.findOne(id));
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(value = "/vinnsl/{id}/instanceschema", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Add/Replace ViNNSL Instanceschema of Neural Network")
	ResponseEntity<?> addInstanceToVinnsl(@PathVariable("id") String id, @RequestBody Instanceschema instance) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			result.instance = instance;
			nnRepository.save(result);
			return ResponseEntity.ok().body(nnRepository.findOne(id));
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(value = "/vinnsl/{id}/trainingresult", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Add/Replace ViNNSL Trainingresult of Neural Network")
	ResponseEntity<?> addResultToVinnsl(@PathVariable("id") String id, @RequestBody Trainingresultschema trainingresult) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			result.trainingresult = trainingresult;
			nnRepository.save(result);
			return ResponseEntity.ok().body(nnRepository.findOne(id));
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(value = "/vinnsl/{id}/resultschema", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Add/Replace ViNNSL Resultschema of Neural Network")
	ResponseEntity<?> addResultToVinnsl(@PathVariable("id") String id, @RequestBody Resultschema resultSchema) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			result.result = resultSchema;
			nnRepository.save(result);
			return ResponseEntity.ok().body(nnRepository.findOne(id));
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(value = "/vinnsl/{id}/addfile", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Add/Replace File of Neural Network")
	public ResponseEntity<Vinnsl> addFileToVinnslNetwork(@PathVariable("id") String id, @RequestParam("fileId") String fileId) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			Dataschema dataschema = new Dataschema();
			dataschema.setIdentifier(fileId);
			Dataschema.Data data = new Dataschema.Data();
			data.setFile(fileId);
			dataschema.setData(data);
			result.data = dataschema;
			
			Definition.Data defData = new Definition.Data();
			defData.setDataSchemaID(fileId);
			result.definition.setData(defData);
			
			nnRepository.save(result);
			
			return ResponseEntity.ok().body(result);
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value = "/vinnsl/{id}")
	@ApiOperation(value = "Remove Neural Network Object")
	public ResponseEntity removeNn(@PathVariable("id") String id) {
		nnRepository.delete(id);
		
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping(value = "/vinnsl/deleteall", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Delete all Neural Networks")
	public ResponseEntity<?> removeAllNn() {
		nnRepository.deleteAll();
		
		Map<String, String> message = new HashMap<>();
		message.put("message", "all vinnsl deleted");
		
		return ResponseEntity.ok(message);
		
	}


	@PostMapping(value = "/vinnsl/create-update/statistic")
	@ApiOperation(value = "create or update statistics for network")
	ResponseEntity<?> createOrUpdateStatistic(@RequestBody Statistic statistic){

		Statistic result = statisticRepository.findOne(statistic.id);

		/**
		 * create statistic if result is null else update
		 */
		if(result == null){
			statisticRepository.save(statistic);
		}else{
			result.lastUpdateTime = statistic.createTimestamp;
			result.trainingTime = statistic.trainingTime;
			result.numberOfTraining = result.numberOfTraining + 1;
			result.lastResult = statistic.lastResult;

			if(Float.parseFloat(result.lastResult) > Float.parseFloat(result.bestResult)){
				result.bestResult = statistic.lastResult;
			}
			if(statistic.epochs != null)
				result.epochs = statistic.epochs;
			if(statistic.learningRate != null)
				result.learningRate = statistic.learningRate;
			if(statistic.loss != null)
				result.loss = statistic.loss;
			if(statistic.batchSize != null)
				result.batchSize = statistic.batchSize;
			statisticRepository.save(result);
		}
		return ResponseEntity.ok().build();
	}

	@CrossOrigin(origins = "http://localhost:8083")
	@GetMapping(value = "/vinnsl/get/statistic/{id}")
	ResponseEntity<?> getStatisticById(@PathVariable("id") String id){
		Statistic statistic = statisticRepository.findOne(id);

		return ResponseEntity.ok().body(statistic);
	}

	@PostMapping(value = "/vinnsl/create-update/process")
	@ApiOperation(value = "create or update training process for network")
	ResponseEntity<?> createOrUpdateProcess(@RequestBody at.ac.univie.a0908270.nncloud.db.Process process){

		Process result = processRepository.findOne(process.id);

		/**
		 * create training process if result is null else update
		 */
		if(result == null){
			processRepository.save(process);
		}else{
			result.trainingProcess = process.trainingProcess;
			processRepository.save(result);
		}
		return ResponseEntity.ok().build();
	}

	@CrossOrigin(origins = "http://localhost:8083")
	@GetMapping(value = "/vinnsl/get/process/{id}")
	ResponseEntity<?> getProcessById(@PathVariable("id") String id){
		Process process = processRepository.findOne(id);

		return ResponseEntity.ok().body(process);
	}
	
}
