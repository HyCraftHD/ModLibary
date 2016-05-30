package net.hycrafthd.corelib.util.asm;

public enum ClassWriterFlags {

	COMPUTE_MAXS(1), COMPUTE_FRAMES(2);

	int flagid;

	ClassWriterFlags(int flagid) {
		this.flagid = flagid;
	}

	public int getFlagid() {
		return flagid;
	}

}
