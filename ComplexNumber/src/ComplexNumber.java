/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class ComplexNumber {

    private final double real;
    private final double imaginaria;
    private final double modulus;
    private final double theta;

    public double getReal() {
        return real;
    }

    public double getImaginaria() {
        return imaginaria;
    }

    public double getModulus() {
        return modulus;
    }

    public double getThetaDegrees() {
        return Math.toDegrees(theta);
    }

    public double getThetaRadians() {
        return theta;
    }

    public ComplexNumber(double real, double imaginaria) {
        this.real = real;
        this.imaginaria = imaginaria;
        this.modulus = Math.sqrt(real * real + imaginaria * imaginaria);
        this.theta = Math.atan((imaginaria / real));
    }

    @Override
    public String toString() {
        return "(" + real + " " + (imaginaria < 0 ? "-" : "+") + " " + imaginaria + "i)";
    }
}
