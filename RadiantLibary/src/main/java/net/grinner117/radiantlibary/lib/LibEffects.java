package net.grinner117.radiantlibary.lib;

public class LibEffects {
	public static final String LIGHTING = "lighting";
	public static final String HEX = "hex";
	public static final String MAGIC_FIND = "magic_find";
	public static final String BOUNCE = "bounce";
	public static final String GRAVITY = "gravity";
	public static final String SNARE = "snared";
	public static final String GLIDE = "glide";
	public static final String SCRYING = "scrying";
	public static final String FLY = "fly";

	public static final String RECOVERY = "recovery";
	public static final String BLAST = "blasting";
	public static final String FREEZING = "freezing";
	public static final String DEFENCE = "shielding";

	public static String potion(String base) {
		return base + "_potion";
	}
	public static String longPotion(String base) {
		return potion(base) + "_long";
	}
	public static String strongPotion(String base) {
		return potion(base) + "_strong";
	}


}