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

	private String startLocation;
	private String endLocation;
	private LocalDate flightDate;
	private Time flightTime;
	private String flightNumber;
	private String company;

	public Flight(String startLocation, String endLocation, LocalDate flightDate, Time flightTime, String flightNumber,
			String company) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.flightNumber = flightNumber;
		this.company = company;
	}

	public Flight(File f, Scanner s) throws FilerException {///////////////////////////
		this.company = s.nextLine();
		this.flightNumber = s.nextLine();
		this.startLocation = s.nextLine();
		this.endLocation = s.nextLine();
		int year = s.nextInt();
		int month = s.nextInt();
		int day = s.nextInt();
		this.flightDate = LocalDate.of(year, month, day);
		int hour = s.nextInt();
		int min = s.nextInt();
		this.flightTime = new Time(hour, min, 0);
	}

	public void Save(PrintWriter pw) throws FileNotFoundException {
		pw.println(company);
		pw.println(flightNumber);
		pw.println(startLocation);
		pw.println(endLocation);
		pw.print(flightDate.getYear() + " ");
		pw.print(flightDate.getMonthValue() + " ");
		pw.println(flightDate.getDayOfMonth());
		pw.print(flightTime.getHours()+" ");
		pw.println(flightTime.getMinutes());
	}

	public String getStartLocation() {
		return startLocation;
	}

	public String getEndLocation() {

		return endLocation;
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
		if (this.flightDate.getYear() < other.flightDate.getYear())
			return -1;
		else if (this.flightDate.getYear() == other.flightDate.getYear()) {
			if (this.flightDate.getMonthValue() < other.flightDate.getMonthValue())
				return -1;
			else if (this.flightDate.getMonthValue() == other.flightDate.getMonthValue()) {
				if (this.flightDate.getDayOfMonth() < other.flightDate.getDayOfMonth())
					return -1;
				else if (this.flightDate.getDayOfMonth() == other.flightDate.getDayOfMonth()) {
					if (this.flightTime.getHours() < other.flightTime.getHours())
						return -1;
					else if (this.flightTime.getHours() == other.flightTime.getHours()) {
						if (this.flightTime.getMinutes() < other.flightTime.getMinutes())
							return -1;
						else
							return 0;
					} else
						return 1;
				}
			} else
				return 1;
		}
		return 1;
	}

	public String toString() {
		return "Flight number: " + flightNumber + " From: " + startLocation + " To: " + endLocation + " At: "
				+ flightDate + " " + flightTime + " With " + company + "\n";
	}
}
