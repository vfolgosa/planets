package br.com.viniciusrf.planets.event;

import lombok.Value;

@Value(staticConstructor = "name")
public class PlanetSavedEvent {

	private String name;

}

