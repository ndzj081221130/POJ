package p1328radar_wa;import java.util.ArrayList;import java.util.Scanner;public class Main {	public static void main(String[] args) {		// TODO Auto-generated method stub		// boolean [][]map = new boolean[6][6];		Scanner scanner = new Scanner(System.in);		String lineString = scanner.nextLine();		if (lineString.length() == 0)			return;		String[] part = lineString.split(" ");		int n;//		int d;//		ArrayList<myPoint> list;		int caseN = 0;		while (!(part[0].equals("0") && part[1].equals("0"))) {			list = new ArrayList<myPoint>();			n = Integer.parseInt(part[0]);			d = Integer.parseInt(part[1]);			for (int i = 0; i < n; i++) {				String string = scanner.nextLine();				String[] arr = string.split(" ");				myPoint point = new myPoint(Integer.parseInt(arr[0]),						Integer.parseInt(arr[1]), d);				list.add(point);			}			caseN++;			System.out.println("Case " + caseN + ": " + minRadar(n, d, mySort(list)));			lineString = scanner.nextLine();			lineString = scanner.nextLine();			part = lineString.split(" ");		}	}	private static ArrayList<myPoint> mySort(ArrayList<myPoint> list) {  		int i,j,k;		myPoint temp;		int n = list.size();		for ( i = 0; i < n-1; i++) { 			k = i;			for ( j = i+1; j < n; j++) { 				if(list.get(j).mostLeft() < list.get(k).mostLeft() )					k = j;			}						if(i!=k){				temp = list.get(i); 				list.set(i, list.get(k));				list.set(k, temp);			}		}		return list;	}	private static int minRadar(int n, int d, ArrayList<myPoint> list) {		// TODO Auto-generated method stub		// System.out.println(n+","+d+","+list.toString());		// ArrayList<E>		int count = 1;		myPoint cur = list.get(0);		// if (cur.legal() == false ) {		//		// }//		double cur_left = cur.mostLeft();		double cur_right = cur.mostRight();		for (int i = 1; i < list.size(); i++) {			myPoint next = list.get(i);			//			// if( next.legal() == false)			// {			// return -1;			// }			double next_left = next.mostLeft();			double next_right = next.mostRight();			if (next_left > cur_right) {				count++;				// cur_left = next_left;				cur_right = next_right;			} else {//next_left <= cur_right				if (next_right < cur_right) {					cur_right = next_right;				}			}		}		return count;	}	public static class myPoint {		int x;		int y;		int d;		public myPoint() {			// TODO Auto-generated constructor stub		}		public myPoint(int a, int b, int c) {			x = a;			y = b;			d = c;		}		public int getD() {			return d;		}		public void setD(int d) {			this.d = d;		}		public int getX() {			return x;		}		public void setX(int x) {			this.x = x;		}		public int getY() {			return y;		}		public void setY(int y) {			this.y = y;		}		public String toString() {			return "(" + x + "," + y + ",+" + d + ")";		}		public double mostLeft() {			double left = 0;			left = x - Math.sqrt(d * d - y * y);			return left;		}		public double mostRight() {			double right = 0;			right = x + Math.sqrt(d * d - y * y);			return right;		}		public boolean legal() {			if (y > d)				return false;			else {				return true;			}		}	}}