package com.mao.util.geodesy;

/**
 * @author mao by 14:54 2020/2/6
 */
public class GeodeticCalculator {
    private final double TwoPi = 6.283185307179586D;

    public GlobalCoordinates calculateEndingGlobalCoordinates(Ellipsoid ellipsoid, GlobalCoordinates start, double startBearing, double distance, double[] endBearing) {
        double a = ellipsoid.getSemiMajorAxis();
        double b = ellipsoid.getSemiMinorAxis();
        double aSquared = a * a;
        double bSquared = b * b;
        double f = ellipsoid.getFlattening();
        double phi1 = toRadians(start.getLatitude());
        double alpha1 = toRadians(startBearing);
        double cosAlpha1 = Math.cos(alpha1);
        double sinAlpha1 = Math.sin(alpha1);
        double tanU1 = (1.0D - f) * Math.tan(phi1);
        double cosU1 = 1.0D / Math.sqrt(1.0D + tanU1 * tanU1);
        double sinU1 = tanU1 * cosU1;
        double sigma1 = Math.atan2(tanU1, cosAlpha1);
        double sinAlpha = cosU1 * sinAlpha1;
        double sin2Alpha = sinAlpha * sinAlpha;
        double cos2Alpha = 1.0D - sin2Alpha;
        double uSquared = cos2Alpha * (aSquared - bSquared) / bSquared;
        double A = 1.0D + uSquared / 16384.0D * (4096.0D + uSquared * (-768.0D + uSquared * (320.0D - 175.0D * uSquared)));
        double B = uSquared / 1024.0D * (256.0D + uSquared * (-128.0D + uSquared * (74.0D - 47.0D * uSquared)));
        double sOverbA = distance / (b * A);
        double sigma = sOverbA;
        double prevSigma = sOverbA;

        while(true) {
            double sigmaM2 = 2.0D * sigma1 + sigma;
            double cosSigmaM2 = Math.cos(sigmaM2);
            double cos2SigmaM2 = cosSigmaM2 * cosSigmaM2;
            double sinSigma = Math.sin(sigma);
            double cosSigma = Math.cos(sigma);
            double deltaSigma = B * sinSigma * (cosSigmaM2 + B / 4.0D * (cosSigma * (-1.0D + 2.0D * cos2SigmaM2) - B / 6.0D * cosSigmaM2 * (-3.0D + 4.0D * sinSigma * sinSigma) * (-3.0D + 4.0D * cos2SigmaM2)));
            sigma = sOverbA + deltaSigma;
            if(Math.abs(sigma - prevSigma) < 1.0E-13D) {
                sigmaM2 = 2.0D * sigma1 + sigma;
                cosSigmaM2 = Math.cos(sigmaM2);
                cos2SigmaM2 = cosSigmaM2 * cosSigmaM2;
                cosSigma = Math.cos(sigma);
                sinSigma = Math.sin(sigma);
                double phi2 = Math.atan2(sinU1 * cosSigma + cosU1 * sinSigma * cosAlpha1, (1.0D - f) * Math.sqrt(sin2Alpha + Math.pow(sinU1 * sinSigma - cosU1 * cosSigma * cosAlpha1, 2.0D)));
                double lambda = Math.atan2(sinSigma * sinAlpha1, cosU1 * cosSigma - sinU1 * sinSigma * cosAlpha1);
                double C = f / 16.0D * cos2Alpha * (4.0D + f * (4.0D - 3.0D * cos2Alpha));
                double L = lambda - (1.0D - C) * f * sinAlpha * (sigma + C * sinSigma * (cosSigmaM2 + C * cosSigma * (-1.0D + 2.0D * cos2SigmaM2)));
                double alpha2 = Math.atan2(sinAlpha, -sinU1 * sinSigma + cosU1 * cosSigma * cosAlpha1);
                double latitude = toDegrees(phi2);
                double longitude = start.getLongitude() + toDegrees(L);
                if(endBearing != null && endBearing.length > 0) {
                    endBearing[0] = toDegrees(alpha2);
                }

                return new GlobalCoordinates(latitude, longitude);
            }

            prevSigma = sigma;
        }
    }

    public GlobalCoordinates calculateEndingGlobalCoordinates(Ellipsoid ellipsoid, GlobalCoordinates start, double startBearing, double distance) {
        return this.calculateEndingGlobalCoordinates(ellipsoid, start, startBearing, distance, (double[])null);
    }

