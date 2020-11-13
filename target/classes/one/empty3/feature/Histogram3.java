package one.empty3.feature;

import one.empty3.io.ProcessFile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*** 
 * radial density of region (x, y, r)
 * 
 */
public class Histogram3 extends ProcessFile {
    private int numLevels = 3;
    private PixM m = null;
    private double[] max;
    private double[] min;
    private double minimumI = 0.1;
    public double rFact = 2.0;
    public class Circle {
        public double x, y, r;
        public double i;

        public Circle(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.r = r;
            i = 0.0;
        }

        @Override
        public String toString() {
            return "Circle{" +
                    "x=" + x +
                    ", y=" + y +
                    ", r=" + r +
                    ", i=" + i +
                    '}';
        }
    }

    //private final int[][][] levels;
    public void setM(PixM m2) {
        this.m = m2;
    }
    /***
     *
     * 
     */
    
    public Histogram3(int numLevels){
        

      min = new double[numLevels];
      max = new double[numLevels];

      for(int i = 0; i< numLevels; i++) {
          min[i] = 1.0*i/ numLevels;
          max[i] = 1.0*(i+1)/ numLevels;
        }
      this.numLevels = numLevels;
      minimumI = 0.4;
    }
    public Histogram3() {
        this(5);
    
    }
    public void makeHistogram(double r) {

    }
    public Circle getLevel(Circle c) {
        // I mean. Parcourir le cercle
        // mesurer I / numPoints
        // for(int i=Math.sqrt()
        //  return c;
        int count = 0;
        double intensity = 0.0;
        for (double i = -c.r; i <= c.r; i++) {
            for (double j = -c.r; j <= c.r; j++) {
                if (Math.sqrt((i) * (i) + (j) * ( j)) <= c.r*c.r
                        && c.x-i>=0 && c.y-j>=0 && c.x+i<m.columns && c.y+j<m.lines) {
                    intensity += m.getIntensity((int)( c.x+i), (int) (c.y+j));
                    count++;
                }
            }
        }

        if(count>0) {
            c.i = intensity / count;
        }
        else {
            c.i = 0.0;
            c.r = 0;
        }



        return c;
    }
    public double nPoints(int x, int y, int w, int h) {
        return 0.0;
    }


    public List<Circle> getPointsOfInterest(double rMin0, double iMin) {
        ArrayList<Circle> circles;
        circles = new ArrayList<>();

        // Classer les points par intensité et rayon

       // for(double intensity=1.0; intensity>=0.0; intensity-=0.1) {
            for(int i=0; i<m.columns; i++) {
                for(int j=0; j<m.lines; j++) {
                    double rMin = rMin0;
                    Circle level = getLevel(new Circle(i, j, rMin));
                   // level.i = intensity;
                    //int index = Math.max(((int) (level.i * numLevels)), 0);
                    //index = Math.min(numLevels-1, index);
                    double iOrigin = level.i;
                    int 
                        index0 = (int)(level.i*(numLevels-1));
                    //if(index0<0) index0 = 0;
                    //if(index0<=min.length) index0 = min.length-1;
                    while(level.i>=iMin&&level.i>=iOrigin-1.0/numLevels&&level.i<=iOrigin+1.0/numLevels && level.r<Math.max(m.columns, m.lines)) {

                        level.r*= rFact;
                        getLevel(level);
                    }
                    level.r /= rFact;
                    if(level.r>=rMin0) {
                        circles.add(level);
                    }
                }

          //   }
        }

        
        return circles;
    }
    public List<List<Circle>> group(List<Circle> circles) {
        List<List<Circle>> out = new ArrayList<>();
        
        
        return out;
    
    }
    public boolean process(File in, File out) {
      try {
        File directory = new File(out.getParent());
        PixM imageCoutours = new PixM(ImageIO.read(in));
        this.m = imageCoutours;
        BufferedImage file = m.getImage();
        
     
        double radiusIncr = 4;
       
            
                BufferedImage img  = file;
                BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
                List<Circle> pointsOfInterest;
                pointsOfInterest = getPointsOfInterest(4.0, 0.25);
            // grands;cercles = grandes iles les separer
            // verifier les distances et constantes i
            // petits cercles successifs entoures 
            // de grands ou plus grands cercles = 
            // coins, corners et possibles features.
                
          System.out.println("getPointsOfInterest ");
          
          pointsOfInterest.sort(new Comparator<Circle>() {
                    @Override
                    public int compare(Circle o1, Circle o2) {
                        double v = o2.r - o1.r;
                        if(v<0)
                             return -1;
                        if(v>0)
                             return 1;
                        return (int)((o2.i-o1.i)/Math.abs(o2.i - o1.i));
                    }
                });
          
          System.out.println("draw ");
          
          pointsOfInterest.stream().forEach(circle -> {
                    if (circle.i > minimumI && circle.r>img.getWidth()/10) {
                        Graphics graphics = img2.getGraphics();
                        graphics.setColor(Color.RED);
                        graphics.drawOval((int) (circle.x - circle.r), (int) (circle.y - circle.r), (int) (2*circle.r), (int) (2*circle.r));
                        ;
                    }
                });
                // grouper les points par similarites et distances
              /*  group(pointsOfInterest);
                File fileToWrite = new File(directory.getAbsolutePath()
                        + "level" + ".jpg");
                File fileToWrite2 = new File(directory.getAbsolutePath()
                        + "level"+ "_NEW.jpg");
                File fileToWrite3 = new File(directory.getAbsolutePath()
                        + "level"+ "_NEW_RGB.jpg");
                //fileToWrite.mkdirs();*/
                ImageIO.write(img2, "JPEG", out);
                /*
                ImageIO.write(img, "JPEG", fileToWrite);
                ImageIO.write(img, "JPEG", fileToWrite2);
                ImageIO.write(img, "JPEG", fileToWrite3);
*/
            
               
       } catch (IOException exception) {
            exception.printStackTrace();
            return false;
       }
        return true;
    }
}
