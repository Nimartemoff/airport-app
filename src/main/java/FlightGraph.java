import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FlightGraph {
	private HashMap<String, HashMap<OffsetDateTime, HashMap<String, PriorityQueue<Flight>>>> adjMap;
	
	public FlightGraph() {
		adjMap = new HashMap<String, HashMap<OffsetDateTime, HashMap<String, PriorityQueue<Flight>>>>();
	}
	
	public void addFlight(Flight flight) {
		if (!adjMap.containsKey(flight.getDepartureAirport())) {
			PriorityQueue<Flight> pq = new PriorityQueue<Flight>();
			pq.add(flight);
			HashMap<String, PriorityQueue<Flight>> innerNestedMap = new HashMap<String, PriorityQueue<Flight>>();
			innerNestedMap.put(flight.getArrivalAirport(), pq);
			HashMap<OffsetDateTime, HashMap<String, PriorityQueue<Flight>>> nestedMap = new HashMap<OffsetDateTime, HashMap<String, PriorityQueue<Flight>>>();
			nestedMap.put(flight.getDepartureDate(), innerNestedMap);
			adjMap.put(flight.getDepartureAirport(), nestedMap);
		} else {
			if (!adjMap.get(flight.getDepartureAirport()).containsKey(flight.getDepartureDate())) {
				PriorityQueue<Flight> pq = new PriorityQueue<Flight>();
				pq.add(flight);
				HashMap<String, PriorityQueue<Flight>> innerNestedMap = new HashMap<String, PriorityQueue<Flight>>();
				innerNestedMap.put(flight.getArrivalAirport(), pq);
				adjMap.get(flight.getDepartureAirport()).put(flight.getDepartureDate(), innerNestedMap);
			} else {
				if (!adjMap.get(flight.getDepartureAirport()).get(flight.getDepartureDate()).containsKey(flight.getArrivalAirport())) {
					PriorityQueue<Flight> pq = new PriorityQueue<Flight>();
					pq.add(flight);
					adjMap.get(flight.getDepartureAirport()).get(flight.getDepartureDate()).put(flight.getArrivalAirport(), pq);
				} else {
					adjMap.get(flight.getDepartureAirport()).get(flight.getDepartureDate()).get(flight.getArrivalAirport()).add(flight);
				}
			}
		}
	}
	
	public PriorityQueue<Flight> getDirectFlights(String departureAirport, String arrivalAirport, OffsetDateTime departureDate) {
		if (adjMap.containsKey(departureAirport) && adjMap.get(departureAirport).containsKey(departureDate) &&
				adjMap.get(departureAirport).get(departureDate).containsKey(arrivalAirport))
			return adjMap.get(departureAirport).get(departureDate).get(arrivalAirport);
		return null;
	}
	
	// Modified BFS
	public List<List<PriorityQueue<Flight>>> findPaths(String departureAirport, String arrivalAirport, OffsetDateTime departureDate) {
		List<List<PriorityQueue<Flight>>> flights = new LinkedList<List<PriorityQueue<Flight>>>();
		
		// Create a queue which stores
	    // the paths
	    Queue<List<String>> queue = new LinkedList<List<String>>();
	 
	    // Path vector to store the current path
	    List<String> path = new LinkedList<String>();
	    ((LinkedList<String>) path).addLast(departureAirport);
	    queue.offer(path);
	     
	    while (!queue.isEmpty())
	    {
	        path = queue.poll();
	        String last = ((LinkedList<String>) path).getLast();
	 
	        // If last vertex is the desired destination
	        // then restore path
	        if (last.equals(arrivalAirport))
	        {
	        	for(String v : path)
	            {
	                System.out.print(v + " ");
	            }
	            System.out.println();
	            
	            List<PriorityQueue<Flight>> transfers = new LinkedList<PriorityQueue<Flight>>();
	            Iterator<String> it = path.iterator();
	            String prev = "";
	            if (it.hasNext()) prev = it.next(); 
	            
	            while (it.hasNext()) {
	            	String curr = it.next();
	            	PriorityQueue<Flight> pq = getDirectFlights(prev, curr, departureDate);
	            	transfers.add(pq);
	            	prev = curr;
	            }
	            flights.add(transfers);
	        }
	 
	        // Traverse to all the nodes connected to
	        // current vertex and push new path to queue
	        HashMap<OffsetDateTime, HashMap<String, PriorityQueue<Flight>>> lastMap = adjMap.get(last);
	        if (lastMap != null) {
	        	HashMap<String, PriorityQueue<Flight>> lastNode = lastMap.get(departureDate);
		        if (lastNode != null) {
		        	for (String airport : lastNode.keySet()) {
		        		if (!path.contains(airport)) {
		        			List<String> newpath = new LinkedList<String>(path);
			                newpath.add(airport);
			                queue.offer(newpath);
		        		}
		        	}
		        }
	        }
	    }
	    return flights;
	}
}
