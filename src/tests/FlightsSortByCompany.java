package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import core.Flight;
import core.Program;

public class FlightsSortByCompany {
	@Test
	public void companyTest() { 
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 8), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("UK", "ISR", LocalDate.of(2020, 4, 9), new Time(5, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		ArrayList<Flight> results = new ArrayList<>(Program.sortByCompany(flightsForTest, "El-Al"));

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly1);
		expectedResult.append(", ");
		expectedResult.append(fly2);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), results.toString());
	}
}
