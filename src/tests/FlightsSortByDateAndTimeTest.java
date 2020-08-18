package tests;

import static org.junit.Assert.assertEquals;
import java.sql.Time;
import java.time.LocalDate;
import org.junit.Test;
import core.Airport;
import core.Flight;

public class FlightsSortByDateAndTimeTest {
	@Test
	public void sortTheListByDateAndTime() {
		Airport air = new Airport("Test");
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2015, 4, 25), new Time(5, 59, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 5, 12), new Time(4, 12, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2018, 6, 10), new Time(12, 30, 0), "AF 233", "Arkia");
		Flight fly4 = new Flight("ISR", "THI", LocalDate.of(2016, 7, 16), new Time(16, 59, 0), "ED 927", "El-Al");
		Flight fly5 = new Flight("ISR", "MOS", LocalDate.of(2019, 10, 15), new Time(5, 12, 0), "ES 845", "El-Al");
		Flight fly6 = new Flight("ISR", "US", LocalDate.of(2020, 6, 30), new Time(2, 30, 0), "AF 233", "Arkia");
		Flight fly7 = new Flight("ISR", "THI", LocalDate.of(2015, 8, 18), new Time(1, 59, 0), "ED 927", "El-Al");
		Flight fly8 = new Flight("ISR", "MOS", LocalDate.of(2016, 9, 16), new Time(7, 12, 0), "ES 845", "El-Al");
		Flight fly9 = new Flight("ISR", "US", LocalDate.of(2015, 4, 25), new Time(5, 30, 0), "AF 233", "Arkia");
		air.addFlight(fly1);
		air.addFlight(fly2);
		air.addFlight(fly3);
		air.addFlight(fly4);
		air.addFlight(fly5);
		air.addFlight(fly6);
		air.addFlight(fly7);
		air.addFlight(fly8);
		air.addFlight(fly9);

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly9);
		expectedResult.append(", ");
		expectedResult.append(fly1);
		expectedResult.append(", ");
		expectedResult.append(fly7);
		expectedResult.append(", ");
		expectedResult.append(fly4);
		expectedResult.append(", ");
		expectedResult.append(fly8);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly5);
		expectedResult.append(", ");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly6);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), air.showFlights().toString());
	}
}
