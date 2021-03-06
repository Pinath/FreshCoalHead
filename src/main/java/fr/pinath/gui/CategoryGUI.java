package fr.pinath.gui;

import fr.pinath.skull.Category;
import fr.pinath.skull.DataSkull;
import fr.pinath.skull.Skull;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Graphical User Interface of a Category. It contains the different heads of the category
 *
 * @author Nathan Strobbe
 */
public class CategoryGUI extends GUI {
    private Category category;
    private GUI previousGUI;
    private List<PageGUI> pages;
    private PageGUI currentPage;

    public CategoryGUI(JavaPlugin plugin, Category category, Player player, GUI previousGUI) {
        super(plugin, player);
        this.category = category;
        this.previousGUI = previousGUI;
        initializeContent();
    }

    //--region Getter and setter

    protected void setCurrentPage(PageGUI pageGUI) {
        this.currentPage = pageGUI;
    }

    public PageGUI getCurrentPage() {
        return currentPage;
    }

    public List<PageGUI> getPages() {
        return pages;
    }

    public GUI getPreviousGUI() {
        return previousGUI;
    }

    protected Category getCategory() {
        return category;
    }

    //--endregion

    /**
     * Initialize the pages of the category GUI
     */
    @Override
    protected void initializeContent() {
        pages = new ArrayList<>();
        List<Skull> skulls = new DataSkull(category).collectSkull();
        int i = 0;
        for (int from = 0, to = 45; from <= skulls.size(); from += 45, to += 45) {
            if (to > skulls.size()) {
                pages.add(i, new PageGUI(this.plugin, this.getPlayer(), i, skulls.subList(from, skulls.size()), this));
            } else {
                pages.add(i, new PageGUI(this.plugin, this.getPlayer(), i, skulls.subList(from, to), this));
            }
            i++;
        }
        pages.forEach(PageGUI::initializeContent);
    }

    @Override
    public void showGUI() {
        if (!pages.isEmpty()) {
            pages.get(0).showPage();
        }
    }
}
