/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Quantum.ComplexVectorSpaces;
import Quantum.ComplexNumber;
import java.util.Arrays;
import java.lang.Math;
import java.util.Arrays;

/**
 *
 * @author USER
 */
public class Deutsch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ComplexNumber[] topQubit;
        ComplexNumber[] botQubit;
        ComplexNumber[] tensorQubit;
        ComplexNumber[][] uf;
        ComplexNumber[][] hadamard;
        ComplexNumber[][] tensorHadamard;
        ComplexNumber[][] identidad;
        ComplexNumber[][] tensorIdentidad;
        
        // creacion de los elementos
        topQubit=createTopQubit();
        botQubit=createBotQubit();
        hadamard = createHadamard();
        identidad = createIdentidad();
        uf=uf(0); // [0: 0->1 ; 1->0] | [1: 0->0 ; 1->1] | [2: 0->1 ; 1->1] | [3: 0->0 ; 1->0]
        
        // productos tensores
        tensorQubit = ComplexVectorSpaces.ComplexVectorTensorProduct(topQubit, botQubit);
        System.out.println("tensorQubits "+Arrays.toString(tensorQubit));
        tensorHadamard = ComplexVectorSpaces.tensorProduct(hadamard, hadamard);
        tensorIdentidad = ComplexVectorSpaces.tensorProduct(hadamard, identidad);
        
        //pasos del algoritmo        
        ComplexNumber[] result0 = ComplexVectorSpaces.complexMatrixVectorMultiplication(tensorHadamard, tensorQubit);
        System.out.println("Hadamard "+Arrays.toString(result0));
        ComplexNumber[] result1 = ComplexVectorSpaces.complexMatrixVectorMultiplication(uf, result0);
        System.out.println("Uf "+Arrays.toString(result1));
        ComplexNumber[] result2 = ComplexVectorSpaces.complexMatrixVectorMultiplication(tensorIdentidad, result1);
        System.out.println("Hadamard "+Arrays.toString(result2));
        
        
        measure();
    }
    public static ComplexNumber[] createTopQubit(){  
        ComplexNumber[] topQubit = new ComplexNumber[2];
        topQubit[0]= new ComplexNumber(1);
        topQubit[1]= new ComplexNumber(0);
        return topQubit;
    }
    public static ComplexNumber[] createBotQubit(){
        ComplexNumber[] botQubit = new ComplexNumber[2];
        botQubit[0]= new ComplexNumber(0);
        botQubit[1]= new ComplexNumber(1);
        return botQubit;
    }           
    public static ComplexNumber[][] createHadamard(){   
        ComplexNumber[][] Hadamard = new ComplexNumber[2][2];
        Hadamard[0][0]=new ComplexNumber( 1/Math.sqrt(2) );
        Hadamard[0][1]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][0]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][1]=new ComplexNumber(-(1/Math.sqrt(2)));
        return Hadamard;
    }
    public static ComplexNumber[][] createIdentidad(){
        ComplexNumber[][] identidad = new ComplexNumber[2][2];
        identidad[0][0]=new ComplexNumber(1);
        identidad[0][1]=new ComplexNumber(0);
        identidad[1][0]=new ComplexNumber(0);
        identidad[1][1]=new ComplexNumber(1);
        return identidad;
    }
    public static ComplexNumber[][] uf(int i){    
        ComplexNumber[][] uf = new ComplexNumber[4][4];
        if (i==0){// 0->1 ; 1->0
            //fila 1
            uf[0][0] = new ComplexNumber(0);
            uf[0][1] = new ComplexNumber(1);
            uf[0][2] = new ComplexNumber(0);
            uf[0][3] = new ComplexNumber(0);
            //fila 2
            uf[1][0] = new ComplexNumber(1);
            uf[1][1] = new ComplexNumber(0);
            uf[1][2] = new ComplexNumber(0);
            uf[1][3] = new ComplexNumber(0);
            //fila 3
            uf[2][0] = new ComplexNumber(0);
            uf[2][1] = new ComplexNumber(0);
            uf[2][2] = new ComplexNumber(1);
            uf[2][3] = new ComplexNumber(0);
            //fila 4
            uf[3][0] = new ComplexNumber(0);
            uf[3][1] = new ComplexNumber(0);
            uf[3][2] = new ComplexNumber(0);
            uf[3][3] = new ComplexNumber(1);
        }
        else if(i==1){// 0->0 ; 1->1
            //fila 1
            uf[0][0] = new ComplexNumber(1);
            uf[0][1] = new ComplexNumber(0);
            uf[0][2] = new ComplexNumber(0);
            uf[0][3] = new ComplexNumber(0);
            //fila 2
            uf[1][0] = new ComplexNumber(0);
            uf[1][1] = new ComplexNumber(1);
            uf[1][2] = new ComplexNumber(0);
            uf[1][3] = new ComplexNumber(0);
            //fila 3
            uf[2][0] = new ComplexNumber(0);
            uf[2][1] = new ComplexNumber(0);
            uf[2][2] = new ComplexNumber(0);
            uf[2][3] = new ComplexNumber(1);
            //fila 4
            uf[3][0] = new ComplexNumber(0);
            uf[3][1] = new ComplexNumber(0);
            uf[3][2] = new ComplexNumber(1);
            uf[3][3] = new ComplexNumber(0);
        }
        else if (i==2){// 0->1 ; 1->1
            //fila 1
            uf[0][0] = new ComplexNumber(0);
            uf[0][1] = new ComplexNumber(1);
            uf[0][2] = new ComplexNumber(0);
            uf[0][3] = new ComplexNumber(0);
            //fila 2
            uf[1][0] = new ComplexNumber(1);
            uf[1][1] = new ComplexNumber(0);
            uf[1][2] = new ComplexNumber(0);
            uf[1][3] = new ComplexNumber(0);
            //fila 3
            uf[2][0] = new ComplexNumber(0);
            uf[2][1] = new ComplexNumber(0);
            uf[2][2] = new ComplexNumber(0);
            uf[2][3] = new ComplexNumber(1);
            //fila 4
            uf[3][0] = new ComplexNumber(0);
            uf[3][1] = new ComplexNumber(0);
            uf[3][2] = new ComplexNumber(1);
            uf[3][3] = new ComplexNumber(0);
        }
        else if(i==3){// 0->0 ; 1->0
            //fila 1
            uf[0][0] = new ComplexNumber(1);
            uf[0][1] = new ComplexNumber(0);
            uf[0][2] = new ComplexNumber(0);
            uf[0][3] = new ComplexNumber(0);
            //fila 2
            uf[1][0] = new ComplexNumber(0);
            uf[1][1] = new ComplexNumber(1);
            uf[1][2] = new ComplexNumber(0);
            uf[1][3] = new ComplexNumber(0);
            //fila 3
            uf[2][0] = new ComplexNumber(0);
            uf[2][1] = new ComplexNumber(0);
            uf[2][2] = new ComplexNumber(1);
            uf[2][3] = new ComplexNumber(0);
            //fila 4
            uf[3][0] = new ComplexNumber(0);
            uf[3][1] = new ComplexNumber(0);
            uf[3][2] = new ComplexNumber(0);
            uf[3][3] = new ComplexNumber(1);            
        }
        
        return uf;
    }
    public static void measure(){        
    }
    
    
}
