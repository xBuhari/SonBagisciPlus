package me.xBuhari.sbp.sonBagisci.scripts;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.xBuhari.sbp.Main;

public class LeaderOS implements WebScript {
    private String username;
    private Integer money;
    private Long lastUpdate;
    private final WebScriptType webScriptType;

    public LeaderOS() {
        this.webScriptType = WebScriptType.LEADEROS;
        Main.getPlugin().getLogger().info("WebScript " + this.getWSType() + " olarak ayarlandi.");
    }

    @Override
    public WebScriptType getWSType() {
        return webScriptType;
    }

    @Override
    public Long getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Integer getMoney() {
        return money;
    }

    @Override
    public String getAddURL() {
        return "/apps/api/credit-history.php?limit=1";
    }

    @Override
    public void update(String doc) {
        this.lastUpdate = System.currentTimeMillis();
        JsonObject j = new JsonParser().parse(doc).getAsJsonArray().get(0).getAsJsonObject();
        this.username = j.get("username").getAsString();
        this.money = j.get("amount").getAsInt();
    }
}
