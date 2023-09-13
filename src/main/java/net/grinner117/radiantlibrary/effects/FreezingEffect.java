package net.grinner117.radiantlibrary.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FreezingEffect extends MobEffect {
	protected FreezingEffect() {
		super(MobEffectCategory.HARMFUL, 0x00ffff);
	}

	@Override
	public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
		pLivingEntity.setTicksFrozen(Math.min(pLivingEntity.getTicksRequiredToFreeze() + 3, pLivingEntity.getTicksFrozen() + 3 + pAmplifier));
	}

	@Override
	public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
		return true;
	}
}