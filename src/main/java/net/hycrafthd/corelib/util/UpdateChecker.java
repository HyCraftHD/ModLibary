package net.hycrafthd.corelib.util;

import java.io.InputStream;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;

import net.hycrafthd.corelib.CoreLib;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.versioning.ComparableVersion;

public class UpdateChecker {
	
	private static ConcurrentHashMap<String, URL> checkUpdates = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<String, CheckResult> updateMods = new ConcurrentHashMap<>();
	
	public static void add(String modid, String url) {
		try {
			add(modid, new URL(url));
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void add(String modid, URL url) {
		checkUpdates.put(modid, url);
		CoreLib.getLogger().info("Added Updatecheck for " + modid + "(" + url.toString() + ")");
	}
	
	// INTERNALLY USE ONLY
	public static void startUpdatechecking() {
		new Thread("Version Check") {
			
			@Override
			public void run() {
				
				for (Entry<String, URL> entry : checkUpdates.entrySet()) {
					this.process(entry.getKey(), entry.getValue());
				}
			}
			
			private void process(String modid, URL url) {
				Status status = Status.PENDING;
				try {
					ModContainer mod = null;
					for (ModContainer container : Loader.instance().getActiveModList()) {
						if (container.getModId().equalsIgnoreCase(modid)) {
							mod = container;
						}
					}
					
					if (mod == null) {
						throw new NullPointerException("Mod " + modid + " not loaded");
					}
					
					CoreLib.getLogger().info(String.format("[%s] Starting version check at %s", mod.getModId(), url.toString()));
					ComparableVersion target = null;
					
					InputStream con = url.openStream();
					String data = new String(ByteStreams.toByteArray(con));
					con.close();
					
					CoreLib.getLogger().info(String.format("[%s] Received data!", mod.getModId()));
					
					@SuppressWarnings("unchecked")
					Map<String, Object> json = new Gson().fromJson(data, Map.class);
					@SuppressWarnings("unchecked")
					Map<String, String> promos = (Map<String, String>) json.get("promos");
					String display_url = (String) json.get("homepage");
					
					String rec = promos.get(MinecraftForge.MC_VERSION + "-recommended");
					String lat = promos.get(MinecraftForge.MC_VERSION + "-latest");
					ComparableVersion current = new ComparableVersion(mod.getVersion());
					
					if (rec != null) {
						ComparableVersion recommended = new ComparableVersion(rec);
						int diff = recommended.compareTo(current);
						
						if (diff == 0)
							status = Status.UP_TO_DATE;
						else if (diff < 0) {
							status = Status.AHEAD;
							if (lat != null) {
								ComparableVersion latest = new ComparableVersion(lat);
								if (current.compareTo(latest) < 0) {
									status = Status.OUTDATED;
									target = latest;
								}
							}
						} else {
							status = Status.OUTDATED;
							target = recommended;
						}
					} else if (lat != null) {
						ComparableVersion latest = new ComparableVersion(lat);
						if (current.compareTo(latest) < 0) {
							status = Status.BETA_OUTDATED;
							target = latest;
						} else
							status = Status.BETA;
					} else
						status = Status.BETA;
					
					CoreLib.getLogger().info(String.format("[%s] Found status: %s Target: %s", mod.getModId(), status, target));
					
					Map<ComparableVersion, String> changes = new LinkedHashMap<ComparableVersion, String>();
					@SuppressWarnings("unchecked")
					Map<String, String> tmp = (Map<String, String>) json.get(MinecraftForge.MC_VERSION);
					if (tmp != null) {
						List<ComparableVersion> ordered = new ArrayList<ComparableVersion>();
						for (String key : tmp.keySet()) {
							ComparableVersion ver = new ComparableVersion(key);
							if (ver.compareTo(current) > 0 && (target == null || ver.compareTo(target) < 1)) {
								ordered.add(ver);
							}
						}
						Collections.sort(ordered);
						
						for (ComparableVersion ver : ordered) {
							changes.put(ver, tmp.get(ver.toString()));
						}
					}
					if (mod instanceof InjectedModContainer)
						mod = ((InjectedModContainer) mod).wrappedContainer;
					updateMods.put(modid, new CheckResult(status, mod.getName(), target, changes, display_url));
				} catch (Throwable th) {
					updateMods.put(modid, new CheckResult(status, null, null, null, null));
					CoreLib.getLogger().error("Failed to process update information for mod: " + modid, th);
				}
			}
		}.start();
	}
	
	public static class CheckResult {
		
		public final Status status;
		public final String name;
		public final ComparableVersion target;
		public final Map<ComparableVersion, String> changes;
		public final String url;
		
		private CheckResult(Status status, String name, ComparableVersion target, Map<ComparableVersion, String> changes, String url) {
			this.status = status;
			this.name = name;
			this.target = target;
			this.changes = changes == null ? null : Collections.unmodifiableMap(changes);
			this.url = url;
		}
	}
	
}
