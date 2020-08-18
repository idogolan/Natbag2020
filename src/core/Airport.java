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
	private boolean arrivals, departures, dateAndTime, company, rangeOfDatesBegining, rangeOfDatesEnd, country;
	private int[] days; // index+1 is the day in the week, value 0 => show, else => don't show
	private String strCompany, strCountry;
	private LocalDate begining, end;

	public Airport(String name) {
		if (!setName(name))
			this.name = NATBAG;
		flightList = new ArrayList<Flight>();
		arrivals = true;
		departures = true;
		dateAndTime = true;
		company = false;
		rangeOfDatesBegining = false;
		rangeOfDatesEnd = false;
		days = new int[7];// by default all the values are 0
		country = false;
	}

	public boolean addFlight(String start, String end, LocalDate date, Time time, String number, String company) {
		if (start == null || end == null || date == null || time == null || number == null || company == null)
			return false;
		Flight f = new Flight(start, end, date, time, number, company);
		flightList.add(f);
		return true;
	}

	public boolean addFlight(Flight flight) {
		if (flight == null)
			return false;
		flightList.add(flight);
		return true;
	}

	// save the airport to the file
	public void save() throws FileNotFoundException {
		File f = new File(name + ".txt");
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
	public void load() throws FileNotFoundException, FilerException {
		String fileName = name + ".txt";
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

	private void sortRangeOfDatesBegining() {
		for (int i = 0; i < showing.size(); i++) {
			if (showing.get(i).getFlightDate().compareTo(begining) > 0) {
				showing.remove(i);
				i--;
			}
		}
	}

	private void sortRangeOfDatesEnd() {
		for (int i = 0; i < showing.size(); i++) {
			if (showing.get(i).getFlightDate().compareTo(end) < 0) {
				showing.remove(i);
				i--;
			}
		}
	}

	private void sortByDirection() {
		for (int i = 0; i < showing.size(); i++) {
			if (!arrivals && showing.get(i).getDirection().equalsIgnoreCase("Arrivals")) {
				showing.remove(i);
				i--;
			}
			if (!departures && showing.get(i).getDirection().equals("Departures")) {
				showing.remove(i);
				i--;
			}
		}
	}

	private void sortByCompany() {
		for (int i = 0; i < showing.size(); i++) {
			if (!showing.get(i).getCompany().equals(strCompany)) {
				showing.remove(i);
				i--;
			}
		}
	}

	private void sortByCountry() {
		for (int i = 0; i < showing.size(); i++) {
			if (!showing.get(i).getEndLocation().equals(strCountry)) {
				showing.remove(i);
				i--;
			}
		}
	}

	private void sortByDays() {
		for (int i = 1; i <= days.length; i++) {
			if (days[i - 1] != 0) {
				for (int j = 0; j < showing.size(); j++) {
					if (showing.get(j).getFlightDate().getDayOfWeek().getValue() == i) {
						showing.remove(j);
						j--;
					}
				}
			}
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

	public void setCompany(String str) {
		if (str != null) {
			company = true;
			strCompany = str;
			return;
		}
		company = false;
	}

	public void setCountry(String str) {
		if (str != null) {
			country = true;
			strCountry = str;
			return;
		}
		country = false;
	}

	public void setRangeOfDatesBegining(LocalDate begining) {
		if (begining != null) {
			this.begining = LocalDate.of(begining.getDayOfMonth(), begining.getMonthValue(), begining.getYear());
			rangeOfDatesBegining = true;
			return;
		}
		rangeOfDatesBegining = false;
	}

	public void setRangeOfDatesEnd(LocalDate end) {
		if (end != null) {
			this.end = LocalDate.of(end.getDayOfMonth(), end.getMonthValue(), end.getYear());
			rangeOfDatesEnd = true;
			return;
		}
		rangeOfDatesBegining = false;
	}

	public void setSunday(boolean show) {
		if (!show) {
			days[0] = 1;
			return;
		}
		days[0] = 0;
	}

	public void setMonday(boolean show) {
		if (!show) {
			days[1] = 1;
			return;
		}
		days[1] = 0;
	}

	public void setTuesday(boolean show) {
		if (!show) {
			days[2] = 1;
			return;
		}
		days[2] = 0;
	}

	public void setWednesday(boolean show) {
		if (!show) {
			days[3] = 1;
			return;
		}
		days[3] = 0;
	}

	public void setThursday(boolean show) {
		if (!show) {
			days[4] = 1;
			return;
		}
		days[4] = 0;
	}

	public void setFriday(boolean show) {
		if (!show) {
			days[5] = 1;
			return;
		}
		days[5] = 0;
	}

	public void setSaturday(boolean show) {
		if (!show) {
			days[6] = 1;
			return;
		}
		days[6] = 0;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Flight> getAllFlightList() {
		ArrayList<Flight> temp = new ArrayList<Flight>(flightList);
		return temp;
	}

	public ArrayList<Flight> showFlights() {
		showing = new ArrayList<Flight>(flightList);
		if (dateAndTime)
			sortByDateAndTime();
		if (!arrivals || !departures)
			sortByDirection();
		if (company)
			sortByCompany();
		if (rangeOfDatesBegining)
			sortRangeOfDatesBegining();
		if (rangeOfDatesEnd)
			sortRangeOfDatesEnd();
		if (country)
			sortByCountry();
		sortByDays();
		return showing;
	}
}
