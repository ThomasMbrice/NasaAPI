/**
 * The <code>MissDistanceComparator</code> 
 * 
 * @author Thomas Mbrice
 * <Note> comapres NearEarthObject objects 
 */

public class MissDistanceComparator implements java.util.Comparator<NearEarthObject>{
	/**
	 * <code> comapres two objects returns 1 if the first object is larger and 2 if it is smaller
	 */
	public int compare(NearEarthObject o1, NearEarthObject o2) {
		if(o1.getMissDistance() > o2.getMissDistance())
			return 1;
		else
			return 0;
	}

}
