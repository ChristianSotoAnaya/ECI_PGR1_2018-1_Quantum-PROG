/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class Complex {
    
    private int real;
    private int imaginaria;

    public int getReal() {
        return real;
    }

    public int getImaginaria() {
        return imaginaria;
    }
    
    
    public Complex(int real, int imaginaria){
        this.real=real;
        this.imaginaria=imaginaria;
    }
    
    
    public Complex sumComplex(Complex c){
        Complex ans = new Complex(this.real+c.getReal(),this.imaginaria+c.getImaginaria());
        return ans;
    }
    
       
}
