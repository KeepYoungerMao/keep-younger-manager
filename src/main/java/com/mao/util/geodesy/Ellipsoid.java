package com.mao.util.geodesy;

import java.io.Serializable;

/**
 * @author mao by 14:52 2020/2/6
 */
public class Ellipsoid implements Serializable {
    private static final long serialVersionUID = 5651627667L;
    private final double mSemiMajorAxis;
    private final double mSemiMinorAxis;
    private final double mFlattening;
    private final double mInverseFlattening;
    public static final Ellipsoid WGS84 = fromAAndInverseF(6378137.0D, 298.257223563D);
    public static final Ellipsoid GRS80 = fromAAndInverseF(6378137.0D, 298.257222101D);
    public static final Ellipsoid GRS67 = fromAAndInverseF(6378160.0D, 298.25D);
    public static final Ellipsoid ANS = fromAAndInverseF(6378160.0D, 298.25D);
    public static final Ellipsoid WGS72 = fromAAndInverseF(6378135.0D, 298.26D);
    public static final Ellipsoid Clarke1858 = fromAAndInverseF(6378293.645D, 294.26D);
    public static final Ellipsoid Clarke1880 = fromAAndInverseF(6378249.145D, 293.465D);
    public static final Ellipsoid Sphere = fromAAndF(6371000.0D, 0.0D);

    private Ellipsoid(double semiMajor, double semiMinor, double flattening, double inverseFlattening) {
        this.mSemiMajorAxis = semiMajor;
        this.mSemiMinorAxis = semiMinor;
        this.mFlattening = flattening;
        this.mInverseFlattening = inverseFlattening;
    }

    public static Ellipsoid fromAAndInverseF(double semiMajor, double inverseFlattening) {
        double f = 1.0D / inverseFlattening;
        double b = (1.0D - f) * semiMajor;
        return new Ellipsoid(semiMajor, b, f, inverseFlattening);
    }

    public static Ellipsoid fromAAndF(double semiMajor, double flattening) {
        double inverseF = 1.0D / flattening;
        double b = (1.0D - flattening) * semiMajor;
        return new Ellipsoid(semiMajor, b, flattening, inverseF);
    }

    public double getSemiMajorAxis() {
        return this.mSemiMajorAxis;
    }

    public double getSemiMinorAxis() {
        return this.mSemiMinorAxis;
    }

    public double getFlattening() {
        return this.mFlattening;
    }

    public double getInverseFlattening() {
        return this.mInverseFlattening;
    }
}