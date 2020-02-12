package IJS_Sprint1;

import java.text.NumberFormat;

public class RentalLocations {
	private String name; //rental location name,cities for locations
	private double dailyRate;
	private final int vehicles = 50;
	private int rentedVehicles;
	
	private int zip;
	private double days;
	/**
	 * @return the days
	 */
	public double getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(double days) {
		this.days = days;
	}
	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	private double discount;
	private double total;
	
	
	
		
	/* JEFF LOOK HERE FOR LOGIC
	 * Scanner sc = new Scanner(System.in);
	 * System.out.println("how many days would you like to rent a vehicle?"); days =
	 * sc.nextInt(); System.out.println("Please input zip of rental location: ");
	 * zip = sc.nextInt();
	 */
		
	public double getRates(double dailyRate) { 
		if ((zip >= 98001) && (zip <= 98100)) {
			dailyRate = 27.50;				
		}
		if ((zip >= 98201) && (zip <= 98300)) {
			dailyRate = 22.49;
		}
		if ((zip >= 98301) && (zip <= 98400)) {
			dailyRate = 31.00;
		}
		if ((zip >= 98401) && (zip <= 98500)) {
			dailyRate = 12.25;
		}
		total = rentedVehicles * dailyRate;
		System.out.println("the total daily revenue is: " + NumberFormat.getCurrencyInstance().format(total));
		return total;
	}
//		}
	
	}public int getVehicles() {
		return vehicles;
	}
	public int getRentedVehicles() {
		return rentedVehicles;
	}
	public void setRentedVehicles(int rentedVehicles) {
		this.rentedVehicles = rentedVehicles;
	}

	public int availableVehicles() {
		return vehicles - rentedVehicles;
	}

	/**
	 * @return the city
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param city the city to set
	 */
	public void setName (String z) {
		this.name = z;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}	
}
