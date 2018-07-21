package br.com.viniciusrf.planets.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;

import br.com.viniciusrf.planets.dto.PlanetDTO;
import br.com.viniciusrf.planets.dto.util.PlanetsMapper;
import br.com.viniciusrf.planets.exception.PLanetSaveException;
import br.com.viniciusrf.planets.exception.PlanetNotFoundException;
import br.com.viniciusrf.planets.model.Planet;
import br.com.viniciusrf.planets.repository.IPlanetsRepository;

/**
 * Implementação dos serviços.
 * @author Vinicius Folgosa
 *
 */
@Service
public class PlanetsServiceImpl implements IPlanetsService{
	
	
	@Autowired
    IPlanetsRepository repository;
	
	@Autowired
	StarWarsApi apiSW;
	
	ModelMapper modelMapper = new ModelMapper();
	
	
	public List<PlanetDTO> retrieveAllPlanets() {
		
		List<Planet> lista = repository.findAll();
		
		return lista.stream().map(planet -> PlanetsMapper.convertToDto(planet)).collect(Collectors.toList());
		
	}
	
	
	public PlanetDTO createPlanet(@Valid PlanetDTO planetDto) throws PLanetSaveException{
		Planet savedPlanet;
		
		try {
			savedPlanet = repository.save(PlanetsMapper.convertToEntity(planetDto).complete());	
		}catch(Exception e) {
			throw new PLanetSaveException(e.getMessage());
		}
				
		return PlanetsMapper.convertToDto(savedPlanet);
		
	}
	
	
	public PlanetDTO getPlanet(String idPlanet) throws PlanetNotFoundException{
		Optional<Planet> planet = repository.findById(idPlanet);
		if (!planet.isPresent()) {
			throw new PlanetNotFoundException("Planet not found ID: " + idPlanet);
		}
		return PlanetsMapper.convertToDto(planet.get());
	}
	
	
	public void deletePlanet(String idPlanet) throws PlanetNotFoundException{
		Optional<Planet> planet = repository.findById(idPlanet);
		if (!planet.isPresent()) {
			throw new PlanetNotFoundException("Planet not found ID: " + idPlanet);
		}
		repository.deleteById(idPlanet); 
	}

	
	public PlanetDTO searchPlanet(String name) throws PlanetNotFoundException {
		
		Optional<Planet> planet = repository.findByNameIgnoreCase(name);
		
		if (!planet.isPresent()) {
			throw new PlanetNotFoundException("Planet not found with name: " + name);
		}
		return PlanetsMapper.convertToDto(planet.get());
		
	}
		
}
