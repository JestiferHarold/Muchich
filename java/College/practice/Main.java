enum Planets{
	MERCURY,
	VENUS,
	EARTH,
	MARS,
	JUPITER,
	SATURN,
	URANUS,
	NEPTUNE,
	PLUTO,
}


public class Main{
	
	static void canILiveHere(Planets myPlanet){
		switch (myPlanet){
			case EARTH:
				System.out.println("You can live here");
				break;
			default:
				System.out.println("You cannot live here");
		}
	}

	public static void main(String[] args){
		Planets myPlanet = Planets.EARTH;
		canILiveHere(myPlanet);
	}
}
