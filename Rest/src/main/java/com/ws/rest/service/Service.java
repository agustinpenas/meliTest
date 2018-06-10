package com.ws.rest.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ws.rest.vo.RequestVO;

import solarsystem.SolarSystem;



@Path("/SolarSystem")
public class Service {

	@GET
	@Path("/weatherForDay")
	//@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public RequestVO requestWeather(/*RequestVO request*/ @QueryParam("clima") String clima, @QueryParam("dia") int dia) {
		
		SolarSystem system = new SolarSystem();
		RequestVO request =new RequestVO();
		request.setDia(dia);
		system.setDate(request.getDia());
		request.setClima(system.weatherForToday().getName());
		
		
		return request;
	}
	
	@GET
	@Path("/weatherForDayX")
	//@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public RequestVO requestWeather() {
		
		SolarSystem system = new SolarSystem();
		RequestVO request =new RequestVO();
		request.setDia(10);
		system.setDate(request.getDia());
		request.setClima(system.weatherForToday().getName());
		
		
		return request;
	}
}
