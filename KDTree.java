package bearmaps;
import java.util.*; 

   public class KDTree implements PointSet {

         /*
          * no modifier means package accessible.
          */
         Node root;
   		List<Point> points = new ArrayList<Point>();
   		int size = 0;

         /*
          * insert point to the correct position.
          * level1 : compare x, level2 : compare y, l3 ,x, etc...
          */
         public static boolean insertPoint(Node r,Point a){

               if(r==null||a==null){
                  return false;
               }

               /* if the insertPos turn out to be null , can't just create a new node on insertPos!!!!
               *  because null !=null;
               */
               Node insertPos;
               if(r.lr){
                  insertPos = a.getX()< r.p.getX()? r.small:r.big;
                  if(insertPos==null){
                     if(a.getX()< r.p.getX())
                        r.small= new Node(a,false);
                     else{
                        r.big= new Node(a,false);
                     }
                  }
                  else{
                     insertPos = a.getX()< r.p.getX()? r.small:r.big;
                     insertPoint(insertPos,a);
                  }
               }

               else{
                  insertPos = a.getY()< r.p.getY()? r.small:r.big;
                  if(insertPos==null){
                     if(a.getY()< r.p.getY())
                        r.small= new Node(a,true);
                     else{
                        r.big= new Node(a,true);
                     }
                  }
                  else{
                     insertPos = a.getY()< r.p.getY()? r.small:r.big;
                     insertPoint(insertPos,a);
                  }
               }
               return true;
            }


        /**
        * Creating a KDtree by looping through the input points list and x inserting each point into the tree. 
        */
   		public KDTree (List<Point> points){
            this.points = points;
            for(Point p: points){
               if(root == null)
                  root = new Node(p,true);
               else{
                  insertPoint(root,p);
               }
            }
   		}

       /**
        * simply traverse the tree to find the nearest point. just for testing purpose.
        */
         public Point naiveNearestPoint(double x, double y){

            return NaiveNearestHelper(this.root, new Point(x,y));

         }

         Point NaiveNearestHelper(Node r, Point p){
            // base case
            if(r == null)
               return null;
            // post order
            Point nearest = null;
            double minDist;

            Point p1 = NaiveNearestHelper(r.small,p);
            Point p2 = NaiveNearestHelper(r.big,p);

            double d1 = p1==null? Double.MAX_VALUE :Point.distance(p1,p);
            double d2 = p2==null? Double.MAX_VALUE :Point.distance(p2,p);
            double d = Point.distance(r.p,p);
            nearest = d1 < d2? p1:p2;
            minDist = d1 < d2? d1:d2;
            nearest = minDist<d? nearest:r.p;
            // return
            return nearest;

         }

         public Point nearest(double x, double y){
            return nearestHelper(this.root,new Point(x,y));
         }

         public Point nearestHelper(Node r, Point target){
            // base case:  null;
            if(r==null)
               return null;

            /* inorder:
             * looking at the good side. and traverse through the good side and get the best result from good side.
             * then looking at the bad side. if there are possible result from the bad side better then the result from good side, traverse the bad side.
             * if not, just return the good side's result
             */
            
            Node goodSide =null ,badSide = null;
            Point nearest =null;
            if(r.isLr()){
               goodSide = r.p.getX()<target.getX()?r.big:r.small;
               badSide  = r.p.getX()>=target.getX()?r.big:r.small;
            }
            else{
               goodSide = r.p.getY()<target.getY()?r.big:r.small;
               badSide  = r.p.getY()>=target.getY()?r.big:r.small;
            }

            Point good = nearestHelper(goodSide,target);
            double dGood = good==null? Double.MAX_VALUE:Point.distance(good,target);
            double dCur = Point.distance(r.p,target);
            good = dGood<dCur? good:r.p;
            nearest = good;

            double minDist = dGood<dCur? dGood:dCur;
            double potDist = potentialDist(good,target,r.isLr());
            if(minDist> potDist){
               Point bad = nearestHelper(badSide,target);
               double badDist = bad==null? Double.MAX_VALUE:Point.distance(bad,target);
               nearest = badDist<minDist? bad:good;
            }
 
            return nearest;
            // return          
         }

         public static double potentialDist(Point n, Point target,boolean lr){
            if(n ==null || target==null)
               return Double.MAX_VALUE;

            if(lr)
               return Math.abs(n.getX() -target.getX());

               return Math.abs(n.getY() -target.getY());
         }

         public static void main(String[] args) {
           
         }

    }



