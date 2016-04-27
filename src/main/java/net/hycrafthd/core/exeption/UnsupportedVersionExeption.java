package net.hycrafthd.core.exeption;

public class UnsupportedVersionExeption extends RuntimeException {

	public UnsupportedVersionExeption() {
		super();
	}

	public UnsupportedVersionExeption(String s) {
		super(s);
	}

	public UnsupportedVersionExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedVersionExeption(Throwable cause) {
		super(cause);
	}
}
