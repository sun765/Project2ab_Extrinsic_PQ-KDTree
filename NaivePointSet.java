package bearmaps;
import java.util.*; 

   public class NaivePointSet implements PointSet {

   		private List<Point> points = new ArrayList<Point>();
   		private int size = 0;

   		public NaivePointSet(List<Point> points){
   			this.points = points;
   			this.size = points.size();
   		}

   		// implementing inteface must be public?

   		public Point nearest(double x, double y){
   			double minDist = (double)Integer.MAX_VALUE;
   			Point target = new Point(x,y);
   			Point nearest = new Point() ;
   			for(Point p : this.points){
   				double dist = Point.distance(p,target);
   				if(dist <= minDist){
   					nearest = p;
   					minDist = dist;
   				}
   			}
   			return nearest;
   		}

      	public static void main(String[] args) {
      		
      	}
    };

