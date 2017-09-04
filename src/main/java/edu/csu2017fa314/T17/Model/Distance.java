package edu.csu2017fa314.T17.Model;

public class Distance {
  double radius;

  public Distance() {
    radius = 6371.0088;
    return;
  }

  public double convertToRadians(double degrees) {
    return (degrees * (Math.PI / 180.0));
  }

  public double greatCircleDistance(Brewery alpha, Brewery beta) {
    double theta1 = convertToRadians(alpha.getLat());
    double theta2 = convertToRadians(beta.getLat());
    double delta = convertToRadians(alpha.getLon()) -
        convertToRadians(beta.getLon());
    double subLower = (Math.sin(theta1) * Math.sin(theta2)) +
        (Math.cos(theta1) * Math.cos(theta2) * Math.cos(delta));
    double subUpper = Math.pow((Math.cos(theta2) * Math.sin(delta)), 2) +
        Math.pow(((Math.cos(theta1) * Math.sin(theta2)) - (Math.sin(theta1) * Math.cos(theta2) * Math.cos(delta))), 2);
    double angle = Math.atan2(Math.sqrt(subUpper), subLower);
    return (angle * this.radius);
  }
}
