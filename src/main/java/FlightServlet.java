import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FlightServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departureAirport = request.getParameter("departureairport");
		String arrivalAirport = request.getParameter("arrivalairport");
		String departureDate = request.getParameter("departuredate");
		
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
