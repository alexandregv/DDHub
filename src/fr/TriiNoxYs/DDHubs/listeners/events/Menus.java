package fr.TriiNoxYs.DDHubs.listeners.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import fr.TriiNoxYs.DDHubs.Main;
import fr.TriiNoxYs.DDHubs.utils.ChatUtils;
import fr.TriiNoxYs.DDHubs.utils.VisibilityUtils;


public class Menus implements Listener{
    
    private static ArrayList<UUID> 
        noFI = new ArrayList<UUID>(), 
        noPI = new ArrayList<UUID>(), 
        noMP = new ArrayList<UUID>(), 
        noAl = new ArrayList<UUID>();
    
    private static ArrayList<Player> masking = new ArrayList<Player>();
    
    private static HashMap<Player, Long> sugarCooldown = new HashMap<Player, Long>();
    
    public static ItemStack head, gold, compass, sugar, sugarEnch, nametag;                      //Hotbar
    public static ItemStack maskON, maskOFF, mpON, mpOFF, fiON, fiOFF, piON, piOFF, alON, alOFF; //Params GUI
    public static ItemStack party, stats;                                                        //Profile GUI
    
    public Menus(){
        
        //Hotbar
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
        
        //Params GUI
        maskON = new ItemStack(Material.INK_SACK, 1);
        ItemMeta maskONMeta = maskON.getItemMeta();
        maskON.setDurability((short) 8);
        maskONMeta.setDisplayName("§c§lJoueurs Masqués");
        maskON.setItemMeta(maskONMeta);
        
        maskOFF = new ItemStack(Material.INK_SACK, 1);
        ItemMeta masoOFFMeta = maskOFF.getItemMeta();
        maskOFF.setDurability((short) 10);
        masoOFFMeta.setDisplayName("§a§lJoueurs Affichés");
        maskOFF.setItemMeta(masoOFFMeta);
        
        mpON = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta mpONMeta = mpON.getItemMeta();
        mpONMeta.setDisplayName("§a§lMP activés");
        mpON.setItemMeta(mpONMeta);
        
        mpOFF = new ItemStack(Material.BOOK, 1);
        ItemMeta mpOFFMeta = mpOFF.getItemMeta();
        mpOFFMeta.setDisplayName("§c§lMP désactivés");
        mpOFF.setItemMeta(mpOFFMeta);
        
        fiON = new ItemStack(Material.MAP, 1);
        ItemMeta fiONMeta = fiON.getItemMeta();
        fiONMeta.setDisplayName("§a§lDemandes d'ami activées");
        fiON.setItemMeta(fiONMeta);
        
        fiOFF = new ItemStack(Material.PAPER, 1);
        ItemMeta fiOFFMeta = fiOFF.getItemMeta();
        fiOFFMeta.setDisplayName("§c§lDemandes d'ami désactivées");
        fiOFF.setItemMeta(fiOFFMeta);
        
        piON = new ItemStack(Material.SLIME_BALL, 1);
        ItemMeta piONMeta = piON.getItemMeta();
        piONMeta.setDisplayName("§a§lInvitations de party activées");
        piON.setItemMeta(piONMeta);
        
        piOFF = new ItemStack(Material.FIREWORK_CHARGE, 1);
        ItemMeta piOFFMeta = piOFF.getItemMeta();
        piOFFMeta.setDisplayName("§c§lInvitations de party désactivées");
        piOFF.setItemMeta(piOFFMeta);
        
        alON = new ItemStack(Material.RECORD_9, 1);
        ItemMeta alONFMeta = alON.getItemMeta();
        alONFMeta.setDisplayName("§a§lAlertes sonores activées");
        alON.setItemMeta(alONFMeta);
        
        alOFF = new ItemStack(Material.RECORD_11, 1);
        ItemMeta alOFFMeta = alOFF.getItemMeta();
        alOFFMeta.setDisplayName("§c§lAlertes sonores désactivées");
        alOFF.setItemMeta(alOFFMeta);
        
        //Profile GUI
        party = new ItemStack(Material.PRISMARINE_CRYSTALS, 1);
        ItemMeta partyMeta = party.getItemMeta();
        partyMeta.setDisplayName("§9§lParty");
        party.setItemMeta(partyMeta);
        
        stats = new ItemStack(Material.EMERALD, 1);
        ItemMeta statsMeta = stats.getItemMeta();
        statsMeta.setDisplayName("§a§lStats");
        stats.setItemMeta(statsMeta);
        
    }
    
    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        
        if(inv == null) return;
        
