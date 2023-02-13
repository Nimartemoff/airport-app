import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;

public class FlightDB {
	public static FlightGraph selectFlightGraph() {
		FlightGraph flightGraph = new FlightGraph();
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "310874");
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM flight");
			
			while (results.next()) {
	        	int id = results.getInt(1);
	        	String departureAirport = results.getString(2);
	        	String arrivalAirport = results.getString(3);
	        	//OffsetDateTime departureDate = results.getObject(4, OffsetDateTime.class);
	        	BigDecimal cost = results.getBigDecimal(5);
	        	flightGraph.addFlight(new Flight(id, departureAirport, arrivalAirport, OffsetDateTime.now(), cost));
	    	}
	    	connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flightGraph;
	}
}
