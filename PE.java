/*  @author Sidnel Gramata
 * CSCC32 A 2
 * 
 * The algorithm of the Shortest Job First Scheduling Preemptive and Non-Preemptive
 * All sorts are done in Ascending Order
 */
public class PE {
    public static void sjf(String[] name, int num, int[] at, int[] cc, int total, int pon){
        String[] term = new String[num];        //list of terminated process
        String[] pname = name.clone();             //temporary values for the process name
        int pnum = num, Tctr = 0, ctr = 0, same = 0, rctr = 0;
        int[] pat = at.clone(), pcc = cc.clone();    //temporary values for arrival time and clock cycle list
        double tot = 0;        //total turaround time
        
        for(int a = 0; a < pnum - 1; a++){          //sort the process based on arrival time
            for(int b = 0; b < pnum - a - 1; b++){
                if(pat[b] > pat[b + 1]){            
                    int x = pat[b];
                    pat[b] = pat[b + 1];
                    pat[b + 1] = x;

                    x = pcc[b];
                    pcc[b] = pcc[b + 1];
                    pcc[b + 1] = x;

                    String y = pname[b];
                    pname[b] = pname[b + 1];
                    pname[b + 1] = y;
                }
            }
        }
        
        while(ctr != total + 1){                //Cycle until all process are terminated
            for(int a = 0; a < pnum; a++){     //Count all process which are ready or on queue   
                if(pat[a] <= ctr){
                    same++;
                }
            }
            for(int a = 0; a < same - 1; a++){                  //sort ready process according to clock cycle 
                if(pon == 0){                                   //0 --> Preemptive |1 --> Non-Preemptive
                    for(int b = 0; b < same - a - 1; b++){      //sort all the process that are ready
                        if(pcc[b] > pcc[b + 1]){                        
                            int x = pcc[b];                            
                            pcc[b] = pcc[b + 1];                        
                            pcc[b + 1] = x;

                            x = pat[b];
                            pat[b] = pat[b + 1];
                            pat[b + 1] = x;           

                            String y = pname[b];
                            pname[b] = pname[b + 1];
                            pname[b + 1] = y;
                        }
                    }
                }
                else{                                      
                    for(int b = 1; b < same - a - 1; b++){    //sort all the process that are ready, except for the currently running process
                        if(pcc[b] > pcc[b + 1]){                        
                            int x = pcc[b];                            
                            pcc[b] = pcc[b + 1];                        
                            pcc[b + 1] = x;

                            x = pat[b];
                            pat[b] = pat[b + 1];
                            pat[b + 1] = x;           

                            String y = pname[b];
                            pname[b] = pname[b + 1];
                            pname[b + 1] = y;
                        }
                    }
                }
            }
            if(pat[0] <= rctr){                                 //show the state of the processes
                System.out.println();
                System.out.println("Runtime: " + (rctr + 1)); 
                if(ctr != total){
                    System.out.println("Running: " + pname[0]); //running state
                }
                else{
                    System.out.println("Running: ");
                }
                System.out.print("Ready: ");                //Ready state
                for(int a = 1; a < same; a++){              
                    if(same != 1){
                        System.out.print(pname[a] + "   ");
                    }
                }
                System.out.println();
                System.out.print("Waiting: ");              //waiting state
                for(int a = same; a < pnum; a++){            
                    System.out.print(pname[a] + "   ");
                }
                System.out.println();

                System.out.print("Terminated: ");           //terminated state
                for(int a = 0; a < Tctr; a++){              
                    System.out.print(term[a] + "    ");
                }
                System.out.println();
                
                pcc[0] = pcc[0] - 1;                    //decrement the clock cycle of the running process
                if(pcc[0] == 0){                       //if the process finished its cycle     
                    term[Tctr] = pname[0];              //store the terminated process 
                    tot = tot + (rctr - pat[0] + 1);    //store turnaround and add it to the total turnaround time
                    Tctr++;
                }
                if(pcc[0] == 0 && ctr < total){         //if the process
                    for(int a = 0; a < pnum - 1; a++){  //shifts the queue to the left
                        pcc[a] = pcc[a + 1];
                        pat[a] = pat[a + 1];
                        pname[a] = pname[a + 1];
                    }
                    pnum--;                             //reduces the queue
                    if(ctr == total - 1){               //gets rid of the running process in the last runtime
                        pname[0] = "";
                    }
                }
                if(ctr == total){                       //in the last runtime, print the average turnaround time         
                    System.out.println();
                    System.out.println("Average Turnaround Time: " + tot/num);
                }
                ctr++;
            }
            
            else{                                               //only show waiting process when no process is ready
                System.out.println("Runtime: " + (rctr + 1));                                           
                System.out.println("Running: ");
                System.out.print("Ready: ");
                System.out.println();
                System.out.print("Waiting: ");
                for(int a = same; a < pnum; a++){            
                    System.out.print(pname[a] + "   ");
                }
                System.out.println();
                System.out.print("Terminated: ");
                for(int a = 0; a < Tctr; a++){              
                    System.out.print(term[a] + "    ");
                }
                System.out.println();
            }
            
            
            rctr++;                                         
            same = 0;                                        
        }
    }
}
