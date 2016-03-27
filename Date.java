import java.io.Serializable;

/**
 * 
 * @author Jack Lewis
 * CSS 162 
 * Rob Nash
 * Date class has multiple methods and instance variables for determining what the date
 * is, i.e. month, day, year. 
 * Date must be between 2014 and 2024
 * 
 */
public class Date implements Comparable, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Serializable
	private int month; 
	private int day;
	private int year; 

	//constants
	private static final int MIN_LEGAL_YEAR = 2014; //I wanted to experiment with constants for the invariance
	private static final int MAX_LEGAL_YEAR = 2024; //so I generally check year using these two constants instead 
	//of 'if year >= 2014 && year <= 2024'

	public Date() { //all zero values
		month = 0; 
		day = 0; 
		year = 0; 
	}

	//3-arg constructor for Date
	//exception if values invalid
	public Date(int m, int d, int y) throws IllegalArgumentException {
		if (m >= 1 && m <= 12 && d >= 1 && d <= 31 && y >= MIN_LEGAL_YEAR && y <= MAX_LEGAL_YEAR){
			month = m; 
			day = d; 
			year = y; 
		} else {
			throw new IllegalArgumentException("Invalid date. Sorry"); 
		}
	}

	//copy constructor
	public Date(Date other) throws IllegalArgumentException {
		if (other != null) {
			this.month = other.month; 
			this.day = other.day; 
			this.year = other.year; 
		} else {
			throw new IllegalArgumentException("Tried to copy a null Date."); 
		}

	}

	//toString for Date. Will produce a Date with periods (ex. 2.12.2017) 
	public String toString() {
		return (this.month + "." + this.day + "." + this.year); 
	}


	//equals method
	//accepts an Object 
	//will only work if object is instance of Date
	public boolean equals(Object o) {
		if (o != null && o instanceof Date) {
			Date that = (Date)o; 
			return (this.month == that.month && this.day == that.day && this.year == that.year);
		} else {
			throw new IllegalArgumentException("Tried to compare Date with non-Date."); 
		}
	}

	//setDate method
	//basic checks against bad Date values
	public void setDate(int m, int d, int y) {
		if (m >= 1 && m <= 12 && d >= 1 && d <= 31 && y >= MIN_LEGAL_YEAR && y <= MAX_LEGAL_YEAR) {
			this.month = m; 
			this.day = d; 
			this.year = y; 
		} else {
			throw new IllegalArgumentException("setDate failed because bad values were provided."); 
		}
	}

	//setDay
	//specifically sets the day 
	public void setDay(int d) throws IllegalArgumentException {
		if (d >= 1 && d <= 31) {
			this.day = d; 
		}
	}

	//setDay
	//specifically sets the month 
	public void setMonth(int m) throws IllegalArgumentException {
		if (m >= 1 && m <= 12) {
			this.month = m; 
		}
	}

	//setDay
	//specifically sets the year 
	public void setYear(int y) throws IllegalArgumentException {
		if (y >= MIN_LEGAL_YEAR && y <= MAX_LEGAL_YEAR) {
			this.year = y; 
		}
	}


	public int getYear() {
		return this.year; 

	}

	public int getMonth() {
		return this.month; 

	}

	public int getDay() {
		return this.day; 

	}

	//compareTo method
	//Takes an object, makes sure it's a Date, 
	//and then methodically compares all aspects. 
	//If Date is equal to this, return 0; 
	//if less than this, return 1
	//if greater than this, return -1
	@Override
	public int compareTo(Object o) {
		if (o != null && o instanceof Date) {
			Date that = (Date)o; 
			if (this.year > that.year) {
				return 1; 
			}
			if (this.year < that.year) {
				return -1; 
			}
			if (this.year == that.year) { //go deeper in comparison
				if (this.month > that.month) {
					return 1; 
				}
				if (this.month < that.month) {
					return -1; 
				}
				if (this.month == that.month) { //go deeper in comparison
					if (this.day > that.day) {
						return 1; 
					}
					if (this.day < that.day) {
						return -1; 
					}
					if (this.day == that.day) {
						return 0;
					}
				}
			}
		} else { //else it's not a Date, or it's null
			throw new RuntimeException("Date object was either null or not a Date."); 
		}
		return -100;
	}
	
	@Override
	public Date clone() {
		return new Date(this.month, this.day, this.year); 
	}

	public void writeObject() {
		// TODO Auto-generated method stub
		
	}

	public void readObject() {
		// TODO Auto-generated method stub
		
	}
	

}
