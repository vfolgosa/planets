package br.com.viniciusrf.planets.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.viniciusrf.planets.PlanetsApplication;
import br.com.viniciusrf.planets.dto.PlanetDTO;
import br.com.viniciusrf.planets.service.IPlanetsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PlanetsApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PLanetsControllerTest {
	
	private MockMvc mockMvc;
	
	private JacksonTester<PlanetDTO> json;
	
	@Autowired
	private IPlanetsService service;
	
	
	@Autowired
    private WebApplicationContext wac;
	
	private PlanetDTO planet;
	
	private final String PLANET_TEST = "Alderaan_TEST";
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
        
	}
	
	@Test
	public void verify0AllPlanets() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/planets")
                .accept(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()).andDo(print());
        
	}
	
	@Test
	public void verify1CreatePlanet() throws Exception {
		
		this.planet = new PlanetDTO(PLANET_TEST, "temperate", "grasslands, mountains", new ArrayList<String>());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/planets")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(json.write(planet).getJson())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("name", is("Alderaan_TEST")))
				.andExpect(jsonPath("climate", is("temperate")))
				.andExpect(jsonPath("terrain", is("grasslands, mountains")))
				.andExpect(jsonPath("filmsCount", is(0)))
				.andDo(print());
		
	}
	
	@Test
	public void verify3InvalidPlanetArgument() throws Exception {
		
		this.planet = new PlanetDTO();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/planets")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(json.write(planet).getJson())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	public void verify4PlanetByName() throws Exception {
		this.planet = new PlanetDTO();
		mockMvc.perform(MockMvcRequestBuilders.get("/planets/search/" + PLANET_TEST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(json.write(planet).getJson())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is("Alderaan_TEST")))
				.andExpect(jsonPath("climate", is("temperate")))
				.andExpect(jsonPath("terrain", is("grasslands, mountains")))
				.andDo(print());		
	}
	
	@Test
	public void verify5PlanetById() throws Exception {	
		
		PlanetDTO createdPlanet = service.searchPlanet(PLANET_TEST);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/planets/" + createdPlanet.get_id())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is("Alderaan_TEST")))
				.andExpect(jsonPath("climate", is("temperate")))
				.andExpect(jsonPath("terrain", is("grasslands, mountains")))
				.andDo(print());
	}
	
	@Test
	public void verify6DeletePlanet() throws Exception {
		PlanetDTO createdPlanet = service.searchPlanet(PLANET_TEST);
		mockMvc.perform(MockMvcRequestBuilders.delete("/planets/" + createdPlanet.get_id()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test //(expected = PlanetNotFoundException.class)
	public void verify7NotFoundPlanet() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/planets/search/" + PLANET_TEST)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());
	}
	

}
