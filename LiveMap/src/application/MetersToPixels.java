package application;

public class MetersToPixels {
	private static final double PIXELS_PER_METER = 49.382716049;
	
	/**
	 * converts meters to pixels
	 * @param mp meters to be converted to pixels
	 * @return
	 */
	public static int convertPixels(double mp)
	{
		int i = (int) (mp*PIXELS_PER_METER);
		return i;
	}
	
	/**
	 * converts pixels to meters
	 * @param pm pixels to be converted to meters
	 * @return
	 */
	public static double convertMeters(int pm)
	{
		double i = (double) (pm/PIXELS_PER_METER);
		return i;
	}
}
