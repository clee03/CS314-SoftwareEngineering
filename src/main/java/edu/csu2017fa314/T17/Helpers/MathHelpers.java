package edu.csu2017fa314.T17.Helpers;

public class MathHelpers {
  /**
   *  Helper function to exchange lat/lon into their decimal counterparts
   *  @param degrees    the string that will be parsed
   *  @return double    a decimal value representing the string passed as a parameter
   */
  static public double degToDecimal (String degrees) {
    degrees = degrees.replace(" ", "");
    String[] parts = degrees.split("[°'\"]");

    double total = 0.0;
    switch (parts.length) {
      case 4:
        total += (Double.parseDouble(parts[2]) / 3600 );
        // fall through
      case 3:
        total += (Double.parseDouble(parts[1]) / 60 );
        // fall through
      case 2:
        total += (Double.parseDouble( parts[0]));
        String cardinal = parts[ parts.length - 1 ];
        if(cardinal.equals("W") || cardinal.equals("S"))
          total *= -1;
        return total;
      default:
        return Double.parseDouble(parts[0]);
    }
  }
}
