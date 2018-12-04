/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;


import Quantum.ComplexNumber;
import Quantum.ComplexVectorSpaces;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author USER
 */
public class ShorV2 {
    
    public static void main() {
        System.out.println("Algoritmo de Shor v2");
        shor(6);
    }
    
    
    /*
        Parametros: Shor factoriza un numero N
    */
    private static void shor(int N) {
        // "a" es el numero menor que N y coprimo con N .... esta quemado para N= 6 y 15
        int a = 5;if(N==15){a=13;}
        int n = (int) Math.ceil(Math.log10(N)/Math.log10(2) );
        int m = 2*n;
        System.out.println("N "+N+", m "+m+", n "+n);

        
        ComplexNumber[] topQubitString;
        ComplexNumber[] botQubitString;
        ComplexNumber[][] uf;           // NOTA: la matriz UF quedo transpuesta, como parece una matriz simetrica, no creo que halla problema
        ComplexNumber[][] tensorHadamard;
        ComplexNumber[][] tensorIdentidad;
        
         // creacion de los elementos
        topQubitString=createQubitString(m);
        botQubitString=createQubitString(n);
        tensorHadamard=createHadamard(m);
        tensorIdentidad=createIdentidad(n);
        uf = Ufan(a, N, n, m);
        
//        System.out.println(topQubitString.length+" T");
//        System.out.println(botQubitString.length+" B");
//        System.out.println(tensorHadamard.length+" H");
//        System.out.println(tensorIdentidad.length+" I");
//        System.out.println(uf.length+" Uf");
        
        // productos tensores
        ComplexNumber[] tensorQubitString = ComplexVectorSpaces.ComplexVectorTensorProduct(topQubitString,botQubitString);
        ComplexNumber[][] tensorHadamardIdentidad = ComplexVectorSpaces.tensorProduct(tensorHadamard, tensorIdentidad);
//  -->   falta QFT y medir

        //pasos del algoritmo
        ComplexNumber[] result0 = ComplexVectorSpaces.complexMatrixVectorMultiplication(tensorHadamardIdentidad, tensorQubitString);
        ComplexNumber[] result1 = ComplexVectorSpaces.complexMatrixVectorMultiplication(uf, result0);
//  -->   falta QFT y medir
        
        
        
    }
    public static ComplexNumber[] createQubitString(int m){  
        ArrayList<ComplexNumber[]> string = new ArrayList();
        for (int i=0; i<m;i++){
            ComplexNumber[] topQubit = new ComplexNumber[2];
            topQubit[0]= new ComplexNumber(1);
            topQubit[1]= new ComplexNumber(0);
            string.add(topQubit);
        }        
        ComplexNumber[]  tensorQubit = ComplexVectorSpaces.ComplexVectorTensorProduct(string.get(0), string.get(1));
        for (int j=2; j<m;j++){
            tensorQubit = ComplexVectorSpaces.ComplexVectorTensorProduct(tensorQubit, string.get(j));
            
        }
        return tensorQubit;
    }    
    
    public static ComplexNumber[][] createHadamard(int m){   
        ComplexNumber[][] Hadamard = new ComplexNumber[2][2];
        Hadamard[0][0]=new ComplexNumber( 1/Math.sqrt(2) );
        Hadamard[0][1]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][0]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][1]=new ComplexNumber(-(1/Math.sqrt(2)));
        
        ComplexNumber[][]  tensorHadamard = ComplexVectorSpaces.tensorProduct(Hadamard,Hadamard);
        for (int j=2; j<m;j++){
            tensorHadamard = ComplexVectorSpaces.tensorProduct(tensorHadamard, Hadamard);
        } 
        
        return tensorHadamard;
    }
    
    public static ComplexNumber[][] createIdentidad(int n){   
        ComplexNumber[][] Identidad = new ComplexNumber[2][2];
        Identidad[0][0]=new ComplexNumber( 1/Math.sqrt(2) );
        Identidad[0][1]=new ComplexNumber(1/Math.sqrt(2));
        Identidad[1][0]=new ComplexNumber(1/Math.sqrt(2));
        Identidad[1][1]=new ComplexNumber(-(1/Math.sqrt(2)));
        
        ComplexNumber[][]  tensorIdentidad = ComplexVectorSpaces.tensorProduct(Identidad,Identidad);
        for (int j=2; j<n;j++){
            tensorIdentidad = ComplexVectorSpaces.tensorProduct(tensorIdentidad, Identidad);
        } 
        
        return tensorIdentidad;
    }
    
    
    public static ComplexNumber[][] Ufan(int a,int N,int m, int n){
        ComplexNumber[][] UF = new ComplexNumber[(int)(Math.pow(2,m+n))][(int)Math.pow(2,m+n)];
        System.out.println("Uf mide: "+UF.length);
        for (int i=0;i<UF.length;i++){
            for (int j=0;j<UF[i].length;j++){
                UF[i][j]=  new ComplexNumber(YFx(a,N,i,j));
            }
        }
        
//        Integer[][] print = new Integer[(int)(Math.pow(2,m+n))][(int)Math.pow(2,m+n)];
//        for (int p=0;p<UF.length;p++){
//            for (int q=0;q<UF[p].length;q++){
//                print[p][q]=(int) UF[p][q].getReal();
//                if (((int) UF[p][q].getReal())==0){
//                print[p][q]=null;
//            }
//            }
//        }
//        
//        for (Integer[] asd:print){
//            System.out.println(Arrays.toString(asd));
//        }
        return UF;
    }
    public static int YFx(int a, int N, int i,int j){
        // el condicional mira que si entra X,Y salga X, (Y xor f(x))
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
                        
            // Fx es el resultado de FaN(x), una cadena de 3 Qubits "ej:000", para realizar el Xor entre FaN(x) y "Y"            
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


            // (Y XoR FaN(x))  --- >      (input  XoR Fx)
            for (int k=0;k<Fx.length();k++){
                if ( (Fx.charAt(k)==input.charAt(k))){
                    xor=xor.concat("0");
                }
                else{
                    xor=xor.concat("1");
                }
            }
            
            
            
            // InputJ es el indice que recorre la matriz horizontalmente, y se compara con la respuesta al final, para conocer donde poner el 1
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
    
        
}
