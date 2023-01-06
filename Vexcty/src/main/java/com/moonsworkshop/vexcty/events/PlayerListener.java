package com.moonsworkshop.vexcty.events;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.player.PlayerData;
import com.moonsworkshop.vexcty.player.PlayerState;
import com.moonsworkshop.vexcty.util.CC;
import com.moonsworkshop.vexcty.util.Config;
import com.moonsworkshop.vexcty.util.Locations;
import com.moonsworkshop.vexcty.util.User;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {

    Vexcty plugin;

    public PlayerListener(Vexcty captchaIt, Vexcty instance) {
        this.plugin = instance;
        captchaIt.getServer().getPluginManager().registerEvents(this, captchaIt);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if(e.getMessage() == "version" || e.getMessage() == "about" || e.getMessage() == "ver" || e.getMessage() == "icanhasbukkit" || e.getMessage().contains(":") || e.getMessage() == "?" || e.getMessage() == "help" || e.getMessage() == "pl" || e.getMessage() == "plugins" || e.getMessage() == "bukkit:" || e.getMessage() == "me" || (e.getMessage() == "minecraft:")){
            e.getPlayer().sendMessage(CC.RED +  "You do not have permission to execute this command! ");
            e.setCancelled(true);
            return;
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setAllowFlight(true);

        player.teleport(Locations.SPAWN);
        player.setWalkSpeed(0.4F);

        PlayerInventory inv = player.getInventory();

        inv.clear();
        inv.setArmorContents(null);

    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {

        if (plugin.getConfig().getConfigurationSection("double jump") == null) {
            plugin.getConfig().set("double jump.enabled", true);
            plugin.saveConfig();
        }
        if (plugin.getConfig().getBoolean("double jump.enabled") == false) {
            return;
        }

        Player player = e.getPlayer();
        e.setCancelled(true);
        player.setVelocity(player.getLocation().getDirection().multiply(1.6d).setY(1.0d));
        player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 5.0F, 0.733F);
        Location loc = player.getLocation();
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME,true, (float) (loc.getX()), (float) (loc.getY()), (float) (loc.getZ()), 0.6F, 0, 0.6F, 0, 3);
        PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(EnumParticle.SMOKE_NORMAL,true, (float) (loc.getX()), (float) (loc.getY()), (float) (loc.getZ()), 0.6F, 0, 0.6F, 0, 3);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet2);
        for (Entity ent : loc.getWorld().getNearbyEntities(loc, 30, 30, 30)) { // For-loop sends the packet to all nearby players
            if (!(ent instanceof Player)) continue;
            Player nearbyPlayer = (Player) ent;
            ((CraftPlayer)nearbyPlayer).getHandle().playerConnection.sendPacket(packet);
            ((CraftPlayer)nearbyPlayer).getHandle().playerConnection.sendPacket(packet2);
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile proj = event.getEntity();
        if (proj instanceof EnderPearl) {
            EnderPearl pearl = (EnderPearl)proj;
            ProjectileSource source = pearl.getShooter();
            if (source instanceof Player) {
                Player player = (Player)source;
                pearl.setPassenger(player);
                pearl.setVelocity(player.getLocation().getDirection().normalize().multiply(2.0F));
                player.spigot().setCollidesWithEntities(false);
            }
        }
    }


    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL){
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (event.getFoodLevel() < 20) {
            event.setFoodLevel(20);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        e.getEntity().setHealth(20);
        e.getDrops().clear();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onTabCokmplete(PlayerChatTabCompleteEvent e) {
        if(e.getChatMessage().contains(":") || this.betterContains(e.getChatMessage(), "?") || this.betterContains(e.getChatMessage(), "help") || this.betterContains(e.getChatMessage(), "pl") || this.betterContains(e.getChatMessage(), "plugins") || this.betterContains(e.getChatMessage(), "bukkit:") || this.betterContains(e.getChatMessage(), "me") || this.betterContains(e.getChatMessage(), "minecraft:")){
            e.getTabCompletions().clear();
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        if(this.betterContains(e.getMessage(), "version") || this.betterContains(e.getMessage(), "about") || this.betterContains(e.getMessage(), "ver") || this.betterContains(e.getMessage(), "icanhasbukkit") || e.getMessage().contains(":") || this.betterContains(e.getMessage(), "?") || this.betterContains(e.getMessage(), "help") || this.betterContains(e.getMessage(), "pl") || this.betterContains(e.getMessage(), "plugins") || this.betterContains(e.getMessage(), "bukkit:") || this.betterContains(e.getMessage(), "me") || this.betterContains(e.getMessage(), "minecraft:")){
            e.getPlayer().sendMessage(CC.RED +  "You do not have permission to execute this command! ");
            e.setCancelled(true);
            return;
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getTo().getY() < 0) {
            e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
            return;
        }
    }
    public boolean betterContains(String msg, String name) {
        if(msg.toLowerCase().startsWith("/" + name + " ") || msg.toLowerCase().equals("/" + name )) {
            return true;
        }
        return false;
    }


    @EventHandler
    public void onBuild(BlockPlaceEvent e) {
        if(!e.getPlayer().isOp()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.isOp() && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (player.isOp() && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.setCancelled(true);

    }


    @EventHandler
    public void onRain(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        event.setCancelled(true);

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            Player player = (Player) event.getEntity();

            player.teleport(Locations.SPAWN);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        plugin.getPlayerManager().createData(player);
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);
        data.setPlayerState(PlayerState.PLAYING);
        plugin.getServer().getOnlinePlayers().forEach(players -> {
            PlayerData datas = plugin.getPlayerManager().getPlayerData(players);
            if(datas.isVanished()) {
                Player vPlayers = Bukkit.getPlayer(datas.getPlayerID());
                player.hidePlayer(vPlayers);
            }
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);
        if(data.getPlayerState() == PlayerState.FROZEN) {
            plugin.getServer().getOnlinePlayers().stream().filter(players -> players.hasPermission("moderation.staff")).forEach(players -> {
                players.sendMessage(CC.DARK_GRAY + "[" + CC.DARK_RED + "!" + CC.DARK_GRAY + "] " + CC.SECONDARY + player.getName() + CC.PRIMARY + " has left the server while he was frozen!");
                TextComponent clickToBan = new TextComponent(CC.RED + "[Click to ban]");
                clickToBan.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.I_GRAY + "Click to ban " + player.getName()).create()));
                clickToBan.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/punish " + player.getName() + " -b lwf")); // leaving when frozen
                players.spigot().sendMessage(clickToBan);
            });
        }
        plugin.getPlayerManager().destroyData(player);
    }

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if(!(e.getRightClicked() instanceof Player)) return;
        Player target = (Player) e.getRightClicked();
        ItemStack item = e.getPlayer().getItemInHand();

        PlayerData playerData = plugin.getPlayerManager().getPlayerData(player);
        PlayerData targetData = plugin.getPlayerManager().getPlayerData(target);

        PlayerState targetState = targetData.getPlayerState();

        switch(playerData.getPlayerState()) {

            case MODERATOR:
                e.setCancelled(true);

                switch(item.getType()) {

                    case PACKED_ICE:
                        if(target.hasPermission("moderation.staff")) {
                            player.sendMessage(CC.RED + "You cannot interact with other staff members.");
                            return;
                        }
                        targetData.setPlayerState(targetState == PlayerState.FROZEN ? PlayerState.PLAYING : PlayerState.FROZEN);
                        player.sendMessage(CC.PRIMARY + "You've successfully " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen ") + CC.SECONDARY + target.getName());
                        target.sendMessage(CC.PRIMARY + "You've been " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen "));
                        target.closeInventory();
                        break;

                    case BLAZE_ROD:
                        if(target.hasPermission("moderation.staff")) {
                            player.sendMessage(CC.RED + "You cannot interact with other staff members.");
                            return;
                        }
                        if(targetState != PlayerState.PLAYING) targetData.setPlayerState(PlayerState.PLAYING);
                        target.kickPlayer(CC.PRIMARY + "You've been kicked by " + CC.SECONDARY + player.getName());
                        break;
                    case BOOK:
                        player.openInventory(getTargetInventory(target));
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                if(player.getOpenInventory().getTopInventory().getName().contains(CC.PRIMARY + "Viewing")) {
                                    PlayerInventory pi = target.getInventory();
                                    for(int i = 0; i < pi.getSize(); i++) {
                                        player.getOpenInventory().setItem(i, pi.getItem(i));
                                    }
                                }
                            }
                        }.runTaskTimer(plugin, 10L, 10L);
                        break;
                    default:
                        break;
                }

                break;

            case FROZEN:
                e.setCancelled(true);
                player.sendMessage(CC.PRIMARY + "You are frozen, you may not interact with other players.");
                break;

            default:
                break;
        }

    }

    private Inventory getTargetInventory(Player target) {
        PlayerInventory pi = target.getInventory();
        Inventory targetInventory = Bukkit.createInventory(null, 9 * 5, CC.PRIMARY + "Viewing " + CC.DARK_GRAY + "> " + CC.SECONDARY + target.getName());
        for(int i = 0; i < pi.getSize(); i++) {
            if(pi.getItem(i) != null) {
                targetInventory.setItem(i, pi.getItem(i));
            }
        }
        return targetInventory;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);
        e.setCancelled(data.getPlayerState() != PlayerState.PLAYING);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);
        e.setCancelled(data.getPlayerState() != PlayerState.PLAYING);
    }

    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);
        e.setCancelled(data.getPlayerState() != PlayerState.PLAYING);
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);
        e.setCancelled(data.getPlayerState() != PlayerState.PLAYING);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);
        if(data.getPlayerState() == PlayerState.FROZEN) {
            e.setTo(e.getFrom());
        }
    }

    @EventHandler
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);

        switch(plugin.getChatManager().getChatState()) {

            case MUTED:
                if(!player.hasPermission("moderation.mute.bypass")) {
                    e.setCancelled(true);
                    player.sendMessage(CC.RED + "The chat is currently muted.");
                    return;
                }
                break;
            case RESTRICTED:
                if(!plugin.getChatManager().getRestrictedPlayers().contains(player.getUniqueId())) {
                    e.setCancelled(true);
                    player.sendMessage(CC.RED + "Sorry, but the chat is currently restricted.");
                    return;
                }
                break;
            case SLOWED:
                if(!player.hasPermission("moderation.slowed.bypass")) {
                    int delay = plugin.getChatManager().getDelay();
                    int toSeconds = delay / 1000;
                    if(plugin.getChatManager().getSlowedTimer().containsKey(player.getUniqueId())) {
                        e.setCancelled(true);
                        player.sendMessage(CC.RED + "The chat is currently slowed down (" + toSeconds + " second" + (toSeconds >= 2 ? "s" : "") + " delay)");
                        if(System.currentTimeMillis() > (plugin.getChatManager().getSlowedTimer().get(player.getUniqueId()) + delay)) {
                            data.setSlowed(false);
                            plugin.getChatManager().getSlowedTimer().remove(player.getUniqueId());
                        }
                        return;
                    }
                    data.setSlowed(true);
                    plugin.getChatManager().getSlowedTimer().put(player.getUniqueId(), System.currentTimeMillis());
                }
            default:
                break;
        }

        if(plugin.getConfig().getBoolean("chat-filter.enabled")) {
            plugin.getChatManager().getBlockedWords().forEach(word -> {
                if((e.getMessage().length() >= 2 && e.getMessage().contains(" " + word + " ")) || (e.getMessage().length() <= 1 && e.getMessage().contains(word))) {
                    e.setCancelled(true);
                    player.sendMessage(CC.RED + "Your message contained a block word!");
                }
            });
        }


    }

    @EventHandler
    public void noLandDamage(EntityDamageEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(plugin.getConfig().getConfigurationSection("ender butt") == null) {
            plugin.getConfig().set("ender butt.item slot", 5);
            plugin.getConfig().set("ender butt.name", "&aEnder Butt");
            plugin.getConfig().set("ender butt.enabled", true);
            plugin.saveConfig();
        }
        if(plugin.getConfig().getBoolean("ender butt.enabled") == false) {
            return;
        }

        ItemStack enderButt = new ItemStack(Material.ENDER_PEARL, 64);
        ItemMeta enderButtMeta = enderButt.getItemMeta();
        String enderButtName = plugin.getConfig().getString("ender butt.name");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            enderButtName = PlaceholderAPI.setPlaceholders(e.getPlayer(), enderButtName);
        }
        enderButtMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', enderButtName));
        enderButt.setItemMeta(enderButtMeta);

        Action action = e.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            ItemStack itemStack = player.getItemInHand();
            if(itemStack.getType() == Material.AIR) return;
            if  (itemStack.getItemMeta().equals(enderButtMeta)) {
                e.setCancelled(true);


                EnderPearl enderbutt = player.launchProjectile(EnderPearl.class);
                enderbutt.setVelocity(player.getLocation().getDirection().multiply(1.6F));
                enderbutt.setPassenger(player);
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);

            }
        }
    }

}


