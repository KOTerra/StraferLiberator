package greenfoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
/*
 * Port al clasei din Greenfoot
 * ofera informatii despre cursor pe desktop
 */
public class MouseInfo {

	public int getButton() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			return 1;
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
			return 3;
		}
		return -1;
	}

	public float getX() {
		return Gdx.input.getX();
	}

	public float getY() {
		return Gdx.input.getY();
	}

}
