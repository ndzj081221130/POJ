package p2151_probability;import java.util.Scanner; public class Main {	/**	 * @param args	 */	public static void main(String[] args) {		// TODO Auto-generated method stub		Scanner scanner = new Scanner(System.in);		String line = scanner.nextLine();		String[] arr = line.split(" ");		int M = Integer.parseInt(arr[0]);		int T = Integer.parseInt(arr[1]);		int N = Integer.parseInt(arr[2]);		while (!(M == 0 && T == 0 && N == 0)) {			double[][] pros = new double[T][M];			for (int row_i = 0; row_i < T; row_i++) {				String p = scanner.nextLine();				String[] pr = p.split(" ");				for (int col_j = 0; col_j < M; col_j++) {					pros[row_i][col_j] = Double.parseDouble(pr[col_j]);				}			}			cal(pros, M, T, N);			line = scanner.nextLine();			arr = line.split(" ");			M = Integer.parseInt(arr[0]);			T = Integer.parseInt(arr[1]);			N = Integer.parseInt(arr[2]);		}	}	static void print(double[][] pros, int t, int m) {		for (int i = 0; i < t; i++) {			for (int j = 0; j < m; j++) {				System.out.print(pros[i][j] + " ");			}			System.out.println();		}	}	private static void cal(double[][] pros, int m, int t, int n) {		// TODO Auto-generated method stub		Double[][][] F = new Double[t][m + 1][n + 1];		for (int i = 0; i < t; i++) {			for (int j = 0; j < m + 1; j++) {				for (int k = 0; k < n + 1; k++) {					F[i][j][k] = 0.0;				}			}		}//		print(pros, t, m);		for (int i = 0; i < t; i++) {			F[i][0][0] = 1.0;			for (int j = 1; j <= m; j++) {				for (int k = 0; k <= j; k++) {					F[i][j][k] = F[i][j - 1][k] * (1 - pros[i][j - 1]);					if (k != 0) {						F[i][j][k] += F[i][j - 1][k - 1] * pros[i][j - 1];					}				}			}		}		Double s[][] = new Double[t][n + 1];		for (int i = 0; i < t; i++) {			for (int j = 0; j < n + 1; j++) {				s[i][j] = 0.0;				s[i][j] += F[i][m][j];			}		}		double p1 = 1.0f;		double p2 = 1.0f;		for (int i = 0; i < t; i++) {			p1 *= s[i][m] - s[i][0];		}		for (int i = 0; i < t; i++) {			p2 *= s[i][n - 1] - s[i][0];		}		// return		double res = p1 - p2;//		System.out.println(res);				double temp1, temp;	    temp = 1;	    for (int i = 1; i <= t; i++){	        temp1 = 0;	        for (int j = 1; j <= m; j++){	            temp1 += F[i-1][m][j];	        }	        temp *= temp1;	    }	    double ans = temp; // p1	    temp = 1;	    for (int i = 1; i <= t; i++){	        temp1 = 0;	        for (int j = 1; j < n; j++){	            temp1 += F[i-1][m][j];	        }	        temp *= temp1;	    }	    System.out.println( ans - temp); 	}}