/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Quantum.ComplexNumber;
import Quantum.ComplexVectorSpaces;
import Quantum.ComplexCalculator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Andres
 */
public class Qft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creación de 2 qubits para pruebas de QFT
        ComplexNumber[] QubitN1 = new ComplexNumber[2];
        QubitN1[0]= new ComplexNumber(1);
        QubitN1[1]= new ComplexNumber(0);
        
        ComplexNumber[] QubitN2 = new ComplexNumber[2];
        QubitN2[0]= new ComplexNumber(1);
        QubitN2[1]= new ComplexNumber(0);
        
        ComplexNumber[] CN = ComplexVectorSpaces.ComplexVectorTensorProduct(QubitN1, QubitN2);
        
        System.out.println("Antes de la transformada(ejemplo): " + CN[0].toString());
        
        ComplexNumber[] result = transform(CN);
        
        System.out.println("Despues de la transformada(ejemplo): " + result[0].toString());
    }

    //Realizar la QFT sobre los qubits entrantes
    public static ComplexNumber[] transform(ComplexNumber[] states){
        //Matriz de hadamart a utilizar
        ArrayList component;
        ComplexNumber[][] hadamart = hadamardMatrix();
        ComplexNumber[][] identity = identityMatrix();
        int qubits;
        Double lenght = Math.log(states.length)/Math.log(2);
        qubits = lenght.intValue();//Es el numero de qubits (2 numeros representan 1 qubit)
        ComplexNumber[] finalStates = new ComplexNumber[qubits];
        ArrayList<ComplexNumber[][]> fases = new ArrayList<ComplexNumber[][]>();
        if(qubits <=1){
            finalStates = ComplexVectorSpaces.complexMatrixVectorMultiplication(hadamart, states); 
        }
        else{
            //Tamaño de los vectores que van a representar el circuito cúantico
            int size = 1;
            for(int i=2; i<=qubits; i++){
                size+=i;
                fases.add(faseChange(i));
            }
            ArrayList circuit = new ArrayList<ArrayList<ComplexNumber[][]>>();
            component = new ArrayList<ComplexNumber[][]>();
            for(int j=0; j<size; j++){
                component.add(identity);
            }
            //Crear circuito lleno de matrices identidad
            for(int i=0; i<qubits; i++){
                circuit.add(component);
            }  
            //Remplazar matrices necesarias
            int cont =0;
            int faseChange =0;
            boolean flag;
            for(int i=0; i<circuit.size(); i++){
                ArrayList<ComplexNumber[][]> qb = (ArrayList<ComplexNumber[][]>) circuit.get(i);
                flag = false;
                for(int j=0; j<qb.size(); j++){
                    if(flag == false && j==cont){
                        qb.set(j, hadamart);
                        flag = true;
                        cont+=1;
                    }
                    else if(flag){
                        if(faseChange<fases.size()){
                            qb.set(j, fases.get(faseChange));
                            faseChange+=1;
                            cont+=1;
                        }
                        else{
                            faseChange=i+1;
                        }
                        
                    }
                }
                circuit.set(i, qb);
            }
            //El circuito ya esta formado, ahora falta multiplicar
            int orden = 0;
            ArrayList<ComplexNumber[][]> matriz = new ArrayList<ComplexNumber[][]>();
            for(int i=0; i<circuit.size(); i++){
                ArrayList<ComplexNumber[][]> qb = (ArrayList<ComplexNumber[][]>) circuit.get(i);
                for(int j=0; j<qb.size(); j++){
                    //en una matriz se van poniendo las matrices del circuito y se hace producto tensor de las que se necesitan
                    if(orden == 0){
                        matriz.add(qb.get(j));
                    }
                    else{
                        matriz.set(j,ComplexVectorSpaces.tensorProduct(matriz.get(j),qb.get(j)));
                    }
                }
                orden+=1;
            }
            //Matriz final que representa el circuito
            ComplexNumber[][] finalMatrix = matriz.get(matriz.size()-1);
            for(int i=matriz.size()-2; i>=0; i--){
                finalMatrix = ComplexVectorSpaces.complexMatrixMultiplication(finalMatrix, matriz.get(i));               
            }
            //Multiplicar los qubits por la matriz
            finalStates = ComplexVectorSpaces.complexMatrixVectorMultiplication(finalMatrix, states);
        }
        return finalStates;
    }
    
    //Crea una matriz de Hadamard
    public static ComplexNumber[][] hadamardMatrix(){
        ComplexNumber[][] Hadamard = new ComplexNumber[2][2];
        Hadamard[0][0]=new ComplexNumber( 1/Math.sqrt(2) );
        Hadamard[0][1]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][0]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][1]=new ComplexNumber(-(1/Math.sqrt(2)));
        return Hadamard;
    }
    
    public static ComplexNumber[][] identityMatrix(){
        ComplexNumber[][] identity = new ComplexNumber[2][2];
        identity[0][0]=new ComplexNumber( 1 );
        identity[0][1]=new ComplexNumber( 0 );
        identity[1][0]=new ComplexNumber( 0 );
        identity[1][1]=new ComplexNumber( 1 );
        return identity;
    }

    //Crea una Matriz de cambio de fase (R) con el valor necesario
    public static ComplexNumber[][] faseChange(int exponent){
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
