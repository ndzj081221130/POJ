package p2253_ac_dijkstra;import java.text.DecimalFormat;import java.util.ArrayList;import java.util.Scanner;public class Main {	/**	 * @param args	 */	static void input(ArrayList<Point> list){//		System.out.println("Frog Distance = " + dijkstra());//		System.out.println();		int n = list.size();		boolean s[] = new boolean[n];		double dist[] = new double[n];		int v = 0;		int path[] = new int[n];		for (int i = 0; i < n; i++) {			dist[i] = cal(list,v,i);			s[i] = false;			if (i!=v && dist[i] < Double.MAX_VALUE ) {				path[i]=v;			}else {				path[i] = -1;			}		}				s[v] = true;//		System.out.println(dist);		dist[v] = 0;		double min,w;		for (int i = 0; i < n-1; i++) {			min = Double.MAX_VALUE;			int u = v;			for (int j = 0; j < n; j++)  				if(s[j] == false && dist[j] < min){					u = j;					min = dist[j];				}			s[u] = true;				for (int k = 0; k < n; k++) {				w = cal(list, u, k);				if (s[k] == false && w < Double.MAX_VALUE && dist[u]  < dist[k] &&  w < dist[k]) {					if(dist[u] < w)						dist[k] = w;					else {						dist[k] = dist[u] ;					}					path[k] = u;				}			} 		}		DecimalFormat df = new DecimalFormat("0.000");		String num = df.format(dist[1]);//		System.out.println(num);		System.out.println("Frog Distance = "+num);		System.out.println();	}		private static double cal(ArrayList<Point> list, int v, int i) { 		if(v == i)		return 0;		else {			int v_x = list.get(v).getX();			int v_y = list.get(v).getY();			int i_x = list.get(i).getX();			int i_y = list.get(i).getY();			return Math.sqrt( (v_x - i_x)*(v_x - i_x) + ( v_y - i_y )*( v_y - i_y));		}	}	public static void main(String[] args) {		// TODO Auto-generated method stub		Scanner scanner = new Scanner(System.in);		int n = scanner.nextInt();		int index = 1;		while(n!=0){ 			ArrayList<Point> list = new ArrayList<Point>();			for (int i = 0; i < n; i++) {				list.add(new Point(scanner.nextInt(),scanner.nextInt()));			}			System.out.println("Scenario #"+ index);			input(list);			index ++;			n = scanner.nextInt();		}	}	static class Point{		int x;				int y;		public Point(int x,int y) {			this.x = x;			this.y=y;			 		}		public int getX() {			return x;		}		public void setX(int x) {			this.x = x;		}		public int getY() {			return y;		}		public void setY(int y) {			this.y = y;		}			}}