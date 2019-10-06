/**
 * Class: Object-Oriented Design and Analysis
 * Professor: Orlando Montalvo
 * Assignment: HW 2
 * 
 * Date: 2017-09-20
 */
package edu.fitchburgstate.csc7400.hw2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Inventory keeps a searchable collection of Guitars. It is meant to be use to
 * find matching guitars for customers.
 * 
 * @author orlando
 * @version 2.0
 */
public class Inventory {
	/**
	 * Default constructor
	 */
	public Inventory() {
		guitars = new LinkedList<Guitar>();
	}

	/**
	 * Adds a guitar to the inventory
	 * 
	 * @param guitar
	 *            a fully qualified guitar
	 */
	public void addGuitar(Guitar guitar) {
		guitars.add(guitar);
	}

	/**
	 * Adds a new guitar to the inventory given all the guitars information.
	 * 
	 * @param serialNumber
	 *            the guitars manufacturer serial number
	 * @param price
	 *            the customer price
	 * @param manufacturer
	 *            the guitar's manufacturer
	 * @param model
	 *            the manufacturer model id
	 * @param type
	 *            type of guitar
	 * @param topWood
	 *            wood used in front of guitar
	 * @param backWood
	 *            wood used in back of guitar
	 * @param numStrings
	 *            the number of strings
	 * 
	 * @precondition serialNumber is not empty
	 * @precondition price > 0
	 * @precondition manufacturer != null
	 * @precondition model is not empty
	 * @precondition type != null
	 * @precondition topWood != null
	 * @precondition backWood != null
	 * @precondition numStrings >= 6 or null
	 */
	public void addGuitar(String serialNumber, double price, GuitarInterface.Manufacturer manufacturer, GuitarInterface.Type type, String model,
						  GuitarInterface.Wood topWood, GuitarInterface.Wood backWood, Integer numStrings) {
		assert serialNumber != null;
		assert price > 0;
		assert manufacturer != null;
		assert type != null;
		assert model != null;
		assert topWood != null;
		assert backWood != null;
		assert numStrings >= 0;
		Guitar guitar = new Guitar(serialNumber, price, manufacturer, model,type, backWood, topWood, numStrings);
		this.addGuitar(guitar);
	}

	/**
	 * Finds and returns a guitar that has the provided serial number
	 * 
	 * @param serialNumber
	 *            serial number of guitar
	 * @return the guitar with the correct serial number, or null if not found
	 */
	public Guitar getGuitar(String serialNumber) {
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}

	/**
	 * Finds and returns a list of guitars that matches a provided guitar. Any null in
	 * spec are consider wildcards.
	 * 
	 * @param searchGuitar
	 *            the guitar with qualities we want to match
	 * @return the found guitar list, may be null
	 */
	public List<Guitar> search(Guitar searchGuitar) {
		GuitarInterface.Manufacturer manufacturer = searchGuitar.getManufacturer();
		String model = searchGuitar.getModel();
		GuitarInterface.Type type = searchGuitar.getType();
		GuitarInterface.Wood backWood = searchGuitar.getBackWood();
		GuitarInterface.Wood topWood = searchGuitar.getTopWood();
		int numString = searchGuitar.getNumberOfStrings();
		List<Guitar> guitarList = new ArrayList<>();
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			// Ignore serial number since that's unique
			// Ignore price since that's unique
			if ((manufacturer != null) && (!manufacturer.equals(""))
					&& (!manufacturer.equals(guitar.getManufacturer())))
				continue;
			if ((model != null) && (!model.equals("")) && (!model.equalsIgnoreCase(guitar.getModel())))
				continue;
			if ((type != null) && (!type.equals("")) && (!type.equals(guitar.getType())))
				continue;
			if ((backWood != null) && (!backWood.equals("")) && (!backWood.equals(guitar.getBackWood())))
				continue;
			if ((topWood != null) && (!topWood.equals("")) && (!topWood.equals(guitar.getTopWood())))
				continue;
			if (numString != 0 && numString != guitar.getNumberOfStrings())
				continue;
			guitarList.add(guitar);
		}
		return guitarList.isEmpty()? null:guitarList;
	}

	private List<Guitar> guitars; // guitar inventory
}
