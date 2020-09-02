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
public class ClearButton extends Button implements DormGUI{
    /*
     * constructor create a button object with label "Clear Room"
     */
    public ClearButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        label="Clear Room";
        // TODO Auto-generated constructor stub
    }
    
    /*
     * loop through the furniture array and set everything to null
     */
    @Override
    public void mouseDown(Furniture[] furnitures) {
       if(isMouseOver()) {
        for (int i=0; i< furnitures.length;i++) {
            furnitures[i]=null;
        }
    }
    }
}
