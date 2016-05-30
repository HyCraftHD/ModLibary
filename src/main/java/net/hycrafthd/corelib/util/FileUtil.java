package net.hycrafthd.corelib.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

public class FileUtil {

	public static final FileUtils FILE_UTILS = new FileUtils();
	public static final FilenameUtils FILENAME_UTILS = new FilenameUtils();
	public static final FileSystemUtils FILE_SYSTEM_UTILS = new FileSystemUtils();
	public static final FileFilterUtils FILE_FILTER_UTILS = new FileFilterUtils();

	public static File fileFromURI(URI uri) {
		return org.apache.logging.log4j.core.helpers.FileUtils.fileFromURI(uri);
	}

	public static boolean isFile(URL url) {
		return org.apache.logging.log4j.core.helpers.FileUtils.isFile(url);
	}

	public static void mkdir(final File dir, final boolean createDirectoryIfNotExisting) throws IOException {
		org.apache.logging.log4j.core.helpers.FileUtils.mkdir(dir, createDirectoryIfNotExisting);
	}

	public static File getMainDirectionary() {
		return new File(System.getProperty("user.dir"));
	}

}
