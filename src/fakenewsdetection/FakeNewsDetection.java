/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;

import TFIDF.*;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shruti
 */
public class FakeNewsDetection {

    public static FileWriter fwtrain=null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            
            // TODO code application logic here
            
          
            
            OnlineRel o=new OnlineRel("clean3.txt","jaccardfake.txt");
            
            o.stops();
            o.OnlineSearch();
            o.initSearchTerm();
            
            SyntInfPOS pos=new SyntInfPOS();
            pos.load("clean4.txt","posfake.txt");
            
            //Trigram.callTrigram("clean4.txt","faketrigrams.txt");
            
//            TfidfCreateFiles.seperateFiles("clean4.txt","fake");
//                     
//            String[]arg=new String[]{"fake"};
//            TfIdfMain.main(arg);
            
               ARI a1=new ARI("clean4.txt","arifake.txt");
            
            
            
            //Process the real news data
            
            
        
            
          OnlineRel   o1=new OnlineRel("real.txt","jaccardreal.txt");
            
            o1.stops();
            o1.OnlineSearch();
            o1.initSearchTerm();
            
          SyntInfPOS   pos1=new SyntInfPOS();
            pos1.load("real.txt","posreal.txt");
            
            //Trigram.callTrigram("real.txt","trigrams.txt");
            
//            TfidfCreateFiles.seperateFiles("real.txt","real");            
//            //TfIdfMain.callTfidf();            
//            
//            String[]arg1=new String[]{"real"};
//            TfIdfMain.main(arg1);
            
          ARI   a=new ARI("real.txt","arireal.txt");
            
        } catch (Exception ex) {
            Logger.getLogger(FakeNewsDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
