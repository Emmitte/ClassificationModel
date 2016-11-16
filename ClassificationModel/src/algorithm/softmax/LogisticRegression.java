package algorithm.softmax;

public class LogisticRegression {
    public int N;
    public int n_in;
    public int n_out;
    public double[][] W;
    public double[] b;

    public LogisticRegression(int N, int n_in, int n_out) {
        this.N = N;
        this.n_in = n_in;
        this.n_out = n_out;

        W = new double[n_out][n_in];
        b = new double[n_out];
    }

    public double[] train(double[] x, int[] y, double lr) {
        double[] p_y_given_x = new double[n_out];
        double[] dy = new double[n_out];

        for(int i=0; i<n_out; i++) {
            p_y_given_x[i] = 0;
            for(int j=0; j<n_in; j++) {
                p_y_given_x[i] += W[i][j] * x[j];
            }
            p_y_given_x[i] += b[i];
        }
        softmax(p_y_given_x);
        //theta¸üÐÂ
        for(int i=0; i<n_out; i++) {
            dy[i] = y[i] - p_y_given_x[i];

            for(int j=0; j<n_in; j++) {
                W[i][j] += lr * dy[i] * x[j] / N;
            }

            b[i] += lr * dy[i] / N;
        }

        return dy;
    }

    public void softmax(double[] x) {
        double max = 0.0;
        double sum = 0.0;

        for(int i=0; i<n_out; i++) 
        	if(max < x[i]) 
        		max = x[i];
      
        for(int i=0; i<n_out; i++) {
            x[i] = Math.exp(x[i] - max);
            sum += x[i];
        }
       
        for(int i=0; i<n_out; i++) {
        	x[i] /= sum;
        }
    }

    public void predict(double[] x, double[] y) {
        for(int i=0; i<n_out; i++) {
            y[i] = 0.;
            for(int j=0; j<n_in; j++) {
                y[i] += W[i][j] * x[j];
            }
            y[i] += b[i];
        }
        
        softmax(y);
    }
    
    public static void main(String[] args) {
        
    }
}
