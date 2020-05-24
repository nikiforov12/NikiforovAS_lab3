package divideAndConquer;

import java.util.Scanner;

public class Main {
	int[] findMaxCrossingSubarray(int A[], int low, int mid, int high) {
		int left_sum = 0;
		int right_sum = 0;
		int max_left = 0;
		int max_right = 0;

		int sum = 0;
		for (int i = mid; i >= low; i--) {
			sum = sum + A[i];
			if (sum > left_sum) {
				left_sum = sum;
				max_left = i;
			}
		}
		sum = 0;

		for (int j = mid + 1; j <= high; j++) {
			sum = sum + A[j];
			if (sum > right_sum) {
				right_sum = sum;
				max_right = j;
			}
		}

		return new int[] { max_left, max_right, left_sum + right_sum };
		
		
		 
	}

	int[] findMaximumSubarrary(int A[], int low, int high) {
		int mid;
		if (high == low)
			return new int[] { low, high, A[low] };
		else
			mid = (low + high) / 2;
		int arrayl[] = findMaximumSubarrary(A, low, mid);
		int arrayr[] = findMaximumSubarrary(A, mid + 1, high);
		int array[] = findMaxCrossingSubarray(A, low, mid, high);

		if (arrayl[2] >= arrayr[2] && arrayl[2] >= array[2])
			return new int[] { arrayl[0], arrayl[1], arrayl[2] };
		else if (arrayr[2] >= arrayl[2] && arrayr[2] >= array[2])
			return new int[] { arrayr[0], arrayr[1], arrayr[2] };
		else
			return new int[] { array[0], array[1], array[2] };
	}

	public static void main(String[] args) {
		System.out.print("Введите количество дней: ");
		int day;
		Scanner in = new Scanner(System.in);
		day = in.nextInt();
		
		while(day<=1) {
			System.out.println("Количество дней должно быть 2 и более, попробуйте снова: ");
			day = in.nextInt();
		}
		System.out.println("Введите цены: ");
		int price[] = new int [day];
		for (int i = 0; i < price.length; i++)
			price[i] = in.nextInt(); 
			
		System.out.print("Дни:     ");
		for(int i = 1; i <= day; i++)
			System.out.print(i + "     ");
		
		
		System.out.print("\nЦена:   ");
		for(int i = 0; i< price.length;i++)
			System.out.print(price[i] + "  ");
		
		int array[] = new int[price.length - 1];
		System.out.print("\nИзменение:   ");
		for (int i = 0; i < price.length - 1; i++) {
			array[i] = price[i + 1] - price[i];
			System.out.print(array[i] + "   ");
		}

		Main obj = new Main();
		int low = 0, high = array.length - 1;
		int[] res = obj.findMaximumSubarrary(array, low, high);
		if(res[2]>0)
		System.out.println("\nМаксимальный подмасссив: с " + (res[0] + 1 ) + " до " + (res[1]+1+1) + "\nСумма = " + res[2]);
		else System.out.println("\nПрибыли не будет!");
	in.close();
}
}