
import java.util.ArrayList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class VectorSpaces {
    
    public ArrayList<Complex> sumVector(ArrayList<Complex> vect1, ArrayList<Complex> vect2){
        ArrayList<Complex> ans = new ArrayList();
        for (int i=0;i<vect1.size();i++){
            ans.add(vect1.get(i).sumComplex(vect2.get(i)));
        }
        
        return ans;
    }
    
    
    public ArrayList<Complex> inverseVector(ArrayList<Complex> vect){
        ArrayList<Complex> ans = new ArrayList();
        for (Complex c: vect){
            Complex num = new Complex(c.getReal()*-1,c.getImaginaria()*-1);
            ans.add(num);
        }
        return ans;
    }
    
    public ArrayList<Complex> multScalar(ArrayList<Complex> vect, Complex c){
        ArrayList<Complex> ans = new ArrayList();
        for (Complex c2: vect){
            Complex num = new Complex( (c2.getReal()*c.getReal() + c2.getImaginaria()*c.getImaginaria() ) ,( c2.getReal()*c.getImaginaria() + c2.getImaginaria()*c.getReal()) );
            ans.add(num);
        }
        return ans;
    }
    
    
    
}
