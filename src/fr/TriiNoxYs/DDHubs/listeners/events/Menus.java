package fr.TriiNoxYs.DDHubs.listeners.events;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import fr.TriiNoxYs.DDHubs.utils.VisibilityUtils;


public class Menus implements Listener{
    
    private static ArrayList<Player> masking = new ArrayList<Player>();
    public static ItemStack head, gold, compass, sugar, sugarEnch, nametag;
    
    public Menus(){
        head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setLore(Arrays.asList("§7Consultez votre profil !"));
        
        gold = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta goldMeta = gold.getItemMeta();
        goldMeta.setDisplayName("§6§lBoutique");
        goldMeta.setLore(Arrays.asList("§cEn cours de développment."));
        gold.setItemMeta(goldMeta);
        
        compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§f§lMenu des serveurs");
        compassMeta.setLore(Arrays.asList("§6Voyagez parmis nos serveurs !"));
        compass.setItemMeta(compassMeta);
        
        sugar = new ItemStack(Material.SUGAR, 1);
        ItemMeta sugarMeta = sugar.getItemMeta();
        sugarMeta.setDisplayName("§5§lPoudre de cheminette");
        sugarMeta.setLore(Arrays.asList("§5Wooop, un p'tit boost!"));
        sugar.setItemMeta(sugarMeta);
        
        sugarEnch = new ItemStack(Material.SUGAR, 1);
        ItemMeta sugarEnchMeta = sugarEnch.getItemMeta();
        sugarEnchMeta.setDisplayName("§5§lPoudre de cheminette");
        sugarEnchMeta.setLore(Arrays.asList("§5Wooop, un p'tit boost!"));
        sugarEnchMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        sugarEnch.setItemMeta(sugarEnchMeta);
        sugarEnch.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        
        nametag = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta nametagMeta = nametag.getItemMeta();
        nametagMeta.setDisplayName("§7§lParamètres");
        nametagMeta.setLore(Arrays.asList("§7Editez vos paramètres comme bon vous semble !"));
        nametag.setItemMeta(nametagMeta);
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
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§5§lPoudre de cheminette")){
                    if(item.getEnchantments().isEmpty()){
                        e.setCancelled(true);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 3));
                        p.setItemInHand(sugarEnch);
                        p.updateInventory();
                        p.sendMessage("§aEt hop, une petite dose :)");
                    }
                    else if(item.getEnchantments().get(Enchantment.DURABILITY).equals(10)){
                        e.setCancelled(true);
                        p.removePotionEffect(PotionEffectType.SPEED);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 1));
                        p.setItemInHand(sugar);
                        p.updateInventory();
                        p.sendMessage("§cRoh... C'est fini ... :(");
                    }
                }
            }
        }
        else if(item.getType() == Material.INK_SACK){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lJoueurs Affich§s")){
                    e.setCancelled(true);
                    p.sendMessage("§aTous les joueurs sont maintenant §cmasqu§s§e.");
                    ItemStack gray = new ItemStack(Material.INK_SACK, 1);
                    ItemMeta grayMeta = gray.getItemMeta();
                    gray.setDurability((short) 8);
                    grayMeta.setDisplayName("§c§lJoueurs Masqu§s");
                    gray.setItemMeta(grayMeta);
                    p.setItemInHand(gray);
                    p.updateInventory();
                    
                    VisibilityUtils.maskPlayers(p);
                    masking.add(p);
                }
                else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lJoueurs Masqu§s")){
                    e.setCancelled(true);
                    p.sendMessage("§aTous les joueurs sont maintenant §eaffich§s§e.");
                    ItemStack green = new ItemStack(Material.INK_SACK, 1);
                    ItemMeta greenMeta = green.getItemMeta();
                    green.setDurability((short) 10);
                    greenMeta.setDisplayName("§a§lJoueurs Affich§s");
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
