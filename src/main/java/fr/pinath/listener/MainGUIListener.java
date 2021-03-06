package fr.pinath.listener;

import fr.pinath.gui.CategoryGUI;
import fr.pinath.gui.GUI;
import fr.pinath.gui.MainGUI;
import fr.pinath.skull.Category;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * Listener of {@link MainGUI}
 * Load the different {@link CategoryGUI}
 *
 * @author Nathan Strobbe
 */
public class MainGUIListener extends GUIListener {
    private Map<Category, CategoryGUI> categoriesGUI;

    public MainGUIListener(JavaPlugin plugin, GUI gui) {
        super(plugin, gui);
        categoriesGUI = new EnumMap<>(Category.class);
        collectCategories();
    }

    private void collectCategories() {
        Arrays.stream(Category.values())
                .forEach(category -> categoriesGUI.put(category, new CategoryGUI(plugin, category, player, this.gui)));
    }

    @EventHandler
    @Override
    public void onSkullClick(InventoryClickEvent e) {
        if (e.getWhoClicked().equals(player)
                && e.getInventory().equals(inventory)) {
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem != null) {
                if (clickedItem.getType().equals(Material.BARRIER)) {
                    player.closeInventory();
                } else {
                    Category category = getCategory(clickedItem);
                    if (clickedItem.getItemMeta() != null
                            && clickedItem.getItemMeta().getDisplayName() != null
                            && getSkullNames().contains(clickedItem.getItemMeta().getDisplayName())
                            && category != null) {
                        categoriesGUI.get(category).showGUI();
                    }
                }
            }
        }
    }

    private Category getCategory(ItemStack currentItem) {
        for (Category category : Category.values()) {
            if (currentItem.getItemMeta() != null
                    && category.getTitle().equals(currentItem.getItemMeta().getDisplayName())) {
                return category;
            }
        }
        return null;
    }
}
