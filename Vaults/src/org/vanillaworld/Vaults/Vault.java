package org.vanillaworld.Vaults;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Vault {
	
	
	public int rows;
	private Inventory inv;
	public int id;
	public String owner;
	
	Vault(int rows, String owner, int id)
	{
		this.rows = rows;
		this.owner = owner;
		this.id = id;
		if(9 * this.rows > 54 || this.rows <= 0)
		{
			this.rows = 3;
		}
		this.inv = Bukkit.getServer().createInventory(null, this.rows * 9, "Chest");
	}
	
	public String toString()
	{
		return SerializationUtil.saveInventory(this.inv);
	}
	
	public FileConfiguration toFileConfiguration()
	{
		 YamlConfiguration config = new YamlConfiguration();
		 SerializationUtil.saveInventory(getInventory(), config);
		 return config;
	}
	
	public void setInventoryFromString(String text)
	{
		ItemStack items[];
		try {
			items = SerializationUtil.loadInventory(text);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		if(items == null)
		{
			return;
		}
		for (ItemStack item : items)
		{
			if(item != null)
			{
				this.inv.addItem(item);
			}
		}
	}
	
	public Inventory getInventory()
	{
		return this.inv;
	}
	
	public void setInventory(Inventory inv)
	{
		this.inv = inv;
	}
	
}
