package ucb.edu.bo.sabicovid19;

import com.nimbusds.oauth2.sdk.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.http.server.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Test;
import ucb.edu.bo.sabicovid19.model.CaseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class SaBiCovid19ApplicationTests {

	@Test
	void contextLoads() {

		ParameterizedTypeReference<HashMap<String, String>> responseType =
				new ParameterizedTypeReference<HashMap<String, String>>() {};

		RestTemplate restTemplate = new RestTemplate();
//		String fooResourceUrl
//				= "http://localhost:8080/register-case";
//		ResponseEntity<Void> response;
//		response = restTemplate.getForEntity(fooResourceUrl,Void.class);
//		RequestEntity<Void> request = RequestEntity.post(URI.create("http://localhost:8080/register-case"))
//				.accept(MediaType.APPLICATION_JSON).build();
////		assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//		Map<String, String> jsonDictionary = (Map<String, String>) restTemplate.exchange(request, responseType);
//		log.info(jsonDictionary.toString());

		String url = "http://localhost:8080/login";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("username", "Admin");
		map.add("password", "Admin123");

		HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( url, request1 , String.class );
		log.info(response.toString());
	}

}
