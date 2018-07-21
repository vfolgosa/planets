package br.com.viniciusrf.planets.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.viniciusrf.planets.dto.PlanetDTO;
import br.com.viniciusrf.planets.exception.PLanetSaveException;
import br.com.viniciusrf.planets.exception.PlanetValidationException;
import br.com.viniciusrf.planets.service.IPlanetsService;
import br.com.viniciusrf.planets.service.StarWarsApi;

@ControllerAdvice
@RestController
@RequestMapping("/planets")
public class PlanetsController {
	
	@Autowired
	public IPlanetsService service;
	
	@Autowired
	public StarWarsApi swApi;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlanetDTO>> retrieveAllPlanets() {
		return ResponseEntity.ok(service.retrieveAllPlanets());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlanetDTO> createPlanet(@Valid @RequestBody(required = true) PlanetDTO planet) throws PlanetValidationException, PLanetSaveException {
		PlanetDTO newplanet = service.createPlanet(planet);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newplanet.get_id()).toUri();
		
		return ResponseEntity.created(location).body(newplanet);
	}
	
	@GetMapping(value = "/{planetId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlanetDTO> getPlanetById(@PathVariable String planetId){
		return ResponseEntity.ok(service.getPlanet(planetId));
	}
	
	@DeleteMapping(value = "/{planetId}")
	public ResponseEntity<?> deletePlanet(@PathVariable String planetId) {
		service.deletePlanet(planetId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlanetDTO> searchPlanet(@PathVariable String name) {
		return ResponseEntity.ok(service.searchPlanet(name));
	}
	
	
} 
