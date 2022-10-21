package de.enwaffel.projectp.api.guardiencraft;

public interface GCDiscordStats {
    JobStats getJobStats();
    XPStats getXPStats();

    interface JobStats {
        String getJob();
    }

    interface XPStats {
        int getLevel();
        double getXP();
        double getTotalXP();
        double getXPMultiplier();
    }

}
