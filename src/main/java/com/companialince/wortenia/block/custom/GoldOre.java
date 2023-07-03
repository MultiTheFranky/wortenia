package com.companialince.wortenia.block.custom;

import com.companialince.wortenia.Wortenia;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class GoldOre extends Block {

    private final int minTimeSeconds = 60;
    private final int maxTimeSeconds = 5 * 60; // 5 minutes
    private final int respawnTime = new Random().nextInt((maxTimeSeconds - minTimeSeconds) + 1) + minTimeSeconds;

    public GoldOre(Block.Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, @Nullable TileEntity pTe, ItemStack pStack) {
        pPlayer.awardStat(Stats.BLOCK_MINED.get(this));
        pPlayer.causeFoodExhaustion(0.005F);
        dropResources(pState, pLevel, pPos, pTe, pPlayer, pStack);
        if (!pLevel.isClientSide) {
            // Wait the respawn time before respawning the block
            new Thread(() -> {
                try {
                    Thread.sleep(respawnTime * 1000L);
                    try {
                        pLevel.setBlock(pPos, pState, 2);
                    } catch (Exception e) {
                        Wortenia.LOGGER.info("Respawning block on " + pPos.toString());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
