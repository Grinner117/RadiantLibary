package net.grinner117.radiantlibrary.entity;

import net.grinner117.radiantlibrary.lib.LibEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.grinner117.radiantlibrary.RadiantLibrary.MODID;

public class ModEntities {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

	static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) {
		return ENTITIES.register(name, () -> builder.build(MODID + ":" + name));
	}


	public static final RegistryObject<EntityType<LightningEntity>> LIGHTNING_ENTITY = registerEntity(LibEntities.LIGHTINGBOLT, EntityType.Builder.<LightningEntity>of(LightningEntity::new, MobCategory.MISC)
			.sized(0.0F, 0.0F)
			.clientTrackingRange(16)
			.updateInterval(Integer.MAX_VALUE
			).setShouldReceiveVelocityUpdates(true).setUpdateInterval(60));

	public static void registerPlacements() {

	}

	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {

		}
	}

	public static boolean genericGroundSpawn(EntityType<? extends Entity> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
		return worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) && worldIn.getRawBrightness(pos, 0) > 8;
	}

}