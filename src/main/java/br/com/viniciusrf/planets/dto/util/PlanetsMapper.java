package br.com.viniciusrf.planets.dto.util;

import org.modelmapper.ModelMapper;

import br.com.viniciusrf.planets.dto.PlanetDTO;
import br.com.viniciusrf.planets.model.Planet;

public class PlanetsMapper {
		

	private static ModelMapper builder() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	
	public static PlanetDTO convertToDto(Planet planet) {
		PlanetDTO postDto = PlanetsMapper.builder().map(planet, PlanetDTO.class);
	    return postDto;
	}
	
	public static Planet convertToEntity(PlanetDTO planetDto) {
		Planet planet = PlanetsMapper.builder().map(planetDto, Planet.class);
	    return planet;
	}
	

}
