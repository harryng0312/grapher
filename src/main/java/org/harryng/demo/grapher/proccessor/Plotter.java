/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.proccessor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hiep
 */
public class Plotter {

    protected Point axistRootVector = new Point();
    protected static int BAR_DEVIDE_HEIGHT = 4;
    protected static double oneUnit = 50;
    protected boolean antiAliasing = true;

    protected void drawAxis(BufferedImage img, Point center, int barDevide) {
        Graphics g = img.getGraphics();
        Color bak = g.getColor();
        
        double x = axistRootVector.getX() + center.getX();
        double y = -axistRootVector.getY() + center.getY(); 
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.setColor(Color.BLACK);
        g.drawLine((int) x, 0, (int) x, img.getHeight());
        g.drawLine(0, (int) y, img.getWidth(), (int) y);
        double sinNarrow = Math.sin(Math.PI / 12);
        double cosNarrow = Math.cos(Math.PI / 12);
        double lenNarrow = 20;
        g.drawLine((int) x, 0, (int) (x + sinNarrow * lenNarrow), (int) (cosNarrow * lenNarrow));
        g.drawLine((int) x, 0, (int) (x - sinNarrow * lenNarrow), (int) (cosNarrow * lenNarrow));
        g.drawLine(img.getWidth(), (int) y,
                (int) (img.getWidth() - cosNarrow * lenNarrow),
                (int) (y - sinNarrow * lenNarrow));
        g.drawLine(img.getWidth(), (int) y,
                (int) (img.getWidth() - cosNarrow * lenNarrow),
                (int) (y + sinNarrow * lenNarrow));

        for (int i = (int) x; i < img.getWidth() - cosNarrow * lenNarrow; i += barDevide) {
            g.drawLine(i, (int) y - BAR_DEVIDE_HEIGHT / 2, i, (int) y + BAR_DEVIDE_HEIGHT / 2);
        }
        for (int i = (int) x; i > 0; i -= barDevide) {
            g.drawLine(i, (int) y - BAR_DEVIDE_HEIGHT / 2, i, (int) y + BAR_DEVIDE_HEIGHT / 2);
        }
        for (int i = (int) y; i < img.getHeight() - cosNarrow * lenNarrow; i += barDevide) {
            g.drawLine((int) x - BAR_DEVIDE_HEIGHT / 2, i, (int) x + BAR_DEVIDE_HEIGHT / 2, i);
        }
        for (int i = (int) y; i > 0; i -= barDevide) {
            g.drawLine((int) x - BAR_DEVIDE_HEIGHT / 2, i, (int) x + BAR_DEVIDE_HEIGHT / 2, i);
        }

        g.setColor(bak);
    }

    protected Double[] convertToAxis(double x, double y, Point center) {
        Double[] result = new Double[2];
        result[0] = (x * oneUnit + axistRootVector.getX() + center.getX());
        result[1] = -(y * oneUnit + axistRootVector.getY() - center.getY());
        return result;
    }

    protected Double[] convertFromAxis(double x, double y, Point center) {
        Double[] result = new Double[2];
        result[0] = (x - axistRootVector.getX() - center.getX()) / oneUnit;
        result[1] = -(y - axistRootVector.getY() + center.getY()) / oneUnit;
        return result;
    }

    public Image drawFunctionGraph(BufferedImage img, String expression,
            int width, int height, Point center, Integer barDevide, Color color) {
        Graphics g = img.getGraphics();
        Color bak = g.getColor();
        g.setColor(color);

        Proccessor pro = new Proccessor();
        pro.setExpression(expression, "x");
        Map<String, Double> var = new HashMap<String, Double>();
        List<LagrangePointSet> setPoint = new ArrayList<LagrangePointSet>(img.getWidth() + 1);
        float step = 0.1f;
        if (isAntiAliasing()) {
            step = 1.0f;
        }
        for (double i = 0; i <= img.getWidth(); i += step) {
            Double valX = i;
            Double valY = 0.0;
            Double tmp[] = convertFromAxis(valX, valY, center);
            valX = tmp[0];
            var.put("x", valX);
            valY = pro.calculate(var);
            tmp[1] = valY;

            Double[] rs = tmp;
            LagrangePointSet firstP = null;
            firstP = new LagrangePointSet(null, rs, null);

            setPoint.add(firstP);

        }
        int size = setPoint.size();
        Double[] tmp = null;//new Double[2];
        //Double[] pTmp = null;
        Double[] nTmp = null;

        for (int i = 0; i < size - 1; i++) {
            LagrangePointSet p = setPoint.get(i);
//            LagrangePointSet prevP = setPoint.get(i - 1);
            LagrangePointSet nextP = setPoint.get(i + 1);
            //ve p
            tmp = convertToAxis(p.curPoint[0], p.curPoint[1], center);
            nTmp = convertToAxis(nextP.curPoint[0], nextP.curPoint[1], center);
            //pTmp = convertToAxis(prevP.curPoint[0], prevP.curPoint[1]);
            Double f = (nextP.curPoint[1] - p.curPoint[1])
                    / (nextP.curPoint[0] - p.curPoint[0]);
            //double maxF= p.curPoint[1] - prevP.curPoint[1];
//            Double[] valN = convertFromAxis(nTmp[0], nTmp[1], center);
//            Double[] val = convertFromAxis(tmp[0], tmp[1], center);
            Double signed = Math.abs(nTmp[1] - tmp[1]);
            if (!p.curPoint[1].isInfinite() && !p.curPoint[1].isNaN() 
                    && signed < img.getHeight()) {
                if (isAntiAliasing()) {
//                    && (tmp[0] <= nTmp[0] && tmp[0] >= pTmp[0])
//                    && (tmp[1] <= nTmp[1] && tmp[1] >= pTmp[1])) {
//                    && (nTmp[0] <= img.getWidth()&& nTmp[0]>=0 )
//                    && (nTmp[1]<=img.getHeight() && nTmp[1] >=0)
//                    && (pTmp[0]<=img.getWidth() && pTmp[0] >=0)
//                    && (pTmp[1]<=img.getHeight() && pTmp[1] >=0) ) {
                    g.drawLine((int) Math.round(tmp[0]), (int) Math.round(tmp[1] + 0.55 * f),
                            (int) Math.round(tmp[0]), (int) Math.round(tmp[1] - 0.7 * f));
                } else {
                    g.drawLine((int) Math.round(tmp[0]), (int) Math.round(tmp[1]),
                            (int) Math.round(tmp[0]), (int) Math.round(tmp[1]));
                }
            }
        }
        g.setColor(bak);
        return img;
    }

    public Image drawFunctionGraph(String expression,
            int width, int height, Point center, Integer barDevide, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        axistRootVector.setLocation(img.getWidth() / 2, -img.getHeight() / 2);
        oneUnit = barDevide;
        drawAxis(img, center, barDevide);
        if (expression.trim().length() == 0) {
            return img;
        }
        return drawFunctionGraph(img, expression, width, height, center, barDevide, color);
    }

    /**
     * @return the antiAlizing
     */
    public boolean isAntiAliasing() {
        return antiAliasing;
    }

    /**
     * @param antiAliasing the antiAlizing to set
     */
    public void setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }
}
