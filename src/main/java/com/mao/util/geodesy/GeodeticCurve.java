package com.mao.util.geodesy;

import java.io.Serializable;

/**
 * @author mao by 14:55 2020/2/6
 */
public class GeodeticCurve implements Serializable {
    private static final long serialVersionUID = 565162176670L;
    private final double mEllipsoidalDistance;
    private final double mAzimuth;
    private final double mReverseAzimuth;

    public GeodeticCurve(double ellipsoidalDistance, double azimuth, double reverseAzimuth) {
        this.mEllipsoidalDistance = ellipsoidalDistance;
        this.mAzimuth = azimuth;
        this.mReverseAzimuth = reverseAzimuth;
    }

    public double getEllipsoidalDistance() {
        return this.mEllipsoidalDistance;
    }

    public double getAzimuth() {
        return this.mAzimuth;
    }

    public double getReverseAzimuth() {
        return this.mReverseAzimuth;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("s=");
        buffer.append(this.mEllipsoidalDistance);
        buffer.append(";a12=");
        buffer.append(this.mAzimuth);
        buffer.append(";a21=");
        buffer.append(this.mReverseAzimuth);
        buffer.append(";");
        return buffer.toString();
    }
}