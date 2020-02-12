package IJS_Sprint1;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		List<RentalLocations> rL = new ArrayList<RentalLocations>();
		locDetails("",rL);
		locRates("",rL);
		
		
	
	}
	
	//gets the rental rate given a name
	private static void locRates(String name, List<RentalLocations> rL) {
		for(RentalLocations list : rL) {
			if(list.getName() == (name)) {
				System.out.println("Zone:" + name + " " + list.getRentalRate());
			}
		}
	}
	
	//gets the details when given a location name
	private static void locDetails(String name, List<RentalLocations> rL) {//name of city desired
		for(RentalLocations list: rL) {
			if(list.getName() == name) {
				System.out.println(list.toString());
			}
		}
	}
}
