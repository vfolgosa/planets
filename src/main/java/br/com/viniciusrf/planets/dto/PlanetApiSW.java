package br.com.viniciusrf.planets.dto;

import java.util.List;

public class PlanetApiSW {

	private String name;

	private List<String> films;

	public PlanetApiSW(String name, List<String> films) {
		this.name = name;
		this.films = films;
	}
	
	public PlanetApiSW() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}
	
	
	
	
}
