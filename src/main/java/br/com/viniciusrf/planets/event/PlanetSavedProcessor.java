package br.com.viniciusrf.planets.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.viniciusrf.planets.dto.PlanetApiSW;
import br.com.viniciusrf.planets.model.Planet;
import br.com.viniciusrf.planets.repository.IPlanetsRepository;
import br.com.viniciusrf.planets.service.StarWarsApi;

@Service
public class PlanetSavedProcessor {

	
	@Autowired
    private StarWarsApi apiSW;
	
	@Autowired
    private IPlanetsRepository repository;
    

    @Async
    @TransactionalEventListener
    public void updatePlanetFilmsList(PlanetSavedEvent event) throws InterruptedException {
    	List<PlanetApiSW> list = apiSW.getPlanetByName(event.getName());
    	Planet planet = repository.findByNameIgnoreCase(event.getName()).get();
        planet.setFilms(apiSW.getFilms(list));
        repository.save(planet);
        //Thread.sleep(2000L);
    	
    }
    
}