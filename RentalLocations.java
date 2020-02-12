package IJS_Sprint1;

public class RentalLocations {
	private String name; //rental location name,cities for locations
	private double rentalRate;
	private final int vehicles = 50;
	private int rentedVehicles;
	
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
	public void setName (String z) {
		this.name = z;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}	
}
