package fr.pinath.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the Graphical User Interface of the plugin
 *
 * @author Nathan Strobbe
 */
public abstract class GUI {
    protected JavaPlugin plugin;
    private Player player;
    Inventory inventory;

    GUI(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    /**
     * Initialise the content of the GUI with items
     */
    protected abstract void initializeContent();

    /**
     * Display the GUI
     */
    public void showGUI() {
        player.openInventory(inventory);
    }

    ItemStack getClosingItem() {
        ItemStack barrier = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = barrier.getItemMeta();
        meta.setDisplayName("§l§4Close");
        barrier.setItemMeta(meta);
        return barrier;
    }
}
