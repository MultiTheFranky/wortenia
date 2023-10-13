package com.companialince.wortenia.block.custom;

import com.companialince.wortenia.Wortenia;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GoldOre extends DropExperienceBlock {

    private final int minTimeSeconds = 60; // 1 minute
    private final int maxTimeSeconds = 5 * 60; // 5 minutes
    private final int respawnTime = new Random().nextInt((maxTimeSeconds - minTimeSeconds) + 1) + minTimeSeconds;

    public GoldOre(Block.Properties properties,  IntProvider pXpRange) {
        super(properties, pXpRange);
    }

    @Override
    public void destroy(@NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        super.destroy(pLevel, pPos, pState);
        if (!pLevel.isClientSide()) {
            // Wait the respawn time before respawning the block
            new Thread(() -> {
                try {
                    Thread.sleep(respawnTime * 1000L);
                    try {
                        pLevel.setBlock(pPos, pState, 2);
                    } catch (Exception e) {
                        Wortenia.LOGGER.info("Respawning block on " + pPos);
                    }
                } catch (InterruptedException e) {
                    Wortenia.LOGGER.info("Interrupted exception on " + pPos);
                }
            }).start();
        }
    }
}
