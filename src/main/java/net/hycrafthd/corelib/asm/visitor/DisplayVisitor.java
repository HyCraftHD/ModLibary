package net.hycrafthd.corelib.asm.visitor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.Display;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.corelib.util.asm.ASMLogType;
import net.hycrafthd.corelib.util.asm.ASMUtil;
import net.hycrafthd.corelib.util.asm.MethodMatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class DisplayVisitor extends ClassVisitor{

	private MethodMatcher methodmatcher;

	public DisplayVisitor(String name,ClassVisitor vis) {
		super(Opcodes.ASM5,vis);
		ASMUtil.asmLogger(ASMLogType.TRYING, name);
		Type type = Type.getMethodType(Type.VOID_TYPE);
		methodmatcher = new MethodMatcher(name, type.getDescriptor(), "setWindowIcon", "setWindowIcon");
		ASMUtil.asmLogger(ASMLogType.MATCHING, methodmatcher.toString());

	}
	
	public static void icon(){
		try {
			InputStream str = Minecraft.getMinecraft().mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("stone"));
			Display.setIcon(new ByteBuffer[] {LWJGLUtils.readImageToBuffer(str), LWJGLUtils.readImageToBuffer(str)});
		} catch (IOException e) {
			ASMUtil.asmLogger(ASMLogType.TRYING, "NULL");
		}
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		if(methodmatcher.match(name, desc)){
		MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		if(parent != null){
		return new IconMethod(parent);
		}else{
			return new IconMethod();
		}
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}
	
	public class IconMethod extends MethodVisitor{

		private Method method;
		
		public IconMethod() {
			super(Opcodes.ASM5);
			init();
		}

		public IconMethod(MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
			init();
		}
		
		private void init() {
			try {
				method = Method.getMethod(DisplayVisitor.class.getMethod("icon"));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
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
