package script;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import algorithm.softmax.LogisticRegression;


public class Test_Softmax {
	/**
	 * input:path
	 * output:List<List<Double>>
	 * 读取iris数据集,以list矩阵返回
	 */
	public static List<List<Double>> read_iris(String path) {
		List<List<Double>> dataSet = new ArrayList<List<Double>>();
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
        List<Double> data = null; 
        String category;
        double c;
        try {  
            fr=new FileReader(path);
			br=new BufferedReader(fr);
            while ((line=br.readLine()) != null) {  
                String strArray[] = line.split(",");  
                data = new ArrayList<Double>();
                category=strArray[strArray.length-1];
				if(category.endsWith("Iris-setosa"))
					c=0;
				else if(category.endsWith("Iris-versicolor"))
					c=1;
				else
					c=2;
				data.add(c);
                for (int i = 0; i < strArray.length-1; i++) {  
                	data.add(Double.parseDouble(strArray[i]));  
                }  
                dataSet.add(data);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return dataSet;
	}
	/**
	 * input:path
	 * output:List<List<Double>>
	 * 读取wine数据集,以list矩阵返回
	 */
	public static List<List<Double>> read_wine(String path) {
		List<List<Double>> dataSet = new ArrayList<List<Double>>();
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
        List<Double> data = null; 
        double category;
        try {  
            fr=new FileReader(path);
			br=new BufferedReader(fr);
            while ((line=br.readLine()) != null) {  
                String strArray[] = line.split("\t");  
				data = new ArrayList<Double>();
				category = Double.parseDouble(strArray[0]); //标签从0开始标记
				//data.add(category - 1);
				data.add(category);
				for (int i = 1; i < strArray.length; i++) {
					data.add(Double.parseDouble(strArray[i]));
				}
				dataSet.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSet;
	}
	/**
	 * input:dataSet,writePath,col
	 * 归一化,根据指定col列,归一化当前列的内容,生成新文件
	 */
	public static void makeNormalization1(List<List<Double>> dataSet, String writePath, int col) {

		FileOutputStream fos=null;
		PrintStream ps=null;
		double min = 0.0,max = 0.0,temp = 0.0;
		
		try {  
			fos=new FileOutputStream(writePath);
			ps=new PrintStream(fos);
            for(int i = 0;i < dataSet.size();i++){
            	if(dataSet.get(i).get(col) < min){
            		min = dataSet.get(i).get(col);
            	}
            	if(dataSet.get(i).get(col) > max){
            		max = dataSet.get(i).get(col);
            	}
            }
            System.out.println("max="+max+" min="+min);
            for(int i = 0;i < dataSet.size();i++){
            	temp = (dataSet.get(i).get(col) - min) / (max - min);
            	ps.println(temp);
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}	
	/**
	 * input:trainPath,testPath,n_in
	 * 其中trainPath:训练数据集路径路径,testPath:测试数据集路径,n_in:输入维度
	 * softmax进行训练及测试
	 */
	public static void process(String trainPath,String testPath,int n_in,int n_out) {
		/*
		String dir = "data/iris/irisdata9/";
		String trainPath = dir + "irisTrain.data";
		String testPath = dir + "irisTest.data";
		*/
		//String dir = "data/wine/winedata0/";
		//String dir = "data/wine/winedata16/";
		/*
		String dir = "data/dl/9/";
		String trainPath = dir + "train.data";
		String testPath = dir + "test.data";
		*/
		List<List<Double>> trainData = null,testData = null;
		//trainData = read_iris(trainPath);
		//testData = read_iris(testPath);
		trainData = read_wine(trainPath);
		testData = read_wine(testPath);
		double learning_rate = 0.1;
        int n_epochs = 500;

        int train_N = trainData.size();
        int test_N = testData.size();
        
        double[] train_X = null;
        int[] train_Y = null;
        double[] test_X = null;
        double[] test_Y = null;
        
        int count=0;

		LogisticRegression classifier = new LogisticRegression(train_N, n_in,
				n_out);

		// train
		for (int epoch = 0; epoch < n_epochs; epoch++) {
			for (int i = 0; i < train_N; i++) {
				train_X = new double[n_in];
				train_Y = new int[n_out];
				
				for(int j = 0;j < n_in;j++){
					train_X[j] = trainData.get(i).get(j+1);
				}
				for(int j = 0;j < n_out;j++){
					if(trainData.get(i).get(0) == (j)){
						train_Y[j] = 1;
					}else {
						train_Y[j] = 0;
					}
				}
				classifier.train(train_X, train_Y, learning_rate);
			}
			// learning_rate *= 0.95;
		}
		int i;
		System.out.println("predict:");
		// test
        for(i=0; i<test_N; i++) {
        	test_X = new double[n_in];
        	test_Y = new double[n_out];
        	for(int j = 0;j < n_in;j++){
        		test_X[j] = testData.get(i).get(j+1);
        	}
        	/*
        	for(int j = 0;j < n_out;j++){
        		if(testData.get(i).get(0) == j){
        			test_Y[j] = 1;
        		}else {
					test_Y[j] = 0;
				}
        	}
        	*/
            classifier.predict(test_X, test_Y);
            double max;
            max = test_Y[0];
            for(int j=1; j<n_out; j++) {
            	if(max < test_Y[j]){
            		max = test_Y[j];
            	}
            }
            for(int j=0; j<n_out; j++) {
                System.out.print(test_Y[j] + " ");
            	/*
            	if(test_Y[j] > 0.5){
            		System.out.print(1 + " ");
            	}else {
            		System.out.print(0 + " ");
				}
            	*/
                
            	if(max == test_Y[j] && j == testData.get(i).get(0)){
            		count++;
            		//System.out.print("right ");
            	}
            	
            	/*
            	if(test_Y[j] > 0.5 && j == testData.get(i).get(0)){
            		count++;
            		//System.out.print("right ");
            	}
            	*/
            }
            System.out.println();
            System.out.println("accurate:" + count*1.0/(i+1)*100 + "%");
        }
        //System.out.println(test_N);
        System.out.println("accurate:" + count*1.0/test_N*100 + "%");
	}
	
	public static void main(String[] args) {
		String dir = args[0] + "/";        //训练数据集和测试数据集所在的目录
		String trainPath = dir + args[1];  //训练数据集路径路径,文件必须为第一列为标签数据(其中标签从0开始标记),剩余列为x数据
		String testPath = dir + args[2];   //测试数据集路径,文件必须为第一列为标签数据(其中标签从0开始标记),剩余列为x数据
		int n_in = Integer.parseInt(args[3]);  //输入维度
		int n_out = Integer.parseInt(args[4]); //输出维度
		process(trainPath,testPath,n_in,n_out);
	}

}
