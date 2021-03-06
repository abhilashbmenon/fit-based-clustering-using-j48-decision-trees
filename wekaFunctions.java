import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;

public class wekaFunctions {
	
	public static FilteredClassifier train(Instances train, int classIndex) throws Exception
	{
		train.setClassIndex(classIndex);
		
		Remove rm = new Remove();
		rm.setAttributeIndices("1,7");  // REMOVING ID ATTRIBUTE AS THAT WON'T BE INPUT TO THE CLASSIFIER
		//rm.setAttributeIndices("1");
		// classifier
		J48 j48 = new J48();
		//j48.setUnpruned(true);        // using an unpruned J48
		// meta-classifier
		FilteredClassifier fc = new FilteredClassifier();
		fc.setFilter(rm);
		fc.setClassifier(j48);
		// train
		fc.buildClassifier(train);
		return fc;
		
	}
	
	public static double eval(FilteredClassifier fc, Instances train, Instances test)  throws Exception
	{
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(fc, test);
		return eval.pctCorrect();
	}

	public static double evalCrossValidation(FilteredClassifier fc, Instances data) throws Exception
	{
		Random random = new Random();
		Evaluation eval = new Evaluation(data);
		eval.crossValidateModel(fc, data, 10, random);
		return eval.pctCorrect();
	}
	
	public static double trainAndEval(Instances train, Instances test) throws Exception{
		
		Remove rm = new Remove();
		rm.setAttributeIndices("1");  // remove 1st attribute
		// classifier
		J48 j48 = new J48();
		j48.setUnpruned(true);        // using an unpruned J48
		// meta-classifier
		FilteredClassifier fc = new FilteredClassifier();
		fc.setFilter(rm);
		fc.setClassifier(j48);
		// train
		fc.buildClassifier(train);
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(fc, test);
		return eval.pctCorrect();
		
	}
        
        public static Instances trim(Instances data, int classIndex){
                //int count = 0;
                for (int i = data.numInstances()-1; i>=0; i--){
                        //System.out.println(data.instance(i).stringValue(classIndex-1));
                        if (!data.instance(i).stringValue(classIndex-1).equals("always")){
                                //count++;
                                data.delete(i);
                        }
                }
                //System.out.println("Not ALWALS INSTANCES #: "+count);
		return data;
    }
}
