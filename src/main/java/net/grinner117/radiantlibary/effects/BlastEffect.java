package net.grinner117.radiantlibary.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;

public class BlastEffect extends MobEffect {
	public BlastEffect() {
		super(MobEffectCategory.HARMFUL, 0xdc3027);
	}

	@Override
	public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
		pLivingEntity.level.explode(null, pLivingEntity.getX(), pLivingEntity.getY() + 1, pLivingEntity.getZ(), 2.0f + pAmplifier, false, Explosion.BlockInteraction.NONE);
	}

	@Override
	public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
		return pDuration == 1;
	}
}