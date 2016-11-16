package algorithm.bayes.wine;

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
	private final int attributeNum=13; //特征个数
	private int TrainNum;      
	private int TestNum;
	
	private ArrayList<OriginalData> trainData=new ArrayList<OriginalData>();
	
	private ArrayList<OriginalData> testData=new ArrayList<OriginalData>();
	
	private double A[]=new double[3];      //标签概率
	int m;  
	
	//每种标签计算先验概率时的特征频数
	private Map[] C1_map=new TreeMap[attributeNum];
	private Map[] C2_map=new TreeMap[attributeNum];
	private Map[] C3_map=new TreeMap[attributeNum];
	
	//每种标签计算先验概率时的特征概率
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
	
	public void showList(ArrayList<OriginalData> data){
		System.out.println("dataSet:");
		for(int i=0;i<data.size();i++){
			System.out.println(data.get(i).toString());
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

	public void DataRead(String path){
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
		int num;
		String[] str=path.split("/");
		if ("train.data".endsWith(str[3]))
			num = TrainNum;
		else 
			num = TestNum;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			//line=br.readLine();
			OriginalData wine;
			ArrayList<OriginalData> data=new ArrayList<OriginalData>();
			double a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14;
			while ((line=br.readLine())!=null) {
				String strArray[] = line.split(",");
				a1=Double.parseDouble(strArray[0]);
				a2=Double.parseDouble(strArray[1]);
				a3=Double.parseDouble(strArray[2]);
				a4=Double.parseDouble(strArray[3]);
				a5=Double.parseDouble(strArray[4]);
				a6=Double.parseDouble(strArray[5]);
				a7=Double.parseDouble(strArray[6]);
				a8=Double.parseDouble(strArray[7]);
				a9=Double.parseDouble(strArray[8]);
				a10=Double.parseDouble(strArray[9]);
				a11=Double.parseDouble(strArray[10]);
				a12=Double.parseDouble(strArray[11]);
				a13=Double.parseDouble(strArray[12]);
				a14=Double.parseDouble(strArray[13]);
				wine=new OriginalData(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
				data.add(wine);
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
	/**
	 * 计算先验概率，模型训练
	 * */
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
			}//统计每种标签的频数
		}
		//计算每种标签占总量的比例
		A[0] = (double)count1/(double)TrainNum;      
		A[1] = (double)count2/(double)TrainNum;
		A[2] = (double)count3/(double)TrainNum;
		//System.out.println("A:"+A[0]+" "+A[1]+" "+A[2]);
		//Iterator<TreeMap<Double, Double>> p=C1_map[0].entrySet().iterator();
		//TreeMap<Double, Double> pipei=new TreeMap<Double, Double>();
		//计算先验概率
		for(i = 0 ; i < TrainNum; i++){
			if(trainData.get(i).getA1() == 1)   //为计算P(Xk|C1)做统计准备
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
			if(trainData.get(i).getA1() == 2)   //为计算P(Xk|C2)做统计准备
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
			if(trainData.get(i).getA1() == 3)   //为计算P(Xk|C3)做统计准备
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
		//计算先验概率
		for(i = 0 ; i < TrainNum; i++){
			if(trainData.get(i).getA1() == 1)   //计算P(Xk|C1) 
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
			if(trainData.get(i).getA1() == 2)   //计算P(Xk|C2)
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
			if(trainData.get(i).getA1() == 3)   //计算P(Xk|C1)
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
	
	/**
	 * 计算后验概率，模型预测
	 * */
	public void houyan(){
		int i,j,k;
		double p[]=new double[3];
		for(i=0;i<TestNum;i++){
			double pxc[]={0,0,0};
			for(j=0;j<3;j++){
				for(k=0;k<attributeNum;k++){
					double key=testData.get(i).getAi(k);
					if(C1_rate_map[k].containsKey(key))
						pxc[0]+=(double) C1_rate_map[k].get(key);
				}
				p[0]=A[0]+pxc[0];  //p(c)*sum{log(p(x|c))}
				for(k=0;k<attributeNum;k++){
					double key=testData.get(i).getAi(k);
					if(C2_rate_map[k].containsKey(key))
						pxc[1]+=(double) C2_rate_map[k].get(key);
				}
				p[1]=A[1]+pxc[1];  //p(c)*sum{log(p(x|c))}
				for(k=0;k<attributeNum;k++){
					double key=testData.get(i).getAi(k);
					if(C3_rate_map[k].containsKey(key))
						pxc[2]+=(double) C3_rate_map[k].get(key);
				}
				p[2]=A[2]+pxc[2];  //p(c)*sum{log(p(x|c))}
			}
			//找最大的后验概率作为预测值
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
