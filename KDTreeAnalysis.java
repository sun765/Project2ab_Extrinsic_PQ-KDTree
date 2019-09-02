package bearmaps;
import java.util.*; 



public class KDTreeAnalysis {

	public KDTreeAnalysis(){

	}

	public static void analis(int size ,double bound){

		Stopwatch s = new Stopwatch();
		List<Point> l = KDTreeTest.getRandomPointList(size,bound);
		double t      = s.elapsedTime();
		System.out.print(" it took "+ Double.toString(t)+" time to initialze the point list of" +Integer.toString(size)+" points!");
		System.out.print("\n");
	    // using the naive set's nearest to get the correct result and compare with KDTree's navie nearest
	    NaivePointSet nn = new NaivePointSet(l);
	    t = s.elapsedTime();
	    System.out.print(" it took "+ Double.toString(t)+" time to initialze the point list of" +Integer.toString(size)+" points in Naive Set!");
		System.out.print("\n");
	    Point ret = nn.nearest(3.0, 4.0); // returns p2
		t = s.elapsedTime();
		System.out.print(" it took "+ Double.toString(t)+" time to get the nearest point among" +Integer.toString(size)+" points in Naive set!");
		System.out.print("\n");

	    KDTree tree = new KDTree(l);
	    t = s.elapsedTime();
	    System.out.print(" it took "+ Double.toString(t)+" time to initialze the" +Integer.toString(size)+" in KDTree!");
	    System.out.print("\n");

	    Point kdNearst = tree.nearest(3.0,4.0);
	    t = s.elapsedTime();
	    System.out.print(" it took "+ Double.toString(t)+" time to get the nearest point among" +Integer.toString(size)+" points in KDTree!");
	    System.out.print("\n");		

	}
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		double bound = Double.parseDouble(args[1]);
		analis(size,bound);
	}



}