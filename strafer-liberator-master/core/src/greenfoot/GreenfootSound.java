package greenfoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class GreenfootSound {

	Sound sound;
	float volume = 100;
	long id;
	boolean playing = false;

	public GreenfootSound(java.lang.String name) {
		//sound = Gdx.audio.newSound(new FileHandle("assets/sounds/" + name));
		sound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/" + name));
	}

	int getVolume() {
		return (int) volume;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void pause() {
		sound.pause();
		playing = false;
	}

	public void play() {
		playing = true;
		id = sound.play();
	}

	public void setLooping() {
		sound.setLooping(id, isPlaying());
	}

	public void playLoop() {

		sound.loop(volume / 100f);
	}

	public void setVolume(int volume) {
		sound.setVolume(id, volume / 100f);
		this.volume = volume;
	}

	public void stop() {
		sound.stop();
		playing = false;
	}

	public void resume() {
		sound.resume();

	}

}
