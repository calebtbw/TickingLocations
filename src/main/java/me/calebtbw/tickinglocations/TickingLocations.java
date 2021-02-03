package me.calebtbw.tickinglocations;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TickingLocations extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("TickingLocations v1.0 has been enabled!");

        Bukkit.getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        getLogger().info("TickingLocations v1.0 has been disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + e.getPlayer().getName() + " has joined the server! ");
        getLogger().info(e.getPlayer().getUniqueId() + " joined! ");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Bukkit.getServer().broadcastMessage(ChatColor.RED + e.getPlayer().getName() + " has left the server!");
        getLogger().info(e.getPlayer().getUniqueId() + " left! ");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e instanceof Player) {
            Location from = e.getFrom();
            Location to = e.getTo();

            getLogger().info(String.format("From %.2f,%.2f,%.2f to %.2f,%.2f,%.2f", from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ()));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        Material mat = block.getType();
        Location loc = e.getPlayer().getLocation();

        getLogger().info(player.getName() + " placed a " + mat + " at " + loc);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();

        if ((block.getType() == Material.CHEST) || (block.getType() == Material.TRAPPED_CHEST) || (block.getType() == Material.CHEST_MINECART)) {
            Location loc = block.getLocation();
            Player player = e.getPlayer();

            getLogger().info(player.getName() + " broke a chest at " + loc);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tickclear")) {
            Player player = (Player) sender;
            if (player.isOp()) {
                player.getWorld().getEntities().clear();

                player.sendMessage("Cleared all Entities!");
                getLogger().info(player.getName() + " has cleared all entities!");

            }
        } return false;
    }
}
