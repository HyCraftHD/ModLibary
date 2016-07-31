package net.hycrafthd.corelib.util.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * Asm visitor helper
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ClassVisitorHelper {

	/**
	 * Modified bytes
	 */
	private byte[] bytes;

	/**
	 * Constructor
	 * 
	 * @param bytes
	 *            Default bytes
	 * @param name
	 *            Name
	 * @param creator
	 *            Classvisitorcreator
	 */
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

	/**
	 * Getter for bytes
	 * 
	 * @return bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

}
