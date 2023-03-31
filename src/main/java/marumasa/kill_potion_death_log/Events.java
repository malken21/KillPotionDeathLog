package marumasa.kill_potion_death_log;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        for (PotionEffect effect : event.getPotion().getEffects())
            if (Amplifier(effect.getAmplifier()) && effect.getType().equals(PotionEffectType.HEAL)) {
                for (LivingEntity entity : event.getAffectedEntities()) {
                    entity.setHealth(0);
                }
            }
    }

    private static boolean Amplifier(int effect) {
        return effect == 29 || effect == 61 || effect == 93 || effect == 125;
    }
}
