import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FlightGraph {
	private HashMap<String, HashMap<String, PriorityQueue<Flight>>> adjMap;
	
	public FlightGraph() {
		adjMap = new HashMap<String, HashMap<String, PriorityQueue<Flight>>>();
	}
	
	public void addFlight(Flight flight) {
		if (!adjMap.containsKey(flight.getDepartureAirport())) {
			HashMap<String, PriorityQueue<Flight>> nestedMap = new HashMap<String, PriorityQueue<Flight>>();
			PriorityQueue<Flight> pq = new PriorityQueue<Flight>();
			pq.add(flight);
			nestedMap.put(flight.getArrivalAirport(), pq);
			adjMap.put(flight.getDepartureAirport(), nestedMap);
		} else {
			HashMap<String, PriorityQueue<Flight>> nestedMap = adjMap.get(flight.getDepartureAirport());
			if (!nestedMap.containsKey(flight.getArrivalAirport())) {
				PriorityQueue<Flight> pq = new PriorityQueue<Flight>();
				pq.add(flight);
				nestedMap.put(flight.getArrivalAirport(), pq);
				adjMap.put(flight.getDepartureAirport(), nestedMap);
			} else {
				PriorityQueue<Flight> pq = adjMap.get(flight.getDepartureAirport()).get(flight.getArrivalAirport());
				pq.add(flight);
			}
		}
	}
	
	public PriorityQueue<Flight> getDirectFlights(String departureAirport, String arrivalAirport) {
		if (adjMap.containsKey(departureAirport) && adjMap.get(departureAirport).containsKey(arrivalAirport))
			return adjMap.get(departureAirport).get(arrivalAirport);
		return null;
	}
	
	// Modified BFS
	public List<List<PriorityQueue<Flight>>> findPaths(String departureAirport, String arrivalAirport) {
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
	            	PriorityQueue<Flight> pq = getDirectFlights(prev, curr);
	            	transfers.add(pq);
	            	prev = curr;
	            }
	            flights.add(transfers);
	        }
	 
	        // Traverse to all the nodes connected to
	        // current vertex and push new path to queue
	        HashMap<String, PriorityQueue<Flight>> lastNode = adjMap.get(last);
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
	    return flights;
	}
}
