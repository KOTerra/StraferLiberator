package greenfoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.port.GifImage;

public class TestActor extends Actor {
	
	GifImage gif=new GifImage("player/player_m_D.gif");
	
	public TestActor() {
	 setImage(new GreenfootImage("R.png"));
	}
	public void act() {
		this.setImage( gif.getCurrentImage());
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
		this.setLocation(getX()+3, 200);
		
		getWorld().removeObject(this);
		
		}
	}
}
