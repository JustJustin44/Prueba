package dev.mruniverse.prueba;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Prueba extends JavaPlugin {

    @Override
    public void onEnable() {
        File file = new File(getDataFolder(), "config.yml");
        // !
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    getLogger().info("Se ha cargado la config.yml");
                }
            } catch (IOException ignored) {}
        }

        FileConfiguration configuracion = YamlConfiguration.loadConfiguration(file);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
