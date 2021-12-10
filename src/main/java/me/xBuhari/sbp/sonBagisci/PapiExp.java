package me.xBuhari.sbp.sonBagisci;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.xBuhari.sbp.Main;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PapiExp extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "sbp";
    }

    @Override
    public @NotNull String getAuthor() {
        return "xBuhari";
    }

    public @NotNull String getVersion() {
        return Main.getPlugin().getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("user")) {
            return Main.getPlugin().getSbManager().webScript.getUsername();
        }
        if (identifier.equalsIgnoreCase("price")) {
            return Main.getPlugin().getSbManager().webScript.getMoney() + "";
        }
        if (identifier.equalsIgnoreCase("lastupdate")) {
            return Main.getPlugin().getSbManager().webScript.getLastUpdate() + "";
        }
        return "";
    }
}
