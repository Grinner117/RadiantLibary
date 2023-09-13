package net.grinner117.radiantlibary.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.grinner117.radiantlibary.RadiantLibary.MODID;
import static net.grinner117.radiantlibary.lib.LibEffects.*;

public class ModEffects {

	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);

	public static final RegistryObject<MobEffect> LIGHTING_EFFECT = EFFECTS.register(LIGHTING, LightingEffect::new);
	public static final RegistryObject<MobEffect> SCRYING_EFFECT = EFFECTS.register(SCRYING, ScryingEffect::new);
	public static final RegistryObject<MobEffect> GLIDE_EFFECT = EFFECTS.register(GLIDE, GlideEffect::new);
	public static final RegistryObject<MobEffect> SNARE_EFFECT = EFFECTS.register(SNARE, SnareEffect::new);
	public static final RegistryObject<MobEffect> FLY_EFFECT = EFFECTS.register(FLY, FlyEffect::new);
	public static final RegistryObject<MobEffect> GRAVITY_EFFECT = EFFECTS.register(GRAVITY, GravityEffect::new);
	public static final RegistryObject<MobEffect> SPELL_DAMAGE_EFFECT = EFFECTS.register(SPELL_DAMAGE, () -> new PublicEffect(MobEffectCategory.BENEFICIAL, new ParticleColor(30, 200, 200).getColor()));
	public static final RegistryObject<MobEffect> FAMILIAR_SICKNESS_EFFECT = EFFECTS.register(FAMILIAR_SICKNESS, () -> new PublicEffect(MobEffectCategory.NEUTRAL, new ParticleColor(30, 200, 200).getColor(), new ArrayList<>()));
	public static final RegistryObject<MobEffect> BOUNCE_EFFECT = EFFECTS.register(BOUNCE, BounceEffect::new);
	public static final RegistryObject<MobEffect> MAGIC_FIND_EFFECT = EFFECTS.register(MAGIC_FIND, MagicFindEffect::new);

	public static final RegistryObject<MobEffect> RECOVERY_EFFECT = EFFECTS.register(RECOVERY, RecoveryEffect::new);
	public static final RegistryObject<MobEffect> BLAST_EFFECT = EFFECTS.register(BLAST, BlastEffect::new);
	public static final RegistryObject<MobEffect> FREEZING_EFFECT = EFFECTS.register(FREEZING, FreezingEffect::new);
	public static final RegistryObject<MobEffect> DEFENCE_EFFECT = EFFECTS.register(DEFENCE, DefenceEffect::new);

	public static final RegistryObject<Potion> MANA_REGEN_POTION = POTIONS.register(potion(MANA_REGEN), () -> new Potion(new MobEffectInstance(MANA_REGEN_EFFECT.get(), 3600)));
	public static final RegistryObject<Potion> LONG_MANA_REGEN_POTION = POTIONS.register(longPotion(MANA_REGEN), () -> new Potion(new MobEffectInstance(MANA_REGEN_EFFECT.get(), 9600)));
	public static final RegistryObject<Potion> STRONG_MANA_REGEN_POTION = POTIONS.register(strongPotion(MANA_REGEN), () -> new Potion(new MobEffectInstance(MANA_REGEN_EFFECT.get(), 3600, 1)));

	public static final RegistryObject<Potion> SPELL_DAMAGE_POTION = POTIONS.register(potion(SPELL_DAMAGE), () -> new Potion(new MobEffectInstance(SPELL_DAMAGE_EFFECT.get(), 3600)));
	public static final RegistryObject<Potion> SPELL_DAMAGE_POTION_LONG = POTIONS.register(longPotion(SPELL_DAMAGE), () -> new Potion(new MobEffectInstance(SPELL_DAMAGE_EFFECT.get(), 9600)));
	public static final RegistryObject<Potion> SPELL_DAMAGE_POTION_STRONG = POTIONS.register(strongPotion(SPELL_DAMAGE), () -> new Potion(new MobEffectInstance(SPELL_DAMAGE_EFFECT.get(), 3600, 1)));

	public static final RegistryObject<Potion> RECOVERY_POTION = POTIONS.register(potion(RECOVERY), () -> new Potion(new MobEffectInstance(RECOVERY_EFFECT.get(), 3600)));
	public static final RegistryObject<Potion> LONG_RECOVERY_POTION = POTIONS.register(longPotion(RECOVERY), () -> new Potion(new MobEffectInstance(RECOVERY_EFFECT.get(), 9600)));
	public static final RegistryObject<Potion> STRONG_RECOVERY_POTION = POTIONS.register(strongPotion(RECOVERY), () -> new Potion(new MobEffectInstance(RECOVERY_EFFECT.get(), 3600, 1)));

	public static final RegistryObject<Potion> BLAST_POTION = POTIONS.register(potion(BLAST), () -> new Potion(new MobEffectInstance(BLAST_EFFECT.get(), 200)));
	public static final RegistryObject<Potion> LONG_BLAST_POTION = POTIONS.register(longPotion(BLAST), () -> new Potion(new MobEffectInstance(BLAST_EFFECT.get(), 400)));
	public static final RegistryObject<Potion> STRONG_BLAST_POTION = POTIONS.register(strongPotion(BLAST), () -> new Potion(new MobEffectInstance(BLAST_EFFECT.get(), 140, 1)));

	public static final RegistryObject<Potion> FREEZING_POTION = POTIONS.register(potion(FREEZING), () -> new Potion(new MobEffectInstance(FREEZING_EFFECT.get(), 1800)));
	public static final RegistryObject<Potion> LONG_FREEZING_POTION = POTIONS.register(longPotion(FREEZING), () -> new Potion(new MobEffectInstance(FREEZING_EFFECT.get(), 3600)));
	public static final RegistryObject<Potion> STRONG_FREEZING_POTION = POTIONS.register(strongPotion(FREEZING), () -> new Potion(new MobEffectInstance(FREEZING_EFFECT.get(), 1800, 1)));

	public static final RegistryObject<Potion> DEFENCE_POTION = POTIONS.register(potion(DEFENCE), () -> new Potion(new MobEffectInstance(DEFENCE_EFFECT.get(), 3600)));
	public static final RegistryObject<Potion> LONG_DEFENCE_POTION = POTIONS.register(longPotion(DEFENCE), () -> new Potion(new MobEffectInstance(DEFENCE_EFFECT.get(), 9600)));
	public static final RegistryObject<Potion> STRONG_DEFENCE_POTION = POTIONS.register(strongPotion(DEFENCE), () -> new Potion(new MobEffectInstance(DEFENCE_EFFECT.get(), 3600, 1)));

	public static void addRecipes() {

	}
}
