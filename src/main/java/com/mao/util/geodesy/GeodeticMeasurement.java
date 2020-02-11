package com.mao.util.geodesy;

/**
 * @author mao by 14:56 2020/2/6
 */
public class GeodeticMeasurement extends GeodeticCurve {
    private final double mElevationChange;
    private final double mP2P;

    public GeodeticMeasurement(double ellipsoidalDistance, double azimuth, double reverseAzimuth, double elevationChange) {
        super(ellipsoidalDistance, azimuth, reverseAzimuth);
        this.mElevationChange = elevationChange;
        this.mP2P = Math.sqrt(ellipsoidalDistance * ellipsoidalDistance + this.mElevationChange * this.mElevationChange);
    }

    public GeodeticMeasurement(GeodeticCurve averageCurve, double elevationChange) {
        this(averageCurve.getEllipsoidalDistance(), averageCurve.getAzimuth(), averageCurve.getReverseAzimuth(), elevationChange);
    }

    public double getElevationChange() {
        return this.mElevationChange;
    }

    public double getPointToPointDistance() {
        return this.mP2P;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.toString());
        buffer.append("elev12=");
        buffer.append(this.mElevationChange);
        buffer.append(";p2p=");
        buffer.append(this.mP2P);
        return buffer.toString();
    }
}