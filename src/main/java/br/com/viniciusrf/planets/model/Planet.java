package br.com.viniciusrf.planets.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.viniciusrf.planets.event.PlanetSavedEvent;
import lombok.ToString;

@ToString
@Document(collection = "planets")
public class Planet  extends AbstractAggregateRoot<Planet> {
	 
	@Id
	private String _id;
	
	@Indexed(unique = true)
	@NotNull
	private String name;
	@NotNull
	private String climate;
	@NotNull
	private String terrain;
	
	private List<String> films;
	
	public Planet(String _id, String name, String climate, String terrain, List<String> films) {
		this._id = _id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.films = films;
	}
	
	public Planet(String _id, String name, String climate, String terrain) {
		this._id = _id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	public Planet(String name, String climate, String terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	public Planet() {
		
	}
	
	public Planet complete() {
		registerEvent(PlanetSavedEvent.name(this.name));
		return this;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
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
	
	
	
	 
	 
}
