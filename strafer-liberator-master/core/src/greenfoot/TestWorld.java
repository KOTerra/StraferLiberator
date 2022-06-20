package greenfoot;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.game.straferliberator.StraferLiberator;
import com.port.HealthBarPlayer;
import com.port.Picture;

public class TestWorld extends World {

	BitmapFont font = new BitmapFont(new FileHandle("assets/fonts/consolas.fnt"));

	MouseInfo mi = new MouseInfo();
	com.port.Player player = new com.port.Player();
	Actor ta2 = new Actor();
	Actor ta3 = new Actor();
	GreenfootSound sound;
	public HealthBarPlayer healthBar;
	private boolean addedHealthBar = false;


	public TestWorld() {

		super(1024, 576, 1, false);

		
		taa2();
		//poza();
		new Thread(new Runnable() {

			public void run() {
				sound();
			}
		}).start();

		this.setBackground(new GreenfootImage("Capture.png"));
	}

	void taa2() {
		GreenfootImage timg = new GreenfootImage(1000, 200);

		timg.setFont(new Font("consolas", 24));

		timg.drawString("da", 0, 0);

		ta2.setImage(timg);
		this.addObject(ta2, 512, 245);
		this.addObject(new com.port.Text("asta e gen \n alt text", 24), 512, 576 / 2);
	}

	private void addHealthBar() {

		healthBar = new HealthBarPlayer("",  player.getHp(), player.getHpMax());

		healthBar.setSafeColor(Color.TEAL.lerp(Color.SKY, 0.7f));
		healthBar.setDangerColor(Color.ORANGE);
		healthBar.setBarWidth(181);
		healthBar.setBarHeight(14);
		healthBar.setReferenceText("");
		healthBar.setTextColor(new Color(4, 69, 85, 214));
		Picture barBack = new Picture("UI/hud/healthBar.png");
		addObject(barBack, 148, 40);
		addObject(healthBar, 172, 32);
		this.addObject(player, 300, 100);
	}



	void sound() {
		sound = new GreenfootSound("music/Default.mp3");
		sound.setLooping();
		sound.playLoop();
	}

	public void act() {
		super.act();
		if(!addedHealthBar) {
			addHealthBar();
			addedHealthBar=true;
		}
		System.out.println(Gdx.graphics.getFramesPerSecond());
		if (Gdx.input.isKeyJustPressed(Keys.T)) { /// T de la test te ai prins
			// ((TestActor) (this.getObjects(TestActor.class).get(0))).assertion();

			// this.removeObject(ta2);
			// this.removeObject((Actor) getObjectsAt(500, 100,TestActor.class).get(0));
			// sound.pause();
		}
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			// sound.resume();
		}
		if (player.isTouching(TestActor.class)) {
			// System.out.println("atins");
		}
		

	}

}
