//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer 2000
// Files:           DormDesigner.jar; background.png; bed.png
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
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Utility.startApplication();
	}
	public static void setup(Data data) {
		// store bedimage, bedpositions, backgroundimage in data object
		data.bedImage=data.processing.loadImage("images/bed.png");
		data.bedPositions =new float [6][];
		data.processing.background(100,150,250);	
		data.backgroundImage = data.processing.loadImage("images/background.png");
		// draw the background image to the center of the screen
		data.processing.image(data.backgroundImage, data.processing.width/2, data.processing.height/2);
		// initialize drag bed index to -1
		data.dragBedIndex=-1; 
	}
	
	public static void update(Data data) {
		
		data.processing.background(100,150,250);	
		data.processing.image(data.backgroundImage, data.processing.width/2, data.processing.height/2);
	
		for (int i=0;i<data.bedPositions.length;i++) {
			if (data.bedPositions[i]!=(null)) {
				data.processing.image(data.bedImage,data.bedPositions[i][0] , data.bedPositions[i][1]);
			}
		}
		
		for (int i=0;i<data.bedPositions.length;i++) {
			if(data.dragBedIndex==i) {
				data.bedPositions[i]=new float[]  {data.processing.mouseX, data.processing.mouseY};
		}}
		
	}
	public static void mouseDown(Data data) {
		float w=data.bedImage.width;
		float h=data.bedImage.height;
		for (int j=0;j<data.bedPositions.length;j++) {
			if(data.bedPositions[j]!=null) {
			if (Math.abs(data.bedPositions[j][0]-data.processing.mouseX) < w/2 && Math.abs(data.bedPositions[j][1]-data.processing.mouseY) < h/2 ) {
				data.dragBedIndex=j;
			}}
		}
	}
	public static void mouseUp(Data data) {
		data.dragBedIndex=-1;
	}
	public static void keyPressed(Data data) {
		if(data.processing.key=='b' ||data.processing.key=='B' ) {
			int key = 0;
			for (int i=data.bedPositions.length-1;i>=0;i--) {
				if (data.bedPositions[i]==(null)) {
					key=i;
				}
			}
			data.bedPositions[key]= new float[] { data.processing.width/2, data.processing.height/2};
		}
	}
	

}
