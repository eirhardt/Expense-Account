import java.io.Serializable;

/**
 * 
 * @author Jack Lewis
 * CSS 162 
 * Rob Nash
 * 
 * Bill contains a Money Object, two Date objects, and a single String 
 * Object. The Money Object determines the amount of the Bill, the due date 
 * is utilized in identifying when the Bill needs to be paid by, and 
 * the paidDate records the date of the payment, and is null by default. 
 * The originator String determines the company to which the Bill
 * is owed. 
 * 
 * Bill's compareTo() method does not take whether or not the bill is paid
 * into account. 
 *
 */


public class Bill implements Comparable, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//instance variables
	private Money billAmount; 
	private Date billDueDate;
	private Date billPaidDate; 
	private String billOriginator; 

	//Constructor for Bill
	//Consists of a Money, two Dates (one null, initially), and a String
	public Bill(Money amount, Date dueDate, String Originator) throws IllegalArgumentException {
		if ((amount == null) || (dueDate == null) || (Originator == null)) {
			throw new IllegalArgumentException ("At least one supplied argument was null."); 
		} else {
			//avoid privacy leaks
			this.billAmount = amount.clone();  
			this.billDueDate = dueDate.clone(); 
			this.billPaidDate = null; 
			this.billOriginator = new String(Originator); 
		}
	}

	//Copy constructor for Bill. Creates new Objects
	//to avoid privacy leaks
	public Bill(Bill toCopy) throws IllegalArgumentException {
		if (toCopy != null) {
			this.billAmount = toCopy.billAmount.clone(); 
			this.billDueDate = toCopy.billDueDate.clone(); 
			if (toCopy.billPaidDate != null) {
				this.billPaidDate = toCopy.billPaidDate.clone(); 
			} else {
				this.billPaidDate = null; 
			}
			this.billOriginator = new String(toCopy.billOriginator); 
		} else {
			throw new IllegalArgumentException("Attempt to copy a null Bill Object in the copy constructor."); 
		}

	}

	//Bill Money getter
	public Money getAmount() {
		return this.billAmount.clone();  
	}

	//returns the Bill's due date as a Date copy
	public Date getDueDate() {
		return this.billDueDate.clone(); 
	}

	//setter for this Bill's due date
	public void setDueDate(Date nextDate) throws IllegalArgumentException {
		if (nextDate != null) {
			this.billDueDate = nextDate.clone(); 
		} else {
			throw new IllegalArgumentException("Failed to set dueDate; null Object."); 
		}
	}

	//setter for this Bill's amount
	public void setAmount(Money amount) throws IllegalArgumentException {
		if (this.billAmount != null) {
			this.billAmount = amount.clone(); 
		} else {
			throw new IllegalArgumentException("Failed to set Bill's amount; null Object."); 
		}
	}

	//return name of company to which the Bill is owed
	public String getOriginator() {
		return new String(this.billOriginator); 
	}

	//name this Bill's originator from a supplied String
	public void setOriginator(String originator) throws IllegalArgumentException {
		if (originator != null) {
			this.billOriginator = new String(originator); 
		} else {
			throw new IllegalArgumentException ("Failed to set originator; null String given."); 
		}
	}

	//boolean to determine if this Bill is paid already
	public boolean isPaid() {
		if (this.billPaidDate == null) {
			return false; 
		} else {
			return true;
		}
	}

	//set when this Bill was paid
	public void setPaid(Date onDay) throws IllegalArgumentException {
		if (onDay != null) {
			if (onDay.compareTo(this.billDueDate) == 1){
				throw new IllegalArgumentException("The payment is late.");
			} else {
				this.billPaidDate = onDay.clone();  
			}
		} else {
			throw new IllegalArgumentException("Attempted to set the date the Bill was paid to null."); 
		}
	}

	//sets paidDate equal to null, basically marking the Bill as unpaid
	public void setUnpaid() {
		this.billPaidDate = null; 
	}

	//compare a Bill Object with the current Bill
	//return true if they're equal in all respects
	//uses equals methods of each class
	//special case for Objects that may or may not have null value
	public boolean equals(Bill toCompare) throws IllegalArgumentException {
		if (toCompare != null) {
			if (this.billAmount.equals(toCompare.billAmount) && (this.billDueDate.equals(toCompare.billDueDate)) 
					&& this.billOriginator.equals(toCompare.billOriginator)) {
				if (this.billPaidDate == null && toCompare.billPaidDate == null) {
					return true; 
				}
				if (this.billPaidDate == null && toCompare.billPaidDate != null) {
					return false; 
				}
				if (this.billPaidDate != null && toCompare.billPaidDate == null) {
					return false; 
				}
				if (this.billPaidDate.equals(toCompare.billPaidDate)) {
					return true; 
				}

			} else {
				return false; 
			}
		} else {
			throw new IllegalArgumentException("Tried to compare this Bill with a null Bill."); 
		}
		return false; 
	}

	//toString method
	//two variations; one excludes the paidDate member (to avoid NullPointerException)
	public String toString() {
		if (billPaidDate == null) {
			return ("Amount: " + this.billAmount.toString() + ", Due Date: " 
					+ this.billDueDate.toString() + ", To: " + this.billOriginator + ", Paid? " + this.isPaid());
		} else {
			return ("Amount: " + this.billAmount.toString() + ",Due Date: " + this.billDueDate.toString() 
					+ ", To: " + this.billOriginator + ", Paid? " + this.isPaid() + ", Date paid: " 
					+ billPaidDate.toString());
		}
	}

	//compareTo method
	//Takes an object, makes sure it's a Bill, 
	//and then methodically compares all aspects. 
	//If that Bill amount is equal to this, then compare
	//dueDates. If those are also equal, 
	// compare Originator strings.
	//if less than this, return 1
	//if greater than this, return -1
	@Override
	public int compareTo(Object o) {
		if (o != null && o instanceof Bill) {
			Bill that = (Bill)o; 
			if (this.billAmount.compareTo(that.billAmount) > 0) {
				return 1; 
			}
			if (this.billAmount.compareTo(that.billAmount) < 0) {
				return -1;
			}
			if (this.billAmount.compareTo(that.billAmount) == 0) { //go deeper in the comparison
				if (this.billDueDate.compareTo(that.billDueDate) > 0) {
					return 1;
				}
				if (this.billDueDate.compareTo(that.billDueDate) < 0) {
					return -1; 
				}
				if (this.billDueDate.compareTo(that.billDueDate) == 0) { //go deeper in the comparison
					if (this.billOriginator.compareTo(that.billOriginator) > 0) {
						return 1; 
					}
					if (this.billOriginator.compareTo(that.billOriginator) < 0) {
						return -1; 
					}
					if (this.billOriginator.compareTo(that.billOriginator) == 0) { //final possibility
						return 0; 
					}
				}
			}
		} else { //else, it's simply not a bill, or it's null
			throw new RuntimeException("Bill's compareTo() failed to run. Check parameters."); 
		}
		
		return -100; //if we've made it this far (somehow), return nonsensical value; 
	}
	
	@Override
	public Bill clone() {
		return new Bill(this.billAmount.clone(), this.billDueDate.clone(), this.billOriginator);
		
	}



}
