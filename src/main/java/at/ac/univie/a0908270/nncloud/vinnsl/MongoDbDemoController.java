package at.ac.univie.a0908270.nncloud.vinnsl;

import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import at.ac.univie.a0908270.nncloud.db.Vinnsl;
import at.ac.univie.a0908270.nncloud.db.definition.backpropagation.*;
import at.ac.univie.a0908270.nncloud.db.parameters.BoolParameter;
import at.ac.univie.a0908270.nncloud.db.parameters.Parameters;
import at.ac.univie.a0908270.nncloud.db.parameters.ValueParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MongoDbDemoController {
	
	@Autowired
	NeuronalNetworkRepository nnRepository;
	
	@RequestMapping(value = "/vinnsl", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Vinnsl> returnVinnslNetworks() {
		ArrayList<Vinnsl> nnList = new ArrayList<>();
		
		for (Vinnsl nn : nnRepository.findAll()) {
			nnList.add(nn);
		}
		
		return nnList;
	}
	
	
	@RequestMapping(value = "/vinnsl/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody
	Vinnsl demoMongoDbConnectionXml() {
		ArrayList<Vinnsl> nnList = new ArrayList<>();
		
		for (Vinnsl nn : nnRepository.findAll()) {
			nnList.add(nn);
		}
		
		return nnList.get(0);
	}
	
	@RequestMapping(value = "/vinnsl/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Vinnsl> demoMongoDbConnection() {
		
		Vinnsl vinnsl = new Vinnsl();
		VinnslDefinition vinnslDefinition = new VinnslDefinition();
		Structure struc = new Structure();
		struc.input = new Input("input1", 2, 1);
		struc.hidden = new Hidden("hidden1", 5, 1);
		struc.output = new Output("output1", 1, 1);
		struc.connections = "connections";
		
		vinnslDefinition.structure = struc;
		vinnsl.definition = vinnslDefinition;
		
		Parameters params = new Parameters(new ArrayList<>());
		params.parameters.add(new ValueParameter("test", "test1", "value"));
		params.parameters.add(new BoolParameter("test2", "testlabel", false));
		
		vinnsl.definition.parameters = params;
		
		nnRepository.save(vinnsl);
		//nnRepository.save(new VinnslDefinition(String.format("NNEntity %s", new SimpleDateFormat("dd-MM-yyyy HHmmss").format(new Date())).toString()));
		
		ArrayList<Vinnsl> nnList = new ArrayList<>();
		
		for (Vinnsl nn : nnRepository.findAll()) {
			nnList.add(nn);
		}
		
		return nnList;
	}
	
	@RequestMapping(value = "/vinnsl/import", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> add(@RequestBody Vinnsl input) {
		
		return ResponseEntity.ok().body(input);
		
		/*return this.accountRepository
				.findByUsername(userId)
				.map(account -> {
					Bookmark result = bookmarkRepository.save(new Bookmark(account,
							input.uri, input.description));
					
					URI location = ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri();
					
					return ResponseEntity.created(location).build();
				})
				.orElse(ResponseEntity.noContent().build());*/
		
	}
	
	@RequestMapping(value = "/vinnsl/importxml", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	ResponseEntity<?> addXml(@RequestBody Vinnsl input) {
		
		return ResponseEntity.ok().body(input);
		
		/*return this.accountRepository
				.findByUsername(userId)
				.map(account -> {
					Bookmark result = bookmarkRepository.save(new Bookmark(account,
							input.uri, input.description));
					
					URI location = ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri();
					
					return ResponseEntity.created(location).build();
				})
				.orElse(ResponseEntity.noContent().build());*/
		
	}
	
	@RequestMapping(value = "/vinnsl/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public String removeAllNn() {
		nnRepository.deleteAll();
		return "add vinnsl deleted";
	}
	
}
