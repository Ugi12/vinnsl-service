package at.ac.univie.a0908270.nncloud.vinnsl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
	private static final String template = "Hello";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		String ret = new String();
		ret = String.format("%s %d %s", template, counter.incrementAndGet(), name);
		return ret;
	}
}
