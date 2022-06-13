
package greenfoot;

public class Color extends com.badlogic.gdx.graphics.Color {
	
	public static int r,g,b;
	public static float alpha=1;

	public final static Color WHITE = new Color(255, 255, 255);

	public final static Color LIGHT_GRAY = new Color(192, 192, 192);

	public final static Color GRAY = new Color(128, 128, 128);

	public final static Color DARK_GRAY = new Color(64, 64, 64);

	public final static Color BLACK = new Color(0, 0, 0);

	public final static Color RED = new Color(255, 0, 0);

	public final static Color PINK = new Color(255, 175, 175);

	public final static Color ORANGE = new Color(255, 200, 0);

	public final static Color YELLOW = new Color(255, 255, 0);

	public final static Color GREEN = new Color(0, 255, 0);

	public final static Color MAGENTA = new Color(255, 0, 255);

	public final static Color CYAN = new Color(0, 255, 255);

	public final static Color BLUE = new Color(0, 0, 255);

	
	
	
	public Color(int r, int g, int b) {
		super(r, g, b,1);
		this.r=r;
		this.g=g;
		this.b=b;
		
	}

	public Color(int r, int g, int b, float alpha) {			//alpha intre 0 si 1
		super(r, g, b, alpha);
		this.r=r;
		this.g=g;
		this.b=b;
		this.alpha=alpha;
	}

	public Color brighter() {
		return null;
	}

	public Color darker() {
		return null;
	}


	public int getRed() {
		return (int) super.r;
	}

	public int getGreen() {
		return (int) super.g;
	}

	public int getBlue() {
		return (int) super.b;
	}

	public int getAlpha() {
		return (int) super.a;
	}

	
	public int hashCode() {
		return super.hashCode();
	}

}
