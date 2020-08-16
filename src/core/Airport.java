package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.annotation.processing.FilerException;

public class Airport {
	public static final String NATBAG = "NatBag";

	private String name;
	private ArrayList<Flight> flightList;
	private ArrayList<Flight> showing;
	private boolean arrivals, departures, dateAndTime, company;
	private String strCompany;

	public Airport(String name) {
		if (!setName(name))
			this.name = NATBAG;
		flightList = new ArrayList<Flight>();
		showing = new ArrayList<Flight>();
		arrivals = true;
		departures = true;
		dateAndTime = true;
		company = false;
	}

	public boolean addFlight(String start, String end, LocalDate date, Time time, String number, String company) {
		if (start == null || end == null || date == null || time == null || number == null || company == null)
			return false;
		Flight f = new Flight(start, end, date, time, number, company);
		flightList.add(f);
		showing.add(f);
		return true;
	}

	// save the airport to the file
	public void save() throws FileNotFoundException {
		File f = new File("fligths.txt");
		PrintWriter pw = new PrintWriter(f);
		pw.println(name);
		pw.println(flightList.size());
		for (Flight fly : flightList) {
			fly.Save(pw);
			pw.println("");
		}
		pw.close();
	}

	// load from the file and changing the list accordingly
	public void load(String fileName) throws FileNotFoundException, FilerException {
		flightList.clear();
		File f = new File(fileName);
		Scanner s = new Scanner(f);
		setName(s.nextLine());
		int sum = s.nextInt();
		for (int i = 0; i < sum; i++) {
			s.nextLine();
			flightList.add(new Flight(f, s));
			s.nextLine();
		}
		showing = new ArrayList<Flight>(flightList);
	}

	// sort the current list by date and time
	private void sortByDateAndTime() {
		Comparator<Flight> comparatorByDate = new Comparator<Flight>() {
			public int compare(Flight o1, Flight o2) {
				return o1.compareTo(o2);
			}
		};
		Collections.sort(showing, comparatorByDate);
	}

	private void sortByDirection() {
		for (Flight f : showing) {
			if (!arrivals && f.getDirection().equals("Arrivals"))
				showing.remove(f);
			if (!departures && f.getDirection().equals("Departures"))
				showing.remove(f);
		}
	}

	public void sortByCompany() {
		for (Flight f : showing) {
			if (!f.getCompany().equals(strCompany))
				showing.remove(f);
		}
	}

	public boolean setName(String name) {
		if (name == null || name.length() < 1)
			return false;
		this.name = name;
		return true;
	}

	public void setArrivals(boolean arrivals) {
		this.arrivals = arrivals;
	}

	public void setDepartures(boolean departures) {
		this.departures = departures;
	}

	public void setDateAndTime(boolean dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public void setCompany(boolean company, String str) {
		if (company && str != null) {
			this.company = company;
			strCompany = str;
		}
	}

	public String getName() {
		return name;
	}

	public ArrayList<Flight> getAllFlightList() {
		ArrayList<Flight> temp = new ArrayList<Flight>(flightList);
		return temp;
	}

	public ArrayList<Flight> showFlights() {
		if (dateAndTime)
			sortByDateAndTime();
		if (!arrivals || !departures)
			sortByDirection();
		if (company)
			sortByCompany();
		return showing;
	}
}
