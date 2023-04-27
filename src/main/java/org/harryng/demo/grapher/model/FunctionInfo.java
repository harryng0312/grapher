/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.model;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author hiep
 */
public class FunctionInfo implements Serializable{
    protected String functionContent;
    protected Color color;
    protected Boolean drawed;

    public FunctionInfo(String functionContent, Color color, Boolean drawed){
        this.functionContent = functionContent;
        this.color = color;
        this.drawed = drawed;
    }
    
    /**
     * @return the functionContent
     */
    public String getFunctionContent() {
        return functionContent;
    }

    /**
     * @param functionContent the functionContent to set
     */
    public void setFunctionContent(String functionContent) {
        this.functionContent = functionContent;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the drawed
     */
    public Boolean isDrawed() {
        return drawed;
    }

    /**
     * @param drawed the drawed to set
     */
    public void setDrawed(Boolean drawed) {
        this.drawed = drawed;
    }
    
    
}
