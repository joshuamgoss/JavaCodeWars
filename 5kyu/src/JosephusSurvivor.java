import java.util.*;

/*
 * In this kata you have to correctly return who is the "survivor", 
 * ie: the last element of a Josephus permutation.
 * 
 * Basically you have to assume that n people are put into a circle and 
 * that they are eliminated in steps of k elements
 */
public class JosephusSurvivor {
	public static int josephusSurvivor(final int n, final int k) {
		// set the space to be removed
		int removeAt = (k - 1) % n;
		
		// I'll solve the problem by creating the circle
		// and reducing it.  
		ArrayList<Integer> circle = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			circle.add(i);
		}
		
		// Remove one element at a time until there is a single 'survivor'
		while (circle.size() > 1) {
			circle.remove(circle.get(removeAt));
			removeAt = (removeAt + k - 1) % circle.size();
		}
		
		//return the remaining element
		return circle.get(0);
	}
}