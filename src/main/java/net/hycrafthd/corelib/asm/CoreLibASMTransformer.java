package net.hycrafthd.corelib.asm;

import org.objectweb.asm.ClassVisitor;

import net.hycrafthd.corelib.asm.visitor.*;
import net.hycrafthd.corelib.util.asm.*;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * ASMTransformer for bytecode manipulation
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class CoreLibASMTransformer implements IClassTransformer {

	/**
	 * Manipulate javabytecode
	 */
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (transformedName.equals("net.minecraft.client.renderer.entity.RenderPlayer")) {
			return new ClassVisitorHelper(bytes, name, new ClassVisitorCreator(ClassWriterFlags.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createClassVisitor(String name, ClassVisitor cw) {
					return new RenderPlayerVisitor(name, cw);
				}

			}).getBytes();
		}
		if (transformedName.equals("net.minecraft.client.renderer.EntityRenderer")) {
			return new ClassVisitorHelper(bytes, name, new ClassVisitorCreator(ClassWriterFlags.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createClassVisitor(String name, ClassVisitor cw) {
					return new EntityRenderVisitor(name, cw);
				}

			}).getBytes();
		}
		/*
		 * 
		 * if (transformedName.equals("net.minecraft.client.Minecraft")) { return new ClassVisitorHelper(bytes, name, new ClassVisitorCreator(ClassWriterFlags.COMPUTE_FRAMES) {
		 * 
		 * @Override public ClassVisitor createClassVisitor(String name, ClassVisitor cw) { return new DisplayVisitor(name, cw); }
		 * 
		 * }).getBytes(); } / // if // (transformedName.equals("net.minecraft.client.entity.AbstractClientPlayer")) // { // return new ClassVisitorHelper(bytes, name, new //
		 * ClassVisitorCreator(ClassWriterFlags.COMPUTE_FRAMES) { // @Override // public ClassVisitor createClassVisitor(String name, ClassVisitor cw) // { // return new
		 * AbstractClientPlayerVisitor(name, cw); // } // // }).getBytes(); // }
		 * 
		 */
		return bytes;
	}

}
