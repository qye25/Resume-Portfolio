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

// a sub class of button
public class CreateFurnitureButton extends Button implements DormGUI {
    private String type;

    public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
        super(x, y, processing);
        this.type = type;
        label = "Create " + type.substring(0, 1).toUpperCase() + type.substring(1);
    }

    /*
     * loop through the furniture array, create a new furniture object and store the reference to
     * the array
     */
    @Override
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver()) {
            int key = -1;
            for (int i = furniture.length - 1; i >= 0; i--) {
                if (furniture[i] == (null)) {
                    key = i;
                }
            }

            furniture[key] = new Furniture(type, processing);
        }

    }
}
