package dev.kilovice.opsecurity.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import org.bukkit.plugin.Plugin;

public class OPUpdater {

	private static Plugin plugin;
	
    final int resourceId = 6665;
    
    private static String latestVersion = "";
    
    private static boolean updateAvailable = false;
    
    public OPUpdater(Plugin plugin)
    {
    this.plugin = plugin;	
    }
    private String getSpigotVersion() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource="+resourceId)
                            .getBytes("UTF-8"));
            String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            if (version.length() <= 7) {
                return version;
            }
        } catch (Exception ex) {
            OPDebug.log(this.getClass(), "Failed to check for a update on spigot.");
        }
        return null;
    }
    private boolean checkHigher(String currentVersion, String newVersion) {
        String current = toReadable(currentVersion);
        String newVers = toReadable(newVersion);
        return current.compareTo(newVers) < 0;
    }

    public boolean checkForUpdate() {
        if (getLatestVersion() != "")
            return true;
        String version = getSpigotVersion();
        if (version != null) {
            if (checkHigher(plugin.getDescription().getVersion(), version)) {
                latestVersion = version;
                updateAvailable = true;
                return true;
            }
        }
        return false;
    }

    public static boolean updateAvailable() {
        return updateAvailable;
    }

    public static String getLatestVersion() {
        return latestVersion;
    }

    private String toReadable(String version) {
        String[] split = Pattern.compile(".", Pattern.LITERAL).split(
                version.replace("v", ""));
        version = "";
        for (String s : split)
            version += String.format("%4s", s);
        return version;
    }
}
