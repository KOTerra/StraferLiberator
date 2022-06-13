package greenfoot;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.game.straferliberator.StraferLiberator;

public class TestWorld extends World {

	BitmapFont font = new BitmapFont(new FileHandle("assets/fonts/consolas.fnt"));

	MouseInfo mi = new MouseInfo();
	TestActor ta = new TestActor();
	Actor ta2 = new Actor();
	Actor ta3 = new Actor();
	
	public TestWorld() {

		super(1024, 576, 1, false);
		this.addObject(ta, 300, 100);
		taa2();
		poza();
		this.setBackground(new GreenfootImage("Capture.png"));
	}

	void taa2() {
		GreenfootImage timg=new GreenfootImage(1000,200);
		
		timg.setFont(new Font("consolas",24));
		
		timg.drawString("da",0,0);
		
		ta2.setImage(timg);
		this.addObject(ta2, 512,245);
		this.addObject(new com.port.Text("asta e gen \n alt text", 24),512,576/2);
	}
	
	
	void poza() {
		GreenfootImage p=new GreenfootImage(300,100);
		p.setColor(new Color(0,125,120));
		p.drawRect(0, 0, 100, 37);
		p.setColor(new Color(125,0,120));
		p.fillRect(3,2,72,33);
		
		GreenfootImage pt=new GreenfootImage(300,300);
		pt.setColor(Color.WHITE);
		pt.setFont(new Font("edo",24));
		pt.drawString("aici e text ba",100,100);
		
		
		Actor a=new Actor();
		Actor b=new Actor();
		a.setImage(p);
		b.setImage(pt);
		this.addObject(a, 500, 200);
		this.addObject(b,500,200);
	}

	public void act() {
		super.act();

		if (Gdx.input.isKeyJustPressed(Keys.T)) { /// T de la test te ai prins
			// ((TestActor) (this.getObjects(TestActor.class).get(0))).assertion();

			 this.removeObject(ta2);
			// this.removeObject((Actor) getObjectsAt(500, 100,TestActor.class).get(0));

		}
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			ta2.getImage().scale(200, 100);
		}
		if (ta.isTouching(TestActor.class)) {
			// System.out.println("atins");
		}

	}

}
