import java.io.*;
import java.util.*;

public class Main {

	static int P;
	static int[] dnaQuantity = new int[4];
	static int[] nowDnaQuantity = new int[4];
	
	public static boolean isOk() {
		for(int i = 0; i<4; i++) {
			if (nowDnaQuantity[i] < dnaQuantity[i]) 
				return false;
		}
		return true;
	}
	
	public static void plusDnaQuantity(char str) {
		if (str == 'A') {
			nowDnaQuantity[0]++;
		}
		else if(str == 'C') {
			nowDnaQuantity[1]++;
		}
		else if(str == 'G') {
			nowDnaQuantity[2]++;
		}
		else {
			nowDnaQuantity[3]++;
		}
	}
	public static void minusDnaQuantity(char str) {
		if (str == 'A') {
			nowDnaQuantity[0]--;
		}
		else if(str == 'C') {
			nowDnaQuantity[1]--;
		}
		else if(str == 'G') {
			nowDnaQuantity[2]--;
		}
		else {
			nowDnaQuantity[3]--;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int S;
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		line = br.readLine();
		char[] dna = line.toCharArray();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			dnaQuantity[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < P; i++) {
			plusDnaQuantity(dna[i]);
		}

		int count = 0;
		if (isOk()) {
			count++;
		}
		int start = 0;
		for (int index = P; index < S; index++) {
			minusDnaQuantity(dna[start++]);
			plusDnaQuantity(dna[index]);
			if(isOk()) {
				count++;
			}
		}
		System.out.println(count);
		return;
	}
}