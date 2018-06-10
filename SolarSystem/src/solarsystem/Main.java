package solarsystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	static final int DAYS_IN_YEAR=360;


	public static void main(String[] args) {
		
		
		HashMap<Weather, LinkedList<Period>> weatherMap = new HashMap<Weather, LinkedList<Period>>();
		for(Weather weather : Weather.values()) {
			weatherMap.put(weather, new LinkedList<Period>());
		}
		
		SolarSystem system = new SolarSystem();
		
		Period currPeriod = new Period();
		currPeriod.start=0;
		currPeriod.weather = system.weatherForToday();
		//We assume planets start aligned so no rain.

		for(int day=1; day<DAYS_IN_YEAR*10;day++) {
			Weather weather = system.weatherForToday();
			
			if (currPeriod.weather == weather) {
				//if it is the same period and it is raining we need to update heavy rain day
				if(currPeriod.weather == Weather.RAIN && currPeriod.heavyRainPerimeter < system.perimeter()) {
						currPeriod.heavyRainDay=day;
						currPeriod.heavyRainPerimeter=system.perimeter();
				}
				system.newDay();
				continue;
			}else {
				currPeriod.end=day-1;
				weatherMap.get(currPeriod.weather).add(currPeriod);
				currPeriod = new Period();
				currPeriod.start = day;
				currPeriod.weather = weather;
				system.newDay();
			}
			
			
		}
		
		System.out.println("Habra " + weatherMap.get(Weather.RAIN).size() + " periodos de lluvia");
		System.out.println("Sus picos son: ");
		for(Period p : weatherMap.get(Weather.RAIN)) {
			System.out.println("el dia: " + p.heavyRainDay%DAYS_IN_YEAR + " del anio: " + p.heavyRainDay/DAYS_IN_YEAR + " para el periodo entre el "+
		p.start%DAYS_IN_YEAR+"/"+p.start/DAYS_IN_YEAR +" y el " + p.end%DAYS_IN_YEAR+"/"+p.end/DAYS_IN_YEAR);
		}

		System.out.println("Habra " + weatherMap.get(Weather.DROUGHT).size() + " periodos de sequia");

		System.out.println("Habra " + weatherMap.get(Weather.RAIN).size() + " periodos con condiciones optimas de presion y temperatura");
	}

}
