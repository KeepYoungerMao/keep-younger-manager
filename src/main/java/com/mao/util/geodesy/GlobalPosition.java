package com.mao.util.geodesy;

/**
 * @author mao by 14:58 2020/2/6
 */
public class GlobalPosition extends GlobalCoordinates {
    private double mElevation;

    public GlobalPosition(double latitude, double longitude, double elevation) {
        super(latitude, longitude);
        this.mElevation = elevation;
    }

    public GlobalPosition(GlobalCoordinates coords, double elevation) {
        this(coords.getLatitude(), coords.getLongitude(), elevation);
    }

    public double getElevation() {
        return this.mElevation;
    }

    public void setElevation(double elevation) {
        this.mElevation = elevation;
    }

    public int compareTo(GlobalPosition other) {
        int retval = super.compareTo(other);
        if(retval == 0) {
            if(this.mElevation < other.mElevation) {
                retval = -1;
            } else if(this.mElevation > other.mElevation) {
                retval = 1;
            }
        }

        return retval;
    }

    public int hashCode() {
        int hash = super.hashCode();
        if(this.mElevation != 0.0D) {
            hash *= (int)this.mElevation;
        }

        return hash;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof GlobalPosition)) {
            return false;
        } else {
            GlobalPosition other = (GlobalPosition)obj;
            return this.mElevation == other.mElevation && super.equals(other);
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.toString());
        buffer.append("elevation=");
        buffer.append(Double.toString(this.mElevation));
        buffer.append("m");
        return buffer.toString();
    }
}