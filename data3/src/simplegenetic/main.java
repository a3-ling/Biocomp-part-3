/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegenetic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author a3-ling
 */
public class main {
    static int P = 80; //population size
    static int N = 130; //gene length
    static int rulenumber = 10;
    static int datasize = 2000;
    static rule dataSet[] = new rule[datasize];
    static rule indrule[] = new rule[rulenumber];
    
        
    public static void main(String args[]) throws FileNotFoundException {
        
        
        individual[] parents = new individual[P];
        individual[] children = new individual[P];
        individual[] population = new individual[P]; //populate with random numbers between 1 and 9
        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("generation");
        sb.append(',');
        sb.append("Maximum fitness");
        sb.append('\n');
        
        
        File data = new File("C:\\Users\\alexl\\Documents\\data3\\src\\simplegenetic\\data3.txt");
        Scanner scanner = new Scanner(data);
        

        for(int i =0; i<datasize; i++ ) //generate dataSet from data file
        {
            //population[i] = new individual();
            
            //for(int k=0; k<datasize*6; k+=6){ //loop through for the number of rules
           dataSet[i] = new rule();
           dataSet[i].cond = new float[6];
           dataSet[i].cond[0] = scanner.nextFloat();
           dataSet[i].cond[1] = scanner.nextFloat();
           dataSet[i].cond[2] = scanner.nextFloat();
           dataSet[i].cond[3] = scanner.nextFloat();
           dataSet[i].cond[4] = scanner.nextFloat();
           dataSet[i].cond[5] = scanner.nextFloat();
           dataSet[i].out = scanner.nextFloat();
           
//               System.out.println(dataSet[i].cond[4]);
//               System.out.println(dataSet[i].out);
               
               
            //}
            
        }
//        System.out.println("test: "+ dataSet[3].cond[1]);
//        System.out.println("test: "+ dataSet[3].out);
//        System.out.println("test: " +Arrays.toString(dataSet[20].cond));
//        System.out.println("test: " + dataSet[20].out);
        for(int i=0; i<P; i++)
        {
            int R = 0;
            population[i] = new individual();
            for(int k=0; k<N; k++){
               population[i].gene[k] = (float)(Math.random() * 1); R++;
               
               if(R==13){
                   population[i].gene[k] = (int)(Math.random() * 2);
                   R=0;
               }
               
            }
            fitness(population[i]);
        }
        //System.out.println("testtest " +Arrays.toString(population[1].gene));
        for(int g=0; g<1000; g++){ //g = generations
            int fitness1 = 0;
            int fitness2 = 0;
            int fitness3 = 0;
            //System.out.println(Arrays.toString(population[4].gene)); //print random gene
            
            for(int i=0; i<P; i++ ) { //fitness of the initial population
                fitness(population[i]);
            }


            for (int i = 0; i < P; i++){  //selection of parents from population
                int parent1 = (int)(Math.random() * P );
                int parent2 = (int)(Math.random() * P );

                if(population[parent1].fitness > population[parent2].fitness){
                    parents[i] = population[parent1];
                }
                else {
                    parents[i] = population[parent2];
                }
            }

            for(int i = 0; i < P; i+=2){ //crossover reproduction
                individual father = parents[i];
                individual mother = parents[i+1];
                individual child1 = new individual();
                individual child2 = new individual();
                int crossover = (int)(Math.random() *individual.genelength);
                for(int A = 0; A < N; A++){
                    if(A < crossover){
                        child1.gene[A] = father.gene[A];
                        child2.gene[A] = mother.gene[A];
                    }
                    else{
                        child1.gene[A] = mother.gene[A];
                        child2.gene[A] = father.gene[A];
                    }

                }
                children[i] = child1;
                children[i+1] = child2;

            }
            //System.out.println(Arrays.toString(children[4].gene)); //print random child gene
            //System.out.println(children.length);
            
            
            int mutation_rate = 7;
            for(int i = 0; i < (P/mutation_rate); i++){ //mutation
                children[(int)(Math.random() * P)].gene[(int)(Math.random() * N)] = (float)(Math.random() * 1);
            }
            
            for(int i=0; i<P; i++ ) { //calculate fitness of the children
                fitness(children[i]);
                
            }

            for (int i=0; i<P; i++){ //cumulative fitness
                fitness1 += population[i].fitness;
                fitness2 += parents[i].fitness;
                fitness3 += children[i].fitness;
            }
            //System.out.println(fitness1/P);
            //System.out.println(fitness2);
            //System.out.println(fitness3/P);


            //if (fitness1 < fitness3){ //overwite population with children
                population = children;
                //System.out.println("offsring population overwrites population");
            //}
            
            
            int maxfit = 0;
            System.out.println("generation: " + g);
            for(int i= 0; i <P; i++){
                
                if(children[i].fitness > maxfit){
                    maxfit = children[i].fitness;
                }
            }
            System.out.println(maxfit+ " maxfit");
            int minfit = children[1].fitness;
            for(int i= 0; i <P; i++){
                
                if(children[i].fitness < minfit){
                    minfit = children[i].fitness;
                }
            }
            System.out.println(minfit + " minfit");
            
            //System.out.println(minfit + " minfit");
            sb.append(g);
            sb.append(',');
            sb.append(maxfit);
            sb.append('\n');

            
            //System.out.println(Arrays.toString(indrule[3].cond));
//            System.out.println());
        }
        pw.write(sb.toString());
        pw.close();
    }
    
    
    public static void fitness(individual ind){ //fitness function

        int k = 0;
        
//        if(dataSet[i].cond[1] == ind.gene[k]){
//            
//        }
        for (int i=0; i<rulenumber; i++){ //for the number of rules in the gene
            indrule[i] = new rule();
            for(int j=0; j < rule.rulelength; j++){ //run 12 times
                indrule[i].cond[j] = ind.gene[k];
                k++;//
            }
            indrule[i].out = ind.gene[k];
            k++;
        }
        //System.out.println("test: " +indrule[5].cond[7]);
        for(int i=0; i<datasize; i++){
            for (int j=0; j<rulenumber; j++){
                if(checkmatch(dataSet[i],indrule[j])){
                    //System.out.println(indrule[j].out);
                    if(dataSet[i].out == indrule[j].out){
                        ind.fitness++;
                    }
//                    else if (indrule[j].out == 2){
//                        ind.fitness++;
//                    }
                    j=rulenumber;
                }
                
            }
            
        }
    }
    public static boolean checkmatch(rule rule1, rule rule2){
        int check =0;
        int j = 0;
        for (int i=0; i<6; i++){
            //if(rule2.cond[j] + rule2.cond[j+1] > 1.1f){
                if((rule2.cond[j] < rule1.cond[i]) && (rule1.cond[i] < rule2.cond[j+1])){
                check++;
                //System.out.println("test check+");
                }
                else if((rule2.cond[j+1] < rule1.cond[i]) && (rule1.cond[i] < rule2.cond[j])){
                    check++;
                    //System.out.println("test check+");
                }
//            }
//            else {
//                check++;
//            }
            
//            else if(rule2.cond[j] == 2) {
//                check++;
//            }
//            else if(rule2.cond[j+1] == 2){
//                check++;
//            }
            j+=2;
        }
        //System.out.println("test: " +check);
        if (check == 6){
            //System.out.println("true");
            return true;
            
        }
        else{
            return false;
        }
        
    }

}
