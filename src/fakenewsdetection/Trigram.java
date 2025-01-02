/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shruti
 */
public class Trigram {
    
     static FileWriter fw;
    
    public static List<String> trigrams(String str) 
    {
        List<String> ngrams = new ArrayList<>();
        String[] words = str.split(" ");
        String bg;
        for (int i = 0; i < words.length - 2 ; i++)
        {
            bg=concat(words, i, i+3);
            ngrams.add(bg);
            //System.out.print(bg+",");
            try
            {
            fw.write(bg+",");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        //System.out.println();
            try
            {
            fw.write("\n");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) 
    {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append(i > start ? " " : "").append(words[i]);
        return sb.toString();
    }

    public static void callTrigram(String fname,String outfile)
    {
        int n=2;
        try
        {
            fw=new FileWriter(outfile,true);
            BufferedReader br=new BufferedReader(new FileReader(fname));
            String str="";
            String x="";
            String[] bg;
            while(str!=null)
            {
                System.out.println();
                x="";
                str=br.readLine();
                if(str==null)
                            break;
                String[] arr=str.split(" ");
       
                for(int i=1;i<arr.length;i++)
                {
                   x=x+" "+arr[i];  
                }
                trigrams(x);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
