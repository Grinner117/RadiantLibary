package net.grinner117.radiantlibrary.effects;

import net.grinner117.radiantlibrary.networking.PacketUpdateFlight;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

public class FlyEffect extends MobEffect {
	protected FlyEffect() {
		super(MobEffectCategory.BENEFICIAL, 2039587);
	}


	@Override
	public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
		return true;
	}
//
//	@Override
//	public void applyEffectTick(LivingEntity entity, int p_76394_2_) {
//		if (entity instanceof Player player) {
//			player.getAbilities().mayfly = (player.isCreative() || entity.isSpectator()) || entity.getEffect(ModEffects.FLY_EFFECT.get()).getDuration() > 2;
//		}
//	}


	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_111187_2_, int p_111187_3_) {
		super.removeAttributeModifiers(entity, p_111187_2_, p_111187_3_);
		if (entity instanceof Player player) {
			boolean canFly = player.isCreative() || player.isSpectator();
			player.getAbilities().mayfly = canFly;
			player.getAbilities().flying = canFly;
			net.grinner117.radiantlibrary.networking.network.Networking.sendToPlayerClient(new PacketUpdateFlight(canFly, canFly), (ServerPlayer) player);
		}
	}
}