/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xbran
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ComplexVectorSpaces y = new ComplexVectorSpaces();
        ComplexNumber[][] x = new ComplexNumber[2][2];
        ComplexNumber[][] z = new ComplexNumber[3][3];
        x[0][0]= new ComplexNumber(1);
        x[1][1]= new ComplexNumber(2);
        x[0][1]= new ComplexNumber(5,6);
        x[1][0]= new ComplexNumber(3,3);
        
        z[0][0]= new ComplexNumber(1);
        z[1][1]= new ComplexNumber(2);
        z[2][2]= new ComplexNumber(7);
        z[0][1]= new ComplexNumber(1,3);
        z[0][2]= new ComplexNumber(4,7);
        z[1][0]= new ComplexNumber(2,-5);
        z[1][2]= new ComplexNumber(2,-5);
        z[2][1]= new ComplexNumber(2,-5);
        z[2][0]= new ComplexNumber(2,-5);
        
        for(int v = 0; v<x.length;v++){
            for(int b = 0; b<x[v].length;b++){
                System.out.print(x[v][b]);
            }
            System.out.println("\n");
        }
        
        for(int a = 0; a<z.length;a++){
            for(int d = 0; d<z[a].length;d++){
                System.out.print(z[a][d]);
            }
            System.out.println("\n");
        }
        
        System.out.println(y.isHermitian(x));
        y.tensorProduct(x, z);
    }
    
}
