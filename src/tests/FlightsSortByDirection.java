package tests;

import static org.junit.Assert.assertEquals;
import java.sql.Time;
import java.time.LocalDate;
import org.junit.Test;
import core.Airport;
import core.Flight;

public class FlightsSortByDirection {
	//@Test
	public void departuresTest() {
		Airport air = new Airport("Test");
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 8), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("UK", "ISR", LocalDate.of(2020, 4, 9), new Time(5, 30, 0), "AF 233", "Arkia");
		air.addFlight(fly1);
		air.addFlight(fly2);
		air.addFlight(fly3);

		air.setDepartures(true);
		air.setArrivals(false);

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly1);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), air.showFlights().toString());
	}

	@Test
	public void arrivalsTest() {
		Airport air = new Airport("Test");
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 8), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("UK", "ISR", LocalDate.of(2020, 4, 9), new Time(5, 30, 0), "AF 233", "Arkia");
		air.addFlight(fly1);
		air.addFlight(fly2);
		air.addFlight(fly3);

		air.setDepartures(false);
		air.setArrivals(true);

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly3);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), air.showFlights().toString());
	}
}
