package script;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import algorithm.knn.KNN;

public class Test_KNN_Wine {
	/** 
     * 从数据文件中读取数据 
     * @param datas 存储数据的集合对象 
     * @param path 数据文件的路径 
     */  
    public void read(List<List<Double>> datas, String path){  
        
    	FileReader fr=null;
		BufferedReader br=null;
		String line=null;
        List<Double> l = null;  
        try {  
            fr=new FileReader(path);
			br=new BufferedReader(fr);
            while ((line=br.readLine()) != null) {  
                StringTokenizer sz = new StringTokenizer(line);
                l = new ArrayList<Double>();  
                while(sz.hasMoreTokens()){
                	l.add(Double.parseDouble(sz.nextToken()));
                }
                datas.add(l);  
                line = br.readLine();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	public static void main(String[] args) {
		Test_KNN_Wine t = new Test_KNN_Wine();
		String trainfile,testfile;
		/*
        trainfile = "dataset/winedata/winedata7/train.data";  
        testfile = "dataset/winedata/winedata7/test.data";  
        */
		trainfile = "dataset/textdata/1/train.data";  
        testfile = "dataset/textdata/1/test.data";  
        int count=0;
        int flag=0;
        try {  
            List<List<Double>> trainDatas = new ArrayList<List<Double>>();  
            List<List<Double>> testDatas = new ArrayList<List<Double>>();  
            t.read(trainDatas, trainfile);  
            t.read(testDatas, testfile);  
            KNN knn = new KNN();  
            for (int i = 0; i < testDatas.size(); i++) {  
                List<Double> test = testDatas.get(i);  
                System.out.print("测试元组: ");  
                /*
                for (int j = 0; j < test.size(); j++) {  
                    System.out.print(test.get(j) + " ");  
                } 
                */ 
                System.out.print("类别为: "); 
                if(Float.parseFloat((knn.knn(trainDatas, test, 3)))==test.get(0))
                {
                	count++;
                	flag=1;
                }
                System.out.println(Math.round(Float.parseFloat((knn.knn(trainDatas, test, 3))))+" flag:"+flag); 
                flag=0;
            } 
            System.out.println("accurate:"+count*1.0/testDatas.size()*100+"%");
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}

}
