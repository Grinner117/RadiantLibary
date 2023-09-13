package net.grinner117.radiantlibrary.event;


import net.grinner117.radiantlibrary.RadiantLibrary;
import net.grinner117.radiantlibrary.effects.ModEffects;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = RadiantLibrary.MODID)
public class EventHandler {


	@SubscribeEvent
	public static void jumpEvent(LivingEvent.LivingJumpEvent e) {
		if (e.getEntity() == null || !e.getEntity().hasEffect(ModEffects.SNARE_EFFECT.get()))
			return;
		e.getEntity().setDeltaMovement(0, 0, 0);

	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onGlideTick(TickEvent.PlayerTickEvent event) {
		if (event.player.hasEffect(ModEffects.FLY_EFFECT.get())
				&& event.player.level.getGameTime() % 20 == 0
				&& event.player.getEffect(ModEffects.FLY_EFFECT.get()).getDuration() <= 30 * 20
				&& event.player instanceof ServerPlayer serverPlayer) {
			serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(event.player));
		}
	}

	@SubscribeEvent
	public static void entityHurt(LivingHurtEvent e) {
		if (e.getEntity() != null && e.getEntity().hasEffect(ModEffects.DEFENCE_EFFECT.get()) && (e.getSource() == DamageSource.MAGIC || e.getSource() == DamageSource.GENERIC || e.getSource() instanceof EntityDamageSource)) {
			if (e.getAmount() > 0.5) {
				e.setAmount((float) Math.max(0.5, e.getAmount() - 1.0f - e.getEntity().getEffect(ModEffects.DEFENCE_EFFECT.get()).getAmplifier()));
			}
		}

		if (e.getEntity() != null && e.getSource() == DamageSource.LIGHTNING_BOLT && e.getEntity().hasEffect(ModEffects.LIGHTING_EFFECT.get())) {
			float damage = e.getAmount() + 3.0f + 3.0f * e.getEntity().getEffect(ModEffects.LIGHTING_EFFECT.get()).getAmplifier();
			e.setAmount(Math.max(0, damage));
		}
		LivingEntity entity = e.getEntity();
		if (entity != null && entity.hasEffect(ModEffects.NULLCURSE_EFFECT.get()) &&
				(entity.hasEffect(MobEffects.POISON) || entity.hasEffect(MobEffects.WITHER) || entity.isOnFire() || entity.hasEffect(ModEffects.LIGHTING_EFFECT.get()))) {
			e.setAmount(e.getAmount() + 0.5f + 0.33f * entity.getEffect(ModEffects.NULLCURSE_EFFECT.get()).getAmplifier());
		}
		if (entity == null)
			return;
	}

	@SubscribeEvent
	public static void entityHeal(LivingHealEvent e) {
		LivingEntity entity = e.getEntity();
		if (entity != null && entity.hasEffect(ModEffects.NULLCURSE_EFFECT.get())) {
			e.setAmount(e.getAmount() / 2.0f);
		}

		if (entity != null && entity.hasEffect(ModEffects.RECOVERY_EFFECT.get())) {
			e.setAmount(e.getAmount() + 1 + entity.getEffect(ModEffects.RECOVERY_EFFECT.get()).getAmplifier());
		}
	}


	@SubscribeEvent
	public static void potionEvent(MobEffectEvent.Added event) {
		LivingEntity target = event.getEntity();
		Entity applier = event.getEffectSource();
		if (target.level.isClientSide)
			return;
		double bonus = 0.0;

	}


	private EventHandler() {
	}

}