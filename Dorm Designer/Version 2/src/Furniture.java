//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer 3000
// Files:           DormDesigner.jar; background.png; bed.png; sofa.png
// Course:          CS300, Spring, 2018
//
// Author:          Qinglang Ye
// Email:           qye25@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Honghe Pan
// Partner Email:   hpan37@wisc.edu
// Lecturer's Name: Alexi Brooks
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Furniture {
	//fields
	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String type; 
	
	// initializes the fields of a new bed object positioned in the center of the display
	public Furniture(String s, PApplet processing ) { 
		this.processing=processing;
		this.type=s;
		image=processing.loadImage("images/" + type + ".png");
		position= new float[] {processing.width/2, processing.height/2};
		processing.image(image, position[0], position[1]);
		isDragging=false;
		rotations=0;
	}
	
	// draws this bed at its current position
	public void update() { 
		
		processing.image(image, position[0], position[1], rotations*PApplet.PI/2);//processing.image(image, position[0], position[1]);
		if(isDragging) {
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
		}
		
		
	}
	// used to start dragging the bed, when the mouse is over this bed when it is pressed
	public void mouseDown() { 
		if (isMouseOver()) {
			isDragging=true;
		}
	}
	
	
	// used to indicate that the bed is no longer being dragged
	public void mouseUp() {
		isDragging=false;
	}
	
	// helper method to determine whether the mouse is currently over this bed
	public boolean isMouseOver() { 
		float w=image.width;
		float h=image.height;
		
		if(position!=null) {
			if ( rotations%2==0 && Math.abs(position[0]-processing.mouseX) <= w/2 && Math.abs(position[1]-processing.mouseY) <= h/2 ) {
				return true;
			}
			else if ( rotations%2 ==1 && Math.abs(position[0]-processing.mouseX) <= h/2 && Math.abs(position[1]-processing.mouseY) <= w/2 ) {
				return true;
			} 
		
}
		return false;
	}
	
	public void rotate() {
		
			rotations++;
		
			
	}
	
}
