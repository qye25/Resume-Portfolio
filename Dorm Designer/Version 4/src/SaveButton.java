import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dorm Designer 5000
// Files: DormDesigner.jar; background.png; bed.png; sofa.png; desk.png; dresser.png; sink.png;
// CreateFurnitureButton.java; DormGUI.java; Furniture.java; Main.java;
// SaveButton.java; Button.java; LoadButton.java; clearButton.java
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
public class SaveButton extends Button implements DormGUI{
    
    /** initialize the position and text of the button */
    public SaveButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        label = "Save Room";
    }
    
    /*
     * all of the furniture objects from your room’s Furniture[] array being saved 
     * in a file called “RoomData.ddd” in the working directory
     */
    @Override
    public void mouseDown(Furniture[] array) {

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
}
