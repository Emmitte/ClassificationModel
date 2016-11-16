package algorithm.bayes.iris;

public class OriginalData {
	private double A1;
	private double A2;
	private double A3;
	private double A4;
	private double A5;
	public OriginalData(){
		
	}
	public OriginalData(double A1,double A2,double A3,double A4,double A5){
		this.A1=A1;
		this.A2=A2;
		this.A3=A3;
		this.A4=A4;
		this.A5=A5;
	}
	public double getA1() {
		return A1;
	}
	public void setA1(double a1) {
		A1 = a1;
	}
	public double getA2() {
		return A2;
	}
	public void setA2(double a2) {
		A2 = a2;
	}
	public double getA3() {
		return A3;
	}
	public void setA3(double a3) {
		A3 = a3;
	}
	public double getA4() {
		return A4;
	}
	public void setA4(double a4) {
		A4 = a4;
	}
	public double getA5() {
		return A5;
	}
	public void setA5(double a5) {
		A5 = a5;
	}
	
	public double getAi(int i){
		switch (i) {
		case 0:
			return A2;
		case 1:
			return A3;
		case 2:
			return A4;
		case 3:
			return A5;
		default:
			break;
		}
		return 0;
	}
	@Override
	public String toString() {
		return "OriginalData [A1=" + A1 + ", A2=" + A2 + ", A3=" + A3 + ", A4="
				+ A4 + ", A5=" + A5 + "]";
	}
	
}
