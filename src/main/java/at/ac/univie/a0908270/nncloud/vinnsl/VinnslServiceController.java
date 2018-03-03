package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a00908270.vinnsl.schema.Dataschema;
import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import at.ac.univie.a0908270.nncloud.db.Vinnsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
public class VinnslServiceController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;
	
	@Autowired
	MongoConverter mongoConverter;
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@RequestMapping(value = "/vinnsl", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Vinnsl> getAllVinnslNetworks() {
		ArrayList<Vinnsl> nnList = new ArrayList<>();
		
		for (Vinnsl nn : nnRepository.findAll()) {
			nnList.add(nn);
		}
		return nnList;
	}
	
	@RequestMapping(value = "/vinnsl", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<?> addXml(@RequestBody Vinnsl vinnsl) {
		nnRepository.insert(vinnsl);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(vinnsl.identifier).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value = "/vinnsl/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Vinnsl> getVinnslNetwork(@PathVariable("id") String id) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			return ResponseEntity.ok().body(nnRepository.findOne(id));
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/vinnsl/{id}/addfile", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Vinnsl> addFileToVinnslNetwork(@PathVariable("id") String id, @RequestParam("fileId") String fileId) {
		Vinnsl result = nnRepository.findOne(id);
		
		if (result != null) {
			Dataschema dataschema = new Dataschema();
			Dataschema.Data data = new Dataschema.Data();
			data.setFile(fileId);
			dataschema.setData(data);
			result.data = dataschema;
			
			nnRepository.save(result);
			
			return ResponseEntity.ok().body(result);
		} else {
			//TODO https://stackoverflow.com/questions/36848562/add-a-body-to-a-404-not-found-exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/vinnsl/deleteall", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> removeAllNn() {
		nnRepository.deleteAll();
		
		Map<String, String> message = new HashMap<String, String>();
		message.put("message", "all vinnsl deleted");
		
		return ResponseEntity.ok(message);
		
	}
	
	
	/*@RequestMapping(value = "/vinnsl/updatexml/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	ResponseEntity<?> updateXml(@PathVariable("id") String id, @RequestBody Vinnsl input) {
		input.identifier = id;
		
		DBObject update = getDbObject(input);
		//mongoTemplate.updateFirst(query(where("id").is(id)), Update.fromDBObject(new BasicDBObject("$set", update)).push("events", order), Order.class);
		//return mongoTemplate.findOne(query(where("id").is(id)), Order.class);
		mongoOperations.updateFirst(query(where("_id").is(id)), Update.fromDBObject(new BasicDBObject("$set", update)).push("events", input), Vinnsl.class);
		return ResponseEntity.ok().body(input);
		
		*//*return this.accountRepository
				.findByUsername(userId)
				.map(account -> {
					Bookmark result = bookmarkRepository.save(new Bookmark(account,
							input.uri, input.description));
					
					URI location = ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri();
					
					return ResponseEntity.created(location).build();
				})
				.orElse(ResponseEntity.noContent().build());*//*
		
	}*/
	
	/*private DBObject getDbObject(Object o) {
		BasicDBObject basicDBObject = new BasicDBObject();
		mongoConverter.write(o, basicDBObject);
		return basicDBObject;
	}*/
	
}
