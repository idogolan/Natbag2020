package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import core.Flight;

public class FlightsToStringTest {

	@Test
	public void toStringTest() {
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 7), new Time(5, 30, 0), "ED 927", "El-Al");
		StringBuffer expectedResult = new StringBuffer();
		// Flight number: ED 927 From: ISR To: THI At: 2020-04-07 05:30:00 With El-Al
		expectedResult.append("Flight number: ");
		expectedResult.append("ED 927");
		expectedResult.append(" From: ");
		expectedResult.append("ISR");
		expectedResult.append(" To: ");
		expectedResult.append("THI");
		expectedResult.append(" At: ");
		expectedResult.append("2020-04-07 05:30:00");
		expectedResult.append(" With ");
		expectedResult.append("El-Al");
		expectedResult.append("\n");

		assertEquals(expectedResult.toString(), fly1.toString());
	}

	@Test
	public void toStringArrayListTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 7), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 8), new Time(6, 0, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2020, 4, 9), new Time(6, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		StringBuffer expectedResult = new StringBuffer();
		// [Flight number: ED 927 From: ISR To: THI At: 2020-04-07 05:30:00 With El-Al
		// , Flight number: ES 845 From: ISR To: MOS At: 2020-04-08 06:00:00 With El-Al
		// , Flight number: AF 233 From: ISR To: US At: 2020-04-09 06:30:00 With Arkia
		// ]
		expectedResult.append("[");
		expectedResult.append(fly1);
		expectedResult.append(", ");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}

}
