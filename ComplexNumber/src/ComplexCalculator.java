/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rami
 */
public class ComplexCalculator {

    public static ComplexNumber complexSum(ComplexNumber c1, ComplexNumber c2) {
        double a = c1.getReal() + c2.getReal();
        double b = c1.getImaginaria() + c2.getImaginaria();
        return new ComplexNumber(a, b);
    }

    public static ComplexNumber complexSubtraction(ComplexNumber c1, ComplexNumber c2) {
        double a = c1.getReal() - c2.getReal();
        double b = c1.getImaginaria() - c2.getImaginaria();
        return new ComplexNumber(a, b);
    }

    public static ComplexNumber complexMultiplication(ComplexNumber c1, ComplexNumber c2) {
        double a = c1.getReal() * c2.getReal() - c1.getImaginaria() * c2.getImaginaria();
        double b = c1.getReal() * c2.getImaginaria() + c1.getImaginaria() * c2.getReal();
        return new ComplexNumber(a, b);
    }

    public static ComplexNumber complexDivision(ComplexNumber c1, ComplexNumber c2) {
        double div = c2.getReal() * c2.getReal() + c2.getImaginaria() * c2.getImaginaria();
        double a = (c1.getReal() * c2.getReal() + c1.getImaginaria() + c2.getImaginaria()) / div;
        double b = (c2.getReal() * c1.getImaginaria() - c1.getReal() * c2.getImaginaria()) / div;
        return new ComplexNumber(a, b);
    }

    public static ComplexNumber getConjugate(ComplexNumber c) {
        double a = c.getReal();
        double b = (-1) * c.getImaginaria();
        return new ComplexNumber(a, b);
    }
}
