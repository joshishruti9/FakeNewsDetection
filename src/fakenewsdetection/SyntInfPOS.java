/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author shruti
 */
public class SyntInfPOS {
    
    public static int positivecount=0;
    public static  int negativecount=0;
    public static int neutralcount=0;
    MaxentTagger tagger;
    public static ArrayList<Double> rev=new ArrayList<Double>();
    
    int nounCnt,verbCnt,adverbCnt,adjectiveCnt;
    
    ArrayList sentiments=new ArrayList();
        double count=0,count1=0;
	String text,str;
        static String  text1="";
        String feeling = "";
        double totcount=0.0,finalcnt=0;
       

        FileWriter[]fw=new FileWriter[6];
        FileWriter fw2;
	InputStream modelIn1 = null;  
	/**
	 * SentiWordNet object to query the polarity of a word.
	 */

        
        Hashtable<String,Integer> htt=new Hashtable<String,Integer>();
	
  HashSet[] keywords=new HashSet[6];

    
    public void load(String fileName,String outfile) {
          
            try
            
            {
                fw2=new FileWriter(outfile);
                //tagger = new MaxentTagger("F:\\2018\\stanfordnlp\\stanford-postagger-2017-06-09\\models\\english-bidirectional-distsim.tagger");
                tagger=new MaxentTagger("F:\\FakeNewsProject\\Code\\stanford-postagger-2017-06-09\\models\\english-bidirectional-distsim.tagger");

                System.out.println("File name "+fileName);
                List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(fileName)));
   
                for (List<HasWord> sentence : sentences) 
                {         
                    nounCnt=verbCnt=adjectiveCnt=adverbCnt=0;
                       //System.out.println("\n-----------------------------------------------------");
                       List<TaggedWord> tSentence = tagger.tagSentence(sentence);
       
                //System.out.println(SentenceUtils.listToString(tSentence, false));
                //BufferedReader br = new BufferedReader(new FileReader(fileName));
		//String line;
             text = "";
             Iterator<TaggedWord> it=tSentence.iterator();
             int j=0;
            // System.out.println();
                while(it.hasNext())
                     {
                        String tag="";
                        
                        TaggedWord v=it.next();                       
                        
                        tag=v.tag();
                        String word=tSentence.get(j).word();
                        
                       
                        if(tag.equals("NN")||tag.equals("NNS")||tag.equals("NNP")) tag="n";
                            else if (tag.equals("JJ")||tag.equals("JJR")||tag.equals("JJS")) tag="a"; 
                            else if (tag.equals("VB")||tag.equals("VBN")||tag.equals("VBG")||tag.equals("VBD")||tag.equals("VBZ")) tag="v"; 
                            else if (tag.equals("RB")||tag.equals("RBR")||tag.equals("RBS")) tag="r"; 
                        //System.out.println(word+"  "+tag);	
                       // String[] tokens = str.split(delimiters);
                       if(tag.equals("n")) nounCnt++;
                       else if(tag.equals("a")) adjectiveCnt++;
                       else if(tag.equals("v")) verbCnt++;
                       else if(tag.equals("r")) adverbCnt++;
                        
                    j++;
            } // end of while loop
         System.out.println("noun "+nounCnt);
         System.out.println("verb "+verbCnt);
         System.out.println("adjective "+adjectiveCnt);
         System.out.println("adverb"+adverbCnt);
                
         
         fw2.write(nounCnt+","+verbCnt+","+adjectiveCnt+","+adverbCnt+"\n");
         fw2.flush();
        // FakeNewsDetection.fwtrain.write(nounCnt+","+verbCnt+","+adjectiveCnt+","+adverbCnt+",");
         //FakeNewsDetection.fwtrain.flush();
    } // end of for loop
            }
                catch (IOException e) {
			System.out.println("Problem found when reading: " + e);
		}  
		
        }
    
}


    

