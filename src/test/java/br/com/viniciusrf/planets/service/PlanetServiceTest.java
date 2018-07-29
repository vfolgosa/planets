package br.com.viniciusrf.planets.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.viniciusrf.planets.PlanetsApplication;
import br.com.viniciusrf.planets.PlanetsApplicationTests;
import br.com.viniciusrf.planets.dto.PlanetApiSW;
import br.com.viniciusrf.planets.dto.PlanetDTO;
import br.com.viniciusrf.planets.exception.PlanetNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = PlanetsApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetServiceTest {
	
	@Autowired
	public IPlanetsService service;
	
	@Autowired
	public StarWarsApi serviceSw;
	
	private PlanetDTO planet;
	
	private final String PLANET_TEST = "AlderaanService_TEST";
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void verify0CreatePlanet() throws Exception {
		planet = new PlanetDTO(PLANET_TEST, "temperate", "grasslands, mountains", new ArrayList<String>());
		planet = service.createPlanet(planet);
		assertThat(planet.get_id()).isNotNull();
	}
	
	@Test
	public void verify1AllPlanets() throws Exception {
		
		service.retrieveAllPlanets();
		assertThat(service.retrieveAllPlanets()).isNotEmpty();
		
	}
	
	
	@Test
	public void verify4PlanetByName() throws Exception {
		planet = service.searchPlanet(PLANET_TEST);
		assertThat(planet.get_id()).isNotNull();
	}
	
	@Test
	public void verify5PlanetById() throws Exception {	
		planet = service.searchPlanet(PLANET_TEST);
		planet = service.getPlanet(planet.get_id());
		assertThat(planet.get_id()).isNotNull();
	}
	
	@Test
	public void verify6DeletePlanet() throws Exception {
		planet = service.searchPlanet(PLANET_TEST);
		service.deletePlanet(planet.get_id());
		assertThat(planet.get_id()).isNotNull();
	}
	
	@Test(expected = PlanetNotFoundException.class)
	public void verify7NotFoundPlanet() throws Exception {
		planet = new PlanetDTO();
		planet = service.searchPlanet(PLANET_TEST);
		assertThat(planet.get_id()).isNull();
	}
	
	@Test
	public void verify8SwAPI() throws Exception{
		List<PlanetApiSW> list = serviceSw.getPlanetByName("Alderaan");
		assertThat(list).isNotEmpty();
	}

}
