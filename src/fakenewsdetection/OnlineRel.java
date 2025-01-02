/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;
import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author shruti
 */
public class OnlineRel {
    BufferedReader brc3;
    HashSet stopwords=null;
    FileWriter fnew1;
    String searchTerm="";
    FileWriter fw;
    public OnlineRel(String f,String f2) throws Exception
    {
         //searchTerm = "Cannot remove Dhoni as his finalized bipoic sequel script contains 2019 World Cup";
        //searchTerm="NoConfidenceMotion\\n\\nMe to boss, during appraisal meeting"; 
       brc3=new BufferedReader(new FileReader(f));         
       fnew1=new FileWriter("WithoutStops.txt");
       fw=new FileWriter(f2);
    }
    
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    
    public void initSearchTerm()
    {
        try
        {
        String str="";
        String strWithoutDate="";
        while(str!=null)
        //for(int i=0;i<1;i++)
        {
            //str=brc3.readLine();
            str=brc3.readLine();
            if(str==null)
                break;
            strWithoutDate=str.substring(12);
            searchTerm=strWithoutDate;
            searchTerm=searchTerm.replace("\n", " ");
            System.out.println(searchTerm);
            OnlineSearch();
            jaccardsim();
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void jaccardsim()
    {
        int lineCount=0;
        double jaccc=0;
        
        try
        {
            
            ArrayList<String> serlist=new ArrayList<String>();
            
            String[]arr=searchTerm.split(" ");
            for(int i=0;i<arr.length;i++)
            {
                serlist.add(arr[i]);
            }
             FileReader fr = new FileReader("WithoutStops.txt");
            BufferedReader  br=new BufferedReader(fr);
            String str="";
            String f="";
            while(str!=null)
            {
                str=br.readLine();
                //System.out.println(str);
                lineCount++;
                if(str==null)
                    break;
            //Find the intersection between searchterm and str
                double cnt=0;
                  String[] arr1=str.split(" ");
                  
                  for(int j=0;j<arr1.length;j++)
                  {
                      if(serlist.contains(arr1[j]))
                      {
                          cnt++;
                      }
                  }

                
                  System.out.println("Intersection is  "+cnt);
                  
                  int tot=serlist.size()+arr1.length;
                  
                   jaccc+=cnt/(tot-cnt);
                
            } // end of while loop
            jaccc=jaccc/lineCount;
            System.out.println("Jaccard similarity is "+jaccc);
            writeJaccardToFile(jaccc);
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void writeJaccardToFile(double no)
    {
        try
        {
         fw.write(no+"\n");
          fw.flush();
          
          
         // FakeNewsDetection.fwtrain.write(no+",");
         // FakeNewsDetection.fwtrain.flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
	public void OnlineSearch(){
		//Taking search term input from console
		//Scanner scanner = new Scanner(System.in);
		//System.out.println("Please enter the search term.");
		
		//System.out.println("Please enter the number of results. Example: 5 10 20");
		int num = 100;//scanner.nextInt();
		//scanner.close();
		
                
                 String url = "https://www.google.com/search?q=" + searchTerm + "&num=10";
		//String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+num;
		//without proper User-Agent, we will get 403 error
                try
                {
		//Document doc = Jsoup.connect(searchURL).userAgent("\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) \" +\n" +
                 //  \"Chrome/30.0.1599.101 Safari/537.36\"").get();
		

 Document doc = Jsoup
    .connect(url)
    .userAgent("Jsoup client")
    .timeout(5000).get();
		//below will print HTML data, save it to a file and open in browser to compare
		//System.out.println(doc.html());
		
		//If google search results HTML change the <h3 class="r" to <h3 class="r1"
		//we need to change below accordingly
		Elements results = doc.select("h3.r > a");
                int count=0;
		for (Element result : results) {
                        count++;
			String linkHref = result.attr("href");
			String linkText = result.text();
			System.out.println(count+" Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
                        String str1=linkText;
            // while((str1=br1.readLine())!=null)
             {
              
                 String[] words=str1.split(" ");
                 
                 for(String eachword: words)
                 {
                     if(!stopwords.contains(eachword))
                     {
                         fnew1.write(eachword+" ");                         
                         fnew1.flush();
                     }
                     
                 }
                 
                 fnew1.write("\r\n");
                 fnew1.flush();
                 
                        
                        
		}
	}
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
        }
        
    public void stops()
    {
                   
        try
        {  
           
           stopwords=new HashSet();
            String dirname1="stops.txt";
            
           FileReader fr = new FileReader(dirname1);
            BufferedReader  br=new BufferedReader(fr);
            String str="";
            String f="";
            while(str!=null)
            {
                str=br.readLine();
                if(str==null)
                    break;
               stopwords.add(str.trim());
            }
       
            fr.close();
            br.close();
            
            
            /*BufferedReader br1=new BufferedReader(new FileReader("clean3.txt"));
             
             FileWriter fnew1=new FileWriter("WithoutStops.txt");
             
             String str1="";
             while((str1=br1.readLine())!=null)
             {
              
                 String[] words=str1.split(" ");
                 
                 for(String eachword: words)
                 {
                     if(!stopwords.contains(eachword))
                     {
                         fnew1.write(eachword+" ");
                        
                         
                         
                         
                         
                         fnew1.flush();
                     }
                     
                 }
                 
                 fnew1.write("\r\n");
                 fnew1.flush();
                 
             }
             
             fnew1.close();
         
            */
        }catch(Exception e)
        {
        
            System.out.println(e);
        }

    }
    
    
    
    
}
