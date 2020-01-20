/**
 * Starter code for Processor - the class that processes images.
 * <p>
 * This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. 
 * 
 * edited:
 *  the class now has many other manipulation methods including different 
 *  transformations and colour adjustments
 * @author Jordan Cohen 
 * @version November 2013
 */
import greenfoot.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.swing.JOptionPane;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Processor  
{
    private static ArrayList<GreenfootImage> List = new ArrayList<GreenfootImage>();
    private static int term;

    /**
     * Example colour altering method by Mr. Cohen. This method will
     * increase the blue value while reducing the red and green values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage blueify (BufferedImage bi)
    {

        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (blue < 254)
                    blue += 2;
                if (red >= 50)
                    red--;
                if (green >= 50)
                    green--;
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method will increase the red value while 
     * reducing the green and blue values.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage redify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic RED-er
                if (blue >= 50)
                    blue --;
                if (red <254)
                    red+=2;
                if (green >= 50)
                    green--;
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method will increase the green value while 
     * reducing the green and blue values.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage greenify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic GREEN-er
                if (blue >= 50)
                    blue --;
                if (red >= 50)
                    red--;
                if (green <254)
                    green+=2;
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method will adjust the red, green and blue values
     * to make the image look like an old sepia photo
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage sepia (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                //applying ratio changes to the r g b values
                red= (int) (0.393*red + 0.769*green + 0.189*blue);
                green= (int) (0.349*red + 0.686*green + 0.168*blue);
                blue= (int) (0.272*red + 0.534*green + 0.131*blue);

                //check if the values are off the limits
                if (blue >= 255)
                    blue =255;
                if (red >255)
                    red=255;
                if (green >255)
                    green=255;
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method will brighten the image by
     * increasing its red, green and blue values
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage brighten (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic brighter
                red+=15;
                green+=15; 
                blue+=15;
                if (red > 250)
                    red = 250;
                if (green > 250)
                    green = 250;
                if (blue > 250)
                    blue = 250;
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method will darken the image by
     * decreasing its red, green and blue values
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage darken (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic darker
                red-=15;
                green-=15; 
                blue-=15;
                if (red < 0)
                    red = 0;
                if (green< 0)
                    green = 0;
                if (blue < 0)
                    blue = 0;
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method turns the image into greyscale
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage greyscale (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                // make the pic black and white
                red=(red + green + blue)/3;
                if(red>2550)
                {
                    red = 255;
                }
                else if(red <0)
                {
                    red =0;
                }
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, red, red, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method turns the all the colours
     * in the image into their negative colours
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage negative (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                // make the colours negative
                red = 255-red;
                green = 255-green;
                blue= 255-blue;
                // package and assign it to the new buffered image
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method mirrors the image horizontally
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage flipHorizontal (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // new image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        // Using array size as limit
        for(int x=0; x<xSize; x++)
        {
            for(int y=0; y<ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // horizontally backward and encoded together in an integer
                int rgb = bi.getRGB(xSize-x-1, y);
                newBi.setRGB (x, y, rgb);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method mirrors the image vertically
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage flipVertical (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // new image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        for(int x=0; x<xSize; x++)
        {
            for(int y=0; y<ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // vertically backward and encoded together in an integer
                int rgb = bi.getRGB(x, ySize-y-1);
                newBi.setRGB (x, y, rgb);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method rotates the image 90° clockwise
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage rotate90clockwise(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // new image with original width as length and original length as width
        //to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (ySize, xSize, 3);
        for(int x=0; x<xSize; x++)
        {
            for(int y=ySize-1; y>0; y--)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer in the right order
                int rgb = bi.getRGB(x, y);
                newBi.setRGB ((ySize -1)-y, x, rgb);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method rotates the image 90° counterclockwise
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage rotate90counterclockwise(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // new image with original width as length and original length as width
        //to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (ySize, xSize, 3);
        for(int x=xSize-1; x>0; x--)
        {
            for(int y=0; y<ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer in the right order
                int rgb = bi.getRGB(x, y);
                newBi.setRGB (y, (xSize-1)-x, rgb);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method rotates the image 180°
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage rotate180 (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // new image to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        for(int x=0; x<xSize; x++)
        {
            for(int y=0; y<ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer vertically and horizontally backward
                int rgb = bi.getRGB(x, y);
                newBi.setRGB (xSize-1-x, ySize-1-y, rgb);
            }
        }
        addToList(newBi); // add it to the array list of pictures
        return createGreenfootImageFromBI(newBi); //return it as a greenfoot image
    }

    /**
     * This method undo the last adjustment made
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage undo()
    {
        term--; //update current term
        return List.get(term-1); //return the previous image
    }

    /**
     * This method redo the last step that was undid
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage redo()
    {
        term++; //update current term
        return List.get(term-1);//return the previous image
    }

    /**
     * This method returns the current term number
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static int getTermNum ()
    {
        return term;
    }

    /**
     * This method returns the current array list size
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static int getLength ()
    {
        return List.size();
    }

    /**
     * This method resets the array list and term number
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void clear()
    {
        List.clear();
        term=0;
    }

    /**
     * This method adds the image to the array list
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void addToList(BufferedImage bi)
    {
        List.add(createGreenfootImageFromBI(deepCopy(bi)));
        term++;
    }

    /**
     * This method removes the image from the array list
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void removeExcess(int x)
    {
        List.remove(x-1);
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }

    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     *
     * @param newBi The BufferedImage to convert.
     *
     * @return GreenfootImage A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);
        return returnImage;
    }

    /**
     * Takes in a BufferedImage and returns deep copy of it.
     *
     * @param newBi The BufferedImage to convert.
     *
     * @return BufferedImage a copy of the BufferedImage provided.
     */
    public static BufferedImage deepCopy(BufferedImage bi) {

        ColorModel cm = bi.getColorModel();

        boolean isAlphaPremultip = cm.isAlphaPremultiplied();

        WritableRaster raster = bi.copyData(null);

        return new BufferedImage(cm, raster, isAlphaPremultip, null);

    }
}
