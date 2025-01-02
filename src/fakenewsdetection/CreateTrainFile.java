/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Aparna
 */
public class CreateTrainFile {
    
    public CreateTrainFile()
            {
                try
                {
                
                String []arr={"jaccardreal.txt","posreal.txt","arireal.txt","jaccardfake.txt","posfake.txt","arifake.txt"};
               // for(int i=0;i<arr.length;i++)
                {
                FileWriter fw=new FileWriter("train.arff");
                fw.write("@relation s\n");
                fw.write("@attribute a real \n");
                  fw.write("@attribute b real \n");
                    fw.write("@attribute c real \n");
                      fw.write("@attribute d real \n");
                        fw.write("@attribute e real \n");
                          fw.write("@attribute f real \n");
                            fw.write("@attribute class{benign,fake} \n");
                fw.write("@data \n");
                
               BufferedReader br = new BufferedReader(new FileReader(arr[0]));
               BufferedReader br1 = new BufferedReader(new FileReader(arr[1]));
               BufferedReader br2 = new BufferedReader(new FileReader(arr[2]));
                String str="";
                String x="";
                String[] bg;
                
                try
                {
                while(str!=null)
                
                {
                      if(str==null)
                        break;
                    //System.out.println();
                    x="";

                    str=br.readLine();
                    fw.write(str);
                    
                    str=br1.readLine();
                    fw.write(","+str);
                    
                    str=br2.readLine();
                    
                    fw.write(","+str);
                    
                    fw.write("\r\n");
                    fw.flush();
                  
                    
                }
                }catch(Exception e){}
                
                 br = new BufferedReader(new FileReader(arr[3]));
                br1 = new BufferedReader(new FileReader(arr[4]));
                br2 = new BufferedReader(new FileReader(arr[5]));
                 str="";
                 x="";
         
                try{
                while(str!=null)
                {
                    //System.out.println();
                    x="";

                     if(str==null)
                        break;
                    str=br.readLine();
                    fw.write(str);
                    
                    str=br1.readLine();
                    fw.write(","+str);
                    
                    str=br2.readLine();
                    fw.write(","+str);
                      fw.write("\r\n");
                    fw.flush();
                   
                    
                }
                }catch(Exception e){}
                
                
            }
            }
                catch(Exception e){
                    System.out.println(e);
                }
            }
    
    public static void main(String[]args)
    {
        new CreateTrainFile();
    }
    
}
