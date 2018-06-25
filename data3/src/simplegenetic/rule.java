
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


public class rule {
    public static int rulelength =  12;
    public float cond[] = new float[rulelength];
    public float out;
    
    public float[] getcond(){
        return cond;
    }
    public float getout(){
        return out;
    }
    public int getrulelength(){
        return rulelength;
    }
}
