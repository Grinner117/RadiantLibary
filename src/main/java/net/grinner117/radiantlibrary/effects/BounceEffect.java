package net.grinner117.radiantlibrary.effects;

import net.grinner117.radiantlibrary.event.BounceEvent;
import net.grinner117.radiantlibrary.event.EventQueue;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BounceEffect extends MobEffect {
	public BounceEffect() {
		super(MobEffectCategory.BENEFICIAL, 2039587);
	}

	@SubscribeEvent
	public static void onFall(LivingFallEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity == null || !entity.hasEffect(net.grinner117.radiantlibrary.effects.ModEffects.BOUNCE_EFFECT.get())) {
			return;
		}
		boolean isPlayer = entity instanceof Player;
		boolean isClient = entity.level.isClientSide;
		if (isClient && !isPlayer) {
			return;
		}
		if (event.getDistance() > 2) {
			if (entity.isCrouching()) {
				event.setDamageMultiplier(0.0f);
			} else {
				event.setDamageMultiplier(0);
				entity.fallDistance = 0.0F;
				if (!isPlayer || isClient) {
					double f = 0.95d - .1 * entity.getEffect(ModEffects.BOUNCE_EFFECT.get()).getAmplifier();
					// only slow down half as much when bouncing
					entity.setDeltaMovement(entity.getDeltaMovement().x / f, entity.getDeltaMovement().y * (-0.9), entity.getDeltaMovement().z / f);
					entity.hurtMarked = true;
					entity.setOnGround(false);
				}
				if (isClient) {
					EventQueue.getClientQueue().addEvent(new BounceEvent(entity, entity.getDeltaMovement().y));
				} else {
					EventQueue.getServerInstance().addEvent(new BounceEvent(entity, entity.getDeltaMovement().y));
				}
				event.setCanceled(true);
			}
		}
	}

}