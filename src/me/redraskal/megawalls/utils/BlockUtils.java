package me.redraskal.megawalls.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.redraskal.megawalls.MegaWalls;

public class BlockUtils
{
	public static List<FallingBlock> fallingBlocks = new ArrayList<FallingBlock>();
	
	public static void playBoomEffect(Location p, int radius)
	  {
	    final Location loc = p;
	    loc.getWorld().playSound(loc, Sound.EXPLODE, 2.0F, 1.0F);
	    new BukkitRunnable()
	    {
	      int i = 1;
	      
	      @SuppressWarnings("deprecation")
		public void run()
	      {
	        if (this.i == radius) {
	          cancel();
	        }
	        for (org.bukkit.block.Block b : BlockUtils.getBlocksInRadius(loc.clone().add(0.0D, -1.0D, 0.0D), this.i, true)) {
	          if ((b.getLocation().getBlockY() == loc.getBlockY() - 1) && 
	            (b.getType() != org.bukkit.Material.AIR) && 
	            (b.getType() != org.bukkit.Material.SIGN_POST) && 
	            (b.getType() != org.bukkit.Material.CHEST) && 
	            (b.getType() != org.bukkit.Material.STONE_PLATE) && 
	            (b.getType() != org.bukkit.Material.WOOD_PLATE) && 
	            (b.getType() != org.bukkit.Material.WALL_SIGN) && 
	            (b.getType() != org.bukkit.Material.WALL_BANNER) && 
	            (b.getType() != org.bukkit.Material.STANDING_BANNER) && 
	            (b.getType() != org.bukkit.Material.CROPS) && 
	            (b.getType() != org.bukkit.Material.LONG_GRASS) && 
	            (b.getType() != org.bukkit.Material.SAPLING) && 
	            (b.getType() != org.bukkit.Material.DEAD_BUSH) && 
	            (b.getType() != org.bukkit.Material.RED_ROSE) && 
	            (b.getType() != org.bukkit.Material.RED_MUSHROOM) && 
	            (b.getType() != org.bukkit.Material.BROWN_MUSHROOM) && 
	            (b.getType() != org.bukkit.Material.TORCH) && 
	            (b.getType() != org.bukkit.Material.LADDER) && 
	            (b.getType() != org.bukkit.Material.VINE) && 
	            (b.getType() != org.bukkit.Material.DOUBLE_PLANT) && 
	            (b.getType() != org.bukkit.Material.PORTAL) && 
	            (b.getType() != org.bukkit.Material.CACTUS) && 
	            (b.getType() != org.bukkit.Material.WATER) && 
	            (b.getType() != org.bukkit.Material.STATIONARY_WATER) && 
	            (b.getType() != org.bukkit.Material.LAVA) && 
	            (b.getType() != org.bukkit.Material.STATIONARY_LAVA) && 
	            (net.minecraft.server.v1_8_R3.Block.getById(b.getTypeId()).getMaterial().isSolid()) && 
	            (b.getType().getId() != 43) && 
	            (b.getType().getId() != 44) && 
	            (b.getRelative(BlockFace.UP).getType() == org.bukkit.Material.AIR))
	          {
	            FallingBlock fb = loc.getWorld().spawnFallingBlock(b.getLocation().clone().add(0.0D, 1.100000023841858D, 0.0D), b.getType(), b.getData());
	            fb.setVelocity(new Vector(0.0F, 0.3F, 0.0F));
	            fb.setDropItem(false);
	            fallingBlocks.add(fb);
	            for (Entity ent : fb.getNearbyEntities(1.0D, 1.0D, 1.0D)) {
	              if ((ent != p) && (ent.getType() != EntityType.FALLING_BLOCK)) {
	                MathUtils.applyVelocity(ent, new Vector(0.0D, 0.5D, 0.0D));
	              }
	            }
	          }
	        }
	        this.i += 1;
	      }
	    }.runTaskTimer(MegaWalls.getInstance(), 0L, 1L);
	  }
	
  public static Map<Location, String> blocksToRestore = new HashMap<Location, String>();
  
  public static List<org.bukkit.block.Block> getBlocksInRadius(Location location, int radius, boolean hollow)
  {
    List<org.bukkit.block.Block> blocks = new ArrayList<Block>();
    
    int bX = location.getBlockX();
    int bY = location.getBlockY();
    int bZ = location.getBlockZ();
    for (int x = bX - radius; x <= bX + radius; x++) {
      for (int y = bY - radius; y <= bY + radius; y++) {
        for (int z = bZ - radius; z <= bZ + radius; z++)
        {
          double distance = (bX - x) * (bX - x) + (bY - y) * (bY - y) + (bZ - z) * (bZ - z);
          if ((distance < radius * radius) && ((!hollow) || (distance >= (radius - 1) * (radius - 1))))
          {
            Location l = new Location(location.getWorld(), x, y, z);
            if (l.getBlock().getType() != org.bukkit.Material.BARRIER) {
              blocks.add(l.getBlock());
            }
          }
        }
      }
    }
    return blocks;
  }
  
