package net.hycrafthd.corelib.util.asm;

import org.objectweb.asm.ClassVisitor;

public abstract class ClassVisitorCreator {

	private int flags;

	public ClassVisitorCreator(ClassWriterFlags flags) {
		this.flags = flags.flagid;
	}

	public int getFlags() {
		return flags;
	}

	public abstract ClassVisitor createClassVisitor(String name, ClassVisitor cw);

}
