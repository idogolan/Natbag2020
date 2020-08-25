package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Scanner;
import javax.annotation.processing.FilerException;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, FilerException, IOException {
		Scanner scan = new Scanner(System.in);
		Airport natbag = new Airport("NatBag");
		try {
			natbag.load();
		} catch (Exception e) {
			System.out.println("no current data available about this airport");
		}

		if (args.length > 0) {
			try {
				boolean isHtml = args[0].equalsIgnoreCase("html");
				boolean isDepartures = args[1].equalsIgnoreCase("Departures");
				LocalDate startDate = LocalDate.of(Integer.parseInt(args[6]), Integer.parseInt(args[5]),
						Integer.parseInt(args[4]));
				LocalDate endDate = LocalDate.of(Integer.parseInt(args[9]), Integer.parseInt(args[8]),
						Integer.parseInt(args[7]));
				if (isHtml) {
					natbag.setDepartures(isDepartures);
					natbag.setArrivals(!isDepartures);
					natbag.setCompany(args[2]);
					natbag.setCountry(args[3]);
					natbag.setRangeOfDatesBegining(startDate);
					natbag.setRangeOfDatesEnd(endDate);
					natbag.setSunday(args[10].equalsIgnoreCase("true"));
					natbag.setMonday(args[11].equalsIgnoreCase("true"));
					natbag.setTuesday(args[12].equalsIgnoreCase("true"));
					natbag.setWednesday(args[13].equalsIgnoreCase("true"));
					natbag.setThursday(args[14].equalsIgnoreCase("true"));
					natbag.setFriday(args[15].equalsIgnoreCase("true"));
					natbag.setSaturday(args[16].equalsIgnoreCase("true"));
					System.out.println("<br>");
					System.out.println(natbag.showFlights());
				}
			} catch (Exception e) {
				System.out.println("<br>");
				System.out.println(natbag.showFlights());
			}
		} else {
			while (menu(scan, natbag)) {
			}
		}
	}

	public static boolean menu(Scanner scan, Airport airport) throws FileNotFoundException, FilerException {
		printMenu();
		String str = scan.nextLine();
		switch (str) {
		case "1":
			showMenu(scan, airport);
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

	public static void showMenu(Scanner scan, Airport airport) {
		boolean again = true;
		while (again) {
			printShow(airport);
			String str = scan.nextLine();
			switch (str) {
			case "1":
				airport.setArrivals(inputOptionCheck(scan));
				again = true;
				break;
			case "2":
				airport.setDepartures(inputOptionCheck(scan));
				again = true;
				break;
			case "3":
				System.out.println("Enter the company name");
				airport.setCompany(scan.nextLine());
				again = true;
				break;
			case "4":
				System.out.println("Enter the country name");
				airport.setCountry(scan.nextLine());
				again = true;
				break;
			case "5":
				LocalDate begining = createDate(scan);
				airport.setRangeOfDatesBegining(begining);
				again = true;
				break;
			case "6":
				LocalDate end = createDate(scan);
				airport.setRangeOfDatesEnd(end);
				again = true;
				break;
			case "7":
				chooseDay(scan, airport);
				again = true;
				break;
			case "8":
				airport.setRest();
				again = false;
				break;
			default:
				System.out.println("Not a valid option: Please choose again");
				again = true;
			}
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

	public static void printShow(Airport airport) {
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
		System.out.println("7) Sort by days in the week");
		System.out.println("8) Back to the main menu ");
		System.out.println("----------------------------------------------------");
	}
	

	// making new flight using user input
	public static void addFlight(Scanner scan, Airport airport) {
		String start, end, company, number;
		int hour, minute;
		LocalDate date;
		Time time;
		System.out.println("Please enter number flight: ");
		number = scan.nextLine();
		System.out.println("Please enter the start location");
		start = scan.nextLine();
		System.out.println("Please enter the end location");
		end = scan.nextLine();
		System.out.println("Please enter the company name");
		company = scan.nextLine();
		date = createDate(scan);
		System.out.println("Please enter the hour");
		hour = inputOptionCheck(scan, 0, 23);
		System.out.println("Please enter the minute");
		minute = inputOptionCheck(scan, 0, 59);
		time = new Time(hour, minute, 0);

		airport.addFlight(start, end, date, time, number, company);
		System.out.println("The flight added successfully to the list\n");
	}

	public static LocalDate createDate(Scanner scan) {
		int year, month, day;
		LocalDate date;
		System.out.println("Please enter the year");
		year = inputOptionCheck(scan, 1900, 2100);
		System.out.println("Please enter the month");
		month = inputOptionCheck(scan, 1, 12);
		System.out.println("Please enter the day");
		day = inputOptionCheck(scan, 1, 31);
		date = LocalDate.of(year, month, day);
		return date;
	}

	public static void chooseDay(Scanner scan, Airport airport) {
		System.out.println("Do you want show Sunday? Y/N");
		airport.setSunday(answer(scan));
		System.out.println("Do you want show Monday? Y/N");
		airport.setMonday(answer(scan));
		System.out.println("Do you want show Tuesday? Y/N");
		airport.setTuesday(answer(scan));
		System.out.println("Do you want show Wednesday? Y/N");
		airport.setWednesday(answer(scan));
		System.out.println("Do you want show Thursday? Y/N");
		airport.setThursday(answer(scan));
		System.out.println("Do you want show Friday? Y/N");
		airport.setFriday(answer(scan));
		System.out.println("Do you want show Saturday? Y/N");
		airport.setSaturday(answer(scan));
	}

	public static boolean answer(Scanner scan) {
		String answer;
		boolean again = true;
		while (again) {
			answer = scan.nextLine();
			if (answer.equalsIgnoreCase("y"))
				return true;
			else if (answer.equalsIgnoreCase("n"))
				return false;
		}
		return again;
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

	public static boolean inputOptionCheck(Scanner scan) {
		System.out.println("Enter S to show\nEnter H to hide");
		String res;
		boolean repeat = true;
		while (repeat) {
			res = scan.nextLine();
			if (res.equalsIgnoreCase("h") || res.equalsIgnoreCase("hide"))
				return false;
			else if (res.equalsIgnoreCase("s") || res.equalsIgnoreCase("show"))
				return true;
			else
				System.out.println("not valid input: please try again (S/H)");
		}
		return true;
	}

	//http://localhost:8000/departures?outformat=html&company=El-Al&country=MOS&day1=2&month1=2&year1=2020&day2=5&month2=9&year2=2020&sunday=true&monday=true&tuesday=true&wednesday=true&thursday=true&friday=true&saturday=true
	//http://localhost:8000/departures?outformat=html&company=El-Al&country=ISR&day1=20&month1=11&year1=2020&day2=5&month2=9&year2=2020&sunday=true&monday=true&tuesday=true&wednesday=true&thursday=true&friday=true&saturday

}
