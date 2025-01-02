/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 *
 * @author shruti
 */
public class Preprocessor {
    
    public static void removeTime()
    {
        try
                {
                    
                    String[]arr2={"@fakingnews","#","RT"};
                    BufferedReader br=new BufferedReader(new FileReader("tweets.txt"));
                    FileWriter fw=new FileWriter("clean.txt");
                    String str="";
                     int count=0;
                     int x;
                    while(str!=null)
                    {
                        String y="";
                        count++;
                        str=br.readLine();
                        if(str==null)
                            break;
                        
                        String[] arr=str.split("\t");
                        
                        StringTokenizer st=new StringTokenizer(arr[0]," ");
                        String myDate="";
                         
                        x=arr[1].indexOf("ht");
                        
                        //System.out.println(x);
                        
                        if(x>=0)
                        {
                        y=arr[1].substring(0,x);
                        
                        while(st.hasMoreTokens())
                        {
                            st.nextToken();
                            myDate=st.nextToken();
                            myDate=myDate+"_"+st.nextToken();
                            st.nextToken();
                            st.nextToken();
                            myDate=myDate+"_"+st.nextToken();
                            myDate=myDate.replace("RT","");
                            
                            //System.out.println(myDate);
                            
                            fw.write(myDate);
                            
                            //fw.write(" "+y);
                            //System.out.println(y);
                           for(int i=0;i<arr2.length;i++)
                           {
                               y=y.replaceAll(arr2[i]," ");
                           }
                            StringTokenizer st1=new StringTokenizer(y,":");
                            String ynew="";
                                st1.nextToken();
                                try
                                {
                                ynew=st1.nextToken();
                                }catch(Exception e){
                                fw.write(""+y);
                                }
                                
                                //System.out.println(ynew);
                                fw.write(""+ynew);
                          
                            fw.write("\r\n");
                            fw.flush();
                        }
                        }
                    }
                    
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
    }  // end of method removeTime
    
    public static void removeDuplicates()
        {
            TreeSet t=new TreeSet();
            try
            {
                BufferedReader br2=new BufferedReader(new FileReader("clean.txt"));
                FileWriter fw=new FileWriter("clean2.txt");
                String str2="";
                
                while(str2!=null)
                {
                    str2=br2.readLine();
                    //System.out.println(str2);
                    if(str2==null)
                        break;
                    t.add(str2);   
                }
                
                Iterator i=t.iterator();
                
                while(i.hasNext())
                {
                    fw.write(i.next()+"\r\n");
                    fw.flush();
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }  // end of method removeDuplicates
    
        public static void removeShortTweets()
        {
            int count;
            String[] str3=new String[40];
            try
            {
                BufferedReader br2=new BufferedReader(new FileReader("clean2.txt"));
                FileWriter fw=new FileWriter("clean3.txt");
                String str2="";
                
                while(str2!=null)
                {
                    str2=br2.readLine();
                    
                     if(str2==null)
                     {
                        System.out.println("Null reached");
                        break;
                     } 
                     str3=str2.split(" ");
                     count=str3.length-1;
                     
                     if(count>5)
                     {
                         System.out.println(str2+":"+count+"\n");
                         fw.write(str2+"\n");
                         fw.flush();
                     }
                         
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }  // end of method removeShortWords
    
        
        public static void removeDate()  // also adds fullstop at the end of sentence for POS tag
        {
            try
            {
                BufferedReader br2=new BufferedReader(new FileReader("clean3.txt"));
                FileWriter fw=new FileWriter("clean4.txt");
                
                String str="";
                String strWithoutDate;
                
                while(str!=null)
                {
                    str=br2.readLine();
                    strWithoutDate=str.substring(11);
                    fw.write(strWithoutDate+".\n");
                    fw.flush();
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
}
