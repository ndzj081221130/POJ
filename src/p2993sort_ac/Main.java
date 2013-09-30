package p2993sort_ac;import java.util.ArrayList;import java.util.Scanner; public class Main {	/**	 * @param args	 */	public static void main(String[] args) {		// TODO Auto-generated method stub		Scanner scanner = new Scanner(System.in);		 		String lineWhite = scanner.nextLine();// +--- line 1		String[] arrWhite = lineWhite.split( " ");		String[] listWhite = arrWhite[1].split(",");				String lineBlack = scanner.nextLine();		String[]arrBlack = lineBlack.split(" ");		String[]listBlack = arrBlack[1].split(",");				ArrayList<Black> blacks = new ArrayList<Black>();		for (int i = 0; i < listBlack.length; i++) {//			System.out.println("i="+i);			String string = listBlack[i]; 			int col;			int row;			char digit;			if (string.length() == 3) {				row = Integer.parseInt(string.charAt(2)+"");				col = string.charAt(1) - 'a' + 1;				digit = string.charAt(0);			}else{				row = Integer.parseInt(string.charAt(1)+"");				col = string.charAt(0) - 'a' + 1;				digit = 'p';			}			blacks.add(new Black(col, row, digit));		}//		System.out.println(blacks);		ArrayList<White> whites = new ArrayList<White>();		for (int i = 0; i < listWhite.length; i++) {//			System.out.println("i="+i);			String string = listWhite[i]; 			int col;			int row;			char digit;			if (string.length() == 3) {				row = Integer.parseInt(string.charAt(2)+"");				col = string.charAt(1) - 'a' + 1;				digit = string.charAt(0);			}else{				row = Integer.parseInt(string.charAt(1)+"");				col = string.charAt(0) - 'a' + 1;				digit = 'P';			}			whites.add(new White(col, row, digit));		}//		System.out.println(whites);		System.out.println("+---+---+---+---+---+---+---+---+");			String[][] map = new String[8][8];		int count = 0;		for (int i = 0; i < 8; i++) {			for (int j = 0; j < 8 ; j++) {				if(count % 2 ==0)					map[i][j] = "...|";				else {					map[i][j] = ":::|";				}				count++;			}			count++;		}				map = locateBlack(blacks,map,8);		map = locateWhite(whites, map, 8);		print(map,8);//		Sort(whites);//		mySort(blacks);	}	 private static String[][] locateBlack(ArrayList<Black> blacks,			String[][] map, int n) {		// TODO Auto-generated method stub		 for (int j = 0; j < blacks.size(); j++) {			 Black black = blacks.get(j);			 int row = black.getRow();			 int col = black.getCol();			 String digit = black.getDigital()+"";			 String string = map[8-row][col-1];			 char d = string.charAt(0);			 map[8-row][col-1] =  d+""+digit.toLowerCase()+""+d+"|";//string.re		}		return map;	}	 	 private static String[][] locateWhite(ArrayList<White> whites,				String[][] map, int n) {			// TODO Auto-generated method stub			 for (int j = 0; j < whites.size(); j++) {				 White white = whites.get(j);				 int row = white.getRow();				 int col = white.getCol();				 String digit = white.getDigital()+"";				 String string = map[8-row][col-1];				 char d = string.charAt(0);				 map[8-row][col-1] =  d+""+digit+""+d+"|";//string.re			}			return map;		}	static void print(String[][] str,int n){		 for (int i = 0; i < n; i++) {			 System.out.print("|");			for (int j = 0; j < n; j++) {				System.out.print(str[i][j]);			}			System.out.println();			System.out.println("+---+---+---+---+---+---+---+---+");		}	 }	 	static class Black {		int col;		int row;		char digital;		int digitalValue;		public Black(int c, int r, char d) {			this.row = r;			this.col = c;			this.digital = d;			this.digitalValue = getAssign(digital);		}		public int getCol() {			return col;		}		public void setCol(int col) {			this.col = col;		}		public int getRow() {			return row;		}		public void setRow(int row) {			this.row = row;		}		public char getDigital() {			return digital;		}		public void setDigital(char digital) {			this.digital = digital;			this.digitalValue = getAssign(digital);		}		public int getDigitalValue() {			return digitalValue;		}		public void setDigitalValue(int digitalValue) {			this.digitalValue = digitalValue;		}		public String toString() {			return "<" + col + "," + row + "," + digital + ">";		}		public boolean smallerThan(Black black) {			if (digitalValue < black.getDigitalValue()) {				return true;			} else if (digitalValue > black.getDigitalValue()) {				return false;			} else if (row > black.getRow()) {				return true;			} else if (row == black.getRow()) {				return col < black.getCol();			} else {				return false;			}		}		public String toStr() {			String str = "";			if (digital != 'p') {				str = "" + digital;				str = str.toUpperCase();			}			char di = (char) (col + 'a' - 1);			str += di;			str += row;			return str;		}	}	static int getAssign(char a) {		int re = -1;		switch (a) {		case 'k':			re = 0;			break;		case 'K':			re = 1;			break;		case 'q':			re = 2;			break;		case 'Q':			re = 3;			break;		case 'r':			re = 4;			break;		case 'R':			re = 5;			break;		case 'b':			re = 6;			break;		case 'B':			re = 7;			break;		case 'n':			re = 8;			break;		case 'N':			re = 9;			break;		case 'p':			re = 10;			break;		case 'P':			re = 11;			break;		default:			break;		}		return re;	}	static class White {		int col;		int row;		char digital;		int digitalValue;		public int getDigitalValue() {			return digitalValue;		}		public void setDigitalValue(int digitalValue) {			this.digitalValue = digitalValue;		}		public White(int c, int r, char d) {			this.row = r;			this.col = c;			this.digital = d;			this.digitalValue = getAssign(digital);		}		public int getCol() {			return col;		}		public void setCol(int col) {			this.col = col;		}		public int getRow() {			return row;		}		public void setRow(int row) {			this.row = row;		}		public char getDigital() {			return digital;		}		public void setDigital(char digital) {			this.digital = digital;		}		public String toString() {			return "<" + col + "," + row + "," + digital + ">";		}		public boolean smallerThan(White white) {			if (digitalValue < white.getDigitalValue()) {				return true;			} else if (digitalValue > white.getDigitalValue()) {				return false;			} else if (row < white.getRow()) {				return true;			} else if (row == white.getRow()) {				return col < white.getCol();			} else {				return false;			}		}		public String toStr() {			String str = "";			if (digital != 'P') {				str = "" + digital;				str = str.toUpperCase();			}			char di = (char) (col + 'a' - 1);			str += di;			str += row;			return str;		}	}	private static ArrayList<Black> mySort(ArrayList<Black> list) {		int i, j, k;		Black temp;		int n = list.size();		for (i = 0; i < n - 1; i++) {			k = i;			for (j = i + 1; j < n; j++) {				if (list.get(j).smallerThan(list.get(k)))// list.get(j).mostLeft()															// <															// list.get(k).mostLeft()					k = j;			}			if (i != k) {				temp = list.get(i);				list.set(i, list.get(k));				list.set(k, temp);			}		}		int l = 0;		System.out.print("Black: ");		for (l = 0; l < list.size() - 1; l++) {			System.out.print(list.get(l).toStr() + ',');		}		System.out.println(list.get(l).toStr());		return list;	}	private static void Sort(ArrayList<White> list) {		int i, j, k;		White temp;		int n = list.size();		for (i = 0; i < n - 1; i++) {			k = i;			for (j = i + 1; j < n; j++) {				if (list.get(j).smallerThan(list.get(k)))					k = j;			}			if (i != k) {				temp = list.get(i);				list.set(i, list.get(k));				list.set(k, temp);			}		}		int l = 0;		System.out.print("White: ");		for (l = 0; l < list.size() - 1; l++) {			System.out.print(list.get(l).toStr() + ',');		}		System.out.println(list.get(l).toStr());	}}	