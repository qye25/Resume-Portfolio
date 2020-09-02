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

// base class of all the button classes
public class Button implements DormGUI {
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    protected PApplet processing;
    private float[] position;
    protected String label;

    /*
     * default constructor set the label as "Button"
     */
    public Button(float x, float y, PApplet processing) {
        this.processing = processing;
        label = "Button";
        position = new float[] {x, y};
    }

    public void update() {
        // TODO Auto-generated method stub
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
     * default mouseDown method
     */
    public void mouseDown(Furniture[] furniture) {
        // TODO Auto-generated method stub
        if (isMouseOver()) {
            System.out.println("A button was pressed.");
        }
    }


    public void mouseUp() {
        // TODO Auto-generated method stub

    }


    public boolean isMouseOver() {
        // TODO Auto-generated method stub
        if (position != null) {
            if (Math.abs(position[0] - processing.mouseX) < WIDTH / 2
                            && Math.abs(position[1] - processing.mouseY) < HEIGHT / 2) {
                return true;
                // if the difference of the button's and mouse's x coordinate is
                // within half of the button's width, and so as the y coordinates,
                // the mouse is over the button
            }
        }
        return false;
    }

}
