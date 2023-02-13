import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Flight implements Comparable<Flight> {
	private int flightId;
	private String departureAirport;
	private String arrivalAirport;
	private OffsetDateTime departureDate;
	private BigDecimal cost;
	
	public String getDepartureAirport() {
		return departureAirport;
	}
	
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	
	public OffsetDateTime getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(OffsetDateTime departureDate) {
		this.departureDate = departureDate;
	}
	
	public BigDecimal getCost() {
		return cost;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public Flight(int flightId, String departureAirport, String arrivalAirport, OffsetDateTime departureDate, BigDecimal cost) {
		this.flightId = flightId;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDate = departureDate;
		this.cost = cost;
	}

	public int compareTo(Flight o) {
		if (getCost() == o.getCost()) return getDepartureDate().compareTo(o.getDepartureDate());
		return getCost().compareTo(o.getCost());
	}
	
	public String toString() {
		return getCost().toString();
	}
}
