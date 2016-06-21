package fr.TriiNoxYs.DDHubs.listeners.events;

import java.util.Arrays;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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


public class Menus implements Listener{
    
//    private static ArrayList<Player> masking = new ArrayList<Player>();
    
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
    
    private static void sendBypassError(Player p){
        p.sendMessage("§cVous devez être en créatif pour que le bypass soit actif.");
        
        IChatBaseComponent comp = ChatSerializer.a("[\"\",{\"text\":\"Cliquez ici pour passer en créatif.\",\"color\":\"red\",\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/gamemode 1\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez pour passer en créatif\",\"color\":\"gray\"}]}}}]");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
    
    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        
        if(Main.bypassed.contains(p)){
            if(!p.getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(true);
                sendBypassError(p);
            }
        }
        else e.setCancelled(true);
    }
    
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        final Player p = e.getPlayer();
        if(Main.bypassed.contains(p)){
            if(!p.getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(true);
                sendBypassError(p);
            }
        }
        else e.setCancelled(true);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        final Player p = e.getPlayer();
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
                p.openInventory(invParams);
            }
        }
        
//        else if(item.getType() == Material.INK_SACK){
//            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
//                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lJoueurs Affichés")){
//                    e.setCancelled(true);
//                    p.sendMessage("§aTous les joueurs sont maintenant §cmasqués§e.");
//                    ItemStack gray = new ItemStack(Material.INK_SACK, 1);
//                    ItemMeta grayMeta = gray.getItemMeta();
//                    gray.setDurability((short) 8);
//                    grayMeta.setDisplayName("§c§lJoueurs Masqu§s");
//                    gray.setItemMeta(grayMeta);
//                    p.setItemInHand(gray);
//                    p.updateInventory();
//                    
//                    VisibilityUtils.maskPlayers(p);
//                    masking.add(p);
//                }
//                else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lJoueurs Masqués")){
//                    e.setCancelled(true);
//                    p.sendMessage("§aTous les joueurs sont maintenant §eaffichés§e.");
//                    ItemStack green = new ItemStack(Material.INK_SACK, 1);
//                    ItemMeta greenMeta = green.getItemMeta();
//                    green.setDurability((short) 10);
//                    greenMeta.setDisplayName("§a§lJoueurs Affich§s");
//                    green.setItemMeta(greenMeta);
//                    p.getInventory().setItemInHand(green);
//                    p.updateInventory();
//                    
//                    VisibilityUtils.showPlayers(p);
//                    masking.remove(p);
//                }
//            }
//        }
    }
    
}
