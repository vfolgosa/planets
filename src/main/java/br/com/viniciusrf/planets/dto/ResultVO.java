package br.com.viniciusrf.planets.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultVO {
	
	private List<PlanetApiSW> results;
	
	public ResultVO () {
		
	}
	
	
	public ResultVO(List<PlanetApiSW> results, String name) {
		this.results = results;
	}


	public List<PlanetApiSW> getResults() {
		return results;
	}

	public void setResults(List<PlanetApiSW> results) {
		this.results = results;
	}

}
