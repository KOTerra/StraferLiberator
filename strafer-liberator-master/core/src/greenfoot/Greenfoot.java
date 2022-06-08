package greenfoot;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Greenfoot extends com.badlogic.gdx.Gdx {

	static MouseInfo mouseInfo = new MouseInfo();

	public static int getRandomNumber(int limit) {
		Random rand = new Random();
		return rand.nextInt(limit);
	}

	public static boolean isKeyDown(java.lang.String keyName) {
		int key = Keys.valueOf(keyName);
		if (Gdx.input.isKeyPressed(key)) {
			return true;
		}
		return false;
	}

	public static boolean mouseClicked(Object object) {
		if (object instanceof greenfoot.Actor) {
			Actor actor = (Actor) object;

			Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
			Vector2 mouseLocalPosition = actor.screenToLocalCoordinates(mouseScreenPosition);

			if (actor.hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
				if (Gdx.input.justTouched()) {
					return true;
				}
			}
		}
		if (Gdx.input.justTouched()) {
			if (object == null) {
				return false;
			}
		}
		return false;
	}

	public static MouseInfo getMouseInfo() {
		return mouseInfo;
	}

}
