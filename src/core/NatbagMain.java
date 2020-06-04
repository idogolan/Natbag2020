package core;

public class NatbagMain {

	public static void main(String[] args) {
		boolean isHtml=args.length>0 &&args[0].equalsIgnoreCase("html");
		boolean isDepartures = args.length>1 && args[1].equalsIgnoreCase("Departures");
		if (isDepartures) {
			System.out.println("Departures");
			if (isHtml)
				System.out.println("<br>");
		}
		else {
			System.out.println("Arrivals");
			if (isHtml)
				System.out.println("<br>");
		}
	}
}
