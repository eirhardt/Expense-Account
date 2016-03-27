import java.util.Iterator;

/**
 * 
 * @author Jack Lewis
 * 
 * CSS 162
 * Professor Rob Nash
 *
 * Expense Account extends my ArrayList. 
 * I made it so that it can only hold generic Bill objects. 
 * 
 * 
 */
public class ExpenseAccount<Type extends Bill> extends ArrayList<Type> {

	private static Money testMoney = new Money(); 
	private static Date testDate = new Date(4, 15, 2020); 
	
	//Simply calls the ArrayList constructor
	public ExpenseAccount() {
		super(); 
	}
	
	
	
	
	public static void main(String[] args) {
		
		
		
		ExpenseAccount<Bill> a = new ExpenseAccount<Bill>(); 
		a.add(new Bill(testMoney, testDate, "Eminem"));
		//a.add(new Money(1500.50)); //Correctly is unable to add
		System.out.println(a.toString()); 
		//a.remove();
		//System.out.println(a.toString());
		//a.remove();
		//System.out.println(a.toString());
		Iterator<Bill> myIterator = a.iterator(); //quick test of iterator
		while (myIterator.hasNext()) {
			Object element = myIterator.next(); 
			System.out.println(element.toString() + ", ");
		}
	}
}
