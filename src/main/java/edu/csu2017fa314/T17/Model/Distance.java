package edu.csu2017fa314.T17.Model;

public class Distance {
    double radius;
    public Distance()
    {
        radius = 6371.0088;
        return;
    }

    public double convertToRadians(double degrees)
    {
        return (degrees * (Math.PI/ 180.0));
    }

    public int greatCircleDistance(Brewery alpha, Brewery beta)
    {
        int distance = 0;
        double theta1 = convertToRadians(alpha.getLat());
        double theta2 = convertToRadians(beta.getLat());
        double delta = convertToRadians(alpha.getLon()) -
                    convertToRadians(beta.getLon());
        double subLower = (Math.sin(theta1) * Math.sin(theta2)) +
                (Math.cos(theta1) * Math.cos(theta2) * Math.cos(delta));
        double subUpper = Math.pow((Math.cos(theta2)*Math.sin(delta)),2) +
                Math.pow(((Math.cos(theta1) * Math.sin(theta2)) - (Math.sin(theta1) * Math.cos(theta2) * Math.cos(delta))),2);
        double angle = Math.atan2( Math.sqrt(subUpper), subLower);
        distance = (int) Math.round(angle * this.radius);
        return distance;
    }
    /*public int greatCircleDistance(Brewery alpha, Brewery beta)
    {
        int R = 6371; // metres
        double φ1 = convertToRadians(alpha.getLat());
        double φ2 = convertToRadians(beta.getLat());
        double Δφ = convertToRadians(beta.getLat() - alpha.getLat());
        double Δλ = convertToRadians(beta.getLon() - alpha.getLon());

        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double d = R * c;
        return (int) d;
    }*/
}
