/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statcompiler;

/**
 *
 * @author djp217
 */
public class StatCompiler {

    
    public static int averageOfPosInts(int a, int b, int c) throws IllegalArgumentException {
        if((a<0) || (b<0) || (c<0))
            throw new IllegalArgumentException("No neg values");
        int sum = a + b + c;
        return sum/3;
    }
    
    public static int median(int a, int b, int c) {
        if((a>=b) && (a<=c)) return a;
        else if ((a>=c) && (a<=b)) return a;
        else if((c>=b) && (b>= a)) return b;
        else if((a>=b) && (a>=c)) return b;
        else    return c;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
