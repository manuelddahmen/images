package one.empty3.feature ;
import one.empty3.io.ProcessFile;
import one.empty3.library.*;
import one.empty3.library.shader.Vec;
import java.awt.Color;
import java.io.File;
import java.awt.Point;
import java.util.*;
import javax.image.ImageIO;
public class DiffE extends ProcessFile {
   private PixM m1; private PixM m2;
private int sizeElement = 20;
    class ColorTranform {
        
        public void rotate(){}
        public double compare(double[] rbga){}
        
    }
    
    class Circle {
        
 double x, y, r, a;
       
        public Circle(PixM m,double x,double
                          y, double r)
            {
           
        this.x=x;this.y=y;this.r=r;
        }
        public void rotate(double angle) {
            a+=angle;
        }
        public double get(double r, double g, double b){
           return Math.sqrt(r*r+g*g+b*b);
        }
         
        
    
         
         
        
       public Point2D get(double a, double r) {
          Point3D n =new Point2D(r*Math.cos(2*Math.PI*a),
                              r*Math.sin(2*Math.PI*a));
          return n;
       }
        public void variate(double x, double y,
                           double r, double rotate) {
            this.x+= x;
            this.y+= y;
            this.r+= r;
            this.a+= rotate;
        }
        public double getIntensity(int i, int j) {
            double count=0.0;
         double
            i0, j0;
            
              
              
              
              
 double t= 0.0;
         for(
 i0=i-r; i0<i+r; i0++)
                for( j0=i-r;j0<i+r; j0++) {                  count +=1 ; ii+= r;}
            
               
               
               
               
               
               
               
               
               t  +=count/ii;
         return t;
       }
        public double get(Circle c, double r, double a1, double a2) {
            double pi = 0.0;
            for (double a = a1; a<a2; a+= 1/r/2/Math.PI) {
            
                pi+= c.getIntensity((int)(r*Math.cos(a)) ,(int)(r*Math.sin(a)));
            }
            return pi;
                
        }
       }
        public double dist(Circle b) {
           
            double diff = 0.0;
              
            for(int i = (int)(-b.x+ b.r); i<b.y+b.r;i++)
                for(int j=(int)( -b.x+ b.r); j<b.y+b.r; j++) {
                    
               
                diff +=new Vec(new Vec(m1.getRgb(i. j)), new Vec(0.0)).norme();
               diff -= new Vec(new Vec(m1.getRgb(i, j)),new Vec(0.0)).norme();
             
           
                  
              /*
                   diff =(P.n(b.x,b.oy, b.i)).moins(
                    p1.mean(b.x-b.r, b.y-r, b.x+b.i, b.y+b.i).norme();
                       
                       */
                    
               }
                
     
                 return diff; // attention red
                
        } 

public Circle getLevel(Circle c) {
        // I mean. Parcourir le cercle
        // mesurer I / numPoints
        // for(int i=Math.sqrt()
        //  return c;
        int count = 0;
        double intensity = 0.0;
         c.r = r;
        for (double i = c.x-c.r; i <= c.x+c.r; i++) {
            for (double j = c.y-c.r; j <= c.y+c.r; j++) {
                if (Math.sqrt((i-c.x) * (i-c.x) + (j-c.y) * ( j-c.y)) <= c.r*c.r
                        && c.x-c.r>=0 && c.y-c.r>=0 && c.x+c.r<m.columns && c.y+c.r<m.lines) {
                    intensity += m1.getIntensity((int) i, (int) j);
                    count++;
                    
                }
            }
        }

        if(count>0) {
            c.i = intensity / count;
        }
        else {
            c.i = 0.0;
            c.r = 0.0;
        }
    return c;
    }
 
    public void sort(double [] [] m) {

        // loop for rows of matrix

        for (int i = 0; i < m.length; i++) {

 

            // loop for column of matrix

            for (int j = 0; j < m[i].length; j++) {

 

                // loop for comparison and swapping

                for (int k = 0; k < m[i].length - j - 1; k++) {
                    if (m[i][k] > m[i][k + 1]) {

 

                        // swapping of elements

                        double t = m[i][k];

                        m[i][k] = m[i][k + 1];

                        m[i][k + 1] = t;

                    }

                  }
               
           }
        
     }
    
        
    
    
    
     
   
    
      
       
List<Vec> candidates = new ArrayList<>() ;
         Circle [] [] [] c= new  Circle[elementSize ] [elementSize ] [2] ;
       double [][] dist = new double[m1.columns][m1.lines];
       for(int  g=0;m1.columns;  g++) {
           for(int h=0; h<m1.lines;  h++) {
       
      c[g][h] [0] =new Circle(g*p1.columns/elementSize, 1.*h*p1.lines/elementSize, elementSize);  
      c[g][h] [1] =new Circle(g*p2.columns/elementSize, 1.*h*p2.lines/elementSize, elementSize);  

                               
    
           

    

        
        
        
               // sort by importance or surgace
    p1=m1;p2=m2;
    
         
 ImageIO.write(m2.getImage(), "jpeg", out);
        
               // mapping
               
       int sort=0;int i=0;int j=0;
       while(i<c.length) {
           while(j<c[i].length) {
               double rMin = sort[i][j];
                
           }
       }
           
             for(double g=0; g<p1.columns;  g++) {
           for(int h=0; h<p1.lines;  h++) {
              
           
       for(int i1=0; i1<p2.columns ; i1++) {
          for(int j1=0; j1<p2.lines ; j1++) {
for(int i2=0; i1<p2.columns ; i2++) {
   
          for(int j2=0; j2<p2.lines ; j2++) {
if(level(cil) ==level(cir)) {
p2.colorsRegion(i1, j1, j1-i1,j1-i1, Color. Red) ;
} 
}
   }
   }
}}}}}}
*/

public void writeEnd(){

        // try min diff
               
              
           
        p2=m2;p1=m1;
        
    candidates. forEach( c -> {
       p2.setCompNo(0);
        p2.set(i,j, rij) ;
           p2.setCompNo(1);
        p2.set(i,j,rij);
   p2.setCompNo(2) 
     ;
p1.set(di1);
p1.setCompNo(0) ;
p1. set(i,j,di1);
           pix1.setCompNo(1) ;
p1. set(i,j,di1);
 p1.setCompNo(2);
p1. set(i,j,di1);
        //return dist;
      });// end method
     
       
    
    di1 = cij.dist(ci1);
               di2 = cir.dist(ci1);
           
        for(int i=0; i<cij.r; i++)
           for(int j = 0 ; j< ci1.r; j++)
               dist[i][j]= cij.r-ci1.r;
            
        
    for(int i=0; i<cij.r; i++)
           for(int j1 = 0 ; j1< cir.r; j1++)
               dist[i][j]= cij.r-cir.r;
               if(dist[i][j]<10.0)
                   candidates.add(new Vec(i,j,i1,j1));
        
        
            
            
         
        
        try {
        
    if(!in.getName().endsWith(".jpg"))
        return false;
    File file = in;
     
        
       try{
ImageIO.write(m2.getImage(), "JPEG", out);
           ImageIO.write(m2.getImage(), "JPEG", new File(out.getParent()+2+"jpg"));
           
           //ImageIO.write(m2g, "JPEG", new File(out.getParent()+5+"jpg"));
           return true;
      } catch(Exception ex) {
           ex.printStackTrace();
      }
    
  
   }

   
            
