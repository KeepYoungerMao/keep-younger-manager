package com.mao.util.geodesy;

import java.io.Serializable;

/**
 * @author mao by 14:57 2020/2/6
 */
public class GlobalCoordinates implements Comparable<GlobalCoordinates>, Serializable {
    private double mLatitude;
    private double mLongitude;

    private void canonicalize() {
        this.mLatitude = (this.mLatitude + 180.0D) % 360.0D;
        if(this.mLatitude < 0.0D) {
            this.mLatitude += 360.0D;
        }

        this.mLatitude -= 180.0D;
        if(this.mLatitude > 90.0D) {
            this.mLatitude = 180.0D - this.mLatitude;
            this.mLongitude += 180.0D;
        } else if(this.mLatitude < -90.0D) {
            this.mLatitude = -180.0D - this.mLatitude;
            this.mLongitude += 180.0D;
        }

        this.mLongitude = (this.mLongitude + 180.0D) % 360.0D;
        if(this.mLongitude <= 0.0D) {
            this.mLongitude += 360.0D;
        }

        this.mLongitude -= 180.0D;
    }

    public GlobalCoordinates(double latitude, double longitude) {
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.canonicalize();
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
        this.canonicalize();
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
        this.canonicalize();
    }

    public int compareTo(GlobalCoordinates other) {
        byte retval;
        if(this.mLongitude < other.mLongitude) {
            retval = -1;
        } else if(this.mLongitude > other.mLongitude) {
            retval = 1;
        } else if(this.mLatitude < other.mLatitude) {
            retval = -1;
        } else if(this.mLatitude > other.mLatitude) {
            retval = 1;
        } else {
            retval = 0;
        }

        return retval;
    }

    public int hashCode() {
        return (int)(this.mLongitude * this.mLatitude * 1000000.0D + 1021.0D) * 1000033;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof GlobalCoordinates)) {
            return false;
        } else {
            GlobalCoordinates other = (GlobalCoordinates)obj;
            return this.mLongitude == other.mLongitude && this.mLatitude == other.mLatitude;
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Math.abs(this.mLatitude));
        buffer.append((char)(this.mLatitude >= 0.0D?'N':'S'));
        buffer.append(';');
        buffer.append(Math.abs(this.mLongitude));
        buffer.append((char)(this.mLongitude >= 0.0D?'E':'W'));
        buffer.append(';');
        return buffer.toString();
    }
}