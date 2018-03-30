
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
public class ProbabilisticSystems {
    
    //Drill 3.2.1
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Ingrese el numero de vertices: ");
        int vertex = reader.nextInt();
        
        double matrix[][];
        matrix = deterministic(vertex);
        System.out.println("La matriz es la siguiente: ");
        printMatrix(matrix,vertex);
        
        //Ingresar el vector de estados
        System.out.println("Ingrese el vector de estados: ");
        double vector[] = new double[vertex];
        for (int i=0; i<vertex;i++){
            vector[i] = Double.parseDouble(reader.next());
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
    public static double[][] deterministic(int vertex){
        Scanner readerl = new Scanner(System.in);
        System.out.println("Creacion de la matriz de adyacencia... ");
        double cont=0;
        double f[][] = new double[vertex][vertex];
        int i = 0;
        for (i=0; i<vertex;i++){
            for ( int j = 0; j<vertex;j++){
                System.out.println("Introdusca el valor de la posicion [" + j + "," + i + "]: ");
                double inp = Double.parseDouble(readerl.next());
                cont = cont + inp;
                while (cont > 1){
                    cont= cont - inp;
                    System.out.println("Error introduciendo la matriz de adyacencia, la suma de la columna debe ser igual a 0, vuelva a introducir el valor...");
                    inp = Double.parseDouble(readerl.next());
                    cont = cont + inp;
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
    
    public static void printMatrix(double[][] matrix, int vertex){
        for (int i=0; i<vertex;i++){
            for ( int j = 0; j<vertex;j++){
                System.out.print(matrix[i][j] + " ");
                
            }
            System.out.println("");
        }
    }
    
    public static double[] dynamics(double[] vector, double[][] matrix){
        double[] newState = new double[vector.length];
        for (int i=0; i<vector.length;i++){
            for ( int j = 0; j<vector.length;j++){
                newState[i] = newState[i] + (matrix[i][j]*vector[j]);
            }
        }
        return newState;
    }
}
