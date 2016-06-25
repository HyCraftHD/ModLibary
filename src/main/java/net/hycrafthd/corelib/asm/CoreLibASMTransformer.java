package net.hycrafthd.corelib.asm;

import org.objectweb.asm.ClassVisitor;

import net.hycrafthd.corelib.asm.visitor.AbstractClientPlayerVisitor;
import net.hycrafthd.corelib.asm.visitor.EntityRenderVisitor;
import net.hycrafthd.corelib.asm.visitor.RenderPlayerVisitor;
import net.hycrafthd.corelib.util.asm.ClassVisitorCreator;
import net.hycrafthd.corelib.util.asm.ClassVisitorHelper;
import net.hycrafthd.corelib.util.asm.ClassWriterFlags;
import net.minecraft.launchwrapper.IClassTransformer;

public class CoreLibASMTransformer implements IClassTransformer {

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
//		if (transformedName.equals("net.minecraft.client.entity.AbstractClientPlayer")) {
//			return new ClassVisitorHelper(bytes, name, new ClassVisitorCreator(ClassWriterFlags.COMPUTE_FRAMES) {
//				@Override
//				public ClassVisitor createClassVisitor(String name, ClassVisitor cw) {
//					return new AbstractClientPlayerVisitor(name, cw);
//				}
//
//			}).getBytes();
//		}
		return bytes;
	}

}
