import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.lang.NullPointerException;
/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * Add to it and make it your own!
 * edited:
 * now the world also organizes the buttons and saves image as png files
 * @author Jordan Cohen
 * @version November 2013
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "colour.jpg";

    // Objects and Variables:
    private ImageHolder image;
    //buttons
    private TextButton transformations;
    private TextButton transformationsClicked;
    private TextButton colourAdjustments;
    private TextButton colourAdjustmentsClicked;
    private TextButton blueButton;
    private TextButton redButton;
    private TextButton greenButton;
    private TextButton hRevButton;
    private TextButton vRevButton;
    private TextButton openFile;
    private TextButton rotate90clockwise;
    private TextButton rotate90counterclockwise;
    private TextButton rotate180;
    private TextButton greyscale;
    private TextButton negative;
    private TextButton undo;
    private TextButton undoDisable;
    private TextButton redo;
    private TextButton redoDisable;
    private TextButton brighten;
    private TextButton darken;
    private TextButton sepia;
    private TextButton saveAsPNG;

    private String fileName;
    //useful private variables
    private int undoNum;
    private int acctNum;
    private boolean changeAfterUndo;
    private boolean transClicked;
    private boolean colourClicked;
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE);
        transformations = new TextButton(" Transformations [+] ");
        transformationsClicked = new TextButton(" Transformations [-]  ", true);
        colourAdjustments = new TextButton(" Colour Adjustments [+] ");
        colourAdjustmentsClicked = new TextButton(" Colour Adjustments [-]  ", true);
        blueButton = new TextButton(" [ Blue-ify ] ");
        redButton = new TextButton(" [ Red-ify] ");
        greenButton = new TextButton(" [ Green-ify ] ");
        hRevButton = new TextButton(" [ Flip Horizontal ] ");
        vRevButton = new TextButton(" [ Flip Vertical ] ");
        openFile = new TextButton(" [ Open File: " + STARTING_FILE + " ] ");
        rotate90clockwise = new TextButton(" [ Rotate  90° right ] ");
        rotate90counterclockwise = new TextButton(" [ Rotate  90° left ] ");
        rotate180 = new TextButton(" [ Rotate 180° ] ");
        greyscale = new TextButton(" [ Greyscale ] ");
        negative = new TextButton (" [ Negative ] ");
        undo = new TextButton (" [ Undo ] ");
        redo = new TextButton (" [ Redo ] ");
        brighten = new TextButton (" [ Brighten ] ");
        darken = new TextButton (" [ Darken ] ");
        sepia = new TextButton (" [ Sepia Photo ] ");
        saveAsPNG = new TextButton (" [ Save As PNG ] ");

        // Add objects to the screen
        addObject (image, 300, 300);
        addObject (transformations, 684, 90);
        addObject (colourAdjustments, 692, 120);
        addObject (saveAsPNG, 690, 570);
        addObject (openFile, 692, 24);
        //clears the world every time it is reset
        Processor.clear();
        Processor.addToList(image.getBufferedImage());
        //initialize variables
        transClicked = false;
        colourClicked = false;
    }

    /**
     * Act() method just checks for mouse input
     */
    public void act ()
    {
        // organizes the buttons
        if (Greenfoot.mouseClicked(transformations)&& !colourClicked)
        {
            addObject (transformationsClicked, 684, 90);
            addObject (hRevButton, 700, 120);
            addObject (vRevButton, 694, 150);
            addObject (rotate90clockwise, 705, 180);
            addObject (rotate90counterclockwise, 701, 210);
            addObject (rotate180, 692, 240);
            removeObject(colourAdjustments);
            addObject (colourAdjustments, 692, 270);
            transClicked= true;
        }
        if (Greenfoot.mouseClicked(transformations)&& colourClicked)
        {
            addObject (transformationsClicked, 684, 90);
            addObject (hRevButton, 700, 120);
            addObject (vRevButton, 694, 150);
            addObject (rotate90clockwise, 705, 180);
            addObject (rotate90counterclockwise, 701, 210);
            addObject (rotate180, 692, 240);
            removeObject(colourAdjustments);
            addObject (colourAdjustments, 692, 270);
            transClicked= true;
            removeObject(colourAdjustmentsClicked);
            removeObject(colourAdjustments);
            removeObject(blueButton);
            removeObject(redButton);
            removeObject(greenButton);
            removeObject(greyscale);
            removeObject(negative);
            removeObject(brighten);
            removeObject(darken);
            removeObject(sepia);
            addObject (colourAdjustments, 692, 270);
            addObject (colourAdjustmentsClicked, 692, 270);
            addObject (redButton, 679, 300);
            addObject (blueButton, 682, 330);
            addObject (greenButton, 687, 360);
            addObject (greyscale, 689, 390);
            addObject (negative, 685, 420);
            addObject (brighten, 685, 450);
            addObject (darken, 681, 480);
            addObject (sepia, 694, 510);
        }
        else if (Greenfoot.mouseClicked(transformationsClicked) && !colourClicked)
        {
            removeObject(transformationsClicked);
            removeObject(hRevButton);
            removeObject(vRevButton);
            removeObject(rotate90clockwise);
            removeObject(rotate90counterclockwise);
            removeObject(rotate180);
            removeObject(colourAdjustments);
            addObject (colourAdjustments, 692, 120);
            transClicked=false;
        }
        else if (Greenfoot.mouseClicked(transformationsClicked) && colourClicked)
        {
            removeObject(transformationsClicked);
            removeObject(hRevButton);
            removeObject(vRevButton);
            removeObject(rotate90clockwise);
            removeObject(rotate90counterclockwise);
            removeObject(rotate180);
            removeObject(colourAdjustments);
            addObject (colourAdjustments, 692, 120);
            transClicked=false;
            removeObject(colourAdjustmentsClicked);
            removeObject(colourAdjustments);
            removeObject(blueButton);
            removeObject(redButton);
            removeObject(greenButton);
            removeObject(greyscale);
            removeObject(negative);
            removeObject(brighten);
            removeObject(darken);
            removeObject(sepia);
            addObject (colourAdjustments, 692, 120);
            addObject (colourAdjustmentsClicked, 692, 120);
            addObject (redButton, 679, 150);
            addObject (blueButton, 682, 180);
            addObject (greenButton, 687, 210);
            addObject (greyscale, 689, 240);
            addObject (negative, 685, 270);
            addObject (brighten, 685, 300);
            addObject (darken, 681, 330);
            addObject (sepia, 694, 360);
            colourClicked = true;
        }
        if (Greenfoot.mouseClicked(colourAdjustments)&&transClicked)
        {
            addObject (colourAdjustmentsClicked, 692, 270);
            addObject (redButton, 679, 300);
            addObject (blueButton, 682, 330);
            addObject (greenButton, 687, 360);
            addObject (greyscale, 689, 390);
            addObject (negative, 685, 420);
            addObject (brighten, 685, 450);
            addObject (darken, 681, 480);
            addObject (sepia, 694, 510);
            colourClicked = true;
        }
        else if (Greenfoot.mouseClicked(colourAdjustments)&&!transClicked)
        {
            addObject (colourAdjustmentsClicked, 692, 120);
            addObject (redButton, 679, 150);
            addObject (blueButton, 682, 180);
            addObject (greenButton, 687, 210);
            addObject (greyscale, 689, 240);
            addObject (negative, 685, 270);
            addObject (brighten, 685, 300);
            addObject (darken, 681, 330);
            addObject (sepia, 694, 360);
            colourClicked = true;
        }
        else if (Greenfoot.mouseClicked(colourAdjustmentsClicked) )
        {
            removeObject(colourAdjustmentsClicked);
            removeObject(blueButton);
            removeObject(redButton);
            removeObject(greenButton);
            removeObject(greyscale);
            removeObject(negative);
            removeObject(brighten);
            removeObject(darken);
            removeObject(sepia);
            colourClicked = false;
        }
        checkMouse();
        if(Processor.getTermNum() >1)
        {
            addObject (undo, 650, 54);
        }
        if(undoNum >0)
        {
            addObject (redo, 735, 54);
        }

    }

    /**
     * Check for user clicking on a button
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.

        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(blueButton)){
                image.setImage(Processor.blueify(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(redButton)){
                image.setImage(Processor.redify(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(greenButton)){
                image.setImage(Processor.greenify(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(hRevButton)){
                image.setImage(Processor.flipHorizontal(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(vRevButton)){
                image.setImage(Processor.flipVertical(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(rotate90clockwise)){
                image.setImage(Processor.rotate90clockwise(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(rotate90counterclockwise)){
                image.setImage(Processor.rotate90counterclockwise(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(rotate180)){
                image.setImage(Processor.rotate180(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(greyscale)){
                image.setImage(Processor.greyscale(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(negative)){
                image.setImage(Processor.negative(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(brighten)){
                image.setImage(Processor.brighten(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(darken)){
                image.setImage(Processor.darken(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(sepia)){
                image.setImage(Processor.sepia(image.getBufferedImage()));
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(saveAsPNG)) {
                save();
                checkForExcess();
            }
            else if (Greenfoot.mouseClicked(undo)){
                image.setImage(Processor.undo());
                removeObject (undo);
                undoNum++;
            }
            else if (Greenfoot.mouseClicked(redo)){
                image.setImage(Processor.redo());
                removeObject (redo);
                undoNum--;
            }
            else if (Greenfoot.mouseClicked(openFile))
            {
                openFile ();
            }

        }
        else if (undoNum==0)
        {
            removeObject (redo);
        }

    }

    /**
     * checks if excess images are being created
     */
    private void checkForExcess()
    {
        if(undoNum != 0)
        {
            undoNum=0;
            Processor.removeExcess(Processor.getTermNum());
        }
    }

    /**
     * saves the currents images as a png files
     */
    public void save () 
    {
        try
        {
            String fileName = JOptionPane.showInputDialog("Enter file name (donnot include extension)");
            fileName += ".png";
            File f = new File (fileName);
            ImageIO.write(image.getImage().getAwtImage(), "png", f); // need to do some imports
            JOptionPane.showMessageDialog( null,
                "File " + fileName + " saved successfully",
                "Complete",
                JOptionPane.INFORMATION_MESSAGE);
        }

        catch(IOException e)
        {
            JOptionPane.showMessageDialog( null,
                "Invalid File Name",
                "IO Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name with extension");

        // If the file opening operation is successful, update the text in the open file button
        if (image.openFile (fileName))
        {
            String display = " [ Open File: " + fileName + " ] ";
            openFile.update (display);
        }

    }
}

