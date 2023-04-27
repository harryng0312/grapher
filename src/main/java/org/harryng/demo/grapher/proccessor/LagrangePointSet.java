/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.proccessor;

import java.awt.geom.Point2D;

/**
 *
 * @author hiep
 */
public class LagrangePointSet {

    protected Double[] curPoint;
    protected Double[] prevPoint;
    protected Double[] nextPoint;

    public LagrangePointSet() {
    }

    public LagrangePointSet(Double[] prevPoint, Double[] curPoint, Double[] nextPoint) {
        this.curPoint = curPoint;
        this.prevPoint = prevPoint;
        this.nextPoint = nextPoint;
    }

    /**
     * @return the curPoint
     */
    public Double[] getCurPoint() {
        return curPoint;
    }

    /**
     * @param curPoint the curPoint to set
     */
    public void setCurPoint(Double[] curPoint) {
        this.curPoint = curPoint;
    }

    /**
     * @return the prevPoint
     */
    public Double[] getPrevPoint() {
        return prevPoint;
    }

    /**
     * @param prevPoint the prevPoint to set
     */
    public void setPrevPoint(Double[] prevPoint) {
        this.prevPoint = prevPoint;
    }

    /**
     * @return the nextPoint
     */
    public Double[] getNextPoint() {
        return nextPoint;
    }

    /**
     * @param nextPoint the nextPoint to set
     */
    public void setNextPoint(Double[] nextPoint) {
        this.nextPoint = nextPoint;
    }
}
