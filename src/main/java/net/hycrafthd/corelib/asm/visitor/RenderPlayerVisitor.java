package net.hycrafthd.corelib.asm.visitor;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.Method;

import com.google.common.base.Throwables;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.event.PlayerRenderBodyEvent;
import net.hycrafthd.corelib.util.asm.*;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;

/**
 * Visit {@link RenderPlayer} to change player rendering TODO more comments
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class RenderPlayerVisitor extends ClassVisitor {

	private MethodMatcher methodmatcher;

	public RenderPlayerVisitor(String name, ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		ASMUtil.asmLogger(ASMLogType.TRYING, name);
		Type type = Type.getMethodType(Type.VOID_TYPE, MappedType.from(AbstractClientPlayer.class).getType(), Type.FLOAT_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE);
		methodmatcher = new MethodMatcher(name, type.getDescriptor(), "func_180595_a", "func_180595_a");
		ASMUtil.asmLogger(ASMLogType.MATCHING, methodmatcher.toString());
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		return methodmatcher.match(name, desc) ? new RenderPlayerVisitorMethodRotateCorpse(parent) : parent;
	}

	public static void injectMethod(AbstractClientPlayer player, float partialTickTime) {
		CoreLib.getInstance().getEventBus().post(new PlayerRenderBodyEvent(player, partialTickTime));
	}

	private static class RenderPlayerVisitorMethodRotateCorpse extends MethodVisitor {

		private final Method method;

		public RenderPlayerVisitorMethodRotateCorpse(MethodVisitor mv) {
			super(Opcodes.ASM5, mv);

			try {
				method = Method.getMethod(RenderPlayerVisitor.class.getMethod("injectMethod", AbstractClientPlayer.class, float.class));
			} catch (Exception e) {
				throw Throwables.propagate(e);
			}

			ASMUtil.asmLogger(ASMLogType.INJECTING, RenderPlayerVisitor.class + "." + method.toString());
		}

		@Override
		public void visitInsn(int opcode) {
			if (opcode == Opcodes.RETURN) {
				Type type = Type.getType(RenderPlayerVisitor.class);
				visitVarInsn(Opcodes.ALOAD, 1);
				visitVarInsn(Opcodes.FLOAD, 4);
				visitMethodInsn(Opcodes.INVOKESTATIC, type.getInternalName(), method.getName(), method.getDescriptor(), false);
				ASMUtil.asmLogger(ASMLogType.INJECTED);
			}

			super.visitInsn(opcode);
		}
	}
}
