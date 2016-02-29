package cs355.controller;


//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;

import Util.ColorGenerator;
import cs355.scene.HouseModel;
import cs355.scene.Line3D;
import cs355.scene.Point3D;
import cs355.scene.WireFrame;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 *
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController 
{
	  
	//This is a model of a house.
	//It has a single method that returns an iterator full of Line3Ds.
	//A "Line3D" is a wrapper class around two Point2Ds.
	//It should all be fairly intuitive if you look at those classes.
	//If not, I apologize.
	private WireFrame model = new HouseModel();
	private ControllerState state;

  	//This method is called to "resize" the viewport to match the screen.
  	//When you first start, have it be in perspective mode.
  	@Override
  	public void resizeGL() 
  	{
  		// initialize controller state
  		this.state = new ControllerState(new Point3D(-0.75, -5.0, -20.0), 0.0, 700.0f, 600.0f);

  		// set up projection matrix
  		glMatrixMode (GL_PROJECTION);
	  	glLoadIdentity ();
	    gluPerspective(45.0f, this.state.getWidth()/this.state.getHeight(), 0.01f, 1000.0f);
	    glViewport(0, 0, (int)this.state.getWidth(), (int)this.state.getHeight()); 
	  	
	    // set up model view matrix
  		glMatrixMode(GL_MODELVIEW);
  		glLoadIdentity();
  		glRotatef((float)this.state.getCamRot(), 0.0f, 1.0f, 0.0f);
  		glTranslatef((float)this.state.getCamPos().x, (float)this.state.getCamPos().y, (float)this.state.getCamPos().z);
  	}
	
  	@Override
	public void update() 
	{
	    
	}
	
	//This is called every frame, and should be responsible for keyboard updates.
	//An example keyboard event is captured below.
	//The "Keyboard" static class should contain everything you need to finish
	// this up.s
	@Override
	public void updateKeyboard() 
	{
	    if(Keyboard.isKeyDown(Keyboard.KEY_A)){
	    	// Move Left    	
	    	this.state.getCamPos().z += 0.5 * Math.sin(Math.toRadians(this.state.getCamRot()));
	    	this.state.getCamPos().x += 0.5 * Math.cos(Math.toRadians(this.state.getCamRot()));
	        System.out.println("You are pressing A!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
	    	// Move Right
	    	this.state.getCamPos().z -= 0.5 * Math.sin(Math.toRadians(this.state.getCamRot()));
	    	this.state.getCamPos().x -= 0.5 * Math.cos(Math.toRadians(this.state.getCamRot()));
	    	System.out.println("You are pressing D!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_W)){
	    	// Move Forward
	    	this.state.getCamPos().z += 0.5 * Math.sin(Math.toRadians(this.state.getCamRot() + 90));
	    	this.state.getCamPos().x += 0.5 * Math.cos(Math.toRadians(this.state.getCamRot() + 90));
	    	this.state.getCamPos().z += 0.5 * Math.sin(Math.toRadians(0));
	    	System.out.println("You are pressing W!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
	    	// Move Backward
	    	this.state.getCamPos().z -= 0.5 * Math.sin(Math.toRadians(this.state.getCamRot() + 90));
	    	this.state.getCamPos().x -= 0.5 * Math.cos(Math.toRadians(this.state.getCamRot() + 90));
	    	this.state.getCamPos().z -= 0.5 * Math.sin(Math.toRadians(0));
	    	System.out.println("You are pressing S!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
	    	// Turn Left
	    	this.state.setCamRot(-0.5);
	    	System.out.println("You are pressing Q!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_E)){
	    	// Turn Right
	    	this.state.setCamRot(0.5);
	    	System.out.println("You are pressing E!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_R)){
	    	// Move Up
	    	this.state.setCamPosY(-0.5);
	    	System.out.println("You are pressing R!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_F)){
	    	// Move Down
	    	this.state.setCamPosY(0.5);
	    	System.out.println("You are pressing F!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_H)){
	    	// Return to the original home position and orientation
	    	this.state = new ControllerState(new Point3D(-0.75, -5.0, -20.0), 0.0, 700.0f, 600.0f);
	    	System.out.println("You are pressing H!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_O)){
	    	// Switch to orthoraphic projection
	    	glMatrixMode (GL_PROJECTION);
		  	glLoadIdentity ();
		  	glOrtho(-7, 7, -7, 7, -10, 80);
		    glViewport(0, 0, (int)this.state.getWidth(), (int)this.state.getHeight()); 
		    
	    	System.out.println("You are pressing O!");
	    }
	    else if(Keyboard.isKeyDown(Keyboard.KEY_P)){
	    	glMatrixMode (GL_PROJECTION);
		  	glLoadIdentity ();
		    gluPerspective(45.0f, this.state.getWidth()/this.state.getHeight(), 0.01f, 1000.0f);
		    glViewport(0, 0, (int)this.state.getWidth(), (int)this.state.getHeight()); 
	    	System.out.println("You are pressing P!");
	    }

	}
	
	//This method is the one that actually draws to the screen.
	@Override
	public void render() 
	{
  		
	    //This clears the screen.
	    glClear(GL_COLOR_BUFFER_BIT);

	    // left street
	    for(int i = 0; i <= 100; i += 20){
	    	glMatrixMode(GL_MODELVIEW);
	  		glLoadIdentity();
		    glRotatef((float)this.state.getCamRot(), 0.0f, 1.0f, 0.0f);
	  		glTranslatef((float)this.state.getCamPos().x + i, (float)this.state.getCamPos().y, (float)this.state.getCamPos().z);
	  		
		    Iterator<Line3D> linesHouse2 = this.model.getLines();
			   
		    Color color = ColorGenerator.generateColor(i);
		    glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		    glBegin(GL_LINES);
		    while(linesHouse2.hasNext()){
		    	Line3D line = linesHouse2.next();
		    	glVertex3d(line.start.x, line.start.y, line.start.z);
		    	glVertex3d(line.end.x, line.end.y, line.end.z);
		    }
		    glEnd();
	    }
	    
	    // right street
	    for(int i = 0; i <= 100; i += 20){
	    	glMatrixMode(GL_MODELVIEW);
	  		glLoadIdentity();

		    glRotatef((float)this.state.getCamRot(), 0.0f, 1.0f, 0.0f);
	  		glTranslatef((float)this.state.getCamPos().x + i, (float)this.state.getCamPos().y, (float)this.state.getCamPos().z + 30);
	  		glRotatef( 180.0f, 0.0f, 1.0f, 0.0f);
	  		
	  		
		    Iterator<Line3D> linesHouse2 = this.model.getLines();
			   
		    Color color = ColorGenerator.generateColor(i);
		    glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		    glBegin(GL_LINES);
		    while(linesHouse2.hasNext()){
		    	Line3D line = linesHouse2.next();
		    	glVertex3d(line.start.x, line.start.y, line.start.z);
		    	glVertex3d(line.end.x, line.end.y, line.end.z);
		    }
		    glEnd();
	    }
	}
    
}
