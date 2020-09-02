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

public class Main {
	
	//fields
		private Furniture[] furniture;
		private PApplet processing;
		private PImage backgroundImage;
		private CreateBedButton bedbutton;
		private CreateSofaButton sofabutton;
		 
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Utility.startApplication();
		
	}
	
	//constructor
	public Main(PApplet processing) {
		this.processing=processing;
		this.furniture= new Furniture[6];
		this.backgroundImage=processing.loadImage("images/background.png");	
		bedbutton = new CreateBedButton(50,24, processing);
		sofabutton = new CreateSofaButton(150,24,processing);
		
	}

	
	public void update() {
		
		processing.background(100,150,250);	
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		
		for (int i=0;i<furniture.length;i++) {
			if (furniture[i]!=(null)) {
				furniture[i].update();
				
			}
		}
		
		bedbutton.update();
		sofabutton.update();
		
		
	}
	public void mouseDown() {
		
		if(bedbutton.mouseDown()!=null) {
			//furniture[dragBedIndex].mouseDown();
				int key = -1;
				for (int i=furniture.length-1;i>=0;i--) {
					if (furniture[i]==(null)) {
						key=i;
					}
				}
				//dragBedIndex=key;
				furniture[key]= new Furniture("bed",processing);
			}
		else if(sofabutton.mouseDown()!=null) {
				int key = -1;
				for (int i=furniture.length-1;i>=0;i--) {
					if (furniture[i]==(null)) {
						key=i;
					}
				}
				furniture[key]= new Furniture("sofa",processing);
			}
		
		int index=0;
		for (int i=furniture.length-1;i>=0;i--) {
			if (furniture[i]!=(null) && furniture[i].isMouseOver()) {
				//furniture[i].mouseDown();
				index=i;
				break;
				}
			} 
		furniture[index].mouseDown();
	}
	
	
	public void mouseUp() {
		for (int i=0;i<furniture.length;i++) {
			if (furniture[i]!=(null)) {
				furniture[i].mouseUp();
			}
		}
	}
	
	public void keyPressed() {
		
		for (int i=0;i<furniture.length;i++) {
			if (furniture[i]!=null && furniture[i].isMouseOver()) {
				 if (processing.key=='d' ||processing.key=='D' ) {
					 furniture[i]=null;
				 }
				 else if (processing.key=='r' ||processing.key=='R' ) {
					 furniture[i].rotate();

				 }
			}
		}
		
	}
	

	
}
