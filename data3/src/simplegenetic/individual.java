/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegenetic;

/**
 *
 * @author a3-ling
 */
public class individual {
    
    public static int genelength = 130;
    public float gene[] = new float[genelength];
    public int fitness;
    
    public individual(){
        for (int x = 0; x < genelength ; x++){
            gene [x] = 1;
        }
        
    }
    public float[] getgene(){
        return gene;
    }
//    public void writegene(int A, int B){
//        for (int x = 0; x < genelength ; x++){
//            gene [A] = B;
//        }
//    }
    public int getfitness(){
        return fitness;
    }
    
    
        
}
