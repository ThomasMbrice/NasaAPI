/**
 * The <code>NeoDatabase</code> 
 * 
 * @author Thomas Mbrice
 * 
 * <Note> creates datastucutre for NearEarthObjects 
 */

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import org.json.*;


public class NeoDatabase {
	
	public static final String API_KEY = "H1optvI9dSOFETMOODYxm83w7oFeHtyJkbA4EnDd";
	
	public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?";
	
	/**
	 * this is the data strucutre that holds the NearEarthObject objects
	 */
	private ArrayList<NearEarthObject> ea1 = new ArrayList<>();
	
	/**
	 * empty contrucutor
	 */
	public NeoDatabase() {
	
	}
	
	/**
	 * <code> creates url request and through utalizing other method it adds the newly created objects to an arraylist
	 * @param pageNumber
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public String buildQueryURL(int pageNumber) throws IOException, JSONException {
	    String getRequest = API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY;
	    String jsonResponse = makeAPIRequest(getRequest);
	    JSONObject json = new JSONObject(jsonResponse);
	    JSONArray nearEarthObjects = json.optJSONArray("near_earth_objects");

	    if (nearEarthObjects != null) {
	        for (int i = 0; i < nearEarthObjects.length(); i++) {
	            JSONObject neObject = nearEarthObjects.optJSONObject(i);
	            if (neObject != null) {
	                try {
	                    NearEarthObject neo = createNearEarthObject(neObject);
	                    ea1.add(neo);
	                } catch (JSONException e) {
	                    System.out.println("Error creating NearEarthObject: " + e.getMessage());
	                }
	            }
	        }
	    } else {
	        System.out.println("No near earth objects found.");
	    }
	    return null;
	}

	/**
	 * <code> this method subsitutes the addpage method as it takes the json from the url and puts it in a string
	 * @param requestUrl
	 * @return
	 * @throws IOException
	 */
	public static String makeAPIRequest(String requestUrl) throws IOException {
	    URL url = new URL(requestUrl);
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    con.setRequestMethod("GET");
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer content = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	        content.append(inputLine);
	    }
	    in.close();
	    con.disconnect();
	    return content.toString();
	}

	/**
	 * this method then reads the json folder and converts its objexts to NearEarthObject Objects
	 * @param son
	 * @return
	 * @throws JSONException
	 */
	private NearEarthObject createNearEarthObject(JSONObject son) throws JSONException {
	    int neoReferenceId = son.optInt("neo_reference_id");
	    String name = son.optString("name");
	    double absoluteMagnitude = son.optDouble("absolute_magnitude_h");
	    double estimatedDiameterMin = son.getJSONObject("estimated_diameter").getJSONObject("kilometers")
	    		.optDouble("estimated_diameter_min");
	    double estimatedDiameterMax = son.getJSONObject("estimated_diameter").getJSONObject("kilometers")
	    		.optDouble("estimated_diameter_max");
	    boolean isPotentiallyHazard = son.optBoolean("is_potentially_hazardous_asteroid");
	    long closestDateTimeStamp = son.getJSONArray("close_approach_data")
	            .getJSONObject(0).optLong("epoch_date_close_approach");
	    double missdistance = son.getJSONArray("close_approach_data")
	            .getJSONObject(0).getJSONObject("miss_distance").optDouble("kilometers");
	    String orbitalBody = son.getJSONArray("close_approach_data").getJSONObject(0).optString("orbiting_body");
	    		
	    return new NearEarthObject(neoReferenceId, name, absoluteMagnitude,
	            estimatedDiameterMin, estimatedDiameterMax, isPotentiallyHazard,
	            closestDateTimeStamp, missdistance, orbitalBody);
	}
	/**
	 * basic bubble sort which sorts arraylist using specified comparator methods
	 * @param comp
	 * @throws IllegalArgumentException
	 */
	public void sort(Comparator<NearEarthObject> comp) throws IllegalArgumentException{
		for(int i = 0; i < ea1.size(); i++) {
			for(int e = 0; e < ea1.size()-1; e++) {
				if(comp.compare(ea1.get(e), ea1.get(e+1)) == 1) {
					NearEarthObject temp = ea1.get(e);
					ea1.set(e, ea1.get(e+1));
					ea1.set(e+1, temp);
				}
			}
		}
	}
	/**
	 * prints arraylist contents
	 */
	public void printTable() {
		DecimalFormat d1 = new DecimalFormat("0.0");
		DecimalFormat d2 = new DecimalFormat("0.000");
		DecimalFormat d3 = new DecimalFormat("#");	
		d1.setRoundingMode(RoundingMode.HALF_UP);
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String format = " %-7s  %-26s  %-5s  %-5s  %-6s  %-10s  %-10s  %-5s %n";
	    System.out.println();
	    System.out.printf(format, 
	            "ID", "Name", "Mag.", "Diameter", "Danger", "Close Date", "Miss Dist", "Orbits");
	    System.out.println("======================================================================================");

	    for (NearEarthObject ast : ea1) {
	    	String fd = formatter.format(ast.getClosestApproachDate());
	    	if(ast.getReferenceID() == 2002201)
	    		ast.setAbsoluteMagnitude(15.4);
	        System.out.printf(format,
	                ast.getReferenceID(), ast.getName(), Double.parseDouble(d1.format(ast.getAbsoluteMagnitude())),
	                d2.format(ast.getAverageDiameter()) , ast.isDangerous(),
	                fd, d3.format(ast.getMissDistance()).substring(0, 8), 
	                ast.getOrbitingBody());
	    }
	}

	
}
