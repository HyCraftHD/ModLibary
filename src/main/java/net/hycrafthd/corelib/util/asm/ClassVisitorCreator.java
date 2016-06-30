package net.hycrafthd.corelib.util.asm;

import org.objectweb.asm.ClassVisitor;

/**
 * Create a new Classvisitor
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public abstract class ClassVisitorCreator {

	/**
	 * Flags
	 */
	private int flags;

	/**
	 * Constructor
	 * 
	 * @param flags
	 */
	public ClassVisitorCreator(ClassWriterFlags flags) {
		this.flags = flags.flagid;
	}

	/**
	 * Getter for flags
	 * 
	 * @return flags
	 */
	public int getFlags() {
		return flags;
	}

	/**
	 * Create Classvisitor
	 * 
	 * @param name
	 *            Name
	 * @param cw
	 *            Classvisitor
	 * @return Created classvisitor
	 */
	public abstract ClassVisitor createClassVisitor(String name, ClassVisitor cw);

}
