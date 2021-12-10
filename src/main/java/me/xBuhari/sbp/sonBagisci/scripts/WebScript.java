package me.xBuhari.sbp.sonBagisci.scripts;

public interface WebScript {

    WebScriptType getWSType();
    Long getLastUpdate();

    String getUsername();
    Integer getMoney();

    String getAddURL();

    void update(String doc);

    enum WebScriptType {
        LEADEROS
    }
}
