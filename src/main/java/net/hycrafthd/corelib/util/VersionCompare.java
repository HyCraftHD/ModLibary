package net.hycrafthd.corelib.util;

import java.util.Arrays;
import java.util.List;

/**
 * Compare minecraft versions
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class VersionCompare {

	/**
	 * Versions
	 */
	private final List<String> versions;

	/**
	 * Constructor
	 * 
	 * @param versionrange
	 */
	public VersionCompare(String versionrange) {
		try {
			versions = Arrays.asList(versionrange.replace("[", "").replace("]", "").split(","));
		} catch (Exception ex) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Contains version
	 * 
	 * @param version
	 *            Version
	 * @return true or false
	 */
	public boolean containsVersion(String version) {
		return versions.contains(version);
	}

}
