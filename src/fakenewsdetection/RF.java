/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsdetection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 *
 * @author Aparna
 */
public class RF {
    
    public static final String TRAINING_DATA_SET_FILENAME="train.arff";
	public static final String TESTING_DATA_SET_FILENAME="test.arff";
	
   
        

	/** file names are defined*/
	

	/**
	 * This method is to load the data set.
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Instances getDataSet(String fileName) throws IOException {
		/**
		 * we can set the file i.e., loader.setFile("finename") to load the data
		 */
		int classIdx = 6;
		/** the arffloader to load the arff file */
		ArffLoader loader = new ArffLoader();
		/** load the traing data */
		loader.setFile(new File("test.arff"));//setSource(RandomForestDemo.class.getResourceAsStream("/" + fileName));
		/**
		 * we can also set the file like loader3.setFile(new
		 * File("test-confused.arff"));
		 */
		//loader.setFile(new File(fileName));
		Instances dataSet = loader.getDataSet();
		/** set the index based on the data given in the arff files */
		dataSet.setClassIndex(classIdx);
		return dataSet;
	}

	/**
	 * This method is used to process the input and return the statistics.
	 * 
	 * @throws Exception
	 */
        
         public static void main(String[]args)
         {
        try {
           // process();
            
             BufferedReader br;
        int numFolds = 2;
        br = new BufferedReader(new FileReader("train.arff"));
 
        Instances trainData = new Instances(br);
        trainData.setClassIndex(trainData.numAttributes() - 1);
        br.close();
        
        
        
         BufferedReader br1;
        
        br1 = new BufferedReader(new FileReader("test.arff"));
 
        Instances testdata = new Instances(br1);
        testdata.setClassIndex(trainData.numAttributes() - 1);
        br1.close();
        
        RandomForest rf = new RandomForest();
        rf.setNumTrees(100);         
     
        Evaluation evaluation = new Evaluation(testdata);
        
        evaluation.crossValidateModel(rf, trainData, numFolds, new Random(1));
        rf.buildClassifier(trainData);
        
        PrintWriter out = new PrintWriter("orf_out");
        out.println("No.\tTrue\tPredicted");
        for (int i = 0; i < testdata.numInstances(); i++)      
        {
            String trueClassLabel;
            trueClassLabel = trainData.instance(i).toString(trainData.classIndex());
             // Discreet prediction
            double predictionIndex = 
            rf.classifyInstance(testdata.instance(i)); 

            // Get the predicted class label from the predictionIndex.
            String predictedClassLabel;            
            predictedClassLabel = testdata.classAttribute().value((int) predictionIndex);
            out.println((i+1)+"\t"+trueClassLabel+"\t"+predictedClassLabel);
            
            TestGui.jTextField2.setText(predictedClassLabel);
        }
        
        out.println(evaluation.toSummaryString("\nResults\n======\n", true));
        out.println(evaluation.toClassDetailsString());
        out.println("Results For Class -1- ");
        out.println("Precision=  " + evaluation.precision(0));
        out.println("Recall=  " + evaluation.recall(0));
        out.println("F-measure=  " + evaluation.fMeasure(0));
        out.println("Results For Class -2- ");
        out.println("Precision=  " + evaluation.precision(1));
        out.println("Recall=  " + evaluation.recall(1));
        out.println("F-measure=  " + evaluation.fMeasure(1)); 
        out.close();
    
            
        } catch (Exception ex) {
            Logger.getLogger(RF.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    
    
	public static void process() throws Exception {

		Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
		
		
		
		RandomForest forest=new RandomForest();//numTrees, null, null);
		forest.setNumTrees(4);
		
		
		/** */
		forest.buildClassifier(trainingDataSet);
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
		Evaluation eval = new Evaluation(trainingDataSet);
		eval.evaluateModel(forest, testingDataSet);
	
		
		/** Print the algorithm summary */
		System.out.println("** Decision Tress Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(forest);
		System.out.println(eval.toMatrixString());
		 System.out.println(eval.toClassDetailsString());
		
	}
}
    

