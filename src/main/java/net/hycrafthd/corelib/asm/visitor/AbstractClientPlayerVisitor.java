package net.hycrafthd.corelib.asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import com.google.common.base.Throwables;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.event.CapeUpdatedEvent;
import net.hycrafthd.corelib.util.asm.ASMLogType;
import net.hycrafthd.corelib.util.asm.ASMUtil;
import net.hycrafthd.corelib.util.asm.MappedType;
import net.hycrafthd.corelib.util.asm.MethodMatcher;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;

/**
 * Visit {@link AbstractClientPlayer} class to change skin and cape location
 * TODO more comments
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class AbstractClientPlayerVisitor extends ClassVisitor {

	// TODO Work
	private MethodMatcher methodmatcher;

	public AbstractClientPlayerVisitor(String name, ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		ASMUtil.asmLogger(ASMLogType.TRYING, name);
		Type type = Type.getMethodType(MappedType.from(ResourceLocation.class).getType());
		methodmatcher = new MethodMatcher(name, type.getDescriptor(), "getLocationCape", /* func_110303_q */"getLocationCape");
		ASMUtil.asmLogger(ASMLogType.MATCHING, methodmatcher.toString());
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		return methodmatcher.match(name, desc) ? new AbstractClientPlayerVisitorMethodGetLocationCape(parent) : parent;
	}

	// public static void injectMethod(AbstractClientPlayer player,
	// NetworkPlayerInfo network) {
	// System.out.println("inj");
	// // CapeUpdatedEvent event = new CapeUpdatedEvent(player, network != null
	// // ? network.getLocationCape() : null);
	// // CoreLib.getInstance().getEventBus().post(event);
	// // return event.getRes();
	// }

	public static void injectMethod(NetworkPlayerInfo network) {
		CapeUpdatedEvent event = new CapeUpdatedEvent(null, network != null ? network.getLocationCape() : null);
		CoreLib.getInstance().getEventBus().post(event);
		// return event.getRes();
	}

	private static class AbstractClientPlayerVisitorMethodGetLocationCape extends MethodVisitor {

		private final Method method;

		public AbstractClientPlayerVisitorMethodGetLocationCape(MethodVisitor mv) {
			super(Opcodes.ASM5, mv);

			try {
				method = Method.getMethod(AbstractClientPlayerVisitor.class.getMethod("injectMethod", NetworkPlayerInfo.class));
			} catch (Exception e) {
				throw Throwables.propagate(e);
			}

			ASMUtil.asmLogger(ASMLogType.INJECTING, AbstractClientPlayerVisitor.class + "." + method.toString());
		}

		@Override
		public void visitInsn(int opcode) {
			if (opcode == Opcodes.ARETURN) {
				Type type = Type.getType(AbstractClientPlayerVisitor.class);

				visitVarInsn(Opcodes.ALOAD, 1);
				visitMethodInsn(Opcodes.INVOKESTATIC, type.getInternalName(), method.getName(), method.getDescriptor(), false);
				ASMUtil.asmLogger(ASMLogType.INJECTED);
			}
			super.visitInsn(opcode);
		}
	}
}
