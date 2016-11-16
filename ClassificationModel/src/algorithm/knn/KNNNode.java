package algorithm.knn;  
/** 
 * KNN结点类，用来存储�?近邻的k个元组相关的信息 
 */  
public class KNNNode {  
    private int index; // 元组标号  
    private double distance; // 与测试元组的距离  
    private String c; // �?属类�?  
    public KNNNode(int index, double distance, String c) {  
        super();  
        this.index = index;  
        this.distance = distance;  
        this.c = c;  
    }  
      
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
    public double getDistance() {  
        return distance;  
    }  
    public void setDistance(double distance) {  
        this.distance = distance;  
    }  
    public String getC() {  
        return c;  
    }  
    public void setC(String c) {  
        this.c = c;  
    }  
}