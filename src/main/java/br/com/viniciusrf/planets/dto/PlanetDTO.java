package br.com.viniciusrf.planets.dto;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetDTO {
	
	private String _id;
	@NotNull
	private String name;
	@NotNull
	private String climate;
	@NotNull
	private String terrain;
	
	private List<String> films;

	public PlanetDTO(String _id, String name, String climate, String terrain, List<String> films) {
		this._id = _id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.films = films;
	}
	
	public PlanetDTO(String _id, String name, String climate, String terrain) {
		this._id = _id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	public PlanetDTO(String name, String climate, String terrain, List<String> films) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.films = films;
	}
	
	public PlanetDTO() {
	}
	
	public String getName() {
		return name;
	}
	
	@JsonProperty("filmsCount")
	public Integer getFilmsCount() {
		return Objects.isNull(films) ? null : films.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	
	
	
	
	

}
