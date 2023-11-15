package com.shnupbups.quicksand.util;

public interface ZombieEntityInterface {
	boolean quicksand_isConvertingInQuicksand();

	boolean quicksand_canConvertInQuicksand();

	void quicksand_setTicksUntilQuicksandConversion(int ticksUntilQuicksandConversion);

	void quicksand_convertInQuicksand();

	boolean quicksand_isSubmergedInQuicksand();

	void quicksand_updateSubmergedInQuicksand();
}
