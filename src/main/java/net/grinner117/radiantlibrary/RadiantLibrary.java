package net.grinner117.radiantlibrary;

import com.mojang.logging.LogUtils;
import net.grinner117.radiantlibrary.effects.ModEffects;
import net.grinner117.radiantlibrary.entity.ModEntities;
import net.grinner117.radiantlibrary.event.ClientEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(RadiantLibrary.MODID)
public class RadiantLibrary {
	public static final String MODID = "radiantlibrary";
	private static final Logger LOGGER = LogUtils.getLogger();
	public static CommandSourceStack proxy;

	@SuppressWarnings("deprecation")
	public RadiantLibrary() {

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(ClientEventHandler.class));

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientSetup);
		MinecraftForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::commonSetup);
		ModEffects.EFFECTS.register(modEventBus);
		ModEntities.ENTITIES.register(modEventBus);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		// Some common setup code
		LOGGER.info("HELLO FROM COMMON SETUP");
		LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
	}

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	public void setup(final FMLCommonSetupEvent event) {
		net.grinner117.radiantlibrary.networking.network.Networking.registerMessages();
		event.enqueueWork(ModEffects::addRecipes);
		event.enqueueWork(ModEntities::registerPlacements);

		event.enqueueWork(() -> {

		});
	}

	public void clientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
		});
		try {
			Class.forName("net.optifine.Config");
		} catch (Exception e) {
		}
	}

	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			// Some client setup code
			LOGGER.info("HELLO FROM CLIENT SETUP");
			LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
		}
	}
}
