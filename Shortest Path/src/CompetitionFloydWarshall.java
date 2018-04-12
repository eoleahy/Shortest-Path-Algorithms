import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	Random r = new Random();
	double[][] city;
	int N;

	String filename;
	int sA, sB, sC;
	public static final int INF = Integer.MAX_VALUE;

	/**
	 * @param filename:
	 *            A filename containing the details of the city road network
	 * @param sA,
	 *            sB, sC: speeds for 3 contestants
	 */
	CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {

		this.sA = sA;
		this.sB = sB;
		this.sC = sC;
		this.filename = filename;

		try {
			Scanner s = new Scanner(new File(filename));

			N = Integer.parseInt(s.nextLine());
			int S = Integer.parseInt(s.nextLine());

			System.out.println("FloydWarshall\nTotal number of intersections: " + N);
			System.out.println("Total number of streets: " + S);

			ArrayList<String> streets = new ArrayList<String>();
			while (s.hasNextLine()) {
				streets.add(s.nextLine());
			}
			s.close();
			double[][] graph = new double[N][N];

			for (int i = 0; i < S; i++) {
				// Reading info
				String[] streetInfo = streets.get(i).split(" ");

				int src = Integer.parseInt(streetInfo[0]);
				int dest = Integer.parseInt(streetInfo[1]);
				Double length = Double.parseDouble(streetInfo[2]);

				graph[src][dest] = length;

			}
			this.city = graph;
			floydWarshall();
			print();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	void floydWarshall() {

		// Set the unset chars to the max possible number
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {

				if (city[i][k] == 0 && i != k)
					city[i][k] = INF;
			}
		}
		
		//Actual execution of FloydWarshall

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (city[i][k] + city[k][j] < city[i][j]) {
						city[i][j] = city[i][k] + city[k][j];
						
					}
				}
			}
		}
	}
	
	//Prints the array

	void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				System.out.print(city[i][j] + "  ");

			}
			System.out.println();
		}
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants
	 *         can meet
	 */
	public int timeRequiredforCompetition() {
		
		// A few cases for when the competition can't be completed

		if (N < 3)
			return -1;

		if (filename == null)
			return -1;

		if (sA < 50 || sB < 50 || sC < 50)
			return -1;

		if (sA > 100 || sB > 100 || sC > 100)
			return -1;

		double longestDistance = 0;
		
		//Finding the longest path in the graph

		for (int i = 0; i < city.length; i++) {
			for (int j = 0; j < city.length; j++) {
				if (city[i][j] > longestDistance)
					longestDistance = city[i][j];
			}
		}
		
		if(longestDistance == INF)
			return -1;
		
		// KM -> Metres

		longestDistance *= 1000;

		System.out.println("Longest possible path = " + longestDistance + " metres");

		int speedArray[] = { sA, sB, sC };

		int slowestSpeed = INF;
		
		//Finding the slowest of the speeds for the worst case scenario

		for (int i = 0; i < speedArray.length; i++) {
			if (slowestSpeed > speedArray[i])
				slowestSpeed = speedArray[i];
		}

		System.out.println("Slowest speed is " + slowestSpeed);

		int time = (int) Math.ceil(longestDistance / slowestSpeed);

		return time;
	}

}