/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.proccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 *
 * @author hiep.nq
 */
public class Proccessor {

    protected static String patternNum = "(_)|([0-9]+(\\.)?[0-9]*)|(_?[%s])";
    protected static String patternCharNum = "([0-9])|(\\.)";
    protected static String pattermOperator = "[\\+\\-\\*/\\^\\%%]|(s)|(c)|(t)|(g)";
    protected static String patternParenthese = "\\(|\\)";
    protected static String rectangleId = "1234";
    protected static String pattermNumber = "(-)?([0-9]+(\\.[0-9]+)?)";
    private String expression;
    protected List<ExpressionQueueElement> expressionLs = new ArrayList<ExpressionQueueElement>();
    protected Stack<ExpressionQueueElement> sufficExpression = null;
//    protected static Map<String, String> mapFunc = new Hashtable<String, String>();

    static {
//        mapFunc.put("1", ExpressionQueueElement.REC_SIN);
//        mapFunc.put("2", ExpressionQueueElement.REC_COS);
//        mapFunc.put("3", ExpressionQueueElement.REC_TAN);
//        mapFunc.put("4", ExpressionQueueElement.REC_COT);
    }

    public void setExpression(String express, String varNames) {
        express = express.replace(" ", "").toLowerCase();
//        express = express.replaceAll(ExpressionQueueElement.REC_COS, "c");
//        express = express.replaceAll(ExpressionQueueElement.REC_SIN, "s");
//        express = express.replaceAll(ExpressionQueueElement.REC_TAN, "t");
//        express = express.replaceAll(ExpressionQueueElement.REC_COT, "g");
        if (express.length() > 0) {
            //List<ExpressionQueueElement> rs = new LinkedList<ExpressionQueueElement>();
            main:
            for (int i = 0; i < express.length(); i++) {
                String curChar = express.substring(i, i + 1);
                //so hoac .
                if (Pattern.matches(patternCharNum, curChar)) {
                    StringBuffer cur = new StringBuffer(curChar);
                    int j = 0;
                    for (j = i + 1; j < express.length(); j++) {
                        if (Pattern.matches(patternCharNum, express.subSequence(j, j + 1))
                                || curChar.equals(".")) {
                            cur.append(express.substring(j, j + 1));
                        } else {
                            break;
                        }
                    }
                    i = j - 1;
                    expressionLs.add(new ExpressionQueueElement(Double.parseDouble(cur.toString()),
                            ExpressionQueueElement.OPERAND));
                }//la ( )            
                else if (Pattern.matches(patternParenthese, curChar)) {
                    expressionLs.add(new ExpressionQueueElement(curChar,
                            ExpressionQueueElement.PARENTTHESE));
                }//la toan tu
                else if (Pattern.matches(pattermOperator, curChar)
                        || curChar.equals("s") || curChar.equals("c")
                        || curChar.equals("t") || curChar.equals("a")) {
                    boolean isMinus = "-".equals(curChar)
                            && ((i == 0)
                            || Pattern.matches(pattermOperator,
                            expressionLs.get(expressionLs.size() - 1).getValue().toString())
                            || "(".equals(expressionLs.get(expressionLs.size() - 1).getValue().toString()));

                    ExpressionQueueElement exQueElem =
                            new ExpressionQueueElement(curChar, ExpressionQueueElement.OPERATOR);
                    if (isMinus) {
                        exQueElem.setValue("_");
                    } else if ("s".equals(curChar)
                            || "c".equals(curChar)
                            || "t".equals(curChar)
                            || "a".equals(curChar)) {
                        String func = null;
                        StringBuffer strB = new StringBuffer(curChar);
                        if (curChar.equals("s")) {
                            for (int j = i + 1; j < express.length() && j < i + 10; j++) {
                                strB.append(express.charAt(j));
                                if (strB.toString().equals(ExpressionQueueElement.REC_SIN)) {
                                    func = ExpressionQueueElement.REC_SIN;
                                    i = j;
                                    break;
                                } else if (strB.toString().equals(ExpressionQueueElement.OPE_SQRT)) {
                                    func = ExpressionQueueElement.OPE_SQRT;
                                    i = j;
                                    break;
                                }
                            }

                        } else if (curChar.equals("c")) {
                            for (int j = i + 1; j < express.length() && j < i + 10; j++) {
                                strB.append(express.charAt(j));
                                if (strB.toString().equals(ExpressionQueueElement.REC_COS)) {
                                    func = ExpressionQueueElement.REC_COS;
                                    i = j;
                                    break;
                                } else if (strB.toString().equals(ExpressionQueueElement.REC_COT)) {
                                    func = ExpressionQueueElement.REC_COT;
                                    i = j;
                                    break;
                                }
                            }

                        } else if (curChar.equals("t")) {
                            for (int j = i + 1; j < express.length() && j < i + 10; j++) {
                                strB.append(express.charAt(j));
                                if (strB.toString().equals(ExpressionQueueElement.REC_TAN)) {
                                    func = ExpressionQueueElement.REC_TAN;
                                    i = j;
                                    break;
                                }
                            }
                        } else if (curChar.equals("a")) {
                            for (int j = i + 1; j < express.length() && j < i + 10; j++) {
                                strB.append(express.charAt(j));
                                if (strB.toString().equals(ExpressionQueueElement.OPE_ABS)) {
                                    func = ExpressionQueueElement.OPE_ABS;
                                    i = j;
                                    break;
                                } else if (strB.toString().equals(ExpressionQueueElement.REC_ARCSIN)) {
                                    func = ExpressionQueueElement.REC_ARCSIN;
                                    i = j;
                                    break;
                                } else if (strB.toString().equals(ExpressionQueueElement.REC_ARCCOS)) {
                                    func = ExpressionQueueElement.REC_ARCCOS;
                                    i = j;
                                    break;
                                } else if (strB.toString().equals(ExpressionQueueElement.REC_ARCTAN)) {
                                    func = ExpressionQueueElement.REC_ARCTAN;
                                    i = j;
                                    break;
                                }
                            }
                        }
                        exQueElem.setValue(func);
                    }
                    expressionLs.add(exQueElem);
                }//la bien
                else if (varNames.contains(curChar)) {
                    expressionLs.add(new ExpressionQueueElement(curChar,
                            ExpressionQueueElement.VARIABLE));
                }
            }//end for
            for (int i = 0; i < expressionLs.size() - 1; i++) {
                ExpressionQueueElement elem = expressionLs.get(i);
                if (elem.getValue().equals("_")) {
                    ExpressionQueueElement nextElem = expressionLs.get(i + 1);
                    if (nextElem.getType() == ExpressionQueueElement.OPERAND) {
                        nextElem.setValue(-1 * Double.parseDouble(nextElem.getValue().toString()));
                        expressionLs.remove(elem);
                    }
                    if (nextElem.getType() == ExpressionQueueElement.VARIABLE
                            || nextElem.getType() == ExpressionQueueElement.PARENTTHESE
                            || nextElem.getType() == ExpressionQueueElement.OPERATOR ) {
//                        elem.setValue(-1.0d);
//                        elem.setValue(ExpressionQueueElement.OPERAND);
                        expressionLs.add(i + 1, new ExpressionQueueElement(-1.0, ExpressionQueueElement.OPERAND));
                        expressionLs.add(i + 2, new ExpressionQueueElement("*",
                                ExpressionQueueElement.OPERATOR));
                        expressionLs.remove(elem);
                    }
                }
            }

//            StringBuffer strB = new StringBuffer();
//            for (ExpressionQueueElement elem : expressionLs) {
//                strB.append(elem.getValue()).append(" ");
//            }
//            System.out.println(strB.toString());
        }
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    protected Stack<ExpressionQueueElement> getSuffixNotation() {
        Stack<ExpressionQueueElement> p = new Stack<ExpressionQueueElement>();
        Stack<ExpressionQueueElement> q = new Stack<ExpressionQueueElement>();

        for (ExpressionQueueElement elem : this.expressionLs) {
            if (elem.getType() == ExpressionQueueElement.OPERAND
                    || elem.getType() == ExpressionQueueElement.VARIABLE) {
                p.push(elem);
            } else if ((elem.getType() == ExpressionQueueElement.PARENTTHESE
                    && elem.getValue().equals("("))) {
                q.push(elem);
            } else if ((elem.getType() == ExpressionQueueElement.PARENTTHESE
                    && elem.getValue().equals(")"))) {
                while (!q.empty()) {
                    ExpressionQueueElement ele = q.pop();
                    if (ele.getType() == ExpressionQueueElement.OPERATOR) {
                        p.push(ele);
                    }
                    if (ele.getValue().equals("(")) {
                        break;
                    }
                }
            } else if (elem.getType() == ExpressionQueueElement.OPERATOR) {
                while (!q.empty()) {
                    //lấy thử đỉnh;
                    ExpressionQueueElement ele = q.peek();
                    Integer elemPri = ExpressionQueueElement.mpPriority.get(elem.getValue());
                    Integer topPri = ExpressionQueueElement.mpPriority.get(ele.getValue());
                    //nếu là ngoặc ( - chọ vào
                    if (ele.getType() == ExpressionQueueElement.PARENTTHESE
                            && ele.getValue().equals("(")) {
                        break;
                    } //nếu là toán tử ưu tiên cao hơn hoặc = 
                    //- lấy ra cho vào p tới khi cạn hoặc gặp toán thử thấp hơn thì break
                    else if (elemPri != null && topPri != null && elemPri <= topPri) {
                        p.push(q.pop());
                    } else {
                        break;
                    }
                }
                q.push(elem);
            }
        }
        while (!q.empty()) {
            p.push(q.pop());
        }
        return p;
    }

    public Double calculate(Map<String, Double> vars) {
        if (sufficExpression == null) {
            sufficExpression = getSuffixNotation();
        }
        Stack<ExpressionQueueElement> q = new Stack<ExpressionQueueElement>();
        double result = 0d;
        try {
            for (ExpressionQueueElement elem : sufficExpression) {
                if (elem.getType() == ExpressionQueueElement.OPERAND) {
                    q.push(elem);
                } else if (elem.getType() == ExpressionQueueElement.OPERATOR) {
                    ExpressionQueueElement firstOpe = q.pop();
                    ExpressionQueueElement secondOpe = null;//q.pop();
                    double ope1 = Double.parseDouble(firstOpe.getValue().toString());
                    double ope2 = 0;
                    double rs = 0;
                    if (elem.getValue().equals(ExpressionQueueElement.OPE_PLUS)) {
                        secondOpe = q.pop();
                        ope2 = Double.parseDouble(secondOpe.getValue().toString());
                        rs = ope2 + ope1;
                    } else if (elem.getValue().equals(ExpressionQueueElement.OPE_SUBTRAC)) {
                        secondOpe = q.pop();
                        ope2 = Double.parseDouble(secondOpe.getValue().toString());
                        rs = ope2 - ope1;
                    } else if (elem.getValue().equals(ExpressionQueueElement.OPE_MULTIPLY)) {
                        secondOpe = q.pop();
                        ope2 = Double.parseDouble(secondOpe.getValue().toString());
                        rs = ope2 * ope1;
                    } else if (elem.getValue().equals(ExpressionQueueElement.OPE_DEVIDE)) {
                        secondOpe = q.pop();
                        ope2 = Double.parseDouble(secondOpe.getValue().toString());
                        rs = ope2 / ope1;
                    } else if (elem.getValue().equals(ExpressionQueueElement.OPE_MOD)) {
                        secondOpe = q.pop();
                        ope2 = Double.parseDouble(secondOpe.getValue().toString());
                        rs = ope2 % ope1;
                    } else if (elem.getValue().equals(ExpressionQueueElement.OPE_REACTOR)) {
                        secondOpe = q.pop();
                        ope2 = Double.parseDouble(secondOpe.getValue().toString());
                        rs = Math.pow(ope2, ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.REC_SIN)) {
                        rs = Math.sin(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.REC_COS)) {
                        rs = Math.cos(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.REC_TAN)) {
                        rs = Math.tan(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.REC_COT)) {
                        rs = 1 / Math.tan(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.OPE_ABS)) {
                        rs = Math.abs(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.REC_ARCSIN)) {
                        rs = Math.asin(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.REC_ARCCOS)) {
                        rs = Math.acos(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.REC_ARCTAN)) {
                        rs = Math.atan(ope1);
                    } else if (elem.getValue().equals(ExpressionQueueElement.OPE_SQRT)) {
                        rs = Math.sqrt(ope1);
                    }
                    q.push(new ExpressionQueueElement(rs, ExpressionQueueElement.OPERAND));
                } else if (elem.getType() == ExpressionQueueElement.VARIABLE) {
                    if (vars.containsKey(elem.getValue())) {
                        Double varVal = vars.get(elem.getValue());
                        q.push(new ExpressionQueueElement(varVal, ExpressionQueueElement.OPERAND));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Double.NaN;
        }
        result = Double.parseDouble(q.pop().getValue().toString());
        return result;
    }
}
