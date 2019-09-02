package bearmaps;

import java.util.*; 
import org.junit.Test;
import static org.junit.Assert.*;

public class KDTreeTest {

	@Test
	public void testNearst(){

		Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
	    Point p2 = new Point(3.3, 4.4);
	    Point p3 = new Point(-2.9, 4.2);
	    List<Point> l = new LinkedList<Point>();
	    l.add(p1);
	    l.add(p2);
	    l.add(p3);

	    NaivePointSet nn = new NaivePointSet(l);
	    Point ret = nn.nearest(3.0, 4.0); // returns p2
		 // evaluates to 4.4
	    assertEquals(ret.getX(), 3.3,0);
	    assertEquals(ret.getY(), 4.4,0);
	}

	public boolean samePoint(Point p1,Point p2){
		return p1.getX() == p2.getX()&& p1.getY()== p2.getY();
	}

	@Test
	public void testInsertPoint(){

		Point p1 = new Point(1, 2); // constructs a Point with x = 1.1, y = 2.2
	    Point p2 = new Point(4, 5);
	    Point p3 = new Point(-6, 1);
	    Point p4 = new Point(7, 3);
	    Point p5 = new Point(2, 10);
	    List<Point> l = new LinkedList<Point>();
	    l.add(p1);
	    l.add(p2);
	    l.add(p3);
	    l.add(p4);
	    l.add(p5);
	    KDTree t = new KDTree(l);
	    assertTrue(samePoint(t.root.p, p1));
	    assertTrue(samePoint(t.root.small.p,p3)); 
	    assertTrue(samePoint(t.root.big.p,p2));
	    assertTrue(samePoint(t.root.big.small.p, p4));
	    assertTrue(samePoint(t.root.big.big.p, p5));
	}

	@Test
	public void testNaiveNearestOfKDTree(){

		Point p1 = new Point(1, 2); // constructs a Point with x = 1.1, y = 2.2
	    Point p2 = new Point(4, 5);
	    Point p3 = new Point(-6, 1);
	    Point p4 = new Point(7, 3);
	    Point p5 = new Point(2, 10);
	    List<Point> l = new LinkedList<Point>();
	    l.add(p1);
	    l.add(p2);
	    l.add(p3);
	    l.add(p4);
	    l.add(p5);
	    
	    // using the naive set's nearest to get the correct result and compare with KDTree's navie nearest
	    NaivePointSet nn = new NaivePointSet(l);
	    Point ret = nn.nearest(3.0, 4.0); // returns p2

	    KDTree t = new KDTree(l);
	    Point kdNearst = t.naiveNearestPoint(3.0,4.0);
	    assertEquals(ret.getX(),kdNearst.getX(),0);
	    assertEquals(ret.getY(),kdNearst.getY(),0);

	}

	@Test 
	public void testPotentialDist(){
		Point p1 = new Point(1, 2); // constructs a Point with x = 1.1, y = 2.2
	    Point p2 = new Point(4, 5); 
	    Point target = new Point(3,3);
	    assertEquals(KDTree.potentialDist(p1,target,true),2,0);
	    assertEquals(KDTree.potentialDist(p2,target,false),2,0);
	}

	public static List<Point> getRandomPointList(int size, double bound){
		List<Point> points = new ArrayList<Point>();
		Random r = new Random();
		for(int i = 0; i< size ; i++){
			double x = r.nextDouble()*bound;
			double y = r.nextDouble()*bound;
			points.add(new Point(x,y));
		}

		return points;
	}
	@Test

	public void testNearestOfKDTree(){

	    List<Point> l = getRandomPointList(10000000,10.0);

	    
	    // using the naive set's nearest to get the correct result and compare with KDTree's navie nearest
	    NaivePointSet nn = new NaivePointSet(l);
	    Point ret = nn.nearest(3.0, 4.0); // returns p2

	    KDTree t = new KDTree(l);
	    Point kdNearst = t.nearest(3.0,4.0);
	    assertEquals(ret.getX(),kdNearst.getX(),0);
	    assertEquals(ret.getY(),kdNearst.getY(),0);

	}

	public static void main(String[] args) {
		jh61b.junit.TestRunner.runTests("all", KDTreeTest.class);
	}
}
