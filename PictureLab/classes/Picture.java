import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void zeroGR()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int R = pixelObj.getRed();
        int G = pixelObj.getGreen();
        int B = pixelObj.getBlue();
        pixelObj.setRed(255 - R);
        pixelObj.setGreen(255 - G);
        pixelObj.setBlue(255 - B);
      }
    }
  }
  public void water()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int R = pixelObj.getRed();
        int G = pixelObj.getGreen();
        int B = pixelObj.getBlue();
        
        //pixelObj.setRed(R + 40);
        //pixelObj.setGreen(G - 30);
        //pixelObj.setBlue(B - 30);
        pixelObj.setRed(R + 40);
      }
    }
  }
  
  public void cropCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol,
        int endSourceCol,int startDestRow, int startDestCol )
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = sourcePicture.getPixels2D();
    for (int row = startSourceRow , toRow = startDestRow; row < endSourceRow && toRow < toPixels.length; 
        row++, toRow++)
    {
      for (int col = startSourceCol, toCol = startDestCol; col < endSourceCol && 
        toCol < toPixels[0].length; col++, toCol++)
      {
        fromPixel = fromPixels[row][col];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    } 
  }
  
  public void gray()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int R = pixelObj.getRed();
        int G = pixelObj.getGreen();
        int B = pixelObj.getBlue();
        int av = (R + G + B) / 3;
        pixelObj.setRed(av);
        pixelObj.setGreen(av);
        pixelObj.setBlue(av);
      }
    }
  }
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * Horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    int height = pixels.length;
    for (int col = 0; col < width; col++)
    {
      for (int row = 0; row < height / 2; row++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }  
  
  /** Method that mirrors the picture around a 
    * Horizontal and Verticle mirror in the center of the picture
    * from left to right and top to bottom */
  public void mirrorHV()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftTopPixel = null;
    Pixel rightBottomPixel = null;
    int width = pixels[0].length;
    int height = pixels.length;
    for (int row = 0; row < height; row++)
    {
      for (int col = 0; col < width; col++)
      {
        leftTopPixel = pixels[row][col];
        rightBottomPixel = pixels[height - 1 - row][width - 1 - col];
        rightBottomPixel.setColor(leftTopPixel.getColor());
      }
    } 
  }   
  
  /** Method that mirrors the picture around a 
    * Diagonal mirror in the center of the picture
    * from left to right and top to bottom */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftTopPixel = null;
    Pixel rightBottomPixel = null;
    int width = pixels[0].length;
    int height = pixels.length;
    int line = 0;
    if(width <= height)
    {
        line = width;
    }
    else
    {
        line = height;
    }
    for (int row = 0; row < line; row++)
    {
      for (int col = 0; col < line; col++)
      {
        leftTopPixel = pixels[row][col];
        rightBottomPixel = pixels[line - 1 - col][line - 1 - row];
        rightBottomPixel.setColor(leftTopPixel.getColor());
      }
    } 
  }   
  
  /** Method that mirrors the picture around a 
    * Diagonal mirror in the center of the picture
    * from left to right and top to bottom */
  public void mirrorDiagonal2()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftTopPixel = null;
    Pixel rightBottomPixel = null;
    int width = pixels[0].length;
    int height = pixels.length;
    int line = 0;
    int n = width - height;
    if(width <= height)
    {
        line = width;
    }
    else
    {
        line = height;
    }

    for (int row = 0; row < line; row++)
    {
      for (int col = 0; col < line; col++)
      {
        leftTopPixel = pixels[row][col];
        rightBottomPixel = pixels[line - 1 - col][line - 1 - row];
        rightBottomPixel.setColor(leftTopPixel.getColor());
      }
    }
    for (int row = 0; row < height; row++)
    {
      for (int col = 0; col < width; col++)
      {
        leftTopPixel = pixels[row][col];
        rightBottomPixel = pixels[height - 1 - row][width - 1 - col];
        rightBottomPixel.setColor(leftTopPixel.getColor());
      }
    } 
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftTopPixel = pixels[row][col];
        rightBottomPixel = pixels[row][width - 1 - col];
        rightBottomPixel.setColor(leftTopPixel.getColor());
      }
    }
    for (int row = 0; row < line; row++)
    {
      for (int col = 0; col < line; col++)
      {
        leftTopPixel = pixels[row][col];
        rightBottomPixel = pixels[line - 1 - col][line - 1 - row];
        rightBottomPixel.setColor(leftTopPixel.getColor());
      }
    }
    for (int row = n; row < line + n - 1; row++)
    {
      for (int col = 0; col < line; col++)
      {
        leftTopPixel = pixels[col][row];
        rightBottomPixel = pixels[line + line - 1 - row][line - 1 - col];
        rightBottomPixel.setColor(leftTopPixel.getColor());
      }
    }
  }   
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    //Picture canvas = new Picture("1200x1200.jpg");
    Picture nat1 = new Picture("nat.jpg");
    Picture nat2 = new Picture("nat.jpg");
    Picture nat3 = new Picture("nat.jpg");
    Picture nat4 = new Picture("nat.jpg");
    Picture nat5 = new Picture("nat.jpg");
    nat3.mirrorVertical();
    nat4.mirrorVertical();
    nat4.gray();
    nat3.mirrorDiagonal();
    nat1.negate();
    nat5.mirrorHV();
    nat2.mirrorHorizontal();
    this.copy(nat3,175,0);
    this.copy(nat4,175,280);
    this.copy(nat1,0,0);
    this.copy(nat2,0,280);
    this.copy(nat5,86,140);
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
