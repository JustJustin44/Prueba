package dev.mruniverse.oragenstaffchat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public final class OrangeStaffChat extends JavaPlugin implements Listener {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        File cnf = new File(getDataFolder(), "configuraciones.yml");

        if (!cnf.getParentFile().exists()) {
            boolean pepe = cnf.getParentFile().mkdirs();
            if (pepe) {
                getLogger().info("Se ha creado la carpeta");
            }
        }

        if (!cnf.exists()) {
            try (InputStream in = getResource("configuraciones.yml")) {
                Files.copy(in, cnf.toPath());
            } catch (Exception ignored) {}
        }

        config = YamlConfiguration.loadConfiguration(cnf);

        getLogger().info(config.getString("holamundo"));

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String message = config.getString("mensajito", "&cNO SE ENCUENTRA");

        message = message.replace("<jugador>", event.getPlayer().getName());

        event.getPlayer().sendMessage(
                ChatColor.translateAlternateColorCodes('&', message)
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
