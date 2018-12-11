/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quantum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author brand
 */
public class QuantumCompilerMethods {
    
    public static ComplexNumber[] createQubit(String estado){
        ComplexNumber[] Qubit = new ComplexNumber[2];
        if (estado.equals(1)){
            Qubit[0]= new ComplexNumber(0);
            Qubit[1]= new ComplexNumber(1);
        }else{
            Qubit[0]= new ComplexNumber(1);
            Qubit[1]= new ComplexNumber(0);
        }
        System.out.println("Qubit Creado");
              
        return Qubit;
    }  
    
    public static ArrayList<ComplexNumber[]> createQubitRegister(String estados){
        ArrayList<ComplexNumber[]> register = new ArrayList<>();
        for (int i = 0; i<estados.length(); i++) {
            ComplexNumber[] qubit = createQubit(Character.toString(estados.charAt(i)));
            register.add(qubit);
        }
        return register;
    }  
    
    public static ArrayList<ComplexNumber[]> quantumConcat(ArrayList<ComplexNumber[]> r1, ArrayList<ComplexNumber[]> r2){
        ArrayList<ComplexNumber[]> rConcat = new ArrayList();
        for(ComplexNumber[] c1: r1){
            rConcat.add(c1);
        }
        for(ComplexNumber[] c2: r2){
            rConcat.add(c2);
        }
        
        return rConcat; 
    }
    
