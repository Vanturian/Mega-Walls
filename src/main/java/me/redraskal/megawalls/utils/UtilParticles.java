package me.redraskal.megawalls.utils;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.redraskal.megawalls.MegaWalls;

public class UtilParticles
{
  public static void drawParticleLine(Location from, Location to, Effect effect, int particles, int r, int g, int b)
  {
    Location location = from.clone();
    Location target = to.clone();
    double amount = particles;
    Vector link = target.toVector().subtract(location.toVector());
    float length = (float)link.length();
    link.normalize();
    
    float ratio = length / particles;
    Vector v = link.multiply(ratio);
    Location loc = location.clone().subtract(v);
    int step = 0;
    for (int i = 0; i < particles; i++)
    {
      if (step >= amount) {
        step = 0;
      }
      step++;
      loc.add(v);
      if (effect == Effect.COLOURED_DUST)
      {
        float finalR = r / 255;
        float finalG = g / 255;
        float finalB = b / 255;
        play(loc, Effect.COLOURED_DUST, 0, 0, finalR, finalG, finalB, 1.0F, 0);
      }
      else
      {
        play(loc, effect);
      }
    }
  }
  
  public static void playHelix(Location loc, final float i, final Effect effect)
  {
    BukkitRunnable runnable = new BukkitRunnable()
    {
      double radius = 0.0D;
      double step;
      double y = loc.getY();
      Location location = loc.clone().add(0.0D, 3.0D, 0.0D);
      
      public void run()
      {
        double inc = 0.12566370614359174D;
        double angle = this.step * inc + i;
        Vector v = new Vector();
        v.setX(Math.cos(angle) * this.radius);
        v.setZ(Math.sin(angle) * this.radius);
        if (effect == Effect.COLOURED_DUST) {
          UtilParticles.play(this.location.add(v), Effect.COLOURED_DUST, 0, 0, -1.0F, -1.0F, 1.0F, 1.0F, 0);
        } else {
          UtilParticles.play(this.location.add(v), effect, 0.0F);
        }
        this.location.subtract(v);
        this.location.subtract(0.0D, 0.1D, 0.0D);
        if (this.location.getY() <= this.y) {
          cancel();
        }
        this.step += 4.0D;
        this.radius += 0.03333333507180214D;
      }
    };
    runnable.runTaskTimer(MegaWalls.getInstance(), 0L, 1L);
  }
  
  public static void play(Location location, Effect effect)
  {
    play(location, effect, 0, 0, 0.0F, 0.0F, 0.0F, 0.0F, 1);
  }
  
  public static void play(Location location, Effect effect, int data)
  {
    play(location, effect, data, data, 0.0F, 0.0F, 0.0F, 0.0F, 1);
  }
  
  public static void play(Location location, Effect effect, float offsetX, float offsetY, float offsetZ)
  {
    play(location, effect, 0, 0, offsetX, offsetY, offsetZ, 0.0F, 1);
  }
  
  public static void play(Location location, Effect effect, float speed)
  {
    play(location, effect, 0, 0, 0.0F, 0.0F, 0.0F, speed, 1);
  }
  
  public static void play(Location location, Effect effect, int id, int data, float offsetX, float offsetY, float offsetZ, float speed, int amount)
  {
    location.getWorld().spigot().playEffect(location, effect, id, data, offsetX, offsetY, offsetZ, speed, amount, 128);
  }
}
