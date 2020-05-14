package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

import javax.annotation.processing.FilerException;

public class Flight {
	public static final String DEPARTURES = "Departures", ARRIVALS = "Arrivals";

	private String startLocation;
	private String destination;
	private LocalDate flightDate;
	private Time flightTime;
	private String flightNumber;
	private String company;
	private String direction;

	public Flight(String startLocation, String endLocation, LocalDate flightDate, Time flightTime, String flightNumber,
			String company) {
		this.startLocation = startLocation;
		this.destination = endLocation;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.flightNumber = flightNumber;
		this.company = company;
		setDirection();
	}

	public Flight(File f, Scanner s) throws FilerException {
		this.company = s.nextLine();
		this.flightNumber = s.nextLine();
		this.startLocation = s.nextLine();
		this.destination = s.nextLine();
		this.direction = s.nextLine();
		int year = s.nextInt();
		int month = s.nextInt();
		int day = s.nextInt();
		this.flightDate = LocalDate.of(year, month, day);
		int hour = s.nextInt();
		int min = s.nextInt();
		this.flightTime = new Time(hour, min, 0);
	}

	private void setDirection() {
		if (startLocation.equals("Isreal") || startLocation.equals("ISR") || startLocation.equals("isreal"))
			direction = DEPARTURES;
		else
			direction = ARRIVALS;
	}

	public void Save(PrintWriter pw) throws FileNotFoundException {
		pw.println(company);
		pw.println(flightNumber);
		pw.println(startLocation);
		pw.println(destination);
		pw.println(direction);
		pw.print(flightDate.getYear() + " ");
		pw.print(flightDate.getMonthValue() + " ");
		pw.println(flightDate.getDayOfMonth());
		pw.print(flightTime.getHours() + " ");
		pw.println(flightTime.getMinutes());
	}

	public String getStartLocation() {
		return startLocation;
	}

	public String getEndLocation() {

		return destination;
	}

	public String getDirection() {
		return direction;
	}

	public LocalDate getFlightTime() {
		return flightDate;
	}

	public String getNumFlight() {
		return flightNumber;
	}

	public boolean equals(Flight other) {
		if (this.comperTo(other) == 0)
			return true;
		return false;
	}

	public int comperTo(Flight other) {
		if (this.flightDate.compareTo(other.flightDate) == 0) {
			return this.flightTime.compareTo(other.flightTime);
		} else
			return this.flightDate.compareTo(other.flightDate);
	}

	public String toString() {
		return "Flight number: " + flightNumber + " " + direction + " From: " + startLocation + " To: " + destination
				+ " At: " + flightDate + " " + flightTime + " With " + company + "\n";
	}
}