  public static double getDistance(int x1, int z1, int x2, int z2)
  {
    int dx = x1 - x2;
    int dz = z1 - z2;
    return Math.sqrt(dx * dx + dz * dz);
  }
  
  @SuppressWarnings("deprecation")
public static void forceRestore()
  {
    for (Location loc : blocksToRestore.keySet())
    {
      org.bukkit.block.Block b = loc.getBlock();
      String s = (String)blocksToRestore.get(loc);
      org.bukkit.Material m = org.bukkit.Material.valueOf(s.split(",")[0]);
      byte d = Byte.valueOf(s.split(",")[1]).byteValue();
      b.setType(m);
      b.setData(d);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void restoreBlockAt(Location loc)
  {
    if (!blocksToRestore.containsKey(loc)) {
      return;
    }
    org.bukkit.block.Block b = loc.getBlock();
    String s = (String)blocksToRestore.get(loc);
    org.bukkit.Material m = org.bukkit.Material.valueOf(s.split(",")[0]);
    byte d = Byte.valueOf(s.split(",")[1]).byteValue();
    b.setType(m);
    b.setData(d);
    blocksToRestore.remove(loc);
  }
  
  @SuppressWarnings("deprecation")
public static void setToRestore(org.bukkit.block.Block b, org.bukkit.Material newType, byte newData, int tickDelay)
  {
    if (blocksToRestore.containsKey(b.getLocation())) {
      return;
    }
    org.bukkit.block.Block bUp = b.getRelative(BlockFace.UP);
    if ((b.getType() != org.bukkit.Material.AIR) && 
      (b.getType() != org.bukkit.Material.SIGN_POST) && 
      (b.getType() != org.bukkit.Material.CHEST) && 
      (b.getType() != org.bukkit.Material.STONE_PLATE) && 
      (b.getType() != org.bukkit.Material.WOOD_PLATE) && 
      (b.getType() != org.bukkit.Material.WALL_SIGN) && 
      (b.getType() != org.bukkit.Material.WALL_BANNER) && 
      (b.getType() != org.bukkit.Material.STANDING_BANNER) && 
      (b.getType() != org.bukkit.Material.CROPS) && 
      (b.getType() != org.bukkit.Material.LONG_GRASS) && 
      (b.getType() != org.bukkit.Material.SAPLING) && 
      (b.getType() != org.bukkit.Material.DEAD_BUSH) && 
      (b.getType() != org.bukkit.Material.RED_ROSE) && 
      (b.getType() != org.bukkit.Material.RED_MUSHROOM) && 
      (b.getType() != org.bukkit.Material.BROWN_MUSHROOM) && 
      (b.getType() != org.bukkit.Material.TORCH) && 
      (b.getType() != org.bukkit.Material.LADDER) && 
      (b.getType() != org.bukkit.Material.VINE) && 
      (b.getType() != org.bukkit.Material.DOUBLE_PLANT) && 
      (b.getType() != org.bukkit.Material.PORTAL) && 
      (b.getType() != org.bukkit.Material.CACTUS) && 
      (b.getType() != org.bukkit.Material.WATER) && 
      (b.getType() != org.bukkit.Material.STATIONARY_WATER) && 
      (b.getType() != org.bukkit.Material.LAVA) && 
      (b.getType() != org.bukkit.Material.STATIONARY_LAVA) && 
      (b.getType() != org.bukkit.Material.PORTAL) && 
      (b.getType() != org.bukkit.Material.ENDER_PORTAL) && 
      (b.getType() != org.bukkit.Material.SOIL) && 
      (b.getType() != org.bukkit.Material.BARRIER) && 
      (b.getType() != org.bukkit.Material.COMMAND) && 
      (b.getType() != org.bukkit.Material.DROPPER) && 
      (b.getType() != org.bukkit.Material.DISPENSER) && 
      (!b.getType().toString().toLowerCase().contains("door")) && 
      (b.getType() != org.bukkit.Material.BED) && 
      (b.getType() != org.bukkit.Material.BED_BLOCK) && 
      (net.minecraft.server.v1_8_R3.Block.getById(b.getTypeId()).getMaterial().isSolid()) && 
      (a(bUp)) && 
      (b.getType().getId() != 43) && 
      (b.getType().getId() != 44) && 
      (!blocksToRestore.containsKey(b.getLocation())))
    {
      blocksToRestore.put(b.getLocation(), b.getType().toString() + "," + b.getData());
      b.setType(newType);
      b.setData(newData);
      Bukkit.getScheduler().runTaskLater(MegaWalls.getInstance(), new Runnable()
      {
        public void run()
        {
          BlockUtils.restoreBlockAt(b.getLocation());
        }
      }, tickDelay);
    }
  }
  
  @SuppressWarnings("deprecation")
private static boolean a(org.bukkit.block.Block b)
  {
    if ((b.getType() == org.bukkit.Material.AIR) || 
      (net.minecraft.server.v1_8_R3.Block.getById(b.getTypeId()).getMaterial().isSolid())) {
      return true;
    }
    return false;
  }
}