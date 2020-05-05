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
			flights = read("fligths.txt");
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

	public static void sortByDateAndTime(ArrayList<Flight> flights) {
		Comparator<Flight> comparatorByDate = new Comparator<Flight>() {
			public int compare(Flight o1, Flight o2) {
				return o1.comperTo(o2);

			}
		};

		Collections.sort(flights, comparatorByDate);
	}

	public static void printMenu() {
		System.out.println("Menu:");
		System.out.println("1)Show the flight list");
		System.out.println("2)Add new flight");
		System.out.println("3)Show the flight list sorted by date and time");
		System.out.println("4)Make new list of flight from a file");
		System.out.println("5)Save the list to a file");
		System.out.println("6)exit");
	}

	public static void addFlight(Scanner scan, ArrayList<Flight> flights) {
		String start, end, company, number;
		String year, month, day, hour, minute;
		System.out.println("Please enter number flight: ");
		number = scan.nextLine();
		System.out.println("Please enter the start location");
		start = scan.nextLine();
		System.out.println("Please enter the end location");
		end = scan.nextLine();
		System.out.println("Please enter the company name");
		company = scan.nextLine();
		System.out.println("Please enter the year");
		year = scan.nextLine();
		System.out.println("Please enter the month");
		month = scan.nextLine();
		System.out.println("Please enter the day");
		day = scan.nextLine();
		System.out.println("Please enter the hour");
		hour = scan.nextLine();
		System.out.println("Please enter the minute");
		minute = scan.nextLine();
		LocalDate date;
		Time time;
		try {
			date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch (Exception e) {
			date = LocalDate.now();
		}
		try {
			time = new Time(Integer.parseInt(hour), Integer.parseInt(minute), 0);
		} catch (Exception e) {
			time = new Time(0, 0, 0);
		}

		flights.add(new Flight(start, end, date, time, number, company));
		System.out.println("The flight added successfully to the list\n");
	}

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

	public static ArrayList<Flight> read(String fileName) throws FileNotFoundException, FilerException {
		ArrayList<Flight> list = new ArrayList<Flight>();
		File f = new File(fileName);
		Scanner s = new Scanner(f);
		int sum = s.nextInt();
		for (int i = 0; i < sum; i++) {
			list.add(new Flight(f, s));
			s.nextLine();
		}
		return list;
	}
}
