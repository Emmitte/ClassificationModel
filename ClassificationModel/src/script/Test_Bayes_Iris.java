package script;

import algorithm.bayes.iris.Bayes;

public class Test_Bayes_Iris {

	public static void main(String[] args) {
		Bayes bayes=new Bayes();
		String trainpath="dataset/irisdata/irisdata9/irisTrain.data";
		String testpath="dataset/irisdata/irisdata9/irisTest.data";
		bayes.setTrainNum(69);
		bayes.setTestNum(81);
		bayes.DataRead(trainpath);
		bayes.do_bayes();
		bayes.DataRead(testpath);
		bayes.houyan();
		double p;
		int n;
		n=bayes.getM();
		p=n*1.0/bayes.getTestNum()*100;
		System.out.println("accurate:"+p+"%");
	}

}
