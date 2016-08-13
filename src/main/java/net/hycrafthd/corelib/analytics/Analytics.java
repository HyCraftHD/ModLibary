package net.hycrafthd.corelib.analytics;

import java.lang.management.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.hycrafthd.corelib.CoreLib;
import net.minecraft.util.HttpUtil;
import net.minecraftforge.common.ForgeVersion;

public class Analytics {
	
	private final HashMap<Object, Object> getstats = Maps.newHashMap();
	private final HashMap<Object, Object> stats = Maps.newHashMap();
	
	private final String uniqueID = UUID.randomUUID().toString();
	
	private final URL serverUrl;
	
	private final IPlayerAnalytics playeranalytics;
	
	private final Timer threadTrigger = new Timer("Analytics Timer", true);
	private final Object syncLock = new Object();
	
	private final long minecraftStartTimeMilis;
	
	private boolean isRunning;
	private int selfCounter;
	
	private SimpleDateFormat format;
	
	public Analytics(IPlayerAnalytics playeranalytics) {
		try {
			this.serverUrl = new URL("https://www.hycrafthd.net/mods/corelib/analytics.php");
		} catch (MalformedURLException malformedurlexception) {
			throw new IllegalArgumentException();
		}
		this.playeranalytics = playeranalytics;
		this.minecraftStartTimeMilis = System.currentTimeMillis();
		format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
	}
	
	public void startAnalytics() {
		if (!this.isRunning) {
			this.isRunning = true;
			this.threadTrigger.schedule(new TimerTask() {
				
				public void run() {
					addAnalytics();
					if (Analytics.this.playeranalytics.isAnalyticsEnabled()) {
						HashMap<Object, Object> hashmap;
						
						synchronized (Analytics.this.syncLock) {
							hashmap = Maps.newHashMap(Analytics.this.stats);
							
							if (Analytics.this.selfCounter == 0) {
								hashmap.putAll(Analytics.this.getstats);
							}
							
							hashmap.put("count", Integer.valueOf(Analytics.access(Analytics.this)));
							hashmap.put("token", Analytics.this.uniqueID);
						}
						CoreLib.getLogger().info("Sending analytics....");
						HttpUtil.postMap(Analytics.this.serverUrl, hashmap, false);
					}
				}
			}, 0L, 600000L);
		}
	}
	
	private void addAnalytics() {
		this.addJvmArgsToAnalytics();
		this.addMemoryStatsToSnooper();
		this.addStat("token", this.uniqueID);
		this.addStat("time_start", format.format(new Date(minecraftStartTimeMilis)));
		this.addStat("time_now", format.format(new Date()));
		this.addStat("os_name", System.getProperty("os.name"));
		this.addStat("os_version", System.getProperty("os.version"));
		this.addStat("os_architecture", System.getProperty("os.arch"));
		this.addStat("java_version", System.getProperty("java.version"));
		this.addStat("version", ForgeVersion.getVersion());
		this.playeranalytics.addStatsToAnalytics(this);
	}
	
	private void addJvmArgsToAnalytics() {
		RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
		List<String> list = runtimemxbean.getInputArguments();
		int i = 0;
		Iterator<String> iterator = list.iterator();
		
		while (iterator.hasNext()) {
			String s = (String) iterator.next();
			
			if (s.startsWith("-X")) {
				this.addStat("jvm_arg[" + i++ + "]", s);
			}
		}
		
		this.addStat("jvm_args", Integer.valueOf(i));
	}
	
	public void addMemoryStatsToSnooper() {
		this.addStat("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
		this.addStat("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
		this.addStat("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
		this.addStat("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
	}
	
	public void addStat(String string, Object obj) {
		synchronized (this.syncLock) {
			this.stats.put(string, obj);
		}
	}
	
	public Map<Object, Object> getCurrentStats() {
		LinkedHashMap<Object, Object> linkedhashmap = Maps.newLinkedHashMap();
		
		synchronized (this.syncLock) {
			Iterator<?> iterator = this.getstats.entrySet().iterator();
			Entry<?, ?> entry;
			
			while (iterator.hasNext()) {
				entry = (Entry<?, ?>) iterator.next();
				linkedhashmap.put(entry.getKey(), entry.getValue().toString());
			}
			
			iterator = this.stats.entrySet().iterator();
			
			while (iterator.hasNext()) {
				entry = (Entry<?, ?>) iterator.next();
				linkedhashmap.put(entry.getKey(), entry.getValue().toString());
			}
			
			return linkedhashmap;
		}
	}
	
	public boolean isAnalyticsRunning() {
		return this.isRunning;
	}
	
	public void stopAnalytics() {
		this.threadTrigger.cancel();
	}
	
	public String getUniqueID() {
		return this.uniqueID;
	}
	
	public long getMinecraftStartTimeMillis() {
		return this.minecraftStartTimeMilis;
	}
	
	static int access(Analytics analytics) {
		return analytics.selfCounter++;
	}
}
