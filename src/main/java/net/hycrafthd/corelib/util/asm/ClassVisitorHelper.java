package net.hycrafthd.corelib.util.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ClassVisitorHelper {

	private byte[] bytes;

	public ClassVisitorHelper(byte[] bytes, String name, ClassVisitorCreator creator) {
		ClassReader cr = new ClassReader(bytes);
		ClassWriter cw = new ClassWriter(cr, creator.getFlags());
		ClassVisitor mod = creator.createClassVisitor(name, cw);

		try {
			cr.accept(mod, 0);
			this.bytes = cw.toByteArray();
		} catch (Exception ex) {
			this.bytes = bytes;
		}
	}

	public byte[] getBytes() {
		return bytes;
	}

}
