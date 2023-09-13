package net.grinner117.radiantlibary.event;


import net.grinner117.radiantlibary.RadiantLibary;
import net.grinner117.radiantlibary.effects.ModEffects;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = RadiantLibary.MODID)
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
		if (entity != null && entity.hasEffect(ModEffects.HEX_EFFECT.get()) &&
				(entity.hasEffect(ModEffects.POISON) || entity.hasEffect(ModEffects.WITHER) || entity.isOnFire() || entity.hasEffect(ModEffects.LIGHTING_EFFECT.get()))) {
			e.setAmount(e.getAmount() + 0.5f + 0.33f * entity.getEffect(ModEffects.HEX_EFFECT.get()).getAmplifier());
		}
		if (entity == null)
			return;
		double warding = PerkUtil.valueOrZero(entity, PerkAttributes.WARDING.get());
		double feather = PerkUtil.valueOrZero(entity, PerkAttributes.FEATHER.get());
		if (e.getSource().isMagic()) {
			e.setAmount((float) (e.getAmount() - warding));
		}

		if (e.getSource().isFall()) {
			e.setAmount((float) (e.getAmount() - (e.getAmount() * feather)));
		}
	}

	@SubscribeEvent
	public static void fallEvent(LivingFallEvent fallEvent) {
		if (!(fallEvent.getEntity() instanceof Player player))
			return;
		double jumpBonus = PerkUtil.countForPerk(JumpHeightPerk.INSTANCE, player);
		fallEvent.setDistance((float) (fallEvent.getDistance() - (jumpBonus / 0.1)));
		if (CuriosUtil.hasItem(fallEvent.getEntity(), ItemsRegistry.BELT_OF_LEVITATION.asItem())) {
			fallEvent.setDistance(Math.max(0, fallEvent.getDistance() - 6));
		}
	}

	@SubscribeEvent
	public static void entityHeal(LivingHealEvent e) {
		LivingEntity entity = e.getEntity();
		if (entity != null && entity.hasEffect(ModEffects.HEX_EFFECT.get())) {
			e.setAmount(e.getAmount() / 2.0f);
		}

		if (entity != null && entity.hasEffect(ModEffects.RECOVERY_EFFECT.get())) {
			e.setAmount(e.getAmount() + 1 + entity.getEffect(ModEffects.RECOVERY_EFFECT.get()).getAmplifier());
		}
	}

	@SubscribeEvent
	public static void eatEvent(LivingEntityUseItemEvent.Finish event) {
		if (!event.getEntity().level.isClientSide && event.getItem().getItem().getFoodProperties() != null && event.getItem().getItem().isEdible()) {
			if (event.getEntity() instanceof Player player) {
				FoodData stats = player.getFoodData();
				stats.saturationLevel *= PerkUtil.perkValue(player, PerkAttributes.WHIRLIESPRIG.get());
			}
		}
	}

	@SubscribeEvent
	public static void dispelEvent(DispelEvent event) {
		if (event.rayTraceResult instanceof EntityHitResult hit && hit.getEntity() instanceof Witch entity) {
			if (entity.getHealth() <= entity.getMaxHealth() / 2) {
				entity.remove(Entity.RemovalReason.KILLED);
				ParticleUtil.spawnPoof((ServerLevel) event.world, entity.blockPosition());
				event.world.addFreshEntity(new ItemEntity(event.world, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ItemsRegistry.WIXIE_SHARD)));
			}
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