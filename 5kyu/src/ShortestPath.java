import java.util.*;

/*
 * The goal is to accept a string representing an NxN maze
 * and find the length of the shortest path through
 */
public class ShortestPath {

	public static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	/*
	 * The input is passed as a string, where /n separate the rows The output should
	 * be an integer giving the length of the shortest path. The 'entrance' to the
	 * maze is the top-left and the 'exit' is the bottom right
	 */
	public static int shortestPath(String maze) {
		int roomCount = 0;
		ArrayList<Integer> mazeIndexes = new ArrayList<Integer>();

		/*
		 * Basically I'm going to start by changing all of the walls to 0 and assigning
		 * non-wall spaces unique numbers. I'll keep track of how many non-wall spaces
		 * are assigned to use as the size of the adjacency matrix. I could skip the
		 * step of trimming the'maze' variable but it cleans up the code without costing
		 * much time, which is acceptable here This makes it so our 'entrance' is always
		 * '0' and our 'exit' is always roomCount-1
		 */
		maze = maze.replaceAll("\n", "");
		for (String r : maze.split("")) {
			if (r.contentEquals(".")) {
				mazeIndexes.add(roomCount);
				roomCount++;
			} else {
				mazeIndexes.add(-1);
			}
		}
		if (roomCount == 1)
			return 0;

		/*
		 * Now I want to build an adjacency matrix. I'll do that by making an m by m
		 * matrix where m is the current value for roomCount. Cell contents will be 1 if
		 * the row index is adjacent to the column index
		 */
		int[][] adjacencies = new int[roomCount][roomCount];

		// since adjacent is reflexive, I only have to check adjacency for neighbors
		// below it and immediately to its right
		int rowLength = (int) Math.sqrt(mazeIndexes.size());
		for (int i = 0; i < roomCount - 1; i++) {
			int currentCellIndex = mazeIndexes.indexOf(i);
			int rightOneIndex = currentCellIndex + 1;
			int rightOneValue = rightOneIndex % rowLength != 0 ? mazeIndexes.get(rightOneIndex) : -1;
			if (rightOneValue > 0) {
				adjacencies[i][rightOneValue] = 1;
				adjacencies[rightOneValue][i] = 1;
			}
			int downOne = currentCellIndex + rowLength;
			if (downOne < mazeIndexes.size() && mazeIndexes.get(downOne) > 0) {
				adjacencies[i][mazeIndexes.get(downOne)] = 1;
				adjacencies[mazeIndexes.get(downOne)][i] = 1;
			}
		}

		
		/*
		 * Expand shortest paths outwards from node one until a path to node roomCount is found,
		 * or until no new paths are found
		 */
		int counter = 0;
		while (adjacencies[0][roomCount - 1] == 0 && counter < roomCount) {
			/*
			 * If I let this run over every row it would calculate all of the shortest path lengths between
			 * each pair of elements.  As a side effect, it could speed up operations on row one in specific
			 * cases, but it would need a stability check because some long paths could get recorded before
			 * the shortest path is found.  It would end up only being advantageous if all paths are required
			 * so I'm only finding paths for row one
			 */
			for (int i = 1; i < roomCount; i++) {
				//Once an entry is found in the first row, we test the row for that entry
				int iEntry = adjacencies[0][i];
				if (iEntry != 0) {
					//Look in the row for the adjacent node and check for its adjacency.  
					for (int j = 1; j < roomCount; j++) {
						int jEntry = adjacencies[i][j];
						//For each adjacency in the new row, if the path to that entry through the row's node
						//is shorter than the current recorded path, update the path length
						if (jEntry != 0) {
							int newEntry = jEntry + iEntry;
							int oldEntry = adjacencies[0][j];
							adjacencies[0][j] = oldEntry < newEntry && oldEntry != 0 ? oldEntry : newEntry;
						}
					}
				}
			}
			counter++;
		}
		//If the path length is recorded as less than 1 it was never updated, and '-1' should be returned instead of the path length
		return (adjacencies[0][roomCount - 1] > 0 ? adjacencies[0][roomCount - 1] : -1);
	}

}
