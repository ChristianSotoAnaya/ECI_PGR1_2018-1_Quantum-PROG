/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Quantum.ComplexNumber;
import Quantum.ComplexVectorSpaces;
import java.util.Arrays;

/**
 *
 * @author USER
 */
public class Shor {
    public static void main(String[] args) {
        // TODO code application logic here
        ComplexNumber[] a= new ComplexNumber[3];
        ComplexNumber[] b= new ComplexNumber[3];
        a[0]= new ComplexNumber(1,2);
        a[1]= new ComplexNumber(-3,4);
        a[2]= new ComplexNumber(5,6);
        
        b[0]= new ComplexNumber(-2,1);
        b[1]= new ComplexNumber(4,3);
        b[2]= new ComplexNumber(6,5);
        
        shor(5, 6);
                
    }
    
    public static int YFx(int a, int N, int i,int j){
        // el condicional mira que si entra X,Y salga X,Yf(x)
        if ((i/8)==(j/8)){
            String xor = "";
            
            // Input es "Y", la cadena de 3 Cubits "ej:000", que son los 3 ultimos bits de "i"
            String input = Integer.toBinaryString(i);
            while (input.length()<3){
                input="0".concat(input);
            }
            if(input.length()>3){
                input=input.substring(input.length()-3);
            }
                        
            // Fx es el resultado de FaN(x), una cadena de 3 Qubits "ej:000", para realizar el Xor entre FaN(x) y Y            
            i=i/8;
            int resp = (int) (Math.pow(a, i)%N);
            String Fx = Integer.toBinaryString(resp);

            


            // Esto es para emparejar el numero de bits de las cadenas
            // por ejemplo, 1 = 001 
            //System.out.println(" input "+input+" fx "+Fx+ " resp "+resp);
            if(Fx.length()!= input.length()){
                if (Fx.length()>input.length()){
                    while(Fx.length()!=input.length()){
                        input = "0".concat(input);
                    }
                }
                if (Fx.length()<input.length()){
                    while(Fx.length()!=input.length()){
                        Fx = "0".concat(Fx);
                    }
                }
            }


            // Y XoR FaN(x)  --- >      input  XoR Fx
            for (int k=0;k<Fx.length();k++){
                if ( (Fx.charAt(k)==input.charAt(k))){
                    xor=xor.concat("0");
                }
                else{
                    xor=xor.concat("1");
                }
            }
            
            
            
            // InputJ es el indice que recorre la matriz horiuzontalmente, y se compara con la respuesta al final, para conocer donde poner el 1
            String inputJ = Integer.toBinaryString(j);
            while (inputJ.length()<3){
                inputJ="0".concat(inputJ);
            }
            if(inputJ.length()>3){
                inputJ=inputJ.substring(inputJ.length()-3);
            }
            if (inputJ.equals(xor)){
                return 1;
            }
            else{
                return 0;
            }
        }else{
            return 0;
        }
        

        
    }
    
    public static ComplexNumber[][] Ufan(int a,int N){
        int n = (int) Math.round( Math.log10(N)/Math.log10(2) );
        int m = 2*n;
        ComplexNumber[][] UF = new ComplexNumber[(int)(Math.pow(2,m+n))][(int)Math.pow(2,m+n)];
        System.out.println(UF.length);
        for (int i=0;i<UF.length;i++){
            for (int j=0;j<UF[i].length;j++){
                UF[i][j]=  new ComplexNumber(YFx(a,N,i,j));
            }
        }
        
//        for (Integer[] asd:UF){
//            System.out.println(Arrays.toString(asd));
//        }
        return UF;
    }
    
    
    public static void shor(int a,int N){
        System.out.println("SHOR ALGORITHM");
        int n = (int) Math.round( Math.log10(N)/Math.log10(2) );
        int m = 2*n;

// Creo los Qubits de la cadena n         
        ComplexNumber[] QubitN1 = new ComplexNumber[2];
        QubitN1[0]= new ComplexNumber(1);
        QubitN1[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitN2 = new ComplexNumber[2];
        QubitN2[0]= new ComplexNumber(1);
        QubitN2[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitN3 = new ComplexNumber[2];
        QubitN3[0]= new ComplexNumber(1);
        QubitN3[1]= new ComplexNumber(0);
        
        ComplexNumber[] CN = ComplexVectorSpaces.ComplexVectorTensorProduct(QubitN1, QubitN2);
        ComplexNumber[] Qn = ComplexVectorSpaces.ComplexVectorTensorProduct(CN, QubitN3);
        
// Creo los Qubits de la cadena m
        ComplexNumber[] QubitM1 = new ComplexNumber[2];
        QubitM1[0]= new ComplexNumber(1);
        QubitM1[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitM2 = new ComplexNumber[2];
        QubitM2[0]= new ComplexNumber(1);
        QubitM2[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitM3 = new ComplexNumber[2];
        QubitM3[0]= new ComplexNumber(1);
        QubitM3[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitM4 = new ComplexNumber[2];
        QubitM4[0]= new ComplexNumber(1);
        QubitM4[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitM5 = new ComplexNumber[2];
        QubitM5[0]= new ComplexNumber(1);
        QubitM5[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitM6 = new ComplexNumber[2];
        QubitM6[0]= new ComplexNumber(1);
        QubitM6[1]= new ComplexNumber(0);
        
        ComplexNumber[] C = ComplexVectorSpaces.ComplexVectorTensorProduct(QubitM1, QubitM2);
        C = ComplexVectorSpaces.ComplexVectorTensorProduct(C, QubitM3);
        C = ComplexVectorSpaces.ComplexVectorTensorProduct(C, QubitM4);
        C = ComplexVectorSpaces.ComplexVectorTensorProduct(C, QubitM5);
        ComplexNumber[] Qm = ComplexVectorSpaces.ComplexVectorTensorProduct(C,QubitM6);
        System.out.println("Norma de los Qubits 0m "+ComplexVectorSpaces.complexVectorNorm(Qm));
        System.out.println("Cadena de Qubits 0m "+Qm.length);
        

// producto tensor de las matrices de hadamard
        ComplexNumber[][] Hadamard = new ComplexNumber[2][2];
        Hadamard[0][0]=new ComplexNumber( 1/Math.sqrt(2) );
        Hadamard[0][1]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][0]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][1]=new ComplexNumber(-(1/Math.sqrt(2)));
        
        ComplexNumber[][] Hadamard2 = ComplexVectorSpaces.tensorProduct(Hadamard, Hadamard);
        ComplexNumber[][] Hadamard3 = ComplexVectorSpaces.tensorProduct(Hadamard, Hadamard2);
        ComplexNumber[][] Hadamard4 = ComplexVectorSpaces.tensorProduct(Hadamard, Hadamard3);
        ComplexNumber[][] Hadamard5 = ComplexVectorSpaces.tensorProduct(Hadamard, Hadamard4);
        ComplexNumber[][] HadamardTensor = ComplexVectorSpaces.tensorProduct(Hadamard, Hadamard5);

// poner en superposicion la cadena de Qubits (multiplicar el vector de quibits por la matriz de hadamar)
        ComplexNumber[] QubitsSuperposicion = ComplexVectorSpaces.complexMatrixVectorMultiplication(HadamardTensor, Qm);
        System.out.println("Norma de los QubitsSuperpuestos 0m "+ComplexVectorSpaces.complexVectorNorm(QubitsSuperposicion));
        System.out.println("Cadena de QubitsSuperpuestos 0m "+Arrays.toString(QubitsSuperposicion)+" "+QubitsSuperposicion.length);
        System.out.println("tamaño de Qubits superpuestos "+QubitsSuperposicion.length);
        

        ComplexNumber[][] UF = Ufan(a, N);

// como UF es de 512, se procede a hacer producto tensor entre la cadena 0m y 0n para emparejar el tamaño
        ComplexNumber[] Qtotal = ComplexVectorSpaces.ComplexVectorTensorProduct(Qn, QubitsSuperposicion);
        System.out.println("QTotal "+Qtotal.length);
        
        ComplexNumber[] UFaN = ComplexVectorSpaces.complexMatrixVectorMultiplication(UF, Qtotal);
        System.out.println("UFaN "+UFaN.length);
        
        
    }
}
