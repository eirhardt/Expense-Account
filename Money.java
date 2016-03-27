import java.io.Serializable;

/**
 * CSS 162
 * Rob Nash
 * Author: Jack Lewis
 * The Money class stores dollars and cents values 
 * and provides methods for creating and manipulating Money objects. 
 */
public class Money implements Comparable, Cloneable, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Serializable
	private int dollars;
    private int cents;

    //Empty constructor; sets the dollar and cent values to zero if no parameters are handed to Money.
    public Money() {
        dollars = 0;
        cents = 0;
    }

    //Calls the setCents and setDollars methods to update the 
    //dollar and cent values
    public Money(int dollar, int cent) {
        setCents(cent);
        setDollars(dollar);
    }

    //Constructor that accepts a value (of type double) that
    //combines dollars and cents into a single number, and the constructor
    //separates them into their respective categories. 
    public Money(double amount) throws IllegalArgumentException {
        if (amount >= 0.0) {
            int pennies = (int)Math.round(amount * 100);
            dollars = pennies / 100;
            cents = pennies % 100;
        } else {
            throw new IllegalArgumentException ("Tried to create object with negative amount");
        }
    }

    //Copy c'tor, checks to make sure that cents and dollars
    //both fall within valid ranges. 
    public Money(Money other) throws IllegalArgumentException {
        if(other != null) {
            this.cents = other.cents;
            this.dollars = other.dollars;
        }else {
            throw new IllegalArgumentException ("No nulls, please");
        }
    }

    //toString; two versions - if cents is less than 10 it needs a leading 
    //zero inserted. 
    public String toString() {
        if (this.cents <= 9) {
            return "$" + this.dollars + ".0" + this.cents;
        } else {
            return "$" + this.dollars + "." + this.cents;
        }
    }

    //used to compare the current Money object with another one
    public boolean equals(Object other) throws IllegalArgumentException {
        if (other != null && other instanceof Money) {
        	Money that = (Money)other; 
            return (this.cents == that.cents && this.dollars == that.dollars);
        } else {
            throw new IllegalArgumentException("Attempted to compare null");
        }
    }
    

    //Mutator method used to set the dollars variable to the given value
    public void setDollars(int setterDollars) throws IllegalArgumentException {
        if(setterDollars >= 0)
            this.dollars = setterDollars;
        else {
            throw new IllegalArgumentException ("Dollars parameter is negative in setDollars");
        }
    }

    //Mutator method used to set the cents variable to the given value
    public void setCents(int setterCents) throws IllegalArgumentException {
        if(setterCents >= 0 && setterCents <= 99)
            this.cents = setterCents;
        else {
            throw new IllegalArgumentException ("Cents parameter is invalid in setCents");
        }
    }

    //Mutator method used to specify or modify the monetary value of a Money object
    public void setMoney(int dollars, int cents) {
        setDollars(dollars); //more efficient
        setCents(cents);
    }
    

    //Accessor method to return the number of dollars
    public int getDollars() {
        return this.dollars;
    }

    //Accessor method to return the number of cents
    public int getCents() {
        return this.cents;
    }

    //add method used for type double. Creates a new Money object that is the combined value of
    //the current Money object and the amount (both converted to doubles). 
    public void add(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add a negative amount");
        } else {
            Money temp = new Money(this.toDouble() + amount);
            this.dollars = temp.dollars;
            this.cents = temp.cents;
        }
    }

    //add method, handles two ints, one for dollars and one
    //for cents, and adds them to the current Money object
    public void add(int dollars, int cents) throws IllegalArgumentException {
        if(this.dollars >= 0 && this.cents >= 0 && this.cents <= 99) {
            this.cents += cents;
            this.dollars += dollars;
            if(this.cents >= 100) {
                this.dollars += this.cents / 100;
                this.cents = this.cents % 100;
            }

        } else {
            throw new IllegalArgumentException ("Dollar and/or cent values were outside legal range");
        }
    }

    //add method that takes a Money object and calls the above add method using
    //temporary integers for dollars and cents
    public void add(Money addedMoney) {
        int tempDollars;
        int tempCents;
        if(addedMoney != null) {
            tempDollars = addedMoney.dollars;
            tempCents = addedMoney.cents;
            this.add(tempDollars, tempCents);
        }
    }

    //Subtract method used for type double, throws the IllegalArgument in case 
    //a negative value is supplied. 
    public void subtract(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot subtract a negative amount");
        } else {
            Money temp = new Money(this.toDouble() - amount);
            this.dollars = temp.dollars;
            this.cents = temp.cents;
        }
    }

    //Subtract method, handles two ints, one for dollars and one
    //for cents, and subtracts them from the current Money object
    public void subtract(int dollars, int cents) throws IllegalArgumentException {
        if(dollars >= 0 && cents >= 0 && cents <= 99 && this.dollars >= dollars) {
            this.cents -= cents;
            this.dollars -= dollars;
            if (this.cents < 0 && this.dollars > 0) {
                this.dollars--;
                this.cents += 100;
            } else {
                throw new IllegalArgumentException ("Attempted subtraction would result in a negative value for Money");
            }

        } else {
            throw new IllegalArgumentException ("Attempt to subtract invalid number of dollars and/or cents");
        }
    }

    //Subtract method that takes a Money object and calls the above subtract method using
    //temporary integers for dollars and cents
    public void subtract(Money subtractedMoney) {
        int tempDollars;
        int tempCents;
        if(subtractedMoney != null) {
            tempDollars = subtractedMoney.dollars;
            tempCents = subtractedMoney.cents;
            this.subtract(tempDollars, tempCents);
        }
    }

    //Returns the dollar and cent values combined into a single double
    private double toDouble () {
        return (double) dollars + (double) cents/100.0;
    }

    //compareTo method
    //Takes an object, makes sure it's a money, 
    //and then methodically compares all aspects. 
    //If money is equal to this, return 0; 
    //if less than this, return 1
    //if greater than this, return -1
	@Override
	public int compareTo(Object o) {
		
		if (o != null && o instanceof Money) {
			Money that = (Money)o; 
			if (this.getDollars() > that.getDollars()) {
				return 1; 
			}
			if (this.getDollars() < that.getDollars()) {
				return -1; 
			}
			if (this.getDollars() == that.getDollars()) { //go deeper in comparison
				if (this.getCents() > that.getCents()) {
					return 1; 
				}
				if (this.getCents() < that.getCents()) {
					return -1; 
				}
				if (this.getCents() == that.getCents()) { //if we've made it this far, they must be equal
					return 0;
				}
			}
		} else { //else it's not a Money or it's null
			throw new RuntimeException("Money's compareTo() was called with a non-Money object."); 
		}
		return -100; //if for some reason we get this far, return -100; 
		
	}

	//The clone method for Money; returns a new Money Object with the same values of instance variables
		@Override
		public Money clone() {
			return new Money(this.dollars, this.cents);
		}

		public void writeObject() {
			// TODO Auto-generated method stub
			
		}

		public void readObject() {
			// TODO Auto-generated method stub
			
		}
	
}
