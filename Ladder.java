package divideAndConquer;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Ladder {

	private double[] Array;
	private double[] S;
	private Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	//private ArrayList<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) {

		System.out.println("Введите количество ступенек:");

		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		while (N <= 0) {
			System.out.println("Вы ввели неверное количество ступенек, попробуйте снова:");
			N = in.nextInt();
		}

		Ladder ladder = new Ladder(N);

		ladder.initialization(in);
		System.out.println("Массив вероятностей:");
		ladder.outputArrays(ladder.Array);

		System.out.println("Вспомогательный массив:");
		ladder.outputArrays(ladder.S);

		ladder.maxLadder();
		in.close();
	}

	Ladder(int N) {
		Array = new double[N];
		S = new double[N];
	}

	void initialization(Scanner in) {
		System.out.println("Введите вероятности:");

		for (int i = 0; i < Array.length; i++) {
			System.out.print((i + 1) + " ступенька = ");
			Array[i] = in.nextDouble();
		}
		System.out.println();

		for (int i = 0; i < S.length; i++)
			if (i < 3)
			{
				ArrayList<Integer> list = new ArrayList<Integer>();
				S[i] = Array[i];
				map.put(i+1, list);
			}
			else
				S[i] = max(i);
	}

	void maxLadder() {
		double max = -2;
		int index_max = -1;

		for (int i = S.length - 1; i >= S.length - 3; i--) 
			if (max <= S[i]) {
				max = S[i];
				index_max = i+1;
			}
		//list.add(index_max);
	
			//.out.println("Для ключа " + index_max);
			safeLadder(index_max);
			System.out.println(index_max);
		
	}

	void safeLadder(int value) {
		for (int i = 0; i < map.get(value).size(); i++) {
			System.out.println(map.get(value).get(i));
			safeLadder(map.get(value).get(i));
		}
	}                        

	void outputArrays(double[] Array) {
		for (int i = 0; i < Array.length; i++)
			System.out.print(Array[i] + "  ");
		System.out.println();
	}

	private double max(int i) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();

		double max = 0;
		if (S[i - 1] * Array[i] > S[i - 2] * Array[i] && S[i - 1] * Array[i] > S[i - 3] * Array[i])
			max = S[i - 1] * Array[i];
		else if (S[i - 2] * Array[i] > S[i - 1] * Array[i] && S[i - 2] * Array[i] > S[i - 3] * Array[i])
			max = S[i - 2] * Array[i];
		else
			max = S[i - 3] * Array[i];

		if (max == S[i - 1] * Array[i])
			arrayList.add(i - 1+1);
		if (max == S[i - 2] * Array[i])
			arrayList.add(i - 2+1);
		if (max == S[i - 3] * Array[i])
			arrayList.add(i - 3+1);

		map.put(i + 1, arrayList);

		return max;
	}

}
