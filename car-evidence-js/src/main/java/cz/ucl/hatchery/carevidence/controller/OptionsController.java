/**
 * 
 */
package cz.ucl.hatchery.carevidence.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author User
 *
 */
@RestController
public class OptionsController {

	@RequestMapping(method = RequestMethod.OPTIONS, value = "/**")
	public ResponseEntity getOptions() {
		final HttpHeaders headers = new HttpHeaders();
		final Set<HttpMethod> methods = new HashSet<HttpMethod>();

		methods.add(HttpMethod.GET);
		methods.add(HttpMethod.POST);
		methods.add(HttpMethod.OPTIONS);

		headers.setAllow(methods);

		return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
	}
}
