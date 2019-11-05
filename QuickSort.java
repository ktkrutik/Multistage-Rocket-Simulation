package edu.neu.csye6200.launch;

public class QuickSort {
	
	public int[] qSort(int arr[]) {		
		int left = 0;
		int right = arr.length-1;
		return qs(arr, left, right);		
	}
	
	public int[] qs(int arr[], int left, int right) {
		int i = left;
		int j = right;
		int mid = arr[(left+right)/2];
		
		do {
			while(arr[i]<mid && i<right) i++;
			while(mid<arr[j] && j>left) j--;
			
			if(i<=j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		} while(i<=j);
		
		if(left<j) qs(arr, left, j);
		if(i<right) qs(arr, i, right);
		
		return arr;
	}
	
	public void printArr(int arr[]) {
		int len = arr.length;
		for(int i=0; i<len; i++) {
			System.out.print(arr[i] + "|");
		}
	}
	
	public static void main(String[] args) {
		QuickSort obj = new QuickSort();
		int arr[] = {3,5,1,9,8,2,4,6,7};
		obj.printArr(arr);
		System.out.println();
		int sortedArr[] = new int[arr.length];
		sortedArr = obj.qSort(arr);
		obj.printArr(sortedArr);	
	}
}
