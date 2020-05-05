package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import core.Flight;
import core.Program;

public class FlightsSortByDateAndTimeTest {
	@Test
	public void dayTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 8), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2020, 4, 9), new Time(5, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		Program.sortByDateAndTime(flightsForTest);// testing this sort. only the days are different

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly1);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}
	
	@Test
	public void monthTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 12, 2), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 15), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2020, 9, 9), new Time(5, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		Program.sortByDateAndTime(flightsForTest);// testing this sort.

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly1);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}
	
	@Test
	public void yearTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2000, 4, 10), new Time(5, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2019, 4, 8), new Time(5, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2007, 4, 9), new Time(5, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		Program.sortByDateAndTime(flightsForTest);// testing this sort.

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly1);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly2);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}
	
	@Test
	public void hourTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(12, 30, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 10), new Time(3, 30, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2020, 4, 10), new Time(6, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		Program.sortByDateAndTime(flightsForTest);// testing this sort.

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly1);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}
	
	@Test
	public void minuteTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(5, 59, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 10), new Time(5, 12, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2020, 4, 10), new Time(5, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		Program.sortByDateAndTime(flightsForTest);// testing this sort.

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append(", ");
		expectedResult.append(fly1);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}
	
	@Test
	public void sameDateAndTimeTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2020, 4, 10), new Time(5, 00, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 4, 10), new Time(5, 00, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2020, 4, 10), new Time(5, 00, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);

		Program.sortByDateAndTime(flightsForTest);// testing this sort.

		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("[");
		expectedResult.append(fly1);
		expectedResult.append(", ");
		expectedResult.append(fly2);
		expectedResult.append(", ");
		expectedResult.append(fly3);
		expectedResult.append("]");

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}
	
	@Test
	public void totalTest() {
		ArrayList<Flight> flightsForTest = new ArrayList<>();
		Flight fly1 = new Flight("ISR", "THI", LocalDate.of(2015, 4, 25), new Time(5, 59, 0), "ED 927", "El-Al");
		Flight fly2 = new Flight("ISR", "MOS", LocalDate.of(2020, 5, 12), new Time(4, 12, 0), "ES 845", "El-Al");
		Flight fly3 = new Flight("ISR", "US", LocalDate.of(2018, 6, 10), new Time(12, 30, 0), "AF 233", "Arkia");
		Flight fly4 = new Flight("ISR", "THI", LocalDate.of(2016, 7, 16), new Time(16, 59, 0), "ED 927", "El-Al");
		Flight fly5 = new Flight("ISR", "MOS", LocalDate.of(2019, 10, 15), new Time(5, 12, 0), "ES 845", "El-Al");
		Flight fly6 = new Flight("ISR", "US", LocalDate.of(2020, 6, 30), new Time(2, 30, 0), "AF 233", "Arkia");
		Flight fly7 = new Flight("ISR", "THI", LocalDate.of(2015, 8, 18), new Time(1, 59, 0), "ED 927", "El-Al");
		Flight fly8 = new Flight("ISR", "MOS", LocalDate.of(2016, 9, 16), new Time(7, 12, 0), "ES 845", "El-Al");
		Flight fly9 = new Flight("ISR", "US", LocalDate.of(2015, 4, 25), new Time(5, 30, 0), "AF 233", "Arkia");
		flightsForTest.add(fly1);
		flightsForTest.add(fly2);
		flightsForTest.add(fly3);
		flightsForTest.add(fly4);
		flightsForTest.add(fly5);
		flightsForTest.add(fly6);
		flightsForTest.add(fly7);
		flightsForTest.add(fly8);
		flightsForTest.add(fly9);

		Program.sortByDateAndTime(flightsForTest);// testing this sort.

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

		assertEquals(expectedResult.toString(), flightsForTest.toString());
	}
}
