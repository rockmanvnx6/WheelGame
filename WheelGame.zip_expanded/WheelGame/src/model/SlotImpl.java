package model;
import java.util.Objects;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot {
	/* 
	 * This class manages information and functions of a slot
	 */
	private int position;
	private Color color;
	private int number;
	
	public SlotImpl(int position, Color color, int number) {
		/* Constructor to initialize the slot with its position, color and number */
		this.position = position;
		this.color = color;
		this.number = number;
	}
	
	@Override
	public int getPosition() {
		/* Return the position of the slot */
		return this.position;
	}

	@Override
	public int getNumber() {
		/* Return the number value of the slot */
		return this.number;
	}

	@Override
	public Color getColor() {
		/* Return slot color */
		return this.color;
	}

	@Override
	public boolean equals(Slot slot) {
		/* Compares 2 slots based on its color and number */
		if(this.getNumber() == slot.getNumber() && this.getColor() == slot.getColor()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(java.lang.Object slot) {
		/* Test if the object is a slot, if it is then compare using the above method */
		if(slot instanceof Slot) {
			return this.equals((Slot) slot);
		} else {
			return false;
		}
	}
	
	private String reformatString(Color color) {
		/* Take a color, print it as first letter Uppercase and the rest lowercase */
		String colorName = color.toString();
		return colorName.substring(0, 1).toUpperCase() + colorName.substring(1).toLowerCase();
	}
	
	@Override
	public int hashCode() {
		/* Return the hashcode of a slot */
		return Objects.hash(color,number);
	}
	
	@Override
	public String toString() { 
		/* Return slot information as string */
		return String.format("Position: %d, Color: %s, Number %d", this.getPosition(),
				reformatString(this.getColor()), this.getNumber());
	}

}
