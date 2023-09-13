package net.grinner117.radiantlibrary.event;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class BounceEvent implements ITimedEvent {
	LivingEntity livingEntity;
	int duration;
	double oldY;

	public BounceEvent(LivingEntity e, double oldY) {
		this.livingEntity = e;
		this.oldY = oldY;
	}

	public void tick(boolean serverSide) {
		duration++;
		if (duration == 1) {
			double f = 0.91d + 0.025d;
			Vec3 vec3d = this.livingEntity.getDeltaMovement();
			this.livingEntity.setDeltaMovement(vec3d.x / f, oldY, vec3d.z / f);
			this.livingEntity.hurtMarked = true;
		}
	}

	public boolean isExpired() {
		return duration >= 1;
	}
}
