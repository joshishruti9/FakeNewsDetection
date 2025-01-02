/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;
import java.io.*;
import java.util.*;
/**
 *
 * @author Aparna
 */
public class RealNewsProcess {

    public RealNewsProcess() {
    
        try{
            
            File f=new File("F:\\2019\\Prathamesh\\bbc-fulltext\\bbc\\business\\");
            
            FileWriter fw=new FileWriter("real.txt");
            String[]list =f.list();
            
            for(String s :list)
            {
            String fname=f.getPath()+"\\"+s;                
            BufferedReader br=new BufferedReader(new FileReader(fname));
            String str="";
            String x="";
            String[] bg;
            while(str!=null)
            {
                //System.out.println();
                x="";
                str=br.readLine();
                if(str==null)
                            break;
                if(!str.equals(""))
                {
                  fw.write(str+"\r\n");  
                }
                
            }
            fw.flush();               
            }
            fw.close();
        
            
        
        }
    catch(Exception e)
    {
        System.out.println(e);
    }
    }
    
    
    
    
}