    public static ArrayList<? extends ComplexNumber[][]>  createHadamardMatrix(){ 
        ArrayList<ComplexNumber[][]> h = new ArrayList();
        ComplexNumber[][] Hadamard = new ComplexNumber[2][2];
        Hadamard[0][0]=new ComplexNumber( 1/Math.sqrt(2) );
        Hadamard[0][1]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][0]=new ComplexNumber(1/Math.sqrt(2));
        Hadamard[1][1]=new ComplexNumber(-(1/Math.sqrt(2)));
        h.add(Hadamard);
        return h;
    }
    
    public static ArrayList<? extends ComplexNumber[][]> createControlledNotMatrix(){   
        ArrayList<ComplexNumber[][]> h = new ArrayList();
       
    
        ComplexNumber[][] controlledNot = new ComplexNumber[4][4];
        controlledNot[0][0]=new ComplexNumber(1);
        controlledNot[0][1]=new ComplexNumber(0);
        controlledNot[0][2]=new ComplexNumber(0);
        controlledNot[0][3]=new ComplexNumber(0);
        controlledNot[1][0]=new ComplexNumber(0);
        controlledNot[1][1]=new ComplexNumber(1);
        controlledNot[1][2]=new ComplexNumber(0);
        controlledNot[1][3]=new ComplexNumber(0);   
        controlledNot[2][0]=new ComplexNumber(0);
        controlledNot[2][1]=new ComplexNumber(0);
        controlledNot[2][2]=new ComplexNumber(0);
        controlledNot[2][3]=new ComplexNumber(1); 
        controlledNot[3][0]=new ComplexNumber(0);
        controlledNot[3][1]=new ComplexNumber(0);
        controlledNot[3][2]=new ComplexNumber(1);
        controlledNot[3][3]=new ComplexNumber(0);
        h.add(controlledNot);
        return h;
    }
    
    public static ArrayList<? extends ComplexNumber[][]> createIdentityMatrix(){
        ArrayList<ComplexNumber[][]> h = new ArrayList();
        ComplexNumber[][] identidad = new ComplexNumber[2][2];
        identidad[0][0]=new ComplexNumber(1);
        identidad[0][1]=new ComplexNumber(0);
        identidad[1][0]=new ComplexNumber(0);
        identidad[1][1]=new ComplexNumber(1);
        h.add(identidad);
        return h;
    }
    
    
    public static ArrayList<?> tensorProduct (ArrayList<?>  complexMatrix,ArrayList<?> qubitRegister ){
        
        ArrayList<?> qubitTensor = null;
        ArrayList<ComplexNumber[][]> qubitTensorM = null;
        ArrayList<ComplexNumber[][]> qubitTensorM2 = null;
        ArrayList<ComplexNumber[]> qubitTensorM1 = null;
        ArrayList<ComplexNumber[]> qubitTensorM3 = null;
        ArrayList<ComplexNumber[]> qubitTensorM4 = null;
        //List<AGRSalvaguardasInforme> InformeFinal = SalvaguardasAGR.stream().map(x -> (AGRSalvaguardasInforme)x).collect(Collectors.toList());
        
        if(qubitRegister.get(0) instanceof ComplexNumber[][] && complexMatrix.get(0) instanceof ComplexNumber[][]){
            System.out.println("Tensor Product ComplexNumber[][]");
            qubitTensorM = new ArrayList();
            ArrayList<ComplexNumber[][]> qRegister = new ArrayList<>();
            ArrayList<ComplexNumber[][]> qRegister1 = new ArrayList<>();
            for(Object q: qubitRegister){
                ComplexNumber[][] temp = (ComplexNumber[][]) q;
                qRegister.add(temp);
            }
            for(Object q1: complexMatrix){
                ComplexNumber[][] temp1 = (ComplexNumber[][]) q1;
                qRegister1.add(temp1);
            }
            for(int j = 0; j < qRegister1.size() ; j++){
                qubitTensorM.add(ComplexVectorSpaces.tensorProduct(qRegister1.get(j), qRegister.get(j)));
            }  
        }
        else if (qubitRegister.get(0) instanceof ComplexNumber[] && complexMatrix.get(0) instanceof ComplexNumber[]) {
            System.out.println("Tensor Product ComplexNumber[]");
            qubitTensorM1 = new ArrayList();
            ArrayList<ComplexNumber[]> qRegister = new ArrayList<>();
            ArrayList<ComplexNumber[]> qRegister1 = new ArrayList<>();
            for(Object q: qubitRegister){
                ComplexNumber[] temp = (ComplexNumber[]) q;
                qRegister.add(temp);
            }
            for(Object q1: complexMatrix){
                ComplexNumber[] temp1 = (ComplexNumber[]) q1;
                qRegister1.add(temp1);
            }
            for(int j = 0; j < qRegister1.size() ; j++){
                qubitTensorM1.add(ComplexVectorSpaces.ComplexVectorTensorProduct(qRegister1.get(j), qRegister.get(j)));
            }  
        }
        else if (qubitRegister.get(0) instanceof ComplexNumber[] && complexMatrix.get(0) instanceof ComplexNumber[][]) {
            System.out.println("Tensor Product ComplexNumber[]");
            qubitTensorM4 = new ArrayList();
            ArrayList<ComplexNumber[]> qRegister = new ArrayList<>();
            ArrayList<ComplexNumber[][]> qRegister1 = new ArrayList<>();
            for(Object q: qubitRegister){
                ComplexNumber[] temp = (ComplexNumber[]) q;
                qRegister.add(temp);
            }
            for(Object q1: complexMatrix){
                ComplexNumber[][] temp1 = (ComplexNumber[][]) q1;
                qRegister1.add(temp1);
            }
            for(int j = 0; j < qRegister1.size() ; j++){
                qubitTensorM4.add(ComplexVectorSpaces.complexMatrixVectorMultiplication(qRegister1.get(j), qRegister.get(j)));
            }  
        }
        
        if(qubitTensorM != null){
            qubitTensor = qubitTensorM;
        }  
        else if(qubitTensorM1 != null){
            qubitTensor = qubitTensorM1;
        }
        else{
            qubitTensor = qubitTensorM4;
        }
        
        return qubitTensor;   
    }  
    /*
    public static ArrayList<ComplexNumber[]> tensorProduct(ArrayList<ComplexNumber[]> qubitRegister0, ArrayList<ComplexNumber[]> qubitRegister){
        System.out.println("Tensor Product Qubits");
        ArrayList<ComplexNumber[]> qubitTensor = new ArrayList();
        int register0 = qubitRegister0.size();
        int register1 = qubitRegister.size();
        if(register0 <= register1){
            for(int i=0; i<register1; i++){
                qubitTensor.add(ComplexVectorSpaces.ComplexVectorTensorProduct(qubitRegister0.get(i), qubitRegister.get(i)));              
            }
        }
        else{
            for(int i=0; i<register0; i++){
                qubitTensor.add(ComplexVectorSpaces.ComplexVectorTensorProduct(qubitRegister0.get(i), qubitRegister.get(i)));              
            }
        }     
        return qubitTensor;
    }*/
    
    public static ArrayList<ComplexNumber[]> quantumApply(ComplexNumber[][] gate, ArrayList<ComplexNumber[]> qubitRegister){       
            ArrayList<ComplexNumber[]> qubitGate = new ArrayList();            
            for(ComplexNumber[] q: qubitRegister){
                qubitGate.add(ComplexVectorSpaces.complexMatrixVectorMultiplication(gate, q));
            }  
        return qubitGate;
        
    }
    
     public static ArrayList<ComplexNumber[][]> tensorProduct(ComplexNumber[][] qubitRegister0,ComplexNumber[][] qubitRegister){
        ArrayList<ComplexNumber[][]> qubitTensor = new ArrayList();
        qubitTensor.add(ComplexVectorSpaces.tensorProduct(qubitRegister0, qubitRegister));
        return qubitTensor;
    }
    
    
    
    public static ArrayList<ComplexNumber[]> quantumInverse(ArrayList<ComplexNumber[]> qubitRegister0, ArrayList<ComplexNumber[]> qubitRegister){
        System.out.println("Tensor Product Qubits");
        ArrayList<ComplexNumber[]> qubitTensor = new ArrayList();
        int register0 = qubitRegister0.size();
        int register1 = qubitRegister.size();
        if(register0 <= register1){
            for(int i=0; i<register1; i++){
                qubitTensor.add(ComplexVectorSpaces.ComplexVectorTensorProduct(qubitRegister0.get(i), qubitRegister.get(i)));              
            }
        }
        else{
            for(int i=0; i<register0; i++){
                qubitTensor.add(ComplexVectorSpaces.ComplexVectorTensorProduct(qubitRegister0.get(i), qubitRegister.get(i)));              
            }
        }     
        return qubitTensor;
    }
    
    public static ArrayList<?> quantumInverse (HashMap<String,ArrayList<String>> undoingMatrix,HashMap<String,ArrayList<String>> undoing,ArrayList<?> qubitRegister,String varName){
        ArrayList<?> qubitTensor = null;
        ArrayList<ComplexNumber[][]> qubitTensorM = null;
        ArrayList<ComplexNumber[]> qubitTensorM1 = new ArrayList();
        ArrayList<ComplexNumber[]> qubitTensorM2 = new ArrayList();
        //List<AGRSalvaguardasInforme> InformeFinal = SalvaguardasAGR.stream().map(x -> (AGRSalvaguardasInforme)x).collect(Collectors.toList());
        
        if(qubitRegister.get(0) instanceof ComplexNumber[][]){
            
            qubitTensorM = new ArrayList();
            ArrayList<ComplexNumber[][]> qRegister = new ArrayList<>();
            for(Object q: qubitRegister){
                ComplexNumber[][] temp = (ComplexNumber[][]) q;
                qRegister.add(temp);
            }
            
           
        }
        else{
            System.out.println("Tensor Product ComplexNumber[]");
            qubitTensorM1 = new ArrayList();
            ArrayList<ComplexNumber[]> qRegister = new ArrayList<>();
            for(Object q: qubitRegister){
                ComplexNumber[] temp = (ComplexNumber[]) q;
                qRegister.add(temp);
            }
            
        }
        
        if(qubitTensorM != null){
            qubitTensor = qubitTensorM;
        }  
        else{
            qubitTensor = qubitTensorM1;
        }      
        return qubitTensorM2;   
    }
    
    public static double measure(ArrayList<?> result2){  
        ArrayList<ComplexNumber[]> medicion = new ArrayList();
        
         for(Object q: result2){
                ComplexNumber[] temp = (ComplexNumber[]) q;
                medicion.add(temp);
            }
        double prob1=0;
        for(int i=0;i<medicion.get(0).length-2;i++){
            prob1 += Math.pow(medicion.get(0)[i].getReal(),2);
        }
        double prob0=1-prob1;
         System.out.println(prob1);
        System.out.println(prob0);
        System.out.println("El estado 1 del TopQubit tiene una probabilidad de: "+Math.round(prob1));
        System.out.println("El estado 0 del TopQubit tiene una probabilidad de: "+Math.round(prob0));
        if (Math.round(prob1)==1){
            System.out.println("Como el TopQubit esta en estado 1, entonces la funcion es Balanceada");
        } else{
            System.out.println("Como el TopQubit esta en estado 0, entonces la funcion es Constante");
        }
        return 0;
        
    }
   
}
