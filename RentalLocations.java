package IJS_Sprint1;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RentalLocations {
	private String name; // rental location name,cities for locations
	private int id;

	private final int vehicles = 50;
	private int rentedVehicles;

	private double dailyRate;
	private int zip;
	private double discount;

	// total vehicles = 50
	public RentalLocations(String name, double dailyRate, int rentedVehicles, int id, int zip) {
		this.name = name;
		this.dailyRate = dailyRate;
		this.rentedVehicles = rentedVehicles;
		this.id = id;
		this.zip = zip;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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

	public int getVehicles() {
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
	public void setName(String z) {
		this.name = z;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	/*
	 * JEFF LOOK HERE FOR LOGIC Scanner sc = new Scanner(System.in);
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

		double total = rentedVehicles * dailyRate; //May 
		System.out.println("the total is: " + NumberFormat.getCurrencyInstance().format(total));
		return total;
//		
//		}

	}
	//Returns list of location names given zipcode
	public List<String> getLocs(int zip, List<RentalLocations> r){
		List<String> list = new ArrayList<String>();
		for(RentalLocations loc:r) {
			if(loc.getZip() == zip) {
				list.add(loc.getName());
			}
		}
		return list;
	}
	//gets the rental rate given a name
	public void locRates(String name, List<RentalLocations> rL) {
		for(RentalLocations list : rL) {
			if(list.getName() == (name)) {
				System.out.println("Name:" + name + " " + list.getDailyRate()); //Needs to be edited based on GUI
			}
		}
	}
	
	//gets the details when given a location name
	public void locDetails(String name, List<RentalLocations> rL) {//name of city desired
		for(RentalLocations list: rL) {
			if(list.getName() == name) {
				System.out.println(list.toString()); //needs to be edited based on GUI
			}
		}
	}
}


