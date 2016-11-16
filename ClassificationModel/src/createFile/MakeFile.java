package createFile;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import createFile.OriginalData;

public class MakeFile {
	public static ArrayList<OriginalData> data=new ArrayList<OriginalData>();
	public static void readData(String dataPath){
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
		try {
			fr=new FileReader(dataPath);
			br=new BufferedReader(fr);
			OriginalData iris;
			double a1,a2,a3,a4;
			String a5;
			while ((line=br.readLine())!=null) {
				String strArray[] = line.split(",");
				a1=Double.parseDouble(strArray[0]);
				a2=Double.parseDouble(strArray[1]);
				a3=Double.parseDouble(strArray[2]);
				a4=Double.parseDouble(strArray[3]);
				a5=strArray[4];
				iris=new OriginalData(a1, a2, a3, a4, a5);
				data.add(iris);
			}
			showList(data);
			br.readLine();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void showList(ArrayList<OriginalData> data){
		System.out.println("dataSet:");
		for(int i=0;i<data.size();i++){
			System.out.println(data.get(i).toString());
		}
	}
	public static void makeFile(String trainPath,int trainNum,String testPath,int testNum){
		FileOutputStream fos1=null;
		FileOutputStream fos2=null;
		PrintStream ps1=null;
		PrintStream ps2=null;
		try {
			fos1=new FileOutputStream(trainPath);
			fos2=new FileOutputStream(testPath);
			ps1=new PrintStream(fos1);
			ps2=new PrintStream(fos2);
			int n1=trainNum/3;
			int n2=testNum/3;
			int i,j=0;
			boolean flag[]=new boolean[data.size()];
			for(i=0;i<data.size();i++){
				flag[i]=false;
			}
			//随机生成分类为1的train和test的数据集
			for(i=0;i<50;i++){
				int loc=(int) (Math.random()*n1);
				if(j<n1&&flag[loc]==false){
					ps1.println(data.get(loc).toString());
					flag[loc]=true;
					j++;
				}
				else if(j==n1)
					break;
				else
					i--;
			}
			for(i=0;i<50;i++){
				if(flag[i]==false)
					ps2.println(data.get(i).toString());
			}
			//随机生成分类为2的train和test的数据集
			j=0;
			for(i=50;i<100;i++){
				int loc=50+(int) (Math.random()*n1);
				if(j<n1&&flag[loc]==false){
					ps1.println(data.get(loc).toString());
					flag[loc]=true;
					j++;
				}
				else if(j==n1)
					break;
				else
					i--;
			}
			for(i=50;i<100;i++){
				if(flag[i]==false)
					ps2.println(data.get(i).toString());
			}
			//随机生成分类为3的train和test的数据集
			j=0;
			for(i=100;i<150;i++){
				int loc=100+(int) (Math.random()*n1);
				if(j<n1&&flag[loc]==false){
					ps1.println(data.get(loc).toString());
					flag[loc]=true;
					j++;
				}
				else if(j==n1)
					break;
				else
					i--;
			}
			for(i=100;i<150;i++){
				if(flag[i]==false)
					ps2.println(data.get(i).toString());
			}
			ps1.close();
			ps2.close();
			fos1.close();
			fos2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		String dataPath="dataset/irisdata/iris.txt";
		String trainPath="dataset/output/iris/iris10/irisTrain.data";
		String testPath="dataset/output/iris/iris10/irisTest.data";
		int trainNum=78;
		int testNum=72;
		readData(dataPath);
		makeFile(trainPath, trainNum, testPath, testNum);
	}

}
