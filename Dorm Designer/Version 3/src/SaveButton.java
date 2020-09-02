import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dorm Designer 4000
// Files: DormDesigner.jar; background.png; bed.png; sofa.png; CreateBedButton.java
// Main.java; SaveButton.java; Furniture.java; CreateSofaButton.java; LoadButton.java
// Course: CS300, Spring, 2018
//
// Author: Qinglang Ye
// Email: qye25@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/*
 * similar to other button classes used to save the current furniture array to a new file
 */
public class SaveButton {
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;

    private PApplet processing;
    private float[] position;
    private String label;

    /** initialize the position and text of the button */
    public SaveButton(float x, float y, PApplet processing) {
        this.processing = processing;
        label = "Save Room";
        position = new float[] {x, y};

    }

    /** update the color of the button */
    public void update() {
        if (isMouseOver()) {
            // when mouse is over, the button will be dark gray
            processing.fill(100);
        } else {
            // when mouse is not over, the button will be light gray
            processing.fill(200);
        }
        // draw a rectangle with given height and width
        processing.rect(position[0] - WIDTH / 2, position[1] + HEIGHT / 2, 
                        position[0] + WIDTH / 2, position[1] - HEIGHT / 2);
        // print black text on the rectangle
        processing.fill(0);
        processing.text(label, position[0], position[1]);
    }

    /*
     * all of the furniture objects from your room’s Furniture[] array being saved 
     * in a file called “RoomData.ddd” in the working directory
     */
    public void mouseDown(Furniture[] array) throws FileNotFoundException {

        if (isMouseOver()) {
            try {
                File out = new File("RoomData.ddd");
                PrintWriter pw = new PrintWriter(out);
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != null) {
                        // loop through the furniture array
                        // and print the information line by line
                        pw.println(array[i].gettype() + ":" + array[i].getx() + ","
                                        + array[i].gety() + "," + array[i].getro());

                        // FORMAT type: x, y, rotations
                    }
                }
                pw.close();
            } catch (IOException e) {
                // if the file cannot be created or overwritten, print warning message
                System.out.println("WARNING: Could not save room contents"
                +" to file RoomData.ddd.");
            }

        }

    }

    /** check if the position of mouse is within the range of the button */
    public boolean isMouseOver() {
        if (position != null) {
            if (Math.abs(position[0] - processing.mouseX) < WIDTH / 2
                            && Math.abs(position[1] - processing.mouseY) < HEIGHT / 2) {
                return true;
            }
        }
        return false;
    }
}
