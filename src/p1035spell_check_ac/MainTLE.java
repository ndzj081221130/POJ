package p1035spell_check_ac;import java.util.ArrayList;import java.util.Scanner;public class MainTLE {	/**	 * @param args	 */	public static void main(String[] args) {		// TODO Auto-generated method stub		long start = System.currentTimeMillis();		Scanner scanner = new Scanner(System.in);		ArrayList<Word> library = new ArrayList<Word>();		String string = scanner.next();		int index = 0;		while (string.equals("#") == false) {			library.add(new Word(index, string));			index++;			string = scanner.next();		} 		string = scanner.next();		while (string.equals("#") == false) { 			check(library, string);			string = scanner.next();		}		long end = System.currentTimeMillis();		System.out.println(end-start); 	}	private static void check(ArrayList<Word> library, String testString) {		ArrayList<Word> res = new ArrayList<Word>();		for (Word word : library) {			String wordString = word.getWord();			if (wordString.equals(testString)) {				System.out.println(testString + " is correct");				return;			} else {				ArrayList<String> family = getFamily(wordString);				if (family.contains(testString)) {					res.add(word);				}			}		}//		Sort(testString, res);		QuickSort(0, res.size()-1, testString, res);		int l = 0;		System.out.print(testString + ":");		for (l = 0; l < res.size(); l++) {			System.out.print(' ' + res.get(l).getWord());		}		System.out.println();// list.get(l).toStr());	}	static ArrayList<String> getFamily(String s) {		ArrayList<String> family = new ArrayList<String>();		for (int i = 0; i < s.length(); i++) {// replace a char			char ith = s.charAt(i);			for (char a = 'a'; a <= 'z'; a++) {				if (ith != a) {					StringBuffer buffer = new StringBuffer(s);					buffer.setCharAt(i, a);					String replaceCharString = buffer.toString();					family.add(replaceCharString);				}			}		}				for (int j = 0; j <= s.length(); j++) {// 插入			for (char a = 'a'; a <= 'z'; a++) {				StringBuffer buffer = new StringBuffer(s);				StringBuffer bufferNewBuffer = buffer.insert(j, a);				family.add(bufferNewBuffer.toString());			}		}		for (int k = 0; k < s.length(); k++) {			StringBuffer buffer = new StringBuffer(s);			StringBuffer newBuffer = buffer.deleteCharAt(k);			if (newBuffer.length() > 0)				family.add(newBuffer.toString());		}		return family;	}	private static void Sort(String test, ArrayList<Word> list) {		int i, j, k;		Word temp;		int n = list.size();		for (i = 0; i < n - 1; i++) {			k = i;			for (j = i + 1; j < n; j++) {				if (list.get(j).getId() < (list.get(k).getId()))					k = j;			}			if (i != k) {				temp = list.get(i);				list.set(i, list.get(k));				list.set(k, temp);			}		}		int l = 0;		System.out.print(test + ":");		for (l = 0; l < list.size(); l++) {			System.out.print(' ' + list.get(l).getWord());		}		System.out.println();// list.get(l).toStr());	}	private static void QuickSort(int left,int right,String test, ArrayList<Word> list) {		if (left < right) {			int pivotpos = partition(list,left,right);			QuickSort( left, pivotpos-1,test,list);			QuickSort( pivotpos+1, right,test,list);		}	}	private static int partition(ArrayList<Word> array, int left, int right) {		int pivotpos = left;		Word pivot = array.get(left);//[left];		for (int i = left+1; i <= right; i++) {			if (array.get(i).getId() < pivot.getId()) {				pivotpos++;				if (pivotpos != i) {					Swap(array,pivotpos,i);									}			}					}//		array[left] = array[pivotpos];//		array[pivotpos] = pivot;		array.set(left, array.get(pivotpos));		array.set(pivotpos, pivot);		return pivotpos;	}	private static void Swap(ArrayList<Word> array, int pivotpos, int in) {		Word temp = array.get(in);		array.set(in, array.get(pivotpos));		array.set(pivotpos, temp);//		System.out.println("= -");//		for (int i = 0; i < array.length; i++) {//			System.out.print(array[i]+" ");//		}	}		static class Word {		int id;		String word;				public Word(int i, String s) {			this.id = i;			this.word = s;		}		public int getId() {			return id;		}		public void setId(int id) {			this.id = id;		}		public String getWord() {			return word;		}		public void setWord(String word) {			this.word = word;		}	}}