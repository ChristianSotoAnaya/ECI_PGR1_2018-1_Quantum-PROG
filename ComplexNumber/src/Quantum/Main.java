package Quantum;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xbran
 */

import java.lang.Math;
import java.util.Arrays;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     ComplexNumber[] state = new ComplexNumber[2];
     state[0]=new ComplexNumber(0,-0.383);
     state[1]=new ComplexNumber(0.924);
     
     ComplexNumber[][] observable = new ComplexNumber[2][2]  ;
     observable[0][0]=new ComplexNumber(-1);
     observable[0][1]=new ComplexNumber(0,-1);
     observable[1][0]=new ComplexNumber(0,1);
     observable[1][1]=new ComplexNumber(1);
     
    System.out.println(Arrays.toString(ComplexVectorSpaces.complexMatrixVectorMultiplication(observable, state)));
    
        
    System.out.println(Arrays.toString(ComplexVectorSpaces.complexVectorScalarMultiplication(state, new ComplexNumber(Math.sqrt(2)))));

    }
}
