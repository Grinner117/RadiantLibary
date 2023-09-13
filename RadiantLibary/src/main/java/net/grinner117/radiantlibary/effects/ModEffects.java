package net.grinner117.radiantlibary.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.grinner117.radiantlibary.RadiantLibary.MODID;
import static net.grinner117.radiantlibary.lib.LibEffects.*;

public class ModEffects {

	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
	public static final RegistryObject<MobEffect> NULLCURSE_EFFECT = EFFECTS.register(NULLCURSE, NullCurseEffect::new);
	public static final RegistryObject<MobEffect> LIGHTING_EFFECT = EFFECTS.register(LIGHTING, LightingEffect::new);
	public static final RegistryObject<MobEffect> GHOST_EFFECT = EFFECTS.register(GHOST, GhostEffect::new);
	public static final RegistryObject<MobEffect> GLIDE_EFFECT = EFFECTS.register(GLIDE, GlideEffect::new);
	public static final RegistryObject<MobEffect> SNARE_EFFECT = EFFECTS.register(SNARE, SnareEffect::new);
	public static final RegistryObject<MobEffect> FLY_EFFECT = EFFECTS.register(FLY, FlyEffect::new);
	public static final RegistryObject<MobEffect> GRAVITY_EFFECT = EFFECTS.register(GRAVITY, GravityEffect::new);
	public static final RegistryObject<MobEffect> BOUNCE_EFFECT = EFFECTS.register(BOUNCE, BounceEffect::new);

	public static final RegistryObject<MobEffect> RECOVERY_EFFECT = EFFECTS.register(RECOVERY, RecoveryEffect::new);
	public static final RegistryObject<MobEffect> BLAST_EFFECT = EFFECTS.register(BLAST, BlastEffect::new);
	public static final RegistryObject<MobEffect> FREEZING_EFFECT = EFFECTS.register(FREEZING, FreezingEffect::new);
	public static final RegistryObject<MobEffect> DEFENCE_EFFECT = EFFECTS.register(DEFENCE, DefenceEffect::new);


	public static void addRecipes() {

	}
}
