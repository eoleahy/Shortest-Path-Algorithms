import org.junit.Test;
import java.util.Random;

public class CompetitionTests {

	/*
	 * @author Eoin Leahy
	 * 
	 * 
	 * Q1. The only data structures used in this implementation include 1D and
	 * 2D arrays. The choice of these data structures are because they are both
	 * simple and a 2D array can give a easy to read representation on the
	 * adjacency matrix of the graph. Other data structures such as linked lists
	 * could easily be added to give more accurate routing information.
	 * 
	 * Q2. The major difference between Dijkstra's and FloydWarshall's is that
	 * Dijkstra finds the shortest route for 1 point while FloydWarshall finds
	 * the shortest route for all points, as such the worst case running time is
	 * O(N^2) and O(N^3) respectively. Finding the shortest route for every
	 * point with Dijkstra's Algorithm actually changes the practical worst case
	 * running time to O(N^3), which happens in this implementation.
	 * 
	 * The relative performance of FloydWarshall's algorithm decreases by a
	 * factor of N for each vertex of the graph.
	 * 
	 * I would choose Dijkstra over FloydWarshall as it doesn't "waste" time
	 * finding the shortest path for points which you may not want, in other
	 * words, you can selectively choose which paths between points you want to
	 * find, instead of finding every shortest path.
	 * 
	 */

	@Test
	public void testDijkstraConstructor() {

		CompetitionDijkstra d = new CompetitionDijkstra("input-C.txt", 50, 60, 80);

		int time = d.timeRequiredforCompetition();

		System.out.println("Minimum time required for Dijkstra = " + time + " minutes\n");
	}

	@Test
	public void testFWConstructor() {

		CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-C.txt", 50, 60, 80);
		int time = f.timeRequiredforCompetition();

		System.out.println("Min time required for FloydMarshall = " + time + " minutes");
	}

	@Test
	public void testIncomplete() {

		CompetitionFloydWarshall f = new CompetitionFloydWarshall(null, 50, 50, 50);
		CompetitionDijkstra d = new CompetitionDijkstra(null, 50, 60, 80);

		int time = f.timeRequiredforCompetition();
		time = d.timeRequiredforCompetition();

	}
}
