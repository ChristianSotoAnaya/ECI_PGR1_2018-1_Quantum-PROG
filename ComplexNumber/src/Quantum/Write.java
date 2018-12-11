/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quantum;
import java.util.*;
/**
 *
 * @author brand
 */
public class Write {
   
    public static void main(String[] args) {
       ArrayList<?> hadamardMatrix = QuantumCompilerMethods.createHadamardMatrix();
ArrayList<?> controlledNotMatrix = QuantumCompilerMethods.createControlledNotMatrix();
ArrayList<?> identityMatrix = QuantumCompilerMethods.createIdentityMatrix();
HashMap<String,ArrayList<String>> undoing = new HashMap();
HashMap<ArrayList<String>, ComplexNumber[][]> undoingMatrix = new HashMap();
ArrayList<ComplexNumber[]> R1 = new ArrayList();
R1 = QuantumCompilerMethods.createQubitRegister("0");
ArrayList<ComplexNumber[]> R2 = new ArrayList();
R2 = QuantumCompilerMethods.createQubitRegister("1");
ArrayList<?> QUBIT = QuantumCompilerMethods.tensorProduct(R2,R1);
ArrayList<?> Thada = QuantumCompilerMethods.tensorProduct((ComplexNumber[][]) hadamardMatrix.get(0),(ComplexNumber[][]) hadamardMatrix.get(0));
ArrayList<?> Tiden = QuantumCompilerMethods.tensorProduct((ComplexNumber[][]) hadamardMatrix.get(0),(ComplexNumber[][]) identityMatrix.get(0));
ArrayList<?> R0 = QuantumCompilerMethods.tensorProduct(Thada,QUBIT);
ArrayList<?> RE1 = QuantumCompilerMethods.tensorProduct(controlledNotMatrix,R0);
ArrayList<?> RE2 = QuantumCompilerMethods.tensorProduct(Tiden,RE1);
double ANS = QuantumCompilerMethods.measure(RE2);

    
}
}