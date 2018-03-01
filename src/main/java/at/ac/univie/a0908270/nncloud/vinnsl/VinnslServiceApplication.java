package at.ac.univie.a0908270.nncloud.vinnsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories({"at.ac.univie.a0908270.nncloud.db"})
public class VinnslServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(VinnslServiceApplication.class, args);
	}
	
	//public void run(String... args) throws Exception {
	
	//nnRepository.deleteAll();
		
		/*// save a couple of customers
		nnRepository.save(new VinnslDefinition("nn1"));
		nnRepository.save(new VinnslDefinition("nn2"));
		
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (VinnslDefinition customer : nnRepository.findAll()) {
			System.out.println(customer);
		}
		*/
	
	//}
}
