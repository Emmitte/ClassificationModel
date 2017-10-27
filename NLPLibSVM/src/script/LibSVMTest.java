package script;
import java.io.IOException;
import SVMLib.*;
import libsvm.*;

public class LibSVMTest {

	/**JAVA test code for LibSVM
	 * @author yangliu
	 * @throws IOException 
	 * @blog http://blog.csdn.net/yangliuy
	 * @mail yang.liu@pku.edu.cn
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Test for svm_train and svm_predict
		//svm_train: 
		//	  param: String[], parse result of command line parameter of svm-train
		//    return: String, the directory of modelFile
		//svm_predect:
		//	  param: String[], parse result of command line parameter of svm-predict, including the modelfile
		//    return: Double, the accuracy of SVM classification
		/*
		String dir = "data/UCI/";
		String[] trainArgs = {dir+"UCI-breast-cancer-tra"};//directory of training file
		String modelFile = svm_train.main(trainArgs);
		String[] testArgs = {dir+"UCI-breast-cancer-test", modelFile, dir+"UCI-breast-cancer-result"};//directory of test file, model file, result file
		System.out.println("predict:");
		Double accuracy = svm_predict.main(testArgs);
		System.out.println("SVM Classification is done! The accuracy is " + accuracy);
		*/
		
		String dir = "data/text/6/";
		String[] trainArgs = {dir+"text-tra"};//directory of training file
		String modelFile = svm_train.main(trainArgs);
		String[] testArgs = {dir+"text-test", modelFile, dir+"text-result"};//directory of test file, model file, result file
		System.out.println("predict:");
		Double accuracy = svm_predict.main(testArgs);
		System.out.println("SVM Classification is done! The accuracy is " + accuracy);
		
		//Test for cross validation
		//String[] crossValidationTrainArgs = {"-v", "10", "UCI-breast-cancer-tra"};// 10 fold cross validation
		//modelFile = svm_train.main(crossValidationTrainArgs);
		//System.out.print("Cross validation is done! The modelFile is " + modelFile);
	}

}
