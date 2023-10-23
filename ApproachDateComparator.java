/**
 * The <code>ApproachDateComparator</code> 
 * 
 * @author Thomas Mbrice
 * <Note> comapres NearEarthObject objects 
 */

public class ApproachDateComparator implements java.util.Comparator<NearEarthObject>{
	/**
	 * <code> comapres two objects returns 1 if the first object is larger and 2 if it is smaller
	 */
	public int compare(NearEarthObject o1, NearEarthObject o2) {
	if(o1.getClosestApproachDate().compareTo(o2.getClosestApproachDate()) > 0)
		return 1;
	else
		return 0;
		
	}

}
