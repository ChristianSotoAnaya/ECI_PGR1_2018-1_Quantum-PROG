
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
public class ComplexVectorSpaces {

    public ArrayList<ComplexNumber> sumVector(ArrayList<ComplexNumber> vect1, ArrayList<ComplexNumber> vect2) {
        ArrayList<ComplexNumber> ans = new ArrayList();
        for (int i = 0; i < vect1.size(); i++) {
            ans.add(ComplexCalculator.complexSum(vect1.get(i), vect2.get(i)));
//            ans.add(vect1.get(i).sumComplex(vect2.get(i)));
        }

        return ans;
    }

    public ArrayList<ComplexNumber> inverseVector(ArrayList<ComplexNumber> vect) {
        ArrayList<ComplexNumber> ans = new ArrayList();
        vect.stream().map((c) -> new ComplexNumber(c.getReal() * -1, c.getImaginaria() * -1)).forEachOrdered((num) -> {
            ans.add(num);
        });
//        for (Complex c : vect) {
//            Complex num = new Complex(c.getReal() * -1, c.getImaginaria() * -1);
//            ans.add(num);
//        }
        return ans;
    }

    public ArrayList<ComplexNumber> multScalar(ArrayList<ComplexNumber> vect, ComplexNumber c) {
        ArrayList<ComplexNumber> ans = new ArrayList();
        vect.stream().map((c2) -> ComplexCalculator.complexMultiplication(c, c2)).forEachOrdered((num) -> {
            ans.add(num);
        });
//        for (Complex c2 : vect) {
//            Complex num = new Complex((c2.getReal() * c.getReal() + c2.getImaginaria() * c.getImaginaria()), (c2.getReal() * c.getImaginaria() + c2.getImaginaria() * c.getReal()));
//            ans.add(num);
//        }
        return ans;
    }

}
