package ijs_sprint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RentalManagementApp {

	private JFrame frmRentalLocationManager;
	private JTable tblLocations;
	private List<RentalLocations> locationList = new ArrayList<>();

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
				JOptionPane.showMessageDialog(frmRentalLocationManager, "See Ya!");
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnLocation = new JMenu("Location");
		menuBar.add(mnLocation);
		
		JMenuItem mntmNewLocation = new JMenuItem("New Location");
		mntmNewLocation.addActionListener(e -> {
			
		});
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
		mntmNewLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newLocation(locationList);
			}
		});
		
		JMenuItem mntmAvailableVehicles = new JMenuItem("Available Vehicles");
		mntmAvailableVehicles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnQueries.add(mntmAvailableVehicles);
		
		JMenu mnCalculate = new JMenu("Calculate");
		menuBar.add(mnCalculate);
		
		JMenuItem mntmDailyRevenueFor = new JMenuItem("Daily Revenue for Location");
		mnCalculate.add(mntmDailyRevenueFor);
	}
	
	private void newLocation(List<RentalLocations> list) {
//		JFrame frmNewLocation = new JFrame("New Location");
//		frmNewLocation.setSize(new Dimension(500, 300));

		JPanel pnlNewLocation = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = (GridBagConstraints.HORIZONTAL);
		c.ipadx = 5;
		c.ipady = 10;
//		frmNewLocation.add(pnlNewLocation);
		
		JLabel lblName = new JLabel("Location Name:");
		c.gridx = 0;
		c.gridy = 0;
		pnlNewLocation.add(lblName, c);
		
		JTextField txtName = new JTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		pnlNewLocation.add(txtName, c);
		
		JLabel lblNameErr = new JLabel();
		lblNameErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 0;
		pnlNewLocation.add(lblNameErr, c);
		
//		JLabel lblRate = new JLabel("Rental Rate:");
//		c.gridx = 0;
//		c.gridy = 1;
//		pnlNewLocation.add(lblRate, c);
//		
//		JTextField txtRate = new JTextField(20);
//		c.gridx = 1;
//		c.gridy = 1;
//		pnlNewLocation.add(txtRate, c);
//		
//		JLabel lblRateErr = new JLabel();
//		lblRateErr.setForeground(Color.RED);
//		c.gridx = 2;
//		c.gridy = 1;
//		pnlNewLocation.add(lblRateErr, c);
		
		JLabel lblVehicles = new JLabel("# of Rented Vehicles:");
		c.gridx = 0;
		c.gridy = 2;
		pnlNewLocation.add(lblVehicles, c);
		
		JTextField txtVehicle = new JTextField(20);
		c.gridx = 1;
		c.gridy = 2;
		pnlNewLocation.add(txtVehicle, c);
		
		JLabel lblVehiclesErr = new JLabel();
		lblVehiclesErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 2;
		pnlNewLocation.add(lblVehiclesErr, c);
		
		JLabel lblZip = new JLabel("ZIP Code:");
		c.gridx = 0;
		c.gridy = 3;
		pnlNewLocation.add(lblZip, c);
		
		JTextField txtZip = new JTextField(20);
		c.gridx = 1;
		c.gridy = 3;
		pnlNewLocation.add(txtZip, c);
		
		JLabel lblZipErr = new JLabel();
		lblZipErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 3;
		pnlNewLocation.add(lblZipErr, c);
		
		int result = JOptionPane.showOptionDialog(null, new Object[] {pnlNewLocation}, "New Location", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if (result == 0) {
			String name = txtName.getText();
			//double rate = Double.parseDouble(txtRate.getText());
			int vehicles = Integer.parseInt(txtVehicle.getText());
			int id = locationList.size() + 1;
			int zip = Integer.parseInt(txtZip.getText());
			
			RentalLocations loc = new RentalLocations(name, vehicles, id, zip);
			
			list.add(loc);
			updateTable(tblLocations, locationList);
		}
	}
	
	private void updateTable(JTable table, List<RentalLocations> list) {
		String[] columns = {"ID", "Name", "ZIP Code", "Rented Vehicles", "Avail. Vehicles", "Daily Rate"};
		Object[][] data = new Object[list.size()][6];
		
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getName();
			data[i][2] = list.get(i).getZip();
			data[i][3] = list.get(i).getRentedVehicles();
			data[i][4] = list.get(i).availableVehicles();
			data[i][5] = list.get(i).getRates();
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		table.setModel(dtm);
	}
}
