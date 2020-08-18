package core;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Scanner;
import javax.annotation.processing.FilerException;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, FilerException {
		Scanner scan = new Scanner(System.in);
		Airport natbag = new Airport("NatBag");
		try {
			natbag.load();
		} catch (Exception e) {
			System.out.println("no current data available about this airport");
		}
		boolean stayInTheMenu = true;
		while (stayInTheMenu) {
			stayInTheMenu = menu(scan, natbag);
		}
	}

	public static boolean menu(Scanner scan, Airport airport) throws FileNotFoundException, FilerException {
		printMenu();
		String str = scan.nextLine();
		switch (str) {
		case "1":
			showMenu(airport);
			return true;
		case "2":
			addFlight(scan, airport);
			return true;
		case "3":
			airport.save();
			return true;
		case "4":
			System.out.println("The program is closed ");
			return false;
		default:
			System.out.println("Not a valid option: Please choose again");
			return true;
		}
	}

	// print menu test for the user
	public static void printMenu() {
		System.out.println("----------------------------------------------------");
		System.out.println("Main Menu:");
		System.out.println("Choose an option by number");
		System.out.println("1) Show all the available flights");
		System.out.println("2) Add new flight");
		System.out.println("3) Save airport data to a file");
		System.out.println("4) exit");
		System.out.println("----------------------------------------------------");
	}

	public static void showMenu(Airport airport) {
		System.out.println("----------------------------------------------------");
		System.out.println("Airport - " + airport.getName());
		System.out.println("----------------------------------------------------");
		System.out.println(airport.showFlights());
		System.out.println("----------------------------------------------------");
		System.out.println("Choose sorting option by number");
		System.out.println("1) Show/Hide Arrivals");
		System.out.println("2) Show/Hide Departures");
		System.out.println("3) Sort by company name");
		System.out.println("4) Sort by county name");
		System.out.println("5) Sort by start date");
		System.out.println("6) Sort by end date");
		System.out.println("7) Back to the main menu ");
		System.out.println("----------------------------------------------------");
	}

	// making new flight using user input
	public static void addFlight(Scanner scan, Airport airport) {
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
		month = inputOptionCheck(scan, 1, 12);
		System.out.println("Please enter the day");
		day = inputOptionCheck(scan, 1, 31);
		System.out.println("Please enter the hour");
		hour = inputOptionCheck(scan, 1, 60);
		System.out.println("Please enter the minute");
		minute = inputOptionCheck(scan, 1, 60);
		LocalDate date;
		Time time;
		date = LocalDate.of(year, month, day);
		time = new Time(hour, minute, 0);

		airport.addFlight(start, end, date, time, number, company);
		System.out.println("The flight added successfully to the list\n");
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
}
