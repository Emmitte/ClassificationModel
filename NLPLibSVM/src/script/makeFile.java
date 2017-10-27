package script;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import util.Tool;

public class makeFile {
	
	public static FileReader fr1 = null;
	public static BufferedReader br1 = null;
	public static String line1 = null;
	public static FileReader fr2 = null;
	public static BufferedReader br2 = null;
	public static String line2 = null;
	
	/**
	 * input:path,m,n
	 * output:int[n][m]
	 * 读取数据(x)文件,生成一个n行m列的矩阵
	 */
	public static void changeFile(String x_path, String y_path) {
		
		double data_x[];
		int m,n,i,j;
		try {
			fr1 = new FileReader(x_path);
			br1 = new BufferedReader(fr1);
			fr2 = new FileReader(y_path);
			br2 = new BufferedReader(fr2);
			while ((line1 = br1.readLine()) != null && (line2 = br2.readLine())!= null) {
				String[] strArray = line1.split("\t");
				m = strArray.length;
				data_x = new double[m];
				for(j = 0;j < m;j++){
					data_x[j] = Double.parseDouble(strArray[j]);
				}
				Tool.ps1.print(line2+" ");
				for(i = 0;i < m;i++){
					Tool.ps1.print(" "+(i+1)+":"+data_x[i]);
				}
				Tool.ps1.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readFile(String path) {
		try {
			fr1 = new FileReader(path);
			br1 = new BufferedReader(fr1);
			while((line1 = br1.readLine()) != null){
				System.out.println(line1);
				String[] arr1 = line1.split("  ");
				System.out.println(arr1[0]);
				String[] arr2 = arr1[1].split(" ");
				System.out.println(arr2[0]);
				System.out.println(arr2[1]);
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * input:path
	 * output:List<Integer>
	 * 获取标签(Y)数据,以list形式返回
	 */
	public static List<Integer> getYList(String path) {
		List<Integer> list = new ArrayList<Integer>();
		int count = 0;
		try {  
            fr1=new FileReader(path);
			br1=new BufferedReader(fr1);
			
            while ((line1=br1.readLine()) != null ) {  
                StringTokenizer st = new StringTokenizer(line1);
                while(st.hasMoreElements()){
                	String str = st.nextToken();
                	list.add(Integer.parseInt(str));
                	count++;
                }
			}
            
            br1.close();
            br1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * input:Ypath,Xpath,n_c(类别的个数)
	 * 计算召回率、准确率、F-测度值
	 */
	public static void getResult(String Ypath,String Xpath,int n_c) {
		double max;
		int n,i,flag,count1,count2,loc;
		int[] P = new int[n_c];
		int[] TP = new int[n_c];
		int[] PN = new int[n_c];
		List<Integer> yList = getYList(Ypath);
		n = yList.size();
		fr1 = null;
		br1 = null;
		line1 = null;
		for(i = 0;i < n_c;i++){
			P[i] = 0;
			TP[i] = 0;
			PN[i] = 0;
		}
		flag = 1;
		for(i = 0;i < n;i++){
			P[yList.get(i)]++;
		}
		
		try {  
            fr1=new FileReader(Xpath);
			br1=new BufferedReader(fr1);
			count1 = 0;
			System.out.println("xpath="+Xpath);
            while ((line1 = br1.readLine()) != null ) { 
            	if(count1 == n){
            		break;
            	}
            	System.out.println("line="+line1);
            	if(Double.parseDouble(line1) == yList.get(count1)){
            		TP[yList.get(count1)]++;
            	}
            	PN[(int) Double.parseDouble(line1)]++;
            	count1++;
            }
            br1.close();
            br1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double[] r = new double[n_c];
		double[] p = new double[n_c];
		double[] f = new double[n_c];
		for(i = 0;i < n_c;i++){
			r[i] = TP[i]*1.0/P[i];
			p[i] = TP[i]*1.0/PN[i];
			f[i] = (2*p[i]*r[i])/(p[i]+r[i]);
			System.out.println(i+":");
			System.out.println("TP"+i+"="+TP[i]+" P"+i+"="+P[i]+" PN="+i+PN[i]);
			System.out.println("r"+i+"="+r[i]+"\tp"+i+"="+p[i]+"\tf"+i+"="+f[i]);
		}
	}
	
	

	public static void main(String[] args) {
		String dir = "data/text/13/";
		String x_train = dir + "train_x.txt";
		String y_train = dir + "train_y.txt";
		String x_test = dir + "test_x.txt";
		String y_test = dir + "test_y.txt";
		String writePath1 = dir + "text-tra";
		String writePath2 = dir + "text-test";
		/*
		Tool.initWriter1(writePath1);
		changeFile(x_train, y_train);
		Tool.closeWriter1();
		Tool.initWriter1(writePath2);
		changeFile(x_test, y_test);
		Tool.closeWriter1();
		*/
		/*
		String ret = dir + "text-result";
		getResult(y_test, ret, 3);
		*/
		
	}

}
