import java.util.HashMap;
import java.util.TreeSet;

public class FlightGraph {
	private HashMap<String, TreeSet<Flight>> adjMap;
	
	public void addFlight(Flight flight) {
		if (adjMap.containsKey(flight.getDepartureAirport())) {
			adjMap.get(flight.getDepartureAirport()).add(flight);
		} else {
			TreeSet<Flight> flightTree = new TreeSet<Flight>();
			flightTree.add(flight);
			adjMap.put(flight.getDepartureAirport(), flightTree);
		}
	}
}
