import java.io.FileNotFoundException;

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

public class Main {

    // fields
    private Furniture[] furniture;
    private PApplet processing;
    private PImage backgroundImage;
    private CreateBedButton bedbutton;
    private CreateSofaButton sofabutton;
    private SaveButton savebutton;
    private LoadButton loadbutton;


    public static void main(String[] args) {

        Utility.startApplication();

    }

    /*
     * constructor initialize all the fields when creating a Main object
     */
    public Main(PApplet processing) throws FileNotFoundException {
        this.processing = processing;
        this.furniture = new Furniture[6];
        this.backgroundImage = processing.loadImage("images/background.png");
        bedbutton = new CreateBedButton(50, 24, processing);
        sofabutton = new CreateSofaButton(150, 24, processing);
        savebutton = new SaveButton(650, 24, processing);
        loadbutton = new LoadButton(750, 24, processing);
    }

    /*
     * This method is automatically called and will not stop until we stop the program In this
     * method we update the background, furniture objects and four button objects
     */
    public void update() {

        processing.background(100, 150, 250);
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);

        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != (null)) {
                furniture[i].update();
            }
        }

        bedbutton.update();
        sofabutton.update();
        savebutton.update();
        loadbutton.update();

    }

    public void mouseDown() throws FileNotFoundException {


        // loop through the furniture array, find the first "null" place if there is one
        // then create a new bed object and put it in the array
        if (bedbutton.mouseDown() != null) {
            int key = -1;
            for (int i = furniture.length - 1; i >= 0; i--) {
                if (furniture[i] == (null)) {
                    key = i;
                }
            }

            furniture[key] = new Furniture("bed", processing);
        }

        // similar to the bedbutton.mouseDown() method but instead creating a new sofa object
        else if (sofabutton.mouseDown() != null) {
            int key = -1;
            for (int i = furniture.length - 1; i >= 0; i--) {
                if (furniture[i] == (null)) {
                    key = i;
                }
            }
            furniture[key] = new Furniture("sofa", processing);
        }

        // loop through the furniture array to check if the mouse is over any furniture object
        // if we find a corresponding object, we can move that object
        int index = 0;
        for (int i = furniture.length - 1; i >= 0; i--) {
            if (furniture[i] != (null) && furniture[i].isMouseOver()) {
                // furniture[i].mouseDown();
                index = i;
                break; // break the loop so that only one furniture will be moved at one time
            }
        }
        furniture[index].mouseDown();

        // call the save file or load file method when the button is pressed
        savebutton.mouseDown(furniture);
        loadbutton.mouseDown(furniture);

    }


    public void mouseUp() {
        // loop through the furniture array
        // set the isDragging field of each furniture to false
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != (null)) {
                furniture[i].mouseUp();
            }
        }
    }

    /*
     * used to delete and rotate furniture
     */
    public void keyPressed() {
        // loop through the array, when mouse is over a specific furniture
        // pressing "D" will set the reference to null (delete the furniture)
        // pressing "R" will call the rotate method (rotate the furniture)
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null && furniture[i].isMouseOver()) {
                if (processing.key == 'd' || processing.key == 'D') {
                    furniture[i] = null;
                } else if (processing.key == 'r' || processing.key == 'R') {
                    furniture[i].rotate();
                }
            }
        }
    }
}
