/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TFIDF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author shruti
 */
public class TfidfCreateFiles {
    
    public static void seperateFiles(String fname,String folder)
    {
        int i=0;
        String str="";
        try
        {
        BufferedReader br=new BufferedReader(new FileReader(fname));
            while(str!=null)
            {
                str=br.readLine();
                if(str==null) break;
                FileWriter fw=new FileWriter("F:\\2020\\shruti\\FakeNewsDetection\\TfIdf\\"+folder+"\\"+i+".txt");
                System.out.println(str);
                fw.write(str);
                fw.flush();
                fw.close();
                i++;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}

