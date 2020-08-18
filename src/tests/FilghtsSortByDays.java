package tests;

import static org.junit.Assert.assertEquals;
import java.sql.Time;
import java.time.LocalDate;
import org.junit.Test;
import core.Airport;
import core.Flight;

public class FilghtsSortByDays {
	
	@Test
	public void daysTest() {
		Airport air = new Airport("Test");
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 8, 16), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 8, 17), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("UK", "ISR", LocalDate.of(2020, 8, 18), new Time(5, 30, 0), "AF 233", "Arkia");
		Flight fly4 = new Flight("UK", "ISR", LocalDate.of(2020, 8, 19), new Time(5, 30, 0), "AF 234", "Arkia");
		Flight fly5 = new Flight("UK", "ISR", LocalDate.of(2020, 8, 20), new Time(5, 30, 0), "AF 235", "Arkia");
		Flight fly6 = new Flight("UK", "ISR", LocalDate.of(2020, 8, 21), new Time(5, 30, 0), "AF 236", "Arkia");
		Flight fly7 = new Flight("UK", "ISR", LocalDate.of(2020, 8, 22), new Time(5, 30, 0), "AF 237", "Arkia");
		air.addFlight(fly1);
		air.addFlight(fly2);
		air.addFlight(fly3);
		air.addFlight(fly4);
		air.addFlight(fly5);
		air.addFlight(fly6);
		air.addFlight(fly7);
		
		air.setSunday(false);
		air.setMonday(true);
		air.setTuesday(true);
		air.setWednesday(false);
		air.setThursday(true);
		air.setFriday(false);
		air.setSaturday(false);

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly5);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), air.showFlights().toString());
	}
}
