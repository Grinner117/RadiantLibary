package net.grinner117.radiantlibary;

import com.mojang.logging.LogUtils;
import net.grinner117.radiantlibary.effects.ModEffects;
import net.grinner117.radiantlibary.event.ClientEventHandler;
import net.grinner117.radiantlibary.networking.Networking;
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

@Mod(RadiantLibary.MODID)
public class RadiantLibary {
	public static final String MODID = "radiantlibary";
	private static final Logger LOGGER = LogUtils.getLogger();
	public static CommandSourceStack proxy;

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

	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			// Some client setup code
			LOGGER.info("HELLO FROM CLIENT SETUP");
			LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
		}
	}

	@SuppressWarnings("deprecation")
	public RadiantLibary() {

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(ClientEventHandler.class));

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientSetup);
		MinecraftForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::commonSetup);
	}

	public void setup(final FMLCommonSetupEvent event) {
		Networking.registerMessages();
		event.enqueueWork(ModEffects::addRecipes);
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
}
