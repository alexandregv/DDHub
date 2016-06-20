package fr.TriiNoxYs.DDHubs.utils;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InvUtils{

	public static void clearInv(Player player){
	    player.setLevel(0);
        player.setExp(0.0F);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.updateInventory();
	}
	
	@SuppressWarnings("deprecation")
    public static ItemStack parseString(String itemId){
        String[] parts = itemId.split(":");
        int matId = Integer.parseInt(parts[0]);
        if(parts.length == 2){
            short data = Short.parseShort(parts[1]);
            if(matId == 397 && data == 3){
                return new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
            }
            else return new ItemStack(Material.getMaterial(matId), 1, data);
        }
        else return new ItemStack(Material.getMaterial(matId));
    }
}