    public GeodeticCurve calculateGeodeticCurve(Ellipsoid ellipsoid, GlobalCoordinates start, GlobalCoordinates end) {
        double a = ellipsoid.getSemiMajorAxis();
        double b = ellipsoid.getSemiMinorAxis();
        double f = ellipsoid.getFlattening();
        double phi1 = toRadians(start.getLatitude());
        double lambda1 = toRadians(start.getLongitude());
        double phi2 = toRadians(end.getLatitude());
        double lambda2 = toRadians(end.getLongitude());
        double a2 = a * a;
        double b2 = b * b;
        double a2b2b2 = (a2 - b2) / b2;
        double omega = lambda2 - lambda1;
        double tanphi1 = Math.tan(phi1);
        double tanU1 = (1.0D - f) * tanphi1;
        double U1 = Math.atan(tanU1);
        double sinU1 = Math.sin(U1);
        double cosU1 = Math.cos(U1);
        double tanphi2 = Math.tan(phi2);
        double tanU2 = (1.0D - f) * tanphi2;
        double U2 = Math.atan(tanU2);
        double sinU2 = Math.sin(U2);
        double cosU2 = Math.cos(U2);
        double sinU1sinU2 = sinU1 * sinU2;
        double cosU1sinU2 = cosU1 * sinU2;
        double sinU1cosU2 = sinU1 * cosU2;
        double cosU1cosU2 = cosU1 * cosU2;
        double lambda = omega;
        double A = 0.0D;
        double B = 0.0D;
        double sigma = 0.0D;
        double deltasigma = 0.0D;
        boolean converged = false;

        for(int s = 0; s < 20; ++s) {
            double lambda0 = lambda;
            double sinlambda = Math.sin(lambda);
            double coslambda = Math.cos(lambda);
            double sin2sigma = cosU2 * sinlambda * cosU2 * sinlambda + (cosU1sinU2 - sinU1cosU2 * coslambda) * (cosU1sinU2 - sinU1cosU2 * coslambda);
            double sinsigma = Math.sqrt(sin2sigma);
            double cossigma = sinU1sinU2 + cosU1cosU2 * coslambda;
            sigma = Math.atan2(sinsigma, cossigma);
            double sinalpha = sin2sigma == 0.0D?0.0D:cosU1cosU2 * sinlambda / sinsigma;
            double alpha = Math.asin(sinalpha);
            double cosalpha = Math.cos(alpha);
            double cos2alpha = cosalpha * cosalpha;
            double cos2sigmam = cos2alpha == 0.0D?0.0D:cossigma - 2.0D * sinU1sinU2 / cos2alpha;
            double u2 = cos2alpha * a2b2b2;
            double cos2sigmam2 = cos2sigmam * cos2sigmam;
            A = 1.0D + u2 / 16384.0D * (4096.0D + u2 * (-768.0D + u2 * (320.0D - 175.0D * u2)));
            B = u2 / 1024.0D * (256.0D + u2 * (-128.0D + u2 * (74.0D - 47.0D * u2)));
            deltasigma = B * sinsigma * (cos2sigmam + B / 4.0D * (cossigma * (-1.0D + 2.0D * cos2sigmam2) - B / 6.0D * cos2sigmam * (-3.0D + 4.0D * sin2sigma) * (-3.0D + 4.0D * cos2sigmam2)));
            double C = f / 16.0D * cos2alpha * (4.0D + f * (4.0D - 3.0D * cos2alpha));
            lambda = omega + (1.0D - C) * f * sinalpha * (sigma + C * sinsigma * (cos2sigmam + C * cossigma * (-1.0D + 2.0D * cos2sigmam2)));
            double change = Math.abs((lambda - lambda0) / lambda);
            if(s > 1 && change < 1.0E-13D) {
                converged = true;
                break;
            }
        }

        double var96 = b * A * (sigma - deltasigma);
        double alpha1;
        double alpha2;
        if(!converged) {
            if(phi1 > phi2) {
                alpha1 = 180.0D;
                alpha2 = 0.0D;
            } else if(phi1 < phi2) {
                alpha1 = 0.0D;
                alpha2 = 180.0D;
            } else {
                alpha1 = 0.0D / 0.0;
                alpha2 = 0.0D / 0.0;
            }
        } else {
            double radians = Math.atan2(cosU2 * Math.sin(lambda), cosU1sinU2 - sinU1cosU2 * Math.cos(lambda));
            if(radians < 0.0D) {
                radians += 6.283185307179586D;
            }

            alpha1 = toDegrees(radians);
            radians = Math.atan2(cosU1 * Math.sin(lambda), -sinU1cosU2 + cosU1sinU2 * Math.cos(lambda)) + 3.141592653589793D;
            if(radians < 0.0D) {
                radians += 6.283185307179586D;
            }

            alpha2 = toDegrees(radians);
        }

        if(alpha1 >= 360.0D) {
            alpha1 -= 360.0D;
        }

        if(alpha2 >= 360.0D) {
            alpha2 -= 360.0D;
        }

        return new GeodeticCurve(var96, alpha1, alpha2);
    }

    public GeodeticMeasurement calculateGeodeticMeasurement(Ellipsoid refEllipsoid, GlobalPosition start, GlobalPosition end) {
        double elev1 = start.getElevation();
        double elev2 = end.getElevation();
        double elev12 = (elev1 + elev2) / 2.0D;
        double phi1 = toRadians(start.getLatitude());
        double phi2 = toRadians(end.getLatitude());
        double phi12 = (phi1 + phi2) / 2.0D;
        double refA = refEllipsoid.getSemiMajorAxis();
        double f = refEllipsoid.getFlattening();
        double a = refA + elev12 * (1.0D + f * Math.sin(phi12));
        Ellipsoid ellipsoid = Ellipsoid.fromAAndF(a, f);
        GeodeticCurve averageCurve = this.calculateGeodeticCurve(ellipsoid, start, end);
        return new GeodeticMeasurement(averageCurve, elev2 - elev1);
    }

    private double toRadians(double degrees) {
        return degrees * 0.017453292519943295D;
    }

    private double toDegrees(double radians) {
        return radians / 0.017453292519943295D;
    }

}