package me.xBuhari.sbp.sonBagisci;

import me.xBuhari.sbp.Main;
import me.xBuhari.sbp.sonBagisci.scripts.LeaderOS;
import me.xBuhari.sbp.sonBagisci.scripts.WebScript;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class SBManager {
    public WebScript webScript;

    private String webSiteURL;

    public SBManager() {
        String webSc = Main.getPlugin().getConfig().getString("son-bagisci.web-script");
        WebScript.WebScriptType webScriptType;
        try {
            webScriptType = WebScript.WebScriptType.valueOf(webSc);
            if (webScriptType == null) {
                Main.getPlugin().getLogger().severe("WebScript yanlis girildi.");
                return;
            }
        }
        catch (Exception e) {
            Main.getPlugin().getLogger().severe("WebScript yanlis girildi.");
            return;
        }
        this.webSiteURL = Main.getPlugin().getConfig().getString("son-bagisci.website-url", "https://www.demo.com");
        if (this.webSiteURL.equalsIgnoreCase("https://www.demo.com")) {
            Main.getPlugin().getLogger().severe("WebSite URL yanlis girildi.");
            return;
        }
        if (webScriptType == WebScript.WebScriptType.LEADEROS) {
            this.webScript = new LeaderOS();
        }
        setupTimer(this.webScript.getAddURL());
    }

    private Integer getInterval() {
        return Main.getPlugin().getConfig().getInt("son-bagisci.renew-minute", 5);
    }

    private void setupTimer(String addURL) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getPlugin(), () -> {
            try {
                webScript.update(webResponse(addURL));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 20L *3, this.getInterval()*20*60);
    }

    private String webResponse(String ekURL) throws IOException {
        URL uri = new URL(this.webSiteURL + ekURL);
        URLConnection ec = uri.openConnection();
        ec.addRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                ec.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();
        return a.toString();
    }
}
