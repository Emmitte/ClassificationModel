package algorithm.bayes.iris;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Bayes {
	private final int attributeNum=4; 
	private int TrainNum;      
	private int TestNum;
	
	private ArrayList<OriginalData> trainData=new ArrayList<OriginalData>();
	
	private ArrayList<OriginalData> testData=new ArrayList<OriginalData>();
	
	private double A[]=new double[3];      
	int m;  
	
	private Map[] C1_map=new TreeMap[attributeNum];
	private Map[] C2_map=new TreeMap[attributeNum];
	private Map[] C3_map=new TreeMap[attributeNum];
	
	private Map[] C1_rate_map=new TreeMap[attributeNum];
	private Map[] C2_rate_map=new TreeMap[attributeNum];
	private Map[] C3_rate_map=new TreeMap[attributeNum];
	
	public Bayes(){
		this.m=0;
		int i=0;
		for(i=0;i<3;i++)
			A[i]=0;
		for(i=0;i<attributeNum;i++){
			C1_map[i]=new TreeMap<Double, Integer>();
			C2_map[i]=new TreeMap<Double, Integer>();
			C3_map[i]=new TreeMap<Double, Integer>();
			C1_rate_map[i]=new TreeMap<Double, Double>();
			C2_rate_map[i]=new TreeMap<Double, Double>();
			C3_rate_map[i]=new TreeMap<Double, Double>();
		}
	}
	
	public int getTrainNum() {
		return TrainNum;
	}

	public void setTrainNum(int trainNum) {
		TrainNum = trainNum;
	}

	public int getTestNum() {
		return TestNum;
	}

	public void setTestNum(int testNum) {
		TestNum = testNum;
	}

	public void showList(ArrayList<OriginalData> data){
		System.out.println("dataSet:");
		for(int i=0;i<data.size();i++){
			System.out.println(data.get(i).toString());
		}
	}
	
	public void DataRead(String path){
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
		int num;
		String[] str=path.split("/"); 
		if ("irisTrain.data".endsWith(str[str.length-1]))
			num = TrainNum;
		else 
			num = TestNum;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			//line=br.readLine();
			OriginalData iris;
			ArrayList<OriginalData> data=new ArrayList<OriginalData>();
			double a1,a2,a3,a4,a5;
			String category;
			while ((line=br.readLine())!=null) {
				String strArray[] = line.split(",");
				a2=Double.parseDouble(strArray[0]);
				a3=Double.parseDouble(strArray[1]);
				a4=Double.parseDouble(strArray[2]);
				a5=Double.parseDouble(strArray[3]);
				category=strArray[4];
				if(category.endsWith("Iris-setosa"))
					a1=1;
				else if(category.endsWith("Iris-versicolor"))
					a1=2;
				else
					a1=3;
				iris=new OriginalData(a1, a2, a3, a4, a5);
				data.add(iris);
			}
			//showList(data);
			if(num==TrainNum)
			{
				trainData=data;
				//System.out.println("traindata:");
				//showList(trainData);
			}
			else
				testData=data;
			br.readLine();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void do_bayes(){
		int count1 = 0, count2 = 0, count3 = 0;
		int i;
		for(i = 0; i <   TrainNum  ; i++)
		{
			if(trainData.get(i).getA1() == 1)
			{
				count1 ++;
			}
			if(trainData.get(i).getA1() == 2)
			{
				count2 ++;
			}
			if(trainData.get(i).getA1() == 3)
			{
				count3 ++;
			}
		}
		
		A[0] = (double)count1/(double)TrainNum;      
		A[1] = (double)count2/(double)TrainNum;
		A[2] = (double)count3/(double)TrainNum;
		//System.out.println("A:"+A[0]+" "+A[1]+" "+A[2]);
		//Iterator<TreeMap<Double, Double>> p=C1_map[0].entrySet().iterator();
		//TreeMap<Double, Double> pipei=new TreeMap<Double, Double>();
		for(i = 0 ; i < TrainNum; i++){
			if(trainData.get(i).getA1() == 1)   
			{
				for(int j=0;j<attributeNum;j++)
				{
					double tmp=trainData.get(i).getAi(j);
					if(C1_map[j].containsKey(tmp)){
						int t= (int) C1_map[j].get(tmp);
						t++;
						C1_map[j].put(tmp, t);
					}else{
						C1_map[j].put(tmp, 1);
					}
				}
			}
			if(trainData.get(i).getA1() == 2)   
			{
				for(int j=0;j<attributeNum;j++)
				{
					double tmp=trainData.get(i).getAi(j);
					if(C2_map[j].containsKey(tmp)){
						int t= (int) C2_map[j].get(tmp);
						t++;
						C2_map[j].put(tmp, t);
					}else{
						C2_map[j].put(tmp, 1);
					}
				}
			}
			if(trainData.get(i).getA1() == 3)   
			{
				for(int j=0;j<attributeNum;j++)
				{
					double tmp=trainData.get(i).getAi(j);
					if(C3_map[j].containsKey(tmp)){
						int t= (int) C3_map[j].get(tmp);
						t++;
						C3_map[j].put(tmp, t);
					}else{
						C3_map[j].put(tmp, 1);
					}
				}
			}
		} 
		
		for(i = 0 ; i < TrainNum; i++){
			if(trainData.get(i).getA1() == 1)   
			{
				for(int j=0;j<attributeNum;j++)
				{
					double tmp=trainData.get(i).getAi(j);
					if(!C1_rate_map[j].containsKey(tmp)){
						int t= (int) C1_map[j].get(tmp);
						double pt=t*1.0/count1;
						C1_rate_map[j].put(tmp, pt);
					}
				}
			}
			if(trainData.get(i).getA1() == 2)   
			{
				for(int j=0;j<attributeNum;j++)
				{
					double tmp=trainData.get(i).getAi(j);
					if(!C2_rate_map[j].containsKey(tmp)){
						int t= (int) C2_map[j].get(tmp);
						double pt=t*1.0/count2;
						C2_rate_map[j].put(tmp, pt);
					}
				}
			}
			if(trainData.get(i).getA1() == 3)   
			{
				for(int j=0;j<attributeNum;j++)
				{
					double tmp=trainData.get(i).getAi(j);
					if(!C3_rate_map[j].containsKey(tmp)){
						int t= (int) C3_map[j].get(tmp);
						double pt=t*1.0/count3;
						C3_rate_map[j].put(tmp, pt);
					}
				}
			}
		} 
	}
	
	public void houyan(){
		int i,j,k;
		double p[]=new double[3];
		for(i=0;i<TestNum;i++){
			double pxc[]={0,0,0};
			for(j=0;j<3;j++){
				for(k=0;k<attributeNum;k++){
					double key=testData.get(i).getAi(k);
					//System.out.println("key="+key);
					if(C1_rate_map[k].containsKey(key))
						pxc[0]+=(double) C1_rate_map[k].get(key);
				}
				p[0]=A[0]+pxc[0];  
				for(k=0;k<attributeNum;k++){
					double key=testData.get(i).getAi(k);
					if(C2_rate_map[k].containsKey(key))
						pxc[1]+=(double) C2_rate_map[k].get(key);
				}
				p[1]=A[1]+pxc[1];  
				for(k=0;k<attributeNum;k++){
					double key=testData.get(i).getAi(k);
					if(C3_rate_map[k].containsKey(key))
						pxc[2]+=(double) C3_rate_map[k].get(key);
				}
				p[2]=A[2]+pxc[2];  
			}
			
			if(p[0]>p[1]&&p[0]>p[2]){
				//System.out.println((i+1)+":"+p[0]+"\t"+1);
				//System.out.println("other classification's rate:"+p[1]+"\t\t"+p[2]);
				if(testData.get(i).getA1()==1){
					m++;
				}
			}else{
				if(p[1]>p[2]){
					//System.out.println((i+1)+":"+p[1]+"\t"+2);
					//System.out.println("other classification's rate:"+p[0]+"\t\t"+p[2]);
					if(testData.get(i).getA1()==2){
						m++;
					}
				}else{
					//System.out.println((i+1)+":"+p[2]+"\t"+2);
					//System.out.println("other classification's rate:"+p[0]+"\t\t"+p[1]);
					if(testData.get(i).getA1()==3){
						m++;
					}
				}
			}
		}
	}
	public int getM(){
		return m;
	}
}
