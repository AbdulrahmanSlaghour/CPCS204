/*

Given a singly-linked list of integers, rearrange it by separating odd nodes from even ones. The solution should return a list containing all even nodes followed by all odd nodes, where the relative order of even and odd nodes is maintained.

Input : 1 —> 2 —> 3 —> 4 —> 5 —> 6 —> 7 —> null
Output: 2 —> 4 —> 6 —> 1 —> 3 —> 5 —> 7 —> null

Input : 2 —> 4 —> 6 —> null
Output: 2 —> 4 —> 6 —> null

Input : 1 —> 3 —> 5 —> 7 —> null
Output: 1 —> 3 —> 5 —> 7 —> null

*/

class Solution
{
	/*
		A singly-linked list node is defined as:

		class Node {
			int data;		// data field
			Node next;		// pointer to the next node

			Node() {}
			Node(int data) { this.data = data; }
			Node(int data, Node next) { this.data = data; this.next = next; }
		}
	*/

	public static Node rearrange(Node head)
	{
		// Write your code here...

		// Steps to solve problem:
		// 1- Create dummyOddHead to store odd nodes
		// 2- Traverse given linked list using a pointer
		// 3- Check pointer.next.data:
		// 3a- if odd, add that node to dummyOddHead.
		// 3b- if even, continue traversing.
		// 4- at the end, check the given linked list's head:
		// 4a- if odd, set it as the head of the Odd linked list, and move the head of the given linked list to the next node.
		// 5- append the odd linked list to the end of the given linked list and return the head of the given linked list.

		// Edge case
		if ((head == null) || (head.next == null)) {
			return head;
		}
		
		Node dummyOddHead = new Node(0, null);
		Node currentOdd = dummyOddHead;

		Node traversePointer = head;
		while (traversePointer.next != null) {
			if (traversePointer.next.data % 2 != 0) {
				// move odd node to odd linked list
				currentOdd.next = traversePointer.next;
				currentOdd = currentOdd.next;

				traversePointer.next = traversePointer.next.next;
				currentOdd.next = null;
			}
			if (traversePointer.next != null) {
				traversePointer = traversePointer.next;
			}
		}

		Node oddHead = dummyOddHead.next;

		// check if given linked list's head is odd
		if (head.data % 2 != 0) {
			dummyOddHead = head;
			head = head.next;
			dummyOddHead.next = oddHead;
			oddHead = dummyOddHead;
		}

		// append odd linked list to given linked list
		traversePointer.next = oddHead;

		return head;
	}
}
