/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shruti
 */
public class ARI {
    FileWriter fw;
    
    public ARI(String fname,String outfile)
    {            
       // for( int i=0;i<ls.length;i++)
        {
            
           BufferedReader br=null;
            try {
                fw=new FileWriter(outfile);
               // br = new BufferedReader(new FileReader(f.getPath()+"\\"+ls[i]));
                br = new BufferedReader(new FileReader(fname));
                String str="";
                String x="";
                String[] bg;
                while(str!=null)
                {
                    System.out.println();
                    x="";
                    str=br.readLine();
                    double cntwords=0;
                    double cntchars=0;
                    
                    if(str==null)
                        break;
                    
                    String [] arr=str.split(" ");
                    
                    for(int j=0;j<arr.length;j++)
                    {
                        cntwords++;
                        
                       // cntchars=0;
                        String word=arr[j];
                        for(int k=0;k<word.length();k++)
                        {
                            cntchars++;
                        }
                    }
                    
                    double ari=4.71*(cntchars/cntwords)+0.5*(cntwords/1)-21.43;
                    
                    System.out.println(ari);
                    
                    if(fname.equals("real.txt"))
                    {
                       fw.write(ari+",benign"+"\n");
                     fw.flush();;  
                    // FakeNewsDetection.fwtrain.write(ari+",1"+"\n");
                     //FakeNewsDetection.fwtrain.flush();;
                    }
                    else
                    {
                        
                        fw.write(ari+",fake"+"\n");
                     fw.flush();;
                     //FakeNewsDetection.fwtrain.write(ari+",0"+"\n");
                     //FakeNewsDetection.fwtrain.flush();;
                        
                    }                  
                    
                }   
            } catch (Exception ex) {
                
                Logger.getLogger(ARI.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
        
    }
    
    
    public static void main(String[]args)
    {
        new ARI("clean4.txt","fakeari.txt");
    }
    
}
