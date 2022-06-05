package greenfoot;

import com.badlogic.gdx.files.FileHandle;

public class GreenfootImage extends com.badlogic.gdx.graphics.Texture{

	public GreenfootImage(FileHandle file) {super(file);}
	
	public GreenfootImage(String f) {
		super(new FileHandle("assets/images/"+f));
		
		
	}

}
