package net.hycrafthd.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ProgressMonitorInputStream;

public class FileUtil {

	public static void downloadFile(String from, File to) {
		try {
			URL url = new URL(from);
			URLConnection uc = url.openConnection();
			InputStream is = (InputStream) uc.getInputStream();

			FileOutputStream out = new FileOutputStream(to);

			byte[] buffer = new byte[1024];

			for (int n; (n = is.read(buffer)) != -1; out.write(buffer, 0, n)) {
			}

			is.close();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
