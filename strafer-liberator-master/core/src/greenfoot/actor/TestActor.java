package greenfoot.actor;

import com.port.actor.Item;
import com.port.display.GifImage;

import greenfoot.Greenfoot;

public class TestActor extends Actor {

	GifImage gif = new GifImage("player/player_m_D.gif");
	int speed = 5;

	public TestActor() {
		this.setImage(gif.getCurrentImage());
	}

	public void act() {

		miscaTest();
		if (Greenfoot.mouseClicked(this)) {
			System.out.println("click");
		}

	}

	private void miscaTest() {
		this.setImage(gif.getCurrentImage());
		moveTest();
		// this.setLocation(100,100);
	}

	private void moveTest() {

		if (Greenfoot.isKeyDown("W")) {
			// merge in nord

			// gif = "W";
			Item.itemGif = "W";
			setLocation(getX(), (getY() - speed));

		}

		if (Greenfoot.isKeyDown("S")) {
			// merge in sud

			// gif = "S";
			Item.itemGif = "S";
			setLocation(getX(), (getY() + speed));

		}
		if (Greenfoot.isKeyDown("D")) {
			// merg in est

			// gif = "D";
			Item.itemGif = "D";
			setLocation(getX() + speed, getY());

		}

		if (Greenfoot.isKeyDown("A")) {
			// merg in vest

			// gif = "A";
			Item.itemGif = "A";
			setLocation(getX() - speed, getY());

		}
	}

}
