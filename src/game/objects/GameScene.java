package game.objects;

import static javax.media.opengl.GL.*;  // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants
import static javax.media.opengl.GL2ES1.GL_ALPHA_TEST;

import java.awt.Dimension;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

public class GameScene implements GLEventListener {
	String name;
	String desc;
	Dimension coods;
	
	private Texture skyTex;
	private String skyTexFileName = "textures/envoirment/background/bg.png";
	private String skyTexFileType = ".png";
	private float skyTexTop, skyTexBottom, skyTexLeft, skyTexRight;
	
	
	//backgrounds[];
	//backgroundProps;
	private Texture groundTex;
	private String groundTexFileName = "textures/envoirment/ground/ground1.png";
	private String groundTexFileType = ".png";
	private float groundTexTop, groundTexBottom, groundTexLeft, groundTexRight;
	
	//props[];
	
	//foreground[];
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		System.out.println("GS_Disp_BEGIN");
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		//gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers

		// ------ Render a Cube with texture ------
		gl.glLoadIdentity();  // reset the model-view matrix
		gl.glTranslatef(0.0f, 10.0f, -85.0f); // translate into the screen

		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL_ALPHA_TEST);
		gl.glAlphaFunc(GL_GREATER, 0.0f);
		
		// Enables this texture's target in the current GL context's state.
		skyTex.enable(gl);  // same as gl.glEnable(texture.getTarget());
		gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
		// Binds this texture to the current GL context.
		skyTex.bind(gl);  // same as gl.glBindTexture(texture.getTarget(), texture.getTextureObject());
	 
		gl.glBegin(GL_QUADS);

		gl.glTexCoord2f(skyTexLeft, skyTexBottom);
		gl.glVertex3f(-45.0f, -25.0f, 0.0f); // bottom-left of the texture and quad
		gl.glTexCoord2f(skyTexRight, skyTexBottom);
		gl.glVertex3f(45.0f, -25.0f, 0.0f);  // bottom-right of the texture and quad
		gl.glTexCoord2f(skyTexRight, skyTexTop);
		gl.glVertex3f(45.0f, 25.0f, 0.0f);   // top-right of the texture and quad
		gl.glTexCoord2f(skyTexLeft, skyTexTop);
		gl.glVertex3f(-45.0f, 25.0f, 0.0f);  // top-left of the texture and quad

		gl.glEnd();
	    
		gl.glLoadIdentity();  // reset the model-view matrix
		gl.glTranslatef(0.0f, -15.0f, -60.0f); // translate into the screen
		gl.glRotatef(90.0f, -1.0f, 0.0f, 0.0f); // rotate the object
	      
		// Enables this texture's target in the current GL context's state.
		groundTex.enable(gl);  // same as gl.glEnable(texture.getTarget());
		// gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
		// Binds this texture to the current GL context.
		groundTex.bind(gl);  // same as gl.glBindTexture(texture.getTarget(), texture.getTextureObject());
	 
		gl.glBegin(GL_QUADS);

		gl.glTexCoord2f(groundTexLeft, groundTexBottom);
		gl.glVertex3f(-45.0f, -25.0f, 0.0f); // bottom-left of the texture and quad
		gl.glTexCoord2f(groundTexRight, groundTexBottom);
		gl.glVertex3f(45.0f, -25.0f, 0.0f);  // bottom-right of the texture and quad
		gl.glTexCoord2f(groundTexRight, groundTexTop);
		gl.glVertex3f(45.0f, 25.0f, 0.0f);   // top-right of the texture and quad
		gl.glTexCoord2f(groundTexLeft, groundTexTop);
		gl.glVertex3f(-45.0f, 25.0f, 0.0f);  // top-left of the texture and quad
	      
		gl.glEnd();
		
		System.out.println("GS_Disp_END");
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		System.out.println("GS_Del_BEGIN");
		
		System.out.println("GS_Del_END");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.println("GS_Init_BEGIN");
		GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
		//glu = new GLU();                         // get GL Utilities

		// Load texture from image
		try {
			// Create a OpenGL Texture object from (URL, mipmap, file suffix)
			// Use URL so that can read from JAR and disk file.
			skyTex = TextureIO.newTexture(
				getClass().getClassLoader().getResource(skyTexFileName), // relative to project root 
				false, skyTexFileType);
	         
			groundTex = TextureIO.newTexture(
				getClass().getClassLoader().getResource(groundTexFileName), // relative to project root 
				false, groundTexFileType);

			// Use linear filter for texture if image is larger than the original texture
			gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			// Use linear filter for texture if image is smaller than the original texture
			gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

			// Texture image flips vertically. Shall use TextureCoords class to retrieve
			// the top, bottom, left and right coordinates, instead of using 0.0f and 1.0f.
			TextureCoords textureCoords = skyTex.getImageTexCoords();
			skyTexTop = textureCoords.top();
			skyTexBottom = textureCoords.bottom();
			skyTexLeft = textureCoords.left();
			skyTexRight = textureCoords.right();
	         
			textureCoords = groundTex.getImageTexCoords();
			groundTexTop = textureCoords.top();
			groundTexBottom = textureCoords.bottom();
			groundTexLeft = textureCoords.left();
			groundTexRight = textureCoords.right();
		} catch (GLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("GS_Init_END");
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		System.out.println("GS_Resh_BEGIN");
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
		System.out.println("GS_Resh_END");
	}
}
