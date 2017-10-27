package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tool {
	
	public static FileOutputStream fos1 = null,fos2 = null,fos3 = null;
	public static PrintStream ps1 = null,ps2 = null,ps3 = null;
	
	/** 
	 * 初始化写文件器(单一指针)
	 * */
	public static void initWriter1(String writePath) {
		try {
			fos1 = new FileOutputStream(writePath);
			ps1 = new PrintStream(fos1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 关闭文件器(单一指针)
	 * */
	public static void closeWriter1() {
		try {
			ps1.close();
			fos1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 初始化写文件器(双指针)
	 * */
	public static void initWriter2(String writePath1,String writePath2) {
		try {
			fos1 = new FileOutputStream(writePath1);
			ps1 = new PrintStream(fos1);
			fos2 = new FileOutputStream(writePath2);
			ps2 = new PrintStream(fos2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 关闭文件器(双指针)
	 * */
	public static void closeWriter2() {
		try {
			ps1.close();
			fos1.close();
			ps2.close();
			fos2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 初始化写文件器(三指针)
	 * */
	public static void initWriter3(String writePath1,String writePath2,String writePath3) {
		try {
			fos1 = new FileOutputStream(writePath1);
			ps1 = new PrintStream(fos1);
			fos2 = new FileOutputStream(writePath2);
			ps2 = new PrintStream(fos2);
			fos3 = new FileOutputStream(writePath3);
			ps3 = new PrintStream(fos3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 关闭文件器(三指针)
	 * */
	public static void closeWriter3() {
		try {
			ps1.close();
			fos1.close();
			ps2.close();
			fos2.close();
			ps3.close();
			fos3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 计算F测度值
	 * */
	public static double getF_test(double p,double r) {
		double ret = 0.0;
		ret = (2 * p * r)/(p + r);
		return ret;
	}
	
}
