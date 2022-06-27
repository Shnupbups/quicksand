package com.shnupbups.quicksand.util;

public interface ZombieEntityInterface {
	boolean isConvertingInQuicksand();

	boolean canConvertInQuicksand();

	void setTicksUntilQuicksandConversion(int ticksUntilQuicksandConversion);

	void convertInQuicksand();

	boolean isSubmergedInQuicksand();

	void updateSubmergedInQuicksand();
}
