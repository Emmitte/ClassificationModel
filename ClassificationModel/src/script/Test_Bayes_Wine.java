package script;

import algorithm.bayes.wine.Bayes;

public class Test_Bayes_Wine {

	public static void main(String[] args) {
		Bayes bayes=new Bayes();
		String trainpath="dataset/winedata/winedata7/train.data";
		String testpath="dataset/winedata/winedata7/test.data";
		bayes.setTrainNum(88);
		bayes.setTestNum(90);
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
