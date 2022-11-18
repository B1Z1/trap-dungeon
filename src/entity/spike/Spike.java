package entity.spike;

import entity.core.Entity;
import util.size.Size;

import java.util.HashMap;

public class Spike extends Entity<SpikeAnimationType> {
    public Spike(
            float x,
            float y,
            int width,
            int height
    ) {
        super(
                x,
                y,
                0,
                Size.TILES_DEFAULT_SCALE * 16,
                width,
                height,
                new HashMap<>()
        );
    }
}
