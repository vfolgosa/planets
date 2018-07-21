package br.com.viniciusrf.planets.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.viniciusrf.planets.dto.PlanetApiSW;
import br.com.viniciusrf.planets.dto.ResultVO;

@Service
public class StarWarsApi {

	@Value("${services.swapi.url}")
	private String SWAPI_URL;
	
	@Value("${services.swapi.operation.planets}")
	private String PLANETS_RESOURCE;
	
	@Value("${services.config.useragent}")
	private String userAgent;
	

	private HttpEntity<String> entity;
	
	@Autowired
	private final RestTemplate restTemplate;
	
	
	public StarWarsApi() {
		 HttpHeaders headers = new HttpHeaders();
	     headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
	     headers.setContentType(MediaType.APPLICATION_JSON);
	     headers.add("user-agent", userAgent);
	     entity = new HttpEntity<>("parameters", headers);
	     
	     restTemplate = new RestTemplate();
	     
	}

	public List<PlanetApiSW> getPlanetByName(String name) {
			
		ResponseEntity<ResultVO> result = restTemplate.exchange(SWAPI_URL + PLANETS_RESOURCE, HttpMethod.GET, entity, ResultVO.class, name);
		
		List<PlanetApiSW> listResult = result.getBody().getResults();
		
		return listResult;
	}
	
	//TODO: melhorar isso
	public List<String> getFilms(List<PlanetApiSW> list) {
		
		if (!list.isEmpty())
			return list.get(0).getFilms();
		else {
			return null;
		}
	
	}

	

}
