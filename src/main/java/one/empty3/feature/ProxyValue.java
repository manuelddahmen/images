package one.empty3.feature ;


import java.io.File ;
import one.empty3.library.Point3D;
import one.empty3.io.ProcessFile;
import static org.junit.Assert.*;
import javax.imageio.ImageIO;
import one.empty3.feature.*;
import java.util.logging.*; 

public class ProxyValue extends ProcessFile {

    public boolean process (File in, File out) {
        
    if(!in.getName().endsWith(".jpg"))
        return false;
    File file = in;
    PixM original = null;
    
    try {
        original = PixM.getPixM(ImageIO.read(in), 500.0);
    } catch(Exception ex) {
        ex.printStackTrace();
      return false;
       // assertTrue(false);
      
     }
        int p=0;
        PixM copy = original.copy();
         for (int i = 0; i < original.columns; i++)

                for (int j = 0; j < original.lines; j++)
                  
                        
                    
                 
                        if(copy.getIntensity(i,j)<0.3){
                         
                           searchFromTo(original, copy, i, j, 0.3, 1.0);
                            p++;
               
                        }
                
          
    
    
    try {
       ImageIO.write( copy.getImage(), "jpg", out);
     } catch (Exception ex){
        ex.printStackTrace();
        return false;
     }
       
       
        System.out.println("point "+ p);
     
        return true;
    } 
        
        
        
    public void searchFromTo(
           PixM original, PixM copy, int i, int j, double min, double value) {
        Point3D p = null;
        int i2 =i, j2 = j;
        int [] incr = new int[]{
                                 1,1,1,-1,
                                -1,-1,1,-1};
     /*   for(int k=0; k<original.columns*original.lines;k++)
                { 
            
              int [] k1 = new int[] {incr[(k/2)%8], 
                                     incr[(k/2+1)%8]};
                i2+= k1[0];
                j2 += k1[1];
            
           */
        for(int l=1; l<original.columns; l++)
          for(int i3=-l; i3<l; i3++) {
            for(int j3 = -l; j3<l; j3++) {
                i2 = i + i3;
                j2 = j + j3;
                p = null;
                if(i2==i&&j2==j
                  )
                    continue;
                if(original.getIntensity(i2, j2)>= min) {
                        p = new Point3D((double)i2, (double) j2, original.get(i2,j2));
                    }
                
                if(p!=null) {
                     copyPixel(original, (int)(double)(p.get(0)), 
                                     (int)(double)(p.get(1)), 
                               copy, i, j);
                    return;
               } else {
                   
                }
            // }
          
            }
              
          }
       // System.out.println("error not found");
        
        return;
    }
    
    public void copyPixel(PixM m1, int i, int j,
                            PixM m2, int i2, int j2) {
        for(int c = 0; c<3; c++) {
        
            m1.setCompNo(c);
            m2.setCompNo(c);
            
            m2.set(i2, j2, m1.get(i,j));
        }
       
    }
} 
