/**
 * The <code>NearEarthObject</code> 
 * 
 * @author Thomas Mbrice
 * <Note> creates NearEarthObject class 
 */

import java.sql.Date;

public class NearEarthObject {
	private int referenceID;

	private String name;

	private double absoluteMagnitude;

	private double averageDiameter;

	private boolean isDangerous;

	private Date closestApproachDate;

	private double missDistance;

	private String orbitingBody;
	
	/**
	 * <code> constructor <code> 
	 * @param referenceID
	 * @param name
	 * @param absoluteMagnitude
	 * @param minDiameter
	 * @param maxDiameter
	 * @param isDangerous
	 * @param closestDateTimestamp
	 * @param missDistance
	 * @param orbitingBody
	 */
	public NearEarthObject(int referenceID, String name, double absoluteMagnitude, 
			double minDiameter, double maxDiameter, boolean isDangerous, 
			long closestDateTimestamp, double missDistance, String orbitingBody) {
		this.referenceID = referenceID;
		this.name = name;
		this.absoluteMagnitude = absoluteMagnitude;
		this.averageDiameter = ((minDiameter + maxDiameter)/2);
		this.isDangerous = isDangerous;
		Date ds = new Date(closestDateTimestamp);
		this.closestApproachDate = ds;
		this.missDistance = missDistance;
		this.orbitingBody = orbitingBody;
		
	}
	/**
	 * getter
	 * @return
	 */
	public int getReferenceID() {
		return referenceID;
	}
	/**
	 * setter
	 * @param referenceID
	 */
	public void setReferenceID(int referenceID) {
		this.referenceID = referenceID;
	}
	/**
	 * getter
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter
	 * @return
	 */
	public double getAbsoluteMagnitude() {
		return absoluteMagnitude;
	}
	/**
	 * setter
	 * @param absoluteMagnitude
	 */
	public void setAbsoluteMagnitude(double absoluteMagnitude) {
		this.absoluteMagnitude = absoluteMagnitude;
	}
	/**
	 * getter
	 * @return
	 */
	public double getAverageDiameter() {
		return averageDiameter;
	}
	/**
	 * setter
	 * @param averageDiameter
	 */
	public void setAverageDiameter(double averageDiameter) {
		this.averageDiameter = averageDiameter;
	}
	/**
	 * getter
	 * @return
	 */
	public boolean isDangerous() {
		return isDangerous;
	}
	/**
	 * setter
	 * @param isDangerous
	 */
	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}
	/**
	 * getter
	 * @return
	 */
	public Date getClosestApproachDate() {
		return closestApproachDate;
	}
	/**
	 * setter
	 * @param closestApproachDate
	 */
	public void setClosestApproachDate(Date closestApproachDate) {
		this.closestApproachDate = closestApproachDate;
	}
	/**
	 * getter
	 * @return
	 */
	public double getMissDistance() {
		return missDistance;
	}
	/**
	 * setter
	 * @param missDistance
	 */
	public void setMissDistance(double missDistance) {
		this.missDistance = missDistance;
	}
	/**
	 * getter
	 * @return
	 */
	public String getOrbitingBody() {
		return orbitingBody;
	}
	/**
	 * setter
	 * @param orbitingBody
	 */
	public void setOrbitingBody(String orbitingBody) {
		this.orbitingBody = orbitingBody;
	}
}
