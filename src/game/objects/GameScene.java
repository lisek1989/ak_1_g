package game.objects;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL.GL_LINEAR;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static javax.media.opengl.GL2GL3.GL_QUADS;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

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
	private String skyTexFileName = "textures/envoirment/sky/sky.jpg";
	private String skyTexFileType = ".jpg";
	private float skyTexTop, skyTexBottom, skyTexLeft, skyTexRight;
	
	
	//backgrounds[];
	//backgroundProps;
	private Texture groundTex;
	private String groundTexFileName = "textures/envoirment/ground/grass.jpg";
	private String groundTexFileType = ".jpg";
	private float groundTexTop, groundTexBottom, groundTexLeft, groundTexRight;
	
	//props[];
	
	//foreground[];
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
	      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers

	      // ------ Render a Cube with texture ------
	      gl.glLoadIdentity();  // reset the model-view matrix
	      gl.glTranslatef(0.0f, 0.5f, -6.0f); // translate into the screen

	      // Enables this texture's target in the current GL context's state.
	      skyTex.enable(gl);  // same as gl.glEnable(texture.getTarget());
	      // gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
	      // Binds this texture to the current GL context.
	      skyTex.bind(gl);  // same as gl.glBindTexture(texture.getTarget(), texture.getTextureObject());
	 
	      gl.glBegin(GL_QUADS);

	      gl.glTexCoord2f(skyTexLeft, skyTexBottom);
	      gl.glVertex3f(-5.0f, -2.5f, 0.0f); // bottom-left of the texture and quad
	      gl.glTexCoord2f(skyTexRight, skyTexBottom);
	      gl.glVertex3f(5.0f, -2.5f, 0.0f);  // bottom-right of the texture and quad
	      gl.glTexCoord2f(skyTexRight, skyTexTop);
	      gl.glVertex3f(5.0f, 2.5f, 0.0f);   // top-right of the texture and quad
	      gl.glTexCoord2f(skyTexLeft, skyTexTop);
	      gl.glVertex3f(-5.0f, 2.5f, 0.0f);  // top-left of the texture and quad

	      gl.glEnd();
	      skyTex.disable(gl);
	      
	      gl.glLoadIdentity();  // reset the model-view matrix
	      gl.glTranslatef(0.0f, -1.0f, -5.0f); // translate into the screen
	      gl.glRotatef(85.0f, -1.0f, 0.0f, 0.0f); // rotate the object
	      
	   // Enables this texture's target in the current GL context's state.
	      groundTex.enable(gl);  // same as gl.glEnable(texture.getTarget());
	      // gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
	      // Binds this texture to the current GL context.
	      groundTex.bind(gl);  // same as gl.glBindTexture(texture.getTarget(), texture.getTextureObject());
	 
	      gl.glBegin(GL_QUADS);

	      gl.glTexCoord2f(groundTexLeft, groundTexBottom);
	      gl.glVertex3f(-2.0f, -1.0f, 0.0f); // bottom-left of the texture and quad
	      gl.glTexCoord2f(groundTexRight, groundTexBottom);
	      gl.glVertex3f(2.0f, -1.0f, 0.0f);  // bottom-right of the texture and quad
	      gl.glTexCoord2f(groundTexRight, groundTexTop);
	      gl.glVertex3f(2.0f, 1.0f, 0.0f);   // top-right of the texture and quad
	      gl.glTexCoord2f(groundTexLeft, groundTexTop);
	      gl.glVertex3f(-2.0f, 1.0f, 0.0f);  // top-left of the texture and quad
	      
	      gl.glEnd();
	      
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
/*
	      //glu = new GLU();                         // get GL Utilities
	      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
	      gl.glClearDepth(1.0f);      // set clear depth value to farthest
	      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
	      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
	      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
	      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting

*/

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
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
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
		
	}
}
