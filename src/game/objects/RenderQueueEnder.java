package game.objects;

import static javax.media.opengl.GL.*;  // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class RenderQueueEnder implements GLEventListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		System.out.println("RQE_Disp_BEGIN");
		System.out.println("RQE_Disp_BEGIN");
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		System.out.println("RQE_Del_BEGIN");
		
		System.out.println("RQE_Del_END");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.println("RQE_Init_BEGIN");
		
		System.out.println("RQE_Init_END");
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3,
			int arg4) {
		System.out.println("RQE_Resh_BEGIN");
		
		System.out.println("RQE_Resh_END");
	}

}
