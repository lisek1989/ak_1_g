package game.objects;

import java.awt.Dimension;
import java.io.IOException;

import static javax.media.opengl.GL.*;  // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

public class GameCharacter extends GameObject implements GLEventListener {
	float posX, posY, posZ;
	int statStrenght, statDexterity, statIntelligence;
	//relations;
	//equipment;
	//group;
	
	// Texture
	private Texture texture;
	private String textureFileName = "textures/characters/test1.png";
	private String textureFileType = ".png";
	
	// Texture image flips vertically. Shall use TextureCoords class to retrieve the
	// top, bottom, left and right coordinates.
	private float textureTop, textureBottom, textureLeft, textureRight;
	
	public GameCharacter (String _firstName, String _lastName, float _posX, float _posY, float _posZ) {
		this.name = _firstName + " " + _lastName;
		this.posX = _posX;
		this.posY = _posY;
		this.posZ = _posZ;
	}
	
	public void move (Dimension where) {}
	public void attack (Character attacked) {}

	public boolean addItem (GameItem added) { return true;}
	public boolean removeItem (GameItem removed) { return true;}
	public boolean useItem (GameItem used) {return true;}

	@Override
	public void display(GLAutoDrawable drawable) {
		System.out.println("GC_Disp_BEGIN");
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
//		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers  
		
		// ------ Render a Cube with texture ------

	    gl.glLoadIdentity();  // reset the model-view matrix
		gl.glTranslatef(0.0f, 0.0f, -5.0f); // translate into the screen
		
		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL_ALPHA_TEST);
		gl.glAlphaFunc(GL_GREATER, 0.0f);
	      
		// Enables this texture's target in the current GL context's state.
		texture.enable(gl);  // same as gl.glEnable(texture.getTarget());
		//gl.glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
		// Binds this texture to the current GL context.
		texture.bind(gl);  // same as gl.glBindTexture(texture.getTarget(), texture.getTextureObject());
	      
		gl.glBegin(GL_QUADS);

		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-0.5f, -1.0f, 0.0f); // bottom-left of the texture and quad
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(0.5f, -1.0f, 0.0f);  // bottom-right of the texture and quad
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(0.5f, 1.0f, 0.0f);   // top-right of the texture and quad
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-0.5f, 1.0f, 0.0f);  // top-left of the texture and quad
		gl.glEnd();
		gl.glDisable(GL_ALPHA_TEST);
		
		System.out.println("GC_Disp_END");
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		System.out.println("GC_Del_BEGIN");
		
		System.out.println("GC_Del_END");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.println("GC_Init_BEGIN");
		GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
	    //glu = new GLU();                         // get GL Utilities
	    
	    // Load texture from image
	    try {
	    	// Create a OpenGL Texture object from (URL, mipmap, file suffix)
	    	// Use URL so that can read from JAR and disk file.
	    	texture = TextureIO.newTexture(
	    			getClass().getClassLoader().getResource(textureFileName), // relative to project root 
	    			false, textureFileType);

	    	// Use linear filter for texture if image is larger than the original texture
	    	gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    	// Use linear filter for texture if image is smaller than the original texture
	    	gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

	    	// Texture image flips vertically. Shall use TextureCoords class to retrieve
	    	// the top, bottom, left and right coordinates, instead of using 0.0f and 1.0f.
	    	TextureCoords textureCoords = texture.getImageTexCoords();
	    	textureTop = textureCoords.top();
	    	textureBottom = textureCoords.bottom();
	    	textureLeft = textureCoords.left();
	    	textureRight = textureCoords.right();
	    } catch (GLException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    System.out.println("GC_Init_END");
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		System.out.println("GC_Resh_BEGIN");
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		GLU glu = new GLU();

		if (height == 0) height = 1;   // prevent divide by zero
		double aspect = (double)width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
		gl.glLoadIdentity();             // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
		System.out.println("GC_Resh_END");
	}
}
