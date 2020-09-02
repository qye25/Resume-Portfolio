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

public class CreateBedButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	 
	private PApplet processing;
	private float[] position;
	private String label;
	 
	public CreateBedButton(float x, float y, PApplet processing) {
		this.processing=processing;
		label="Create Bed";
		position= new float[]{x,y};
		
	}
	
	public void update() { 
		if (isMouseOver()) {
			processing.fill(100);
		}
		else {
			processing.fill(200);
		}
		processing.rect(position[0]-WIDTH/2, position[1]+HEIGHT/2, position[0]+WIDTH/2, position[1]-HEIGHT/2);
	
		processing.fill(0);
		processing.text(label, position[0], position[1]);
	}
	
	public Furniture mouseDown() { 
		if (isMouseOver()) {
			Furniture bed = new Furniture("bed",processing);
			return bed;
		}
		else
			{return null;}
		
	} // After step 10, this method will instead return Furniture	
	
	public boolean isMouseOver() {
		if(position!=null) {
			if ( Math.abs(position[0]-processing.mouseX) < WIDTH/2 && Math.abs(position[1]-processing.mouseY) < HEIGHT/2 ) {
					return true;
				}
			}
		return false;
	}	
}
