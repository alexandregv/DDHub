package fr.TriiNoxYs.EvenziaManager.listeners.events;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import fr.TriiNoxYs.EvenziaManager.utils.ChatUtils;
import fr.TriiNoxYs.EvenziaManager.utils.InvUtils;
import fr.TriiNoxYs.EvenziaManager.utils.VisibilityUtils;

public class Players implements Listener{

    private static ArrayList<Player> masking = new ArrayList<Player>();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(ChatUtils.getChatState() == false && !p.hasPermission("evenziamanager.bypass.mute")){
            e.setCancelled(true);
            ChatUtils.sendMsg(p, ChatColor.RED + "Le chat est désactivé.");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        InvUtils.clearInv(p);
        
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName("§6§lMon profil");
        headMeta.setLore(Arrays.asList("§7Consultez vos stats !"));
        headMeta.setOwner(p.getName());
        head.setItemMeta(headMeta);
        p.getInventory().setItem(4, head);
        
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        InvUtils.clearInv(p);
        
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName("§6§lMon profil");
        headMeta.setLore(Arrays.asList("§7Consultez vos stats !"));
        headMeta.setOwner(p.getName());
        head.setItemMeta(headMeta);
        p.getInventory().setItem(4, head);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        
        if(item.getType() == Material.SKULL_ITEM && item.hasItemMeta()){
            SkullMeta headMeta = (SkullMeta) item.getItemMeta();
            if(headMeta.hasOwner()) e.setCancelled(true);
            p.updateInventory(); 
        }
    }

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent e){
        if(e.getSource().getHolder() instanceof Player){
            Player p = (Player) e.getSource().getHolder();
            
            e.setCancelled(true);
            p.updateInventory();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommands(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        String[] args = e.getMessage().split(" ");
        
        if(args[0].equals("/pl") 
                || args[0].equals("/plugins")
                || args[0].equals("/bukkit:pl")
                || args[0].equals("/bukkit:plugins") || args[0].equals("/help")
                || args[0].equals("/?") || args[0].equals("/bukkit:help")
                || args[0].equals("/bukkit:?")
                || args[0].equals("/bukkit:ver")
                || args[0].equals("/bukkit:version")
                || args[0].equals("/bungee")){
            if(!(p.hasPermission("evenziatools.plugins") || p.isOp())){
                e.setCancelled(true);
                ChatUtils.sendInfos(p);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = p.getItemInHand();
        
        if((action == Action.PHYSICAL) || (item == null) || (item.getType() == Material.AIR)){
            return;
        }
        
        if(item.getType() == Material.SUGAR){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§5§lCocaïne")){
                    ItemStack no_enchant = new ItemStack(353);
                    ItemMeta noMeta = no_enchant.getItemMeta();
                    noMeta.setDisplayName("§5§lCocaïne");
                    no_enchant.setItemMeta(noMeta);
                    
                    ItemStack enchant = new ItemStack(353);
                    ItemMeta Meta = enchant.getItemMeta();
                    Meta.setDisplayName("§5§lCocaïne");
                    enchant.setItemMeta(Meta);
                    enchant.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    
                    if(item.getEnchantments().isEmpty()){
                        e.setCancelled(true);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 3));
                        p.setItemInHand(enchant);
                        p.updateInventory();
                        p.sendMessage("§aEt hop, une petite dose :)");
                    }
                    else if(item.getEnchantments().get(Enchantment.DURABILITY).equals(10)){
                        e.setCancelled(true);
                        p.removePotionEffect(PotionEffectType.SPEED);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 1));
                        p.setItemInHand(no_enchant);
                        p.updateInventory();
                        p.sendMessage("§cRoh... C'est fini ... :(");
                    }
                }
            }
        }
        else if(item.getType() == Material.INK_SACK){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lJoueurs Affichés")){
                    e.setCancelled(true);
                    p.sendMessage("§aTous les joueurs sont maintenant §cmasqués§e.");
                    ItemStack gray = new ItemStack(Material.INK_SACK, 1);
                    ItemMeta grayMeta = gray.getItemMeta();
                    gray.setDurability((short) 8);
                    grayMeta.setDisplayName("§c§lJoueurs Masqués");
                    gray.setItemMeta(grayMeta);
                    p.setItemInHand(gray);
                    p.updateInventory();
                    
                    VisibilityUtils.maskPlayers(p);
                    masking.add(p);
                }
                else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lJoueurs Masqués")){
                    e.setCancelled(true);
                    p.sendMessage("§aTous les joueurs sont maintenant §eaffichés§e.");
                    ItemStack green = new ItemStack(Material.INK_SACK, 1);
                    ItemMeta greenMeta = green.getItemMeta();
                    green.setDurability((short) 10);
                    greenMeta.setDisplayName("§a§lJoueurs Affichés");
                    green.setItemMeta(greenMeta);
                    p.getInventory().setItemInHand(green);
                    p.updateInventory();
                    
                    VisibilityUtils.showPlayers(p);
                    masking.remove(p);
                }
            }
        }
    }
    
}
