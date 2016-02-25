/**
* UtilParticles2.java
* 5:03:25 PM
* Created by Redraskal
* All rights reserved.
* Some files may be under a Creative Commons License.
*/
package me.redraskal.megawalls.utils;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.redraskal.megawalls.MegaWalls;

public class UtilParticles2 {

    private final static int DEF_RADIUS = 128;

    public static void drawParticleLine(Location from, Location to, ParticleEffect effect, int particles, int r, int g, int b) {
        Location location = from.clone();
        Location target = to.clone();
        double amount = particles;
        Vector link = target.toVector().subtract(location.toVector());
        float length = (float) link.length();
        link.normalize();

        float ratio = length / particles;
        Vector v = link.multiply(ratio);
        Location loc = location.clone().subtract(v);
        int step = 0;
        for (int i = 0; i < particles; i++) {
            if (step >= amount)
                step = 0;
            step++;
            loc.add(v);
            if (effect == ParticleEffect.REDSTONE)
                effect.display(new ParticleEffect.OrdinaryColor(r, g, b), loc, 128);
            else
                effect.display(0, 0, 0, 0, 1, loc, 128);
        }
    }

    public static void playHelix(final Location loc, final float i, final ParticleEffect effect) {
        BukkitRunnable runnable = new BukkitRunnable() {
            double radius = 0;
            double step;
            double y = loc.getY();
            Location location = loc.clone().add(0, 3, 0);

            @Override
            public void run() {
                double inc = (2 * Math.PI) / 50;
                double angle = step * inc + i;
                Vector v = new Vector();
                v.setX(Math.cos(angle) * radius);
                v.setZ(Math.sin(angle) * radius);
                if (effect == ParticleEffect.REDSTONE)
                    display(0, 0, 255, location);
                else
                    display(effect, location);
                location.subtract(v);
                location.subtract(0, 0.1d, 0);
                if (location.getY() <= y) {
                    cancel();
                }
                step += 4;
                radius += 1 / 50f;
            }
        };
        runnable.runTaskTimer(MegaWalls.getInstance(), 0, 1);
    }

    public static void display(ParticleEffect effect, Location location, int amount, float speed) {
        effect.display(0, 0, 0, speed, amount, location, 128);
    }

    public static void display(ParticleEffect effect, Location location, int amount) {
        effect.display(0, 0, 0, 0, amount, location, 128);
    }

    public static void display(ParticleEffect effect, Location location) {
        display(effect, location, 1);
    }

    public static void display(ParticleEffect effect, double x, double y, double z, Location location, int amount) {
        effect.display((float) x, (float) y, (float) z, 0f, amount, location, 128);
    }

    public static void display(ParticleEffect effect, int red, int green, int blue, Location location, int amount) {
        for (int i = 0; i < amount; i++)
            effect.display(new ParticleEffect.OrdinaryColor(red, green, blue), location, DEF_RADIUS);
    }

    public static void display(int red, int green, int blue, Location location) {
        display(ParticleEffect.REDSTONE, red, green, blue, location, 1);
    }

    public static void display(ParticleEffect effect, int red, int green, int blue, Location location) {
        display(effect, red, green, blue, location, 1);
    }


}