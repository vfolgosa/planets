package br.com.viniciusrf.planets.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.viniciusrf.planets.dto.PlanetDTO;
import br.com.viniciusrf.planets.exception.PLanetSaveException;
import br.com.viniciusrf.planets.exception.PlanetNotFoundException;

@Service
public interface IPlanetsService {
	
	public List<PlanetDTO> retrieveAllPlanets();
	public PlanetDTO createPlanet(PlanetDTO planet) throws PLanetSaveException;
	public PlanetDTO getPlanet(String idPlanet) throws PlanetNotFoundException;
	public void deletePlanet(String idPlanet) throws PlanetNotFoundException;
	public PlanetDTO searchPlanet(String name) throws PlanetNotFoundException;
	
}