        if(Main.bypassed.contains(p)){
            if(!p.getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(true);
                ChatUtils.sendBypassError(p);
            }
        }
        else e.setCancelled(true);
        
        if(inv.getName().equals("§8§lParamètres")){
            if(item.getType().equals(Material.INK_SACK)){
                if(masking.contains(p)){
                    inv.setItem(0, maskOFF);
                    masking.remove(p);
                    VisibilityUtils.showPlayers(p);
                }
                else{
                    inv.setItem(0, maskON);
                    masking.add(p);
                    VisibilityUtils.maskPlayers(p);
                }
            }
            

            else if(item.getType().equals(Material.BOOK_AND_QUILL)){
                inv.setItem(2, mpOFF);
                noMP.add(p.getUniqueId());
            }
            else if(item.getType().equals(Material.BOOK)){
                inv.setItem(2, mpON);
                noMP.remove(p.getUniqueId());
            }
            
            
            else if(item.getType().equals(Material.MAP)){
                inv.setItem(4, fiOFF);
                noFI.add(p.getUniqueId());
            }
            else if(item.getType().equals(Material.PAPER)){
                inv.setItem(4, fiON);
                noFI.remove(p.getUniqueId());
            }
            
            
            else if(item.getType().equals(Material.SLIME_BALL)){
                inv.setItem(6, piOFF);
                noPI.add(p.getUniqueId());
            }
            else if(item.getType().equals(Material.FIREWORK_CHARGE)){
                inv.setItem(6, piON);
                noPI.remove(p.getUniqueId());
            }
            
            
            else if(item.getType().equals(Material.RECORD_9)){
                inv.setItem(8, alOFF);
                noAl.add(p.getUniqueId());
            }
            else if(item.getType().equals(Material.RECORD_11)){
                inv.setItem(8, alON);
                noAl.remove(p.getUniqueId());
            }
        }
    }
    
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        final Player p = e.getPlayer();
        if(Main.bypassed.contains(p)){
            if(!p.getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(true);
                ChatUtils.sendBypassError(p);
            }
        }
        else e.setCancelled(true);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        final Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = p.getItemInHand();
        
        if(action == Action.PHYSICAL || item == null || item.getType() == Material.AIR || action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
            return;
        
        if(item.getType() == Material.SUGAR){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§5§lPoudre de cheminette")){
                    if(sugarCooldown.containsKey(p) && System.currentTimeMillis() < sugarCooldown.get(p) + 5000){
                        p.sendMessage("§cMerci de ne pas spam les gadgets. Attendez §e" + TimeUnit.MILLISECONDS.toSeconds(((sugarCooldown.get(p) + 5000) - System.currentTimeMillis())) + "s§c.");
                    }
                    else{
                        sugarCooldown.put(p, System.currentTimeMillis());
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
        }
        else if(item.getType() == Material.GOLD_INGOT){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBoutique")){
                e.setCancelled(true);
                p.sendMessage("§cBoutique en cours de développement.");
            }
        }
        else if(item.getType() == Material.NAME_TAG){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§7§lParamètres")){
                e.setCancelled(true);
                
                Inventory invParams = Bukkit.createInventory(null, 9, "§8§lParamètres");
                
                if(masking.contains(p)) invParams.setItem(0, maskON);
                else invParams.setItem(0, maskOFF);
                
                if(noMP.contains(p.getUniqueId())) invParams.setItem(2, mpOFF);
                else invParams.setItem(2, mpON);
                
                if(noFI.contains(p.getUniqueId())) invParams.setItem(4, fiOFF);
                else invParams.setItem(4, fiON);
                
                if(noPI.contains(p.getUniqueId())) invParams.setItem(6, piOFF);
                else invParams.setItem(6, piON);
                
                if(noAl.contains(p.getUniqueId())) invParams.setItem(8, alOFF);
                else invParams.setItem(8, alON);
                
                p.openInventory(invParams);
            }
        }
        else if(item.getType() == Material.SKULL_ITEM){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lMon profil")){
                e.setCancelled(true);
                
                Inventory invProfile = Bukkit.createInventory(null, 9, "§8§lParamètres");
                
                ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                SkullMeta headMeta = (SkullMeta) head.getItemMeta();
                headMeta.setDisplayName("§6§lAmis");
                headMeta.setLore(Arrays.asList("§7Jouez avec vos amis !"));
                headMeta.setOwner(p.getName());
                head.setItemMeta(headMeta);
                
                invProfile.setItem(1, party);
                invProfile.setItem(4, head);
                invProfile.setItem(7, stats);
                
                p.openInventory(invProfile);
            }
        }
    }
    
}
