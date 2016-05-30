package net.hycrafthd.corelib.asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import com.google.common.base.Throwables;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.event.CameraTransformEvent;
import net.hycrafthd.corelib.util.asm.ASMLogType;
import net.hycrafthd.corelib.util.asm.ASMUtil;
import net.hycrafthd.corelib.util.asm.MethodMatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityRenderVisitor extends ClassVisitor {

	private MethodMatcher methodmatcher;

	public EntityRenderVisitor(String name, ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		ASMUtil.asmLogger(ASMLogType.TRYING, name);
		Type type = Type.getMethodType(Type.VOID_TYPE, Type.FLOAT_TYPE, Type.INT_TYPE);
		methodmatcher = new MethodMatcher(name, type.getDescriptor(), "setupCameraTransform", "func_78479_a");
		ASMUtil.asmLogger(ASMLogType.MATCHING, methodmatcher.toString());
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		return methodmatcher.match(name, desc) ? new EntityRenderVisitorMethodSetupCameraTransform(parent) : parent;
	}

	public static void injectMethod(float partialTickTime, int flag) {
		Minecraft mc = Minecraft.getMinecraft();
		int rendererUpdateCount = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, mc.entityRenderer, "field_78529_t", "rendererUpdateCount");
		CoreLib.getInstance().getEventBus().post(new CameraTransformEvent(mc.thePlayer, rendererUpdateCount, partialTickTime, flag));
	}

	private static class EntityRenderVisitorMethodSetupCameraTransform extends MethodVisitor {

		private final Method method;

		public EntityRenderVisitorMethodSetupCameraTransform(MethodVisitor mv) {
			super(Opcodes.ASM5, mv);

			try {
				method = Method.getMethod(EntityRenderVisitor.class.getMethod("injectMethod", float.class, int.class));
			} catch (Exception e) {
				throw Throwables.propagate(e);
			}

			ASMUtil.asmLogger(ASMLogType.INJECTING, EntityRenderVisitor.class + "." + method.toString());
		}

		@Override
		public void visitInsn(int opcode) {
			if (opcode == Opcodes.RETURN) {
				Type type = Type.getType(EntityRenderVisitor.class);
				visitVarInsn(Opcodes.FLOAD, 1);
				visitVarInsn(Opcodes.ILOAD, 2);
				visitMethodInsn(Opcodes.INVOKESTATIC, type.getInternalName(), method.getName(), method.getDescriptor(), false);
				ASMUtil.asmLogger(ASMLogType.INJECTED);
			}

			super.visitInsn(opcode);
		}
	}
}
