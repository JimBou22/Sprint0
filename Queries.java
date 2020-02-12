package ijs_sprint1;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Queries {
	
	public static DefaultTableModel availableV(List<RentalLocations> list) {
		String[] columns = { "ID", "Name", "Available Vehicles" };
		Object[][] data = new Object[list.size()][3];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getName();
			data[i][2] = list.get(i).availableVehicles();
		}
		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		return dtm;
	}

	public static ArrayList<RentalLocations> ratesByLoc(List<RentalLocations> list) {
		String name = JOptionPane.showInputDialog(null, "Input location name");
		return filterLocationsByName(list, name);
	}
	
	public static ArrayList<RentalLocations> filterLocationsByName(List<RentalLocations> list, String name) {
		ArrayList<RentalLocations> locs = new ArrayList<>();
		for (RentalLocations r : list) {
			if (r.getName().contentEquals(name)) {
				locs.add(r);
			}
		}
		return locs;
	}
	
	public static DefaultTableModel calculateRevenue(List<RentalLocations> list) {
		String[] columns = { "ID", "Name", "Total Revenue" };
		Object[][] data = new Object[list.size()][3];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getName();
			data[i][2] = NumberFormat.getCurrencyInstance().format(list.get(i).total());
		}
		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		return dtm;
	}
}
