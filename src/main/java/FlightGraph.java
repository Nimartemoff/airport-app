import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FlightGraph {
	private HashMap<String, PriorityQueue<Flight>> adjMap;
	
	public FlightGraph() {
		adjMap = new HashMap<String, PriorityQueue<Flight>>();
	}
	
	public void addFlight(Flight flight) {
		if (adjMap.containsKey(flight.getDepartureAirport())) {
			adjMap.get(flight.getDepartureAirport()).add(flight);
		} else {
			PriorityQueue<Flight> flightSet = new PriorityQueue<Flight>();
			flightSet.add(flight);
			adjMap.put(flight.getDepartureAirport(), flightSet);
		}
	}
	
	// Modified BFS
	public void findPaths(String departureAirport, String arrivalAirport) {
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
	        // then print the path
	        if (last.equals(arrivalAirport))
	        {
	        	for(String v : path)
	            {
	                System.out.print(v + " ");
	            }
	            System.out.println();
	        }
	 
	        // Traverse to all the nodes connected to
	        // current vertex and push new path to queue
	        PriorityQueue<Flight> lastNode = adjMap.get(last);
	        if (lastNode != null) {
	        	for(Flight flight : lastNode)
		        {
		            if (!path.contains(flight.getArrivalAirport()))
		            {
		                List<String> newpath = new LinkedList<String>(path);
		                newpath.add(flight.getArrivalAirport());
		                queue.offer(newpath);
		            }
		        }
	        }
	    }
	}
}
