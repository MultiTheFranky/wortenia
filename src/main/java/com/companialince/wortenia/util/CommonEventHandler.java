package com.companialince.wortenia.util;

import com.companialince.wortenia.Wortenia;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

import static com.companialince.wortenia.commands.PlayMusicCommand.player;

@Mod.EventBusSubscriber(modid = Wortenia.MOD_ID)
public class CommonEventHandler {

    private static final List<String> BLOCKS = Arrays.asList(
            "mcwbridges:stone_brick_bridge",
            "mcwbridges:stone_brick_bridge_stair",
            "wortenia:gravel_block"
    );
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        PlayerEntity player = event.player;

        BlockPos blockPosDown = new BlockPos(player.getX(), player.getY() - 1, player.getZ());
        BlockPos blockPosPlayer = new BlockPos(player.getX(), player.getY(), player.getZ());

        World world = player.getEntity().getCommandSenderWorld();

        IForgeBlockState blockDownState = world.getBlockState(blockPosDown);
        IForgeBlockState blockPlayerState = world.getBlockState(blockPosPlayer);

        Block blockDown = blockDownState.getBlockState().getBlock();
        Block blockPlayer = blockPlayerState.getBlockState().getBlock();

        if ((blockDown.getRegistryName() != null && BLOCKS.contains(blockDown.getRegistryName().toString()))
                || (blockPlayer.getRegistryName() != null && BLOCKS.contains(blockPlayer.getRegistryName().toString()))) {
            // If player is on animal, give speed boost to animal
            if (player.isPassenger() && player.getVehicle() instanceof AnimalEntity) {
                AnimalEntity animal = (AnimalEntity) player.getVehicle();
                animal.addEffect(new net.minecraft.potion.EffectInstance(Effects.MOVEMENT_SPEED, 20, 1));
            }else{
                // Give speed boost potion effect
                player.addEffect(new net.minecraft.potion.EffectInstance(Effects.MOVEMENT_SPEED, 20, 1));
            }
        }
    }

    @SubscribeEvent
    public void onLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        player.stopMusic();
    }

/*
    @SubscribeEvent
    public static void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Wortenia.LOGGER.info("PlayerLoggedInEvent");
        World world = event.getPlayer().getEntity().getCommandSenderWorld();
        if (!world.isClientSide()) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            player.sendMessage(new StringTextComponent("Welcome to Wortenia!"), player.getUUID());
            world.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.WORTENIA.get(), player.getSoundSource(), 1.0F, 1.0F);
        }
    }
*/
}
