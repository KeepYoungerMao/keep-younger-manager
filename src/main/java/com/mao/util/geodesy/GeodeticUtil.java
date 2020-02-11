package com.mao.util.geodesy;

/**
 * @author mao by 14:16 2020/2/6
 */
public class GeodeticUtil {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(getDistance(29.490295,106.486654,29.615467,106.581515));
        System.out.println(System.currentTimeMillis());
    }

    private static double getDistance(double lng1, double lat1, double lng2, double lat2){
        GlobalCoordinates from = new GlobalCoordinates(lng1,lat1);
        GlobalCoordinates to = new GlobalCoordinates(lng2,lat2);
        GeodeticCurve geodeticCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere,from,to);
        return geodeticCurve.getEllipsoidalDistance();
    }

}