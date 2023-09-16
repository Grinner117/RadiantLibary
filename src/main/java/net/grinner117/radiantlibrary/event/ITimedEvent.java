package net.grinner117.radiantlibrary.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.TickEvent;

public interface ITimedEvent {

	default void tickEvent(TickEvent event){
		tick(event.side.isServer());
	}

	void tick(boolean serverSide);

	boolean isExpired();

	// Methods for sending a timed event as a packet to the client side
	default CompoundTag serialize(CompoundTag tag) {
		if (getID().isEmpty())
			throw new IllegalStateException("Serialize without ID");
		tag.putString("id", getID());
		return tag;
	}


	default Void onPacketHandled() {
		//Client sided
		EventQueue.getClientQueue().addEvent(this);
		return null;
	}

	default String getID() {
		return "";
	}
}