/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.proccessor;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hiep.nq
 */
public class ExpressionQueueElement {

    public static int OPERAND = 1;
    public static int OPERATOR = 2;
    public static int VARIABLE = 3;
    public static int PARENTTHESE = 4;
    public static String OPE_PLUS = "+";
    public static String OPE_SUBTRAC = "-";
    public static String OPE_MULTIPLY = "*";
    public static String OPE_DEVIDE = "/";
    public static String OPE_MOD = "%";
    public static String OPE_REACTOR = "^";
    public static String REC_SIN = "sin";
    public static String REC_COS = "cos";
    public static String REC_TAN = "tan";
    public static String REC_COT = "cot";
    public static String OPE_ABS = "abs";
    public static String REC_ARCSIN = "arcsin";
    public static String REC_ARCCOS = "arccos";
    public static String REC_ARCTAN = "arctan";
    public static String OPE_SQRT = "sqrt";
    public static Map<String, Integer> mpPriority = new HashMap<String, Integer>();

    static {
        mpPriority.put("+", 1);
        mpPriority.put("-", 1);
        mpPriority.put("*", 2);
        mpPriority.put("/", 2);
        mpPriority.put("%", 2);
        mpPriority.put(REC_SIN, 4);
        mpPriority.put(REC_COS, 4);
        mpPriority.put(REC_TAN, 4);
        mpPriority.put(REC_COT, 4);
        mpPriority.put("^", 5);
//        mpPriority.put("(", 10);
//        mpPriority.put(")", 10);
    }
    protected int type;
    protected Object value;

    public ExpressionQueueElement(Object value, int type) {
        this.value = value;
        this.type = type;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
