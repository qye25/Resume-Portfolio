
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dorm Designer 5000
// Files: DormDesigner.jar; background.png; bed.png; sofa.png; desk.png; dresser.png; sink.png;
// CreateFurnitureButton.java; DormGUI.java; Furniture.java; Main.java;
// SaveButton.java; Button.java; LoadButton.java; clearButton.java
// 
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
 * organize the data related to each furniture into their own instantiable objects
 */
public class Furniture implements DormGUI{
    // fields
    private PApplet processing;
    private PImage image;
    private float[] position;
    private boolean isDragging;
    private int rotations;
    private String type;

    // initializes the fields of a new bed object positioned in the center of the display
    public Furniture(String s, PApplet processing) {
        this.processing = processing;
        this.type = s;
        image = processing.loadImage("images/" + type + ".png");
        position = new float[] {processing.width / 2, processing.height / 2};
        processing.image(image, position[0], position[1]);
        isDragging = false;
        rotations = 0;
    }

    // another constructor
    // if the user want to set the furniture at a specific position and direction
    public Furniture(String s, float x, float y, int rotation, PApplet processing) {
        this.processing = processing;
        this.type = s;
        image = processing.loadImage("images/" + type + ".png");
        position = new float[] {x, y};
        processing.image(image, position[0], position[1]);
        isDragging = false;
        rotations = rotation;
    }

    // draws this bed at its current position
    public void update() {
        // display the image at its position and direction
        processing.image(image, position[0], position[1], rotations * PApplet.PI / 2);
        if (isDragging) {
            // move with the mouse if being dragged
            position[0] = processing.mouseX;
            position[1] = processing.mouseY;
        }
    }

    // used to start dragging the bed, when the mouse is over this bed when it is pressed
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver()) {
            isDragging = true;
        }
    }

    // used to indicate that the bed is no longer being dragged
    public void mouseUp() {
        isDragging = false;
    }

    // helper method to determine whether the mouse is currently over this bed
    public boolean isMouseOver() {
        float w = image.width;
        float h = image.height;

        if (position != null) {
            if (rotations % 2 == 0 && Math.abs(position[0] - processing.mouseX) <= w / 2
                            && Math.abs(position[1] - processing.mouseY) <= h / 2) {
                return true;
                // after even number of rotations
                // if the difference of the bed's and mouse's x coordinate is
                // within half of the bed's width, and so as the y coordinates,
                // the mouse is over the bed
            } else if (rotations % 2 == 1 && Math.abs(position[0] - processing.mouseX) <= h / 2
                            && Math.abs(position[1] - processing.mouseY) <= w / 2) {
                return true;
                // after odd number of rotations
            }
        }
        return false;
    }

    // keep track of the number of rotations
    public void rotate() {
        rotations++;
    }

    // four basic getter methods for user to get the object's private fields in other classes
    public String gettype() {
        return this.type;
    }

    public float getx() {
        return position[0];
    }

    public float gety() {
        return position[1];
    }

    public int getro() {
        return rotations;
    }

}
