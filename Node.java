   package bearmaps;
   
   public class Node {

             Point p;
             Node small;
             Node big;
             boolean lr;

            public Node(Point p, boolean lr){
               this.p = p;
               this.lr = lr;
            }

            public boolean isLr(){
               return this.lr;
            }
   }