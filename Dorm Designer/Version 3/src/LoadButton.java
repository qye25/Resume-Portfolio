import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
 * load the room layout from the RoomData.ddd file
 */
public class LoadButton {
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;

    private PApplet processing;
    private float[] position;
    private String label;

    /** initialize the position and text of the button */
    public LoadButton(float x, float y, PApplet processing) {
        this.processing = processing;
        label = "Load Room";
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
        processing.rect(position[0] - WIDTH / 2, position[1] + HEIGHT / 2, position[0] + WIDTH / 2,
                        position[1] - HEIGHT / 2);
        // print black text on the rectangle
        processing.fill(0);
        processing.text(label, position[0], position[1]);
    }

    /*
     * all of the furniture objects from your room’s Furniture[] array being cleared 
     * load a file called “RoomData.ddd” in the working directory 
     * and create a new furniture array based on the file
     */
    public Furniture[] mouseDown(Furniture[] array) {
        ArrayList<String> room = new ArrayList<String>();
        String[] output = new String[room.size()];
        File in;
        Scanner s = null;
        // clear the original furniture array
        if (isMouseOver()) {
            for (int j = 0; j < array.length; j++) {
                array[j] = null;
            }

            // open the file and save the non-empty, correctly formatted line to the arraylist
            try {
                in = new File("RoomData.ddd");
                s = new Scanner(in);
                int count = 0; // count the number of lines
                while (s.hasNextLine()) {
                    String str = s.nextLine();
                    if (!str.isEmpty()) {
                        if (isValidLine(str)) {
                            room.add(str);
                        }

                        else {
                            // if the line is not correctly formatted
                            System.out.println("WARNING: Found incorrectly formatted line"
                                           + " in file: <"+ (count + 1) + ">");
                        }
                    }
                    count++;
                }
                s.close();
            } catch (FileNotFoundException e) {
               // if the file does not exist
               System.out.println("WARNING: Could load room contents from file RoomData.ddd.");

            }
            // convert the arraylist to an array of string
            output = room.toArray(output);
            int index = 0; // count number of valid furniture stored in the array
            // loop through the array
            for (int i = 0; i < output.length; i++) {
                // decode the type, position and orientation of the furniture
                // from pure text
                String type = output[i].split(":")[0].trim();
                String[] pos = output[i].split(":")[1].split(",");
                float x = Float.parseFloat(pos[0].trim());
                float y = Float.parseFloat(pos[1].trim());
                int rotation = Integer.parseInt(pos[2].trim());
                try {
                    array[index] = new Furniture(type, processing, x, y, rotation);
                    index++;
                } catch (NullPointerException e) {
                    // if there is not an image corresponding a furniture name
                    System.out.println("WARNING: Could not find an image for "
                                    + "a furniture object of type: <" + type + ">");
                } catch (IndexOutOfBoundsException e) {
                    // if there are more than 6 valid furniture
                    System.out.println("WARNING: Unable to load more furniture.");
                    break;
                }
            }
        }

        return array;
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

    /*
     * check if a line in the file is correctly formatted return false if a furniture name 
     * is not present or is not proceeded by a colon and then three comma separated numbers
     */
    public boolean isValidLine(String s) {
        String t = s.split(":")[0]; // should be a furniture name
        // if the line does not contain a colon or comma or a furniture name
        if (!s.contains(":") || !s.contains(",") || t.isEmpty()) {
            return false;
        } else {
            String[] xyr = s.split(":")[1].split(",");
            // if there are not 2 commas
            if (xyr.length != 3) {
                return false;
            }
            // if any of the three numbers is empty
            else if (xyr[0].trim().isEmpty() || xyr[1].trim().isEmpty()
                            || xyr[2].trim().isEmpty()) {
                return false;
            }
            return true;
        }
    }
}
