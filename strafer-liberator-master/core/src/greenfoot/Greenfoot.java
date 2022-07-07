package greenfoot;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.game.straferliberator.StraferLiberator;

public class Greenfoot extends com.badlogic.gdx.Gdx {
	/*Port al clasei Greenfoot
	 * ofera utilitati
	 * */
	
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

	public static boolean mouseMoved(Object object) {
		Actor actor = (Actor) object;
		if(actor==null) {
			return false;
		}
		float iw = actor.getImage().getWidth();
		float ih = actor.getImage().getHeight();

		Rectangle r = new Rectangle(actor.getX() - iw / 2, actor.getY() - ih / 2, iw, ih);

		float mousex = Gdx.input.getX();
		float mousey = Gdx.input.getY();

		if (r.contains(mousex, mousey)) {
			return true;
		}

		return false;
	}

	public static boolean mouseClicked(Object object) {
		if (Gdx.input.justTouched()) {
			if (object == null) {
				return true;
			}
		}

		if (mouseMoved(object)) {
			if (Gdx.input.justTouched()) {

				return true;
			}
		}

		
		return false;
	}

	public static MouseInfo getMouseInfo() {
		return mouseInfo;
	}

	public static void playSound(String string) {
		GreenfootSound sound=StraferLiberator.assetManager.get(string,GreenfootSound.class);
		sound.play();
		
	}

}
