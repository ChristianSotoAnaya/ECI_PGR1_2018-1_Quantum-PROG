
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo Andres
 */
public class QuantumSystems {
    
    //Drill 3.2.1
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Ingrese el numero de vertices: ");
        int vertex = reader.nextInt();
        
        ComplexNumber matrix[][];
        matrix = deterministic(vertex);
        System.out.println("La matriz es la siguiente: ");
        printMatrix(matrix,vertex);
        
        //Ingresar el vector de estados
        System.out.println("Ingrese el vector de estados: ");
        ComplexNumber vector[] = new ComplexNumber[vertex];
        for (int i=0; i<vertex;i++){
            System.out.println("Numero " + i + "°:");
            System.out.println("Ingrese la parte real...");
            Double real = Double.parseDouble(reader.next());
            System.out.println("Ingrese la parte imaginaria...");
            Double imaginaria = Double.parseDouble(reader.next());
            ComplexNumber num = new ComplexNumber(real,imaginaria);
            vector[i] = num;
        }
        
        //Ingresar el número de clicks a hacer
        System.out.println("Ingrese el número de clicks: ");
        int click = reader.nextInt();
        
        for (int i=0;i<click;i++){
            vector = dynamics(vector, matrix);
        }
        
        System.out.println("Despues de los cambios de estado, el vector de estados es: ");
        for (int i=0;i<vertex;i++){
            System.out.println(vector[i]);
        }
        
       
    }
    
    //Llenar matriz adyacente
    public static ComplexNumber[][] deterministic(int vertex){
        Scanner readerl = new Scanner(System.in);
        System.out.println("Creacion de la matriz de adyacencia... ");
        double cont=0;
        ComplexNumber f[][] = new ComplexNumber[vertex][vertex];
        int i = 0;
        for (i=0; i<vertex;i++){
            for ( int j = 0; j<vertex;j++){
                System.out.println("Introdusca el valor de la posicion [" + j + "," + i + "]: ");
                System.out.println("Ingrese la parte real...");
                Double real = Double.parseDouble(readerl.next());
                System.out.println("Ingrese la parte imaginaria...");
                Double imaginaria = Double.parseDouble(readerl.next());
                ComplexNumber inp = new ComplexNumber(real,imaginaria);
                cont = cont + Math.pow(inp.getModulus(),2);
                while (cont > 1){
                    cont= cont - Math.pow(inp.getModulus(),2);
                    System.out.println("Error introduciendo la matriz de adyacencia, la suma de los modulos al cuadrado de la columna debe ser '1', vuelva a introducir el valor...");
                    System.out.println("Ingrese la parte real...");
                    real = Double.parseDouble(readerl.next());
                    System.out.println("Ingrese la parte imaginaria...");
                    imaginaria = Double.parseDouble(readerl.next());
                    inp = new ComplexNumber(real,imaginaria);
                    cont = cont + Math.pow(inp.getModulus(),2);
                }
                f[j][i] = inp;
            }
            cont = cont + 0.0001;
            System.out.println(cont);
            if (cont < 1){
                System.out.println("La suma de la columna no da 1, vuelva a digitarla completamente...");
                i= i-1;
            }
            cont = 0;
        }

        return f;
    }
    
    public static void printMatrix(ComplexNumber[][] matrix, int vertex){
        for (int i=0; i<vertex;i++){
            for ( int j = 0; j<vertex;j++){
                System.out.print(matrix[i][j].toString() + " ");
                
            }
            System.out.println("");
        }
    }
    
    public static ComplexNumber[] dynamics(ComplexNumber[] vector, ComplexNumber[][] matrix){
        ComplexNumber[] newState = new ComplexNumber[vector.length];
        for (int i=0; i<vector.length;i++){
            for ( int j = 0; j<vector.length;j++){
                //newState[i] = newState[i] + (matrix[i][j]*vector[j]);
                newState[i] = ComplexCalculator.complexSum(newState[i], ComplexCalculator.complexMultiplication(matrix[i][j], vector[j]));
            }
        }
        return newState;
    }
}