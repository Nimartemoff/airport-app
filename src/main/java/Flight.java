import java.math.BigDecimal;
import java.time.LocalDate;

public class Flight {
	private String departureAirport;
	private String arrivalAirport;
	private LocalDate departureDate;
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
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	
	public BigDecimal getCost() {
		return cost;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public Flight(String departureAirport, String arrivalAirport, LocalDate departureDate, BigDecimal cost) {
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDate = departureDate;
		this.cost = cost;
	}
}
