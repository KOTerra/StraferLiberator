package greenfoot;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class TestWorld extends World {

	MouseInfo mi = new MouseInfo();
	TestActor ta = new TestActor();
	TestActor ta2 = new TestActor();

	public TestWorld() {
		super(1024, 576, 1, false);
		this.addObject(ta, 300, 100);
		this.addObject(ta2, 500, 100);  
		this.setBackground(new GreenfootImage("Capture.png"));
	}

	public void act() {
		super.act();
		if (Gdx.input.isKeyJustPressed(Keys.T)) { /// T de la test te ai prins
			//((TestActor) (this.getObjects(TestActor.class).get(0))).assertion();
			
			//this.removeObjects(this.getObjects(TestActor.class));
			this.removeObject((Actor) getObjectsAt(500, 100,TestActor.class).get(0));
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			System.out.println(this.numberOfActors());
		}
		if(ta.isTouching(TestActor.class)) {
			//System.out.println("atins");
		}
		
		
	}

}
