package marumasa.kill_potion_death_log;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        //ポーション
        if (event.getItem().getItemMeta() instanceof PotionMeta potion) {
            for (PotionEffect effect : potion.getCustomEffects())
                if (isKillPotion(effect)) {
                    final Player player = event.getPlayer();
                    if (player.isDead()) return;
                    player.setHealth(0);
                }
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        //スプラッシュポーション
        for (PotionEffect effect : event.getEntity().getEffects())
            if (isKillPotion(effect))
                for (LivingEntity entity : event.getAffectedEntities()) {
                    if (entity.isDead()) return;
                    entity.setHealth(0);
                }
    }

    @EventHandler
    public void onAreaEffectCloudApply(AreaEffectCloudApplyEvent event) {
        //残留ポーション
        for (PotionEffect effect : event.getEntity().getCustomEffects())
            if (isKillPotion(effect))
                for (LivingEntity entity : event.getAffectedEntities()) {
                    if (entity.isDead()) return;
                    entity.setHealth(0);
                }
    }

    private static boolean isKillPotion(PotionEffect effect) {
        //もしキルポになる要素があったら true そうでない場合は false

        int amplifier = effect.getAmplifier();
        return (amplifier == 29 || amplifier == 61 || amplifier == 93 || amplifier == 125)
                && effect.getType().equals(PotionEffectType.HEAL);
    }
}
