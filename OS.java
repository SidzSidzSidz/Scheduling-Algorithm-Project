/*  @author Sidnel Gramata
 * CSCC32 A 2
 * 
 * A program that does the Shortes Job First Scheduling of both Preemptive and Non-Preemptive
 */
import java.util.Scanner;
public class OS{
    public static void main(String[] args) {
        Scanner z = new Scanner(System.in);
        PE p = new PE();
          
        System.out.print("Number of Process: ");
        int num = z.nextInt();
        System.out.println();
        String[] name = new String[num];
        int[] at = new int[num];    //Process Arrival time
        int[] cc = new int[num];    //Process Clock cycle
        int total = 0;				//total clock cycle
        
        for(int a = 0; a < num; a++){
            System.out.print("Process " + (a + 1) + " Name: ");
            name[a] = z.next();
            System.out.println();
            
            System.out.print(name[a] + " arrival time: ");
            at[a] = z.nextInt();
            System.out.println();
            
            System.out.print(name[a] + " clock cycle: ");
            cc[a] = z.nextInt();
            total = total + cc[a];		//how many cycle needed to run all the process
            System.out.println();
        }
        System.out.println("Process     Arrival     Cycle");
        for(int b = 0; b < num; b++){ 
            System.out.println(name[b] + "          " + at[b] + "           " + cc[b]);
        }
        System.out.println();
        System.out.println("-------------------Shortest Job First (Preemptive)-------------------");
        p.sjf(name,num,at,cc,total,0); //Preemptive Scheduling
        System.out.println();
        System.out.println("-------------------Shortest Job First (Non-Preemptive)-------------------");            
        p.sjf(name,num,at,cc,total,1);  //Non-Preemptive Scheduling
                    
    }
}