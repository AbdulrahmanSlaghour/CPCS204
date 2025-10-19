/*

Given an integer array with many duplicated elements, efficiently sort it in linear time.

Input : [4, 2, 40, 10, 10, 1, 4, 2, 1, 10, 40]
Output: [1, 1, 2, 2, 4, 4, 10, 10, 10, 40, 40]

Constraints:

• The input elements lie in the range [0, 1000].
• The relative order of equal elements doesn't matter.
• The sorting should be done in-place.

*/

import java.util.Random;
import java.util.Scanner;

class Solution
{   
    public static void main(String[] args) {

        // have user input array size
        Scanner inputScanner = new Scanner(System.in);

        boolean notInt = true;

        // initialize arraySize here to avoid compiler throwing error
        int arraySize = 0;
        do {
            System.out.print("Enter the size of the array to be sorted: ");
            try {
                arraySize = inputScanner.nextInt();
                //clearing \n after parsing integer
                inputScanner.nextLine();
                if (arraySize < 1) {
                    throw new Exception();
                }

                // if code reached this point, input is valid
                notInt = false;
            } catch (Exception err) {
                System.out.println("Invalid array size... Please enter a positive integer.");
                // clearing invalid input from buffer to prevent infinite loop
                inputScanner.nextLine(); 
            }
            
        } while (notInt);

        // create int[] with specified user size
        int[] userArray = new int[arraySize];

        // fill array with user values

        // initialize before loop to avoid compiler throwing errors
        boolean userWantsToInput = true;

        int currentIndex = 0;
        do {
            System.out.printf("Enter element %d (Type \"R\" to fill rest of array with random values between 0 and 1000): ", currentIndex);
            String userInput = inputScanner.nextLine();

            // check if user wants to randomize elements
            if (userInput.equals("R")) {
                userWantsToInput = false;

                // generate random elements for the rest of the array from 0 to 1000
                Random r = new Random();
                for (int i = currentIndex; i < arraySize; i++) {
                    userArray[currentIndex] = r.nextInt(1001);
					currentIndex++;
                }
            } else {
                // since user doesn't want to randomize elements, check whether the input is an
                // integer
                try {
                    int userElement = Integer.parseInt(userInput);
                    userArray[currentIndex] = userElement;
                    currentIndex++;
                } catch (Exception e) {
                    System.out.println("Invalid Input...");
                }
            }

            

            
        } while (userWantsToInput && currentIndex < arraySize);


        // close inputScanner to prevent memory leak
        inputScanner.close();

		// begin sorting user array
		sort(userArray);
    }

	public static void sort(int[] nums)
	{
		// Write your code here...
		//Since recurions is required, a sorting algorithm wich can call itself until a base case is reached will be used: quicksort
		//
		// Steps to solve problem:
		// 1- Divide the array into two parts by partitioning the array using the pivot
		// 1a- pivot will be the last element in the array
		// 2- Push elements smaller than the pivot to the left, and push elments bigger than partition to the right
		// 2a- After going through all elements between low and high, swap the pivot with i+1 to finish partitioning
		// 3- Have the base case be that the size of the passed section be <=1 by checking if low < high
		// 4- Call quicksort method recursively for the right and left side of the array

		System.out.print("Before Sorting: "); printArray(nums);
		quicksort(nums, 0, nums.length - 1);
        System.out.print("After Sorting: "); printArray(nums);
	}

    public static void printArray(int[] array) {
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
	
	public static void quicksort(int[] array, int low, int high) {
		
		// check the base case for recursion
		if (low < high) {
			
			// pick pivot
			int pivot = array[high];
			
			// sort elements smaller than and larger than pivot
			
			// initialize variables to avoid errors
			int i = low - 1;
			int temp = 0;
			for (int j = low; j < high; j++) {
				if (array[j] < pivot) {
					i++;
					// swap i and j
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
			
			// swap pivot with i + 1
			i++;
			temp = array[i];
			array[i] = pivot;
			array[high] = temp;
			
			// quicksort left of pivot
			quicksort(array, low, i - 1);

            // quicksort right of pivot
			quicksort(array, i + 1, high);
		}
	}
}
