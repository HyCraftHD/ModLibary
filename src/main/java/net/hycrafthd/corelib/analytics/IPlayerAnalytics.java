package net.hycrafthd.corelib.analytics;

public interface IPlayerAnalytics {
	
	void addStatsToAnalytics(Analytics analytics);

	boolean isAnalyticsEnabled();
}