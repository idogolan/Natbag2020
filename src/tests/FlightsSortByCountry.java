package tests;

import static org.junit.Assert.assertEquals;
import java.sql.Time;
import java.time.LocalDate;
import org.junit.Test;
import core.Airport;
import core.Flight;

public class FlightsSortByCountry {
	@Test
	public void countryTest() {
		Airport air = new Airport("Test");
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 8), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("UK", "ISR", LocalDate.of(2020, 4, 9), new Time(5, 30, 0), "AF 233", "Arkia");
		Flight fly4 = new Flight("UK", "ISR", LocalDate.of(2020, 4, 9), new Time(5, 30, 0), "AF 234", "Arkia");
		air.addFlight(fly1);
		air.addFlight(fly2);
		air.addFlight(fly3);
		air.addFlight(fly4);

		air.setCountry("ISR");

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly4);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), air.showFlights().toString());
		
		StringBuffer expectedResult2 = new StringBuffer();
		expectedResult2.append("[");
		expectedResult2.append(fly2);
		expectedResult2.append("]");
		
		air.setCountry("MOS");
		
		assertEquals(expectedResult2.toString(), air.showFlights().toString());
	}
}
