package br.com.viniciusrf.planets.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.viniciusrf.planets.model.Planet;

public interface IPlanetsRepository extends MongoRepository<Planet, String>{
	
	Optional<Planet> findByNameIgnoreCase(String name);
	
}
