package net.hycrafthd.corelib.util;

import java.util.Arrays;
import java.util.List;

public class VersionCompare {

	private final List<String> versions;

	public VersionCompare(String versionrange) {
		try {
			versions = Arrays.asList(versionrange.replace("[", "").replace("]", "").split(","));
		} catch (Exception ex) {
			throw new IllegalArgumentException();
		}
	}

	public boolean containsVersion(String version) {
		return versions.contains(version);
	}

}
