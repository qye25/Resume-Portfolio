import java.io.FileNotFoundException;
import java.util.ArrayList;

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

public class Main {

    // fields
    private ArrayList<DormGUI> guiObjects;
    private PApplet processing;
    private PImage backgroundImage;
    private final static int MAX_LOAD_FURNITURE = 100;

    // Max number of furniture that LoadButton will be allowed to load at once.
    /**
     * This method creates a new Furniture[] for the old mouseDown() methods to make use of. It does
     * so by copying all Furniture references from this.guiObjects into a temporary array of size
     * MAX_LOAD_FURNITURE.
     * 
     * @return that array of Furniture references.
     */
    private Furniture[] extractFurnitureFromGUIObjects() {
        Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
        int nextFreeIndex = 0;
        for (int i = 0; i < guiObjects.size() && nextFreeIndex < furniture.length; i++)
            if (guiObjects.get(i) instanceof Furniture)
                furniture[nextFreeIndex++] = (Furniture) guiObjects.get(i);
        return furniture;
    }

    /**
     * This method first removes all Furniture references from this.guiObjects, and then adds back
     * in all of the non-null references from it's parameter.
     * 
     * @param furniture contains the only furniture that will be left in this.guiObjects after this
     *        method is invoked (null references ignored).
     */
    private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
        for (int i = 0; i < guiObjects.size(); i++)
            if (guiObjects.get(i) instanceof Furniture)
                guiObjects.remove(i--);
        for (int i = 0; i < furniture.length; i++)
            if (furniture[i] != null)
                guiObjects.add(furniture[i]);
    }

    public static void main(String[] args) {

        Utility.startApplication();

    }

    /*
     * constructor initialize all the fields when creating a Main object
     */
    public Main(PApplet processing) {
        this.processing = processing;
        this.backgroundImage = processing.loadImage("images/background.png");
        guiObjects = new ArrayList<DormGUI>();
        guiObjects.add(new SaveButton(650, 24, processing));
        guiObjects.add(new LoadButton(750, 24, processing));
        guiObjects.add(new ClearButton(550, 24, processing));
        guiObjects.add(new CreateFurnitureButton("bed", 50, 24, processing));
        guiObjects.add(new CreateFurnitureButton("sofa", 150, 24, processing));
        guiObjects.add(new CreateFurnitureButton("desk", 350, 24, processing));
        guiObjects.add(new CreateFurnitureButton("dresser", 250, 24, processing));
        guiObjects.add(new CreateFurnitureButton("sink", 450, 24, processing));
    }

    /*
     * This method is automatically called and will not stop until we stop the program In this
     * method we update the background, furniture objects and four button objects
     */
    public void update() {
        // loop through the arrayList and call every element's update method
        processing.background(100, 150, 250);
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);
        for (int i = 0; i < guiObjects.size(); i++) {
            guiObjects.get(i).update();
        }
    }

    public void mouseDown() throws FileNotFoundException {
        // extract furniture array from the arrayList
        Furniture[] furniture = extractFurnitureFromGUIObjects();

        for (int i = 0; i < guiObjects.size(); i++) {
            if (guiObjects.get(i).getClass() != Furniture.class) {
                guiObjects.get(i).mouseDown(furniture);
            }
        }

        for (int i = furniture.length - 1; i >= 0; i--) {
            if (furniture[i] != (null) && furniture[i].isMouseOver()) {
                furniture[i].mouseDown(furniture);
                break; // break the loop so that only one furniture will be moved at one time
            }
        }
        // update the arraylist after making changes to the furniture array
        replaceFurnitureInGUIObjects(furniture);
    }

    public void mouseUp() {
        // loop through the furniture array
        // set the isDragging field of each furniture to false
        for (int i = 0; i < guiObjects.size(); i++) {
            guiObjects.get(i).mouseUp();
        }
    }

    /*
     * used to delete and rotate furniture
     */
    public void keyPressed() {
        /*
         * loop through the array, when mouse is over a specific furniture pressing "D" will set the
         * reference to null (delete the furniture) pressing "R" will call the rotate method (rotate
         * the furniture)
         */
        // extract furniture array from the arrayList
        Furniture[] furniture = extractFurnitureFromGUIObjects();
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null && furniture[i].isMouseOver()) {
                if (processing.key == 'd' || processing.key == 'D') {
                    furniture[i] = null;
                } else if (processing.key == 'r' || processing.key == 'R') {
                    furniture[i].rotate();
                }
            }
        }
        // update the arraylist after making changes to the furniture array
        replaceFurnitureInGUIObjects(furniture);
    }



}
