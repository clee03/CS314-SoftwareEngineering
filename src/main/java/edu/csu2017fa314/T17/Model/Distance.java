package edu.csu2017fa314.T17.Model;

public class Distance {
  double radius;

  public Distance(){
    radius = 3958.7613; //default set to miles
  }
  public Distance(boolean toggle) {
    if (toggle) {
      radius = 6371.0088; // if toggle is true then use Kilometers
    }else{
      radius = 3958.7613; //if toggle is not set then use Miles
    }
    return;
  }

  public double convertToRadians(double degrees) {
    return (degrees * (Math.PI / 180.0));
  }

  public int greatCircleDistance(Brewery alpha, Brewery beta) {
    double theta1 = convertToRadians(alpha.getLat());
    double theta2 = convertToRadians(beta.getLat());
    double delta = convertToRadians(alpha.getLon()) -
        convertToRadians(beta.getLon());
    double subLower = (Math.sin(theta1) * Math.sin(theta2)) +
        (Math.cos(theta1) * Math.cos(theta2) * Math.cos(delta));
    double subUpper = Math.pow((Math.cos(theta2) * Math.sin(delta)), 2) +
        Math.pow(((Math.cos(theta1) * Math.sin(theta2)) - (Math.sin(theta1) * Math.cos(theta2) * Math.cos(delta))), 2);
    double angle = Math.atan2(Math.sqrt(subUpper), subLower);
    return (int) Math.round(angle * this.radius);
  }
}
