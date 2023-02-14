import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departureAirport = request.getParameter("departureairport");
		String arrivalAirport = request.getParameter("arrivalairport");
		String departureDate = request.getParameter("departuredate");
		
		// Convert departureDate to OffsetDateTime type
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime dateTime = LocalDateTime.parse(departureDate, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
		ZoneOffset offset = zoneId.getRules().getOffset(dateTime);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(dateTime, offset);
		
		FlightGraph flightGraph = FlightDB.selectFlightGraph();
		System.out.println(flightGraph.findPaths(departureAirport, arrivalAirport, offsetDateTime));
		
		PrintWriter out = response.getWriter();
		try {
			out.println("<p>Departure airport: " + departureAirport + "</p>" +
		            "<p>Arrival airport: " + arrivalAirport + "</p>" + 
				    "<p>Departure date: " + departureDate + "</p>");
		}
		finally {
			out.close();
		}
	}
}
