package Quantum;


import com.sun.media.jfxmedia.logging.Logger;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class ComplexVectorSpaces {
    
    public static ComplexNumber[] create(String estado){
        ComplexNumber[] Qubit = new ComplexNumber[2];
        if (estado.equals(1)){
            Qubit[0]= new ComplexNumber(0);
            Qubit[1]= new ComplexNumber(1);
        }else{
            Qubit[0]= new ComplexNumber(1);
            Qubit[1]= new ComplexNumber(0);
        }
        return Qubit;
    }  

    
    public static ComplexNumber[] createQubit(String estado){
        ComplexNumber[] Qubit = new ComplexNumber[2];
        if (estado.equals(1)){
            Qubit[0]= new ComplexNumber(0);
            Qubit[1]= new ComplexNumber(1);
        }else{
            Qubit[0]= new ComplexNumber(1);
            Qubit[1]= new ComplexNumber(0);
        }
        return Qubit;
    }  

    //Drill 2.1.1
    public static ComplexNumber[] complexVectorSum(ComplexNumber[] vect1, ComplexNumber[] vect2) {
        if (vect1.length==vect2.length){
        
            ComplexNumber[] ans = new ComplexNumber[vect1.length];
            for (int i = 0; i < vect1.length; i++) {
                ans[i]=ComplexCalculator.complexSum(vect1[i], vect2[i]);
            }
            return ans;
        }else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, Los vectores no tienen la misma dimension");
            throw new RuntimeException("INFO: Error, Los vectores no tienen la misma dimension");
        }
    }

    public static ComplexNumber[] complexVectorInverse(ComplexNumber[] vect) {
        ComplexNumber[] ans = new ComplexNumber[vect.length];
        for (int i=0;i<vect.length;i++){
            ComplexNumber num = new ComplexNumber(vect[i].getReal()*-1,vect[i].getImaginaria()*-1);
            ans[i]=num;
        }
        return ans;
    }

    public static ComplexNumber[] complexVectorScalarMultiplication(ComplexNumber[] vect, ComplexNumber c) {
        ComplexNumber[] ans = new ComplexNumber[vect.length];
        for (int i=0;i<vect.length;i++){
            ComplexNumber num = ComplexCalculator.complexMultiplication(vect[i], c);
            ans[i]=num;
        }
        return ans;
    }
    
    //Drill 2.2.1 pag 39
    public static ComplexNumber[][] complexMatrixSum(ComplexNumber[][] matrix1, ComplexNumber[][] matrix2) {
        if (matrix1.length==matrix2.length && matrix1[0].length==matrix2[0].length){
            ComplexNumber[][] ans = new ComplexNumber[matrix1.length][matrix1[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length;j++){
                    ans[i][j]=ComplexCalculator.complexSum(matrix1[i][j], matrix2[i][j]);
                }
            }
            return ans;

        }else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, Las matrices no tienen la misma dimension");
            throw new RuntimeException("INFO: Error, Las matrices no tienen la misma dimension");
        }
    }
    
    public static ComplexNumber[][] complexMatrixSubstraction(ComplexNumber[][] matrix1, ComplexNumber[][] matrix2) {
        if (matrix1.length==matrix2.length && matrix1[0].length==matrix2[0].length){
            ComplexNumber[][] ans = new ComplexNumber[matrix1.length][matrix1[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length;j++){
                    ans[i][j]=ComplexCalculator.complexSubtraction(matrix1[i][j], matrix2[i][j]);
                }
            }
            return ans;

        }else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, Las matrices no tienen la misma dimension");
            throw new RuntimeException("INFO: Error, Las matrices no tienen la misma dimension");
        }
    }
    
    public static ComplexNumber[][] complexMatrixInverse(ComplexNumber[][] matrix) {
        ComplexNumber[][] ans = new ComplexNumber[matrix.length][matrix.length];
        for (int i=0;i<matrix.length;i++){
            for (int j = 0; j < matrix[i].length;j++){
                ComplexNumber num = new ComplexNumber(matrix[i][j].getReal()*-1,matrix[i][j].getImaginaria()*-1);
                ans[i][j]=num;    
            }
        }
        return ans;
    }

    public static ComplexNumber[][] complexMatrixScalarMultiplication(ComplexNumber[][] matrix, ComplexNumber c) {
        ComplexNumber[][] ans = new ComplexNumber[matrix.length][matrix.length];
        for (int i=0;i<matrix.length;i++){
            for (int j = 0; j < matrix[i].length;j++){
                ComplexNumber num = ComplexCalculator.complexMultiplication(matrix[i][j], c);
                ans[i][j]=num;
            }
        }
        return ans;
    }
    
    //Drill 2.2.2 pag 42
    public static ComplexNumber[][] complexMatrixMultiplication(ComplexNumber[][] matrix1, ComplexNumber[][] matrix2) {
        if(matrix1[0].length==matrix2.length){
            ComplexNumber[][] ans = new ComplexNumber[matrix1.length][matrix2[0].length];
            for (int i=0;i<ans.length;i++){
                for (int j = 0; j < ans[i].length;j++){
                    ComplexNumber num = new ComplexNumber(0,0);
                    for (int k = 0; k < matrix1[0].length;k++){
                        num=ComplexCalculator.complexSum(num, ComplexCalculator.complexMultiplication(matrix1[i][k], matrix2[k][j]));
                    }
                    ans[i][j]=num;
                }
            }
            return ans;
        }
        else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, No se pueden multiplicar las matrices AxB, las columnas de A deben ser iguales a las filas de la matriz B");
            throw new RuntimeException("INFO: Error, No se pueden multiplicar las matrices AxB, las columnas de A deben ser iguales a las filas de la matriz B");
            
        }      
    }
    
    //Drill 2.2.3 pag 42
    public static ComplexNumber[] complexMatrixVectorMultiplication(ComplexNumber[][] matrix, ComplexNumber[] vect) {
        if(matrix[0].length==vect.length){
            ComplexNumber[] ans = new ComplexNumber[vect.length];
            for (int i=0;i<ans.length;i++){
                ComplexNumber num = new ComplexNumber(0,0);
                for (int k = 0; k < matrix[0].length;k++){
                    num=ComplexCalculator.complexSum(num, ComplexCalculator.complexMultiplication(matrix[i][k], vect[k]));
                }
                ans[i]=num;        
            }
            return ans;
        }
        else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, Las columnas de la matriz no son iguales ");
            throw new RuntimeException("INFO: Error, Las columnas de la matriz no son iguales ");
        }      
    }
    
    //Shor
    public static ComplexNumber[] complexVectorMatrixMultiplication(ComplexNumber[] vect, ComplexNumber[][] matrix) {
        if(vect.length==matrix.length){
            ComplexNumber[] ans = new ComplexNumber[vect.length];
            for (int i=0;i<ans.length;i++){
                ComplexNumber num = new ComplexNumber(0,0);
                for (int k = 0; k < vect.length;k++){
                    num=ComplexCalculator.complexSum(num, ComplexCalculator.complexMultiplication(matrix[k][i], vect[k]));
                }
                ans[i]=num;        
            }
            return ans;
        }
        else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, Las columnas de la matriz no son iguales ");
            throw new RuntimeException("INFO: Error, Las columnas de la matriz no son iguales ");
            
        }  
    }
    
    //Drill 2.4.1 pag 55
    public static ComplexNumber complexVectorInnerProduct(ComplexNumber[] vect1,ComplexNumber[] vect2){
        if (vect1.length==vect2.length){
            ComplexNumber ans = new ComplexNumber(0, 0);
            for (int i = 0; i<vect1.length;i++){
                ans=ComplexCalculator.complexSum(ans, ComplexCalculator.complexMultiplication(vect1[i],vect2[i]));
            }
            return ans;
        }else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, Los vectores no tienen la misma dimension");
            throw new RuntimeException("INFO: Error, Los vectores no tienen la misma dimension");
        }
    }
 
    //Drill 2.4.2 pag 57
    public static double complexVectorNorm(ComplexNumber[] vect1){
        double ans = 0;
        for (int i = 0; i<vect1.length;i++){
            double a = vect1[i].getReal();
            double b = vect1[i].getImaginaria();
            ans+=a*a + b*b;
        }
        return Math.sqrt(ans);
    }
    

    //Drill 2.4.3 pag 57
    public static ComplexNumber[] complexVectorDistance(ComplexNumber[] vect1,ComplexNumber[] vect2){
        ComplexNumber ans = new ComplexNumber(0, 0);
        ComplexNumber[] diff = new ComplexNumber[vect1.length];
        for (int x = 0; x<vect1.length;x++){
            diff[x]= ComplexCalculator.complexSubtraction(vect1[x],vect2[x]);
        }
        
        for (int i = 0; i<vect1.length;i++){
            ans=ComplexCalculator.complexSum(ans, ComplexCalculator.complexMultiplication(diff[i],diff[i]));
        }
        return ComplexCalculator.complexRoot(ans, 2);
    }
    
    public static boolean isHermitian(ComplexNumber[][] hermitian){
        boolean flag = false;
        if(isSquare(hermitian)){
            for(int i = 0; i<hermitian.length;i++){
                if(hermitian[i][i].getImaginaria()==0){
                    for(int j = 0; j<hermitian[i].length;j++){if(j!=i){if(ComplexCalculator.getConjugate(hermitian[i][j]).equals(hermitian[j][i])){flag = true;
                        }else{flag = false;break;}}}}
            }
        }
        return flag;
    }
   
    public static ComplexNumber[][] tensorProduct(ComplexNumber[][] matrix1,ComplexNumber[][] matrix2){
        ComplexNumber[][] answerMatrix = new ComplexNumber[matrix1.length*matrix2.length][matrix1[0].length*matrix2[0].length];
        for(int i = 0; i<answerMatrix.length;i++){
            for(int j = 0; j<answerMatrix[0].length;j++){
                answerMatrix[i][j]=ComplexCalculator.complexMultiplication(matrix1[i/matrix2.length][j/matrix2[0].length], matrix2[i%matrix2.length][j%matrix2[0].length]);
            }
        }
        return answerMatrix;
    }
    
    
    public static ComplexNumber[] ComplexVectorTensorProduct(ComplexNumber[] vector1,ComplexNumber[] vector2){
        ComplexNumber[] answerVector = new ComplexNumber[vector1.length*vector2.length];
        for(int i = 0; i<answerVector.length;i++){
            answerVector[i]=ComplexCalculator.complexMultiplication(  vector1[i/vector2.length] ,   vector2[i%vector2.length]);
        }
        return answerVector;
    }
    
     

    private static ComplexNumber[][] cofactorMatrix(int position,ComplexNumber[][] matrix){      
        ComplexNumber[][] newMatrix = new ComplexNumber[matrix.length-1][matrix.length-1];
        int counter1 = 0; int counter2 ;
        for(int i = 0; i < matrix.length; i++){
            if(i != 0){
                counter2 = 0;
                for(int j = 0; j < matrix.length; j++){
                    if(j != position && j < matrix.length){                      
                        newMatrix[counter1][counter2] = matrix[i][j];
                        counter2++;
                    }
                }
                counter1++;
            }
        }
        return newMatrix;
    }

    public static ComplexNumber matrixDeterminant(ComplexNumber[][] matrix){
        ComplexNumber determinant = null;
        if(isSquare(matrix)){
            if( matrix.length == 2 ){
                determinant = ComplexCalculator.complexSubtraction(ComplexCalculator.complexMultiplication(matrix[0][0],matrix[1][1]),ComplexCalculator.complexMultiplication(matrix[0][1],matrix[1][0]));
                return determinant;
            }
            else{
                determinant = new ComplexNumber(0);
                for(int i = 0; i < matrix.length; i++){
                    ComplexNumber[][] cofactor = cofactorMatrix(i,matrix);
                    determinant = ComplexCalculator.complexSum(determinant,ComplexCalculator.complexMultiplication(ComplexCalculator.complexMultiplication(new ComplexNumber(Math.pow(-1,i)),matrix[0][i]),matrixDeterminant(cofactor)));
                }
            }
        }
        else{
            System.out.println("La matrix no es cuadrada");
        }
        return determinant;
    }
    
    public static ComplexNumber[][] adjointMatrix(ComplexNumber[][] matrix){ 
        ComplexNumber[][] adjoint = null;
        if(isSquare(matrix)){
            adjoint = new ComplexNumber[matrix.length][matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    adjoint[j][i] = ComplexCalculator.getConjugate(matrix[i][j]);
               }
            }

         }
        else{
            System.out.println("La matrix no es cuadrada");;
        }
        return adjoint;
    }
    
    public static boolean isSquare(ComplexNumber[][] matrix){
        boolean square = false;
        if(matrix.length == matrix[0].length){
            square = true;
        }
        return square;
    }
    public static ComplexNumber[][] matrixMultiplication(ComplexNumber[][] matrix1,ComplexNumber[][] matrix2){
        ComplexNumber[][] newMatrix = null;
        if(matrix1[0].length == matrix2.length){
            newMatrix = new ComplexNumber[matrix1.length][matrix2.length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix2[i].length; j++) {
                    for(int k = 0; k < matrix2.length; k++){
                        newMatrix[i][j] = ComplexCalculator.complexSum(newMatrix[i][j],ComplexCalculator.complexMultiplication(matrix1[i][k], matrix2[k][j]));
                    }
                }
            }
        }
        else{
            System.out.println("No es posible multiplicar estas matrices");
        }
        return newMatrix;
    }
    
    public static boolean isIdentityMatrix(ComplexNumber[][] matrix){
        boolean identity = false;
        if(isSquare(matrix)){        
            int diagonal = 0;int notDiagonal =  0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                     if(i == j){
                         diagonal += i+j;
                     }
                     else{
                         notDiagonal = i+j;
                     }
                }
            }
            if(diagonal == matrix.length && notDiagonal == 0){
                identity = true;
            }
        }
        return identity;
    }
    
    public static boolean isUnitary(ComplexNumber[][] matrix){
        boolean unitary = false;
        if(isSquare(matrix)){
            ComplexNumber[][] matrixByAdjoint = matrixMultiplication(matrix, adjointMatrix(matrix));
            if (isIdentityMatrix(matrixByAdjoint)){
                unitary = true;
            }
        }
        return unitary;
    }
    
    public static ComplexNumber[] observableMeanAndVariance(ComplexNumber[][] matrix, ComplexNumber[] ket){
        if (isHermitian(matrix)){
            // calculate the mnea value
            ComplexNumber[] bra = complexMatrixVectorMultiplication(matrix, ket);
            for (int l=0;l<bra.length;l++){
                bra[l]=ComplexCalculator.getConjugate(bra[l]);
            }
            ComplexNumber Mean = complexVectorInnerProduct(bra, ket);
            
            // calculate the variance
            ComplexNumber[][] identity = new ComplexNumber[matrix.length][matrix.length];
            for (int k=0;k<matrix.length;k++){
                for (int j=0;j<matrix.length;j++){
                    if (k==j){
                        identity[k][j]= Mean;
                    }else{
                        identity[k][j]= new ComplexNumber(0);
                    }
                }
            }
            ComplexNumber[][] hermitianOperator = complexMatrixSubstraction(matrix, identity);
            ComplexNumber[][] powHermitianOperator =  complexMatrixMultiplication(hermitianOperator, hermitianOperator);
            ComplexNumber[] y = complexMatrixVectorMultiplication(powHermitianOperator, ket);
            for (int g=0;g<ket.length;g++){
                ket[g]=ComplexCalculator.getConjugate(ket[g]);
            }
            ComplexNumber variance = complexVectorInnerProduct(ket,y);
            ComplexNumber[] ans = new ComplexNumber[2];
            ans[0]=Mean;
            ans[1]=variance;
            return ans;
            
        }else{
            Logger.logMsg(Logger.ERROR, "INFO: Error, La matriz no es hermitiana");
            throw new RuntimeException("INFO: Error, La matriz no es hermitiana");
        }
    }

}
