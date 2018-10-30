package Quantum;


import java.util.ArrayList;
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
public class DeterministicSystems {
    //Drill 3.1.1
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Ingrese el numero de vertices: ");
        int vertex = reader.nextInt();
        
        int matrix[][];
        matrix = deterministic(vertex);
        System.out.println("La matriz es la siguiente: ");
        printMatrix(matrix,vertex);
        //Ingresar el vector de estados
        System.out.println("Ingrese el vector de estados uno por uno: ");
        int vector[] = new int[vertex];
        for (int i=0; i<vertex;i++){
            vector[i] = reader.nextInt();
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
    public static int[][] deterministic(int vertex){
        Scanner readerl = new Scanner(System.in);
        System.out.println("Creacion de la matriz de adyacencia... ");
        int cont=0;
        int f[][] = new int[vertex][vertex];
        for (int i=0; i<vertex;i++){
            for ( int j = 0; j<vertex;j++){
                System.out.println("Introdusca el valor de la posicion [" + j + "," + i + "]: ");
                int inp = readerl.nextInt();
                cont = cont + inp;
                while (cont > 1){
                    cont= cont - inp;
                    System.out.println("Error introduciendo la matriz de adyacencia, solo un '1' por columna, vuelva a introducir el valor...");
                    inp = readerl.nextInt();
                    cont = cont + inp;
                }
                f[j][i] = inp;
            }
            cont = 0;
        }

        return f;
    }
    
    public static void printMatrix(int[][] matrix, int vertex){
        for (int i=0; i<vertex;i++){
            for ( int j = 0; j<vertex;j++){
                System.out.print(matrix[i][j] + " ");
                
            }
            System.out.println("");
        }
    }
    
    public static int[] dynamics(int[] vector, int[][] matrix){
        int[] newState = new int[vector.length];
        for (int i=0; i<vector.length;i++){
            for ( int j = 0; j<vector.length;j++){
                newState[i] = newState[i] + (matrix[i][j]*vector[j]);
            }
        }
        return newState;
    }
    
}
