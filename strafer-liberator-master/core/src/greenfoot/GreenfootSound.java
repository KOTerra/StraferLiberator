package greenfoot;

import java.util.logging.FileHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class GreenfootSound  {

	Sound sound;
	float volume=100;
	long id;
	boolean playing=false;
	public GreenfootSound(java.lang.String name) {
		sound=Gdx.audio.newSound(new FileHandle("assets/sounds/"+name));
	}

	int getVolume() {
		return (int)volume;
	}

	boolean isPlaying() {
		return playing;
	}

	void pause() {
		sound.pause();
		playing=false;
	}

	void play() {
		playing=true;
		id=sound.play();	
	}
	void setLooping() {
		sound.setLooping(id, isPlaying());
	}

	void playLoop() {					
		
		sound.loop(volume/100f);
	}

	void setVolume(int volume) {
		sound.setVolume(id, volume/100f);
		this.volume=volume;
	}

	void stop() {
		sound.stop();
		playing=false;
	}

	public void resume() {
		sound.resume();
		
	}

	
}
