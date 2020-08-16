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

public class Program {

	public static void main(String[] args) throws FileNotFoundException, FilerException {
		Scanner scan = new Scanner(System.in);
		ArrayList<Flight> flights = new ArrayList<>();
		boolean stayInTheMenu = true;
		while (stayInTheMenu) {
			stayInTheMenu = menu(scan, flights);
		}
	}

	public static boolean menu(Scanner scan, ArrayList<Flight> flights) throws FileNotFoundException, FilerException {
		printMenu();
		String str = scan.nextLine();
		switch (str) {
		case "1":
			if (flights.isEmpty())
				System.out.println("There is no flights available \n");
			else
				System.out.println(flights + "\n");
			return true;
		case "2":
			addFlight(scan, flights);
			return true;
		case "3":
			sortByDateAndTime(flights);
			System.out.println("Sorted by date and time:");
			System.out.println(flights + "\n");
			return true;
		case "4":
			read("fligths.txt", flights);
			return true;
		case "5":
			save(flights);
			return true;
		case "6":
			System.out.println("sya");
			return false;
		default:
			System.out.println("Not a valid option: Please choose again");
			return true;
		}
	}
	
	//sort the current list by date and time 
	public static void sortByDateAndTime(ArrayList<Flight> flights) {
		Comparator<Flight> comparatorByDate = new Comparator<Flight>() {
			public int compare(Flight o1, Flight o2) {
				return o1.compareTo(o2);
			}
		};
		Collections.sort(flights, comparatorByDate);
	}
	
	// return list by direction, 1 for Departures, 2 for Arrivals, other for both
	public static ArrayList<Flight> sortByDirection(ArrayList<Flight> flights, int kind) {
		ArrayList<Flight> list = new ArrayList<Flight>();
		for(Flight f : flights) {
			if(f.getDirection().equals(Flight.DEPARTURES) && kind!=2)
				list.add(f);
			if(f.getDirection().equals(Flight.ARRIVALS) && kind != 1)
				list.add(f);
		}
		return list;
	}
	
	// print menu test for the user 
	public static void printMenu() {
		System.out.println("Menu:");
		System.out.println("1)Show the flight list");
		System.out.println("2)Add new flight");
		System.out.println("3)Show the flight list sorted by date and time");
		System.out.println("4)Make new list of flight from a file");
		System.out.println("5)Save the list to a file");
		System.out.println("6)exit");
	}

	// making new flight using user input 
	public static void addFlight(Scanner scan, ArrayList<Flight> flights) {
		String start, end, company, number;
		int year, month, day, hour, minute;
		System.out.println("Please enter number flight: ");
		number = scan.nextLine();
		System.out.println("Please enter the start location");
		start = scan.nextLine();
		System.out.println("Please enter the end location");
		end = scan.nextLine();
		System.out.println("Please enter the company name");
		company = scan.nextLine();
		System.out.println("Please enter the year");
		year = inputOptionCheck(scan, 1900, 2100);
		System.out.println("Please enter the month");
		month = inputOptionCheck(scan, 1,12);
		System.out.println("Please enter the day");
		day = inputOptionCheck(scan,1,31);
		System.out.println("Please enter the hour");
		hour = inputOptionCheck(scan,1,60);
		System.out.println("Please enter the minute");
		minute = inputOptionCheck(scan,1,60);
		LocalDate date;
		Time time;
		date = LocalDate.of(year, month, day);
		time = new Time(hour, minute, 0);

		flights.add(new Flight(start, end, date, time, number, company));
		System.out.println("The flight added successfully to the list\n");
	}

	// save the list to the file
	public static void save(ArrayList<Flight> flights) throws FileNotFoundException {
		File f = new File("fligths.txt");
		PrintWriter pw = new PrintWriter(f);
		pw.println(flights.size());
		for (Flight fly : flights) {
			fly.Save(pw);
			pw.println("");
		}
		pw.close();
	}

	// read from the file and making new list from it
	public static void read(String fileName, ArrayList<Flight> list) throws FileNotFoundException, FilerException {
		list.clear();
		File f = new File(fileName);
		Scanner s = new Scanner(f);
		int sum = s.nextInt();
		for (int i = 0; i < sum; i++) {
			s.nextLine();
			list.add(new Flight(f, s));
			s.nextLine();
		}
	}
	
	// check Integer input and declined until possible input inserted
	// making sure the input is Integer type and between Min and Max variables
	public static int inputOptionCheck(Scanner scan, int min, int max) {
		int res = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				res = Integer.parseInt(scan.nextLine());
				if (res < min || res > max)
					System.out.println("not valid option: (" + min + "-" + max + ")");
				else
					repeat = false;
			} catch (NumberFormatException e) {
				System.out.println("not valid input: please enter number");
			}
		}
		return res;
	}
	
	public static ArrayList<Flight> sortByDate ( ArrayList<Flight> flights,LocalDate begining, LocalDate end){
		ArrayList<Flight> newList = new ArrayList<Flight>();
		for(Flight f : flights) {
			if ((f.getFlightDate().compareTo(begining)<=0) && (f.getFlightDate().compareTo(end)>=0))
				newList.add(f);
		}
		return newList;
	}
	
	public static ArrayList<Flight> sortByCompany (ArrayList<Flight> flights, String company){
		ArrayList<Flight> newList = new ArrayList<Flight>();
		for(Flight f : flights) {
			if (f.getCompany().equals(company))
				newList.add(f);
		}
		return newList;
	}
	
}
