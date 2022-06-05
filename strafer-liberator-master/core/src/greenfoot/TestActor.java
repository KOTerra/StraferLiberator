package greenfoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class TestActor extends Actor {
	public TestActor() {
	 setImage(new GreenfootImage("R.png"));
	}
	public void act() {
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
		this.setLocation(getX()+3, 200);
		//getWorld().removeObject(this);
		
		}
	}
}
