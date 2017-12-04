package edu.csu2017fa314.T17.Model;

public class Distance {
  private double radius;
  public enum unit {Miles, Kilometers};

  public Distance(){
    this(unit.Kilometers);
  }

  public Distance (unit type) {
    if (type == unit.Kilometers) {
      radius = 6371.0088; // if toggle is true then use Kilometers
    }
    else {
      radius = 3958.7613; //if toggle is not set then use Miles
    }
  }

  public double convertToRadians(double degrees) {
    return (degrees * (Math.PI / 180.0));
  }

  public int greatCircleDistance(Location alpha, Location beta) {
    double theta1 = convertToRadians((double) alpha.get("latitude"));
    double theta2 = convertToRadians((double) beta.get("latitude"));
    double delta = convertToRadians((double) alpha.get("longitude")) -
        convertToRadians((double) beta.get("longitude"));
    double subLower = (Math.sin(theta1) * Math.sin(theta2)) +
        (Math.cos(theta1) * Math.cos(theta2) * Math.cos(delta));
    double subUpper = Math.pow((Math.cos(theta2) * Math.sin(delta)), 2) +
        Math.pow(((Math.cos(theta1) * Math.sin(theta2)) - (Math.sin(theta1) * Math.cos(theta2) * Math.cos(delta))), 2);
    double angle = Math.atan2(Math.sqrt(subUpper), subLower);
    return (int) Math.round(angle * this.radius);
  }
}
