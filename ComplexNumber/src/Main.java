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
        ComplexNumber[][] z = new ComplexNumber[4][4];
        x[0][0]= new ComplexNumber(3);
        x[0][1]= new ComplexNumber(4);
        x[1][0]= new ComplexNumber(1);
        x[1][1]= new ComplexNumber(2);
        //System.out.println(y.matrixDeterminant(x));
        
        z[0][0]= new ComplexNumber(3);
        z[0][1]= new ComplexNumber(-2);
        z[0][2]= new ComplexNumber(1);
        z[0][3]= new ComplexNumber(-5);
        z[1][0]= new ComplexNumber(2);
        z[1][1]= new ComplexNumber(-3);
        z[1][2]= new ComplexNumber(-1);
        z[1][3]= new ComplexNumber(1);
        z[2][0]= new ComplexNumber(-4);
        z[2][1]= new ComplexNumber(4);
        z[2][2]= new ComplexNumber(-1);
        z[2][3]= new ComplexNumber(5);
        z[3][0]= new ComplexNumber(5);
        z[3][1]= new ComplexNumber(3);
        z[3][2]= new ComplexNumber(-2);
        z[3][3]= new ComplexNumber(6);
        
        System.out.println(y.matrixDeterminant(z));
        //System.out.println(y.isHermitian(x));
        //ComplexNumber[][] asd = y.tensorProduct(x, z);
        /*
        for(int i = 0; i<asd.length;i++){
            for(int j = 0; j<asd[i].length;j++){
                
                System.out.print(asd[i][j]);
            }
        System.out.println("\n");
        }*/
                
                


        
    }
    
}
