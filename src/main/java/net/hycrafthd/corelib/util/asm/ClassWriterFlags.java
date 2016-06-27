package net.hycrafthd.corelib.util.asm;

/**
 * Classwriter flags
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public enum ClassWriterFlags {

	COMPUTE_MAXS(1), COMPUTE_FRAMES(2);

	/**
	 * Flag id
	 */
	int flagid;

	/**
	 * Constructor
	 * 
	 * @param flagid
	 */
	private ClassWriterFlags(int flagid) {
		this.flagid = flagid;
	}

	/**
	 * Getter for flagid
	 * 
	 * @return flagid
	 */
	public int getFlagid() {
		return flagid;
	}

}
