/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Quantum.ComplexNumber;
import Quantum.ComplexVectorSpaces;
import Quantum.ComplexCalculator;

/**
 *
 * @author Leonardo Andres
 */
public class Qft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    //Realizar la QFT sobre los estados entrantes
    public ComplexNumber[][] transform(ComplexNumber[] states){
        ComplexNumber[][] Hadamart = this.hadamardMatrix();
        
        return Hadamart;
    }
    
    //Crea una matriz de Hadamard
    public ComplexNumber[][] hadamardMatrix(){
        ComplexNumber[][] Hadamard = new ComplexNumber[2][2];
        Hadamard[0][0]=new ComplexNumber( 1/Math.sqrt(2) );
        Hadamard[0][1]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][0]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][1]=new ComplexNumber(-(1/Math.sqrt(2)));
        return Hadamard;
    }

    //Crea una Matriz de cambio de fase (R) con el valor necesario
    public ComplexNumber[][] faseChange(int exponent){
        ComplexNumber constant = new ComplexNumber(0,2*Math.PI);
        ComplexNumber expBelow = new ComplexNumber(Math.pow(2, exponent));
        ComplexNumber finalExp = ComplexCalculator.complexDivision(constant,expBelow);
        ComplexNumber Euler = new ComplexNumber(Math.exp(1));   
        ComplexNumber[][] Fase = new ComplexNumber[2][2];
        Fase[0][0] = new ComplexNumber(1);
        Fase[1][0] = new ComplexNumber(0);
        Fase[0][1] = new ComplexNumber(0);
        Fase[1][1] = ComplexCalculator.complexPowerComplex(Euler, finalExp);

        return Fase;
    }

    
}
