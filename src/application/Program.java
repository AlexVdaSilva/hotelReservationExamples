package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {

		// Final code refactoring. In this step, I renamed the
		// 'entities' package to 'model.entities' to follow best practices.
		// I also created the 'model.exceptions' package to house the
		// custom 'DomainException' class, which handles date validation errors
		// (e.g., past dates or check-out before check-in).
		//
		// In Program.java, the code now handles three specific exceptions,
		// including 'ParseException' for invalid date formats and 'DomainException'.
		// Additionally, the instantiation of the custom exception was moved
		// inside the Reservation class. In the previous "bad version",
		// updateDates returned a String, and the error handling/printing
		// relied on if-else structures within the main program.

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("Room number: ");
			int rNumber = sc.nextInt();
			System.out.print("Check-in date (dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());

			Reservation r = new Reservation(rNumber, checkIn, checkOut);
			System.out.println("Reservation: " + r);

			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());

			r.updateDates(checkIn, checkOut);
			System.out.println("Reservation: " + r);
		}
		catch (ParseException e) {
			System.out.println("Invalid date format.");
		}
		catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println("Unexpected error.");
		}
		
		sc.close();

	}

}
