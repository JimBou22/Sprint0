package ijs_sprint;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentalManagementApp {

	private JFrame frmRentalLocationManager;
	private JTable tblLocations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalManagementApp window = new RentalManagementApp();
					window.frmRentalLocationManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RentalManagementApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentalLocationManager = new JFrame();
		frmRentalLocationManager.setTitle("Rental Location Manager");
		frmRentalLocationManager.setBounds(100, 100, 800, 500);
		frmRentalLocationManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		frmRentalLocationManager.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tblLocations = new JTable();
		scrollPane.setViewportView(tblLocations);
		
		JMenuBar menuBar = new JMenuBar();
		frmRentalLocationManager.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnLocation = new JMenu("Location");
		menuBar.add(mnLocation);
		
		JMenuItem mntmNewLocation = new JMenuItem("New Location");
		mnLocation.add(mntmNewLocation);
		
		JMenu mnLookupLocations = new JMenu("Lookup Locations");
		mnLocation.add(mnLookupLocations);
		
		JMenuItem mntmByZipCode = new JMenuItem("by ZIP Code");
		mnLookupLocations.add(mntmByZipCode);
		
		JMenuItem mntmByName = new JMenuItem("by Name");
		mnLookupLocations.add(mntmByName);
		
		JMenu mnQueries = new JMenu("Queries");
		menuBar.add(mnQueries);
		
		JMenu mnLookupRentalRates = new JMenu("Lookup Rental Rates");
		mnQueries.add(mnLookupRentalRates);
		
		JMenuItem mntmByLocationName = new JMenuItem("by Location Name");
		mnLookupRentalRates.add(mntmByLocationName);
		
		JMenuItem mntmAvailableVehicles = new JMenuItem("Available Vehicles");
		mnQueries.add(mntmAvailableVehicles);
		
		JMenu mnCalculate = new JMenu("Calculate");
		menuBar.add(mnCalculate);
		
		JMenuItem mntmDailyRevenueFor = new JMenuItem("Daily Revenue for Location");
		mnCalculate.add(mntmDailyRevenueFor);
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
}
