package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer rNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer rNumber, Date checkIn, Date checkOut) {
		
		if (!checkOut.after(checkIn)) {
			throw new DomainException ("Check-out date must be after check-in date.");
		} 
		
		this.rNumber = rNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getrNumber() {
		return rNumber;
	}

	public void setrNumber(Integer rNumber) {
		this.rNumber = rNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException ("Reservation dates for upadates must be futures dates.");
		} 
		if (!checkOut.after(checkIn)) {
			throw new DomainException ("Check-out date must be after check-in date.");
		} 
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	
	@Override
	public String toString() {
		return "Room Number: "
			+ rNumber
			+ ", check-in: "
			+ sdf.format(checkIn)
			+ ", check-out: "
			+ sdf.format(checkOut)
			+ ", "
			+ duration()
			+ " nights";
	}

}
