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
        x[0][0]= new ComplexNumber(9);
        x[0][1]= new ComplexNumber(1);
        x[1][1]= new ComplexNumber(2);     
        x[1][0]= new ComplexNumber(0);
        
        z[0][0]= new ComplexNumber(0);
        z[0][1]= new ComplexNumber(8);
        z[0][2]= new ComplexNumber(9);
        z[1][0]= new ComplexNumber(6);
        z[1][1]= new ComplexNumber(5);
        z[1][2]= new ComplexNumber(4);
        z[2][0]= new ComplexNumber(2);
        z[2][1]= new ComplexNumber(3);
        z[2][2]= new ComplexNumber(1);
       
        
        
        System.out.println(y.isHermitian(x));
        y.tensorProduct(x, z);
    }
    
}
