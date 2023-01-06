package com.moonsworkshop.vexcty.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.report.ReportData;
import com.moonsworkshop.vexcty.report.ReportState;
import com.moonsworkshop.vexcty.util.CC;
import com.moonsworkshop.vexcty.util.inventory.InventoryUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;

@Getter
public class InventoryManager implements Listener {

    private Vexcty plugin;
    private InventoryUI reportInventory;

    public InventoryManager() {
        this.plugin = Vexcty.getInstance();
        this.reportInventory = new InventoryUI(CC.PRIMARY + "Current reports", true, 6);
        this.setupInventories();
    }

    private void setupInventories() {

    }

    int reportnb = 0;

    public void setupReportInventory() {
        this.reportInventory.getInventories().clear();
        reportnb = 0;
        List<ReportData> reportDatas = new ArrayList<ReportData>((Collection<? extends ReportData>) plugin.getReportManager().getAllReports());

        reportDatas.forEach(reportData -> {
            reportnb++;
            ReportState rs = reportData.getReportState();
            ItemStack item = new ItemStack(Material.WOOL, 1, (short) (rs == ReportState.WAITING ? 14 : 5));
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(CC.PRIMARY + "Report " + CC.SECONDARY + "#" + reportnb);
            itemMeta.setLore(getLore(reportData));
            item.setItemMeta(itemMeta);
            this.reportInventory.addItem(new InventoryUI.AbstractClickableItem(item) {

                @Override
                public void onClick(InventoryClickEvent e) {
                    Player player = (Player) e.getWhoClicked();
                    ItemStack cItem = e.getCurrentItem();
                    e.setCancelled(true);
                    switch(e.getClick()) {

                        case RIGHT:
                            if(reportData != null) {
                                reportData.setReportState(rs == ReportState.WAITING ? ReportState.DONE : ReportState.WAITING);
                                ItemStack newItem = new ItemStack(Material.WOOL, 1, (short) (rs == ReportState.WAITING ? 14 : 5));
                                ItemMeta newItemMeta = newItem.getItemMeta();
                                newItemMeta.setDisplayName(cItem.getItemMeta().getDisplayName());
                                newItemMeta.setLore(getLore(reportData));
                                newItem.setItemMeta(newItemMeta);
                                reopenInventory(player, reportInventory);
                                player.updateInventory();
                            }
                            break;
                        case LEFT:
                            plugin.getReportManager().destroyReport(reportData.getReportID());
                            reopenInventory(player, reportInventory);
                            break;
                        default:
                            break;
                    }
                }
            });
        });
    }

    private List<String> getLore(ReportData reportData) {
        return Arrays.asList(" ",
                CC.PRIMARY + "Report ID" + CC.DARK_GRAY + ": " + CC.SECONDARY + reportData.getReportID(),
                CC.PRIMARY + "Current state" + CC.DARK_GRAY + ": " + CC.SECONDARY + reportData.getReportState().getName(),
                " ",
                CC.PRIMARY + "Reporter" + CC.DARK_GRAY + ": " + CC.SECONDARY + Bukkit.getPlayer(reportData.getReporter()).getName(),
                CC.PRIMARY + "Suspect" + CC.DARK_GRAY + ": " + CC.SECONDARY + Bukkit.getPlayer(reportData.getReported()).getName(),
                CC.PRIMARY + "Reason" + CC.DARK_GRAY + ": " + CC.SECONDARY + reportData.getReason(),
                " ",
                CC.GRAY + "Right click to change report state",
                CC.GRAY + "Left click to delete report",
                " ");
    }

    private void reopenInventory(Player player, InventoryUI ui) {
        this.setupReportInventory();
        player.openInventory(ui.getCurrentPage());
    }

}