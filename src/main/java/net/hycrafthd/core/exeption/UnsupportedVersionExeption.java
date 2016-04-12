package net.hycrafthd.core.exeption;

public class UnsupportedVersionExeption extends RuntimeException {

	public UnsupportedVersionExeption() {
		super("Only 1.8 / 1.8.8 / 1.8.9 / 1.9 minecraft versions are compatible.");
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
