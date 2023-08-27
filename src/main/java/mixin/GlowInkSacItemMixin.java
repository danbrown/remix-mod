package mixin;

import com.dannbrown.remix.init.DatagenBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.GlowInkSacItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(GlowInkSacItem.class)
public class GlowInkSacItemMixin extends Item {
  public GlowInkSacItemMixin(Item.Properties properties) {
    super(properties);
  }

  private static final Supplier<BiMap<Block, Block>> GLOWABLES =
          Suppliers.memoize(() ->
                  ImmutableBiMap.<Block, Block>builder()
                          // wool blocks
                          .put(Blocks.WHITE_WOOL, DatagenBlocks.INSTANCE.getGLOW_WOOL().get())
                          .put(Blocks.ORANGE_WOOL, DatagenBlocks.INSTANCE.getGLOW_ORANGE_WOOL().get())
                          .put(Blocks.MAGENTA_WOOL, DatagenBlocks.INSTANCE.getGLOW_MAGENTA_WOOL().get())
                          .put(Blocks.LIGHT_BLUE_WOOL, DatagenBlocks.INSTANCE.getGLOW_LIGHT_BLUE_WOOL().get())
                          .put(Blocks.YELLOW_WOOL, DatagenBlocks.INSTANCE.getGLOW_YELLOW_WOOL().get())
                          .put(Blocks.LIME_WOOL, DatagenBlocks.INSTANCE.getGLOW_LIME_WOOL().get())
                          .put(Blocks.PINK_WOOL, DatagenBlocks.INSTANCE.getGLOW_PINK_WOOL().get())
                          .put(Blocks.GRAY_WOOL, DatagenBlocks.INSTANCE.getGLOW_GRAY_WOOL().get())
                          .put(Blocks.LIGHT_GRAY_WOOL, DatagenBlocks.INSTANCE.getGLOW_LIGHT_GRAY_WOOL().get())
                          .put(Blocks.CYAN_WOOL, DatagenBlocks.INSTANCE.getGLOW_CYAN_WOOL().get())
                          .put(Blocks.PURPLE_WOOL, DatagenBlocks.INSTANCE.getGLOW_PURPLE_WOOL().get())
                          .put(Blocks.BLUE_WOOL, DatagenBlocks.INSTANCE.getGLOW_BLUE_WOOL().get())
                          .put(Blocks.BROWN_WOOL, DatagenBlocks.INSTANCE.getGLOW_BROWN_WOOL().get())
                          .put(Blocks.GREEN_WOOL, DatagenBlocks.INSTANCE.getGLOW_GREEN_WOOL().get())
                          .put(Blocks.RED_WOOL, DatagenBlocks.INSTANCE.getGLOW_RED_WOOL().get())
                          .put(Blocks.BLACK_WOOL, DatagenBlocks.INSTANCE.getGLOW_BLACK_WOOL().get())
                          // carpet blocks
                          .put(Blocks.WHITE_CARPET, DatagenBlocks.INSTANCE.getGLOW_CARPET().get())
                          .put(Blocks.ORANGE_CARPET, DatagenBlocks.INSTANCE.getGLOW_ORANGE_CARPET().get())
                          .put(Blocks.MAGENTA_CARPET, DatagenBlocks.INSTANCE.getGLOW_MAGENTA_CARPET().get())
                          .put(Blocks.LIGHT_BLUE_CARPET, DatagenBlocks.INSTANCE.getGLOW_LIGHT_BLUE_CARPET().get())
                          .put(Blocks.YELLOW_CARPET, DatagenBlocks.INSTANCE.getGLOW_YELLOW_CARPET().get())
                          .put(Blocks.LIME_CARPET, DatagenBlocks.INSTANCE.getGLOW_LIME_CARPET().get())
                          .put(Blocks.PINK_CARPET, DatagenBlocks.INSTANCE.getGLOW_PINK_CARPET().get())
                          .put(Blocks.GRAY_CARPET, DatagenBlocks.INSTANCE.getGLOW_GRAY_CARPET().get())
                          .put(Blocks.LIGHT_GRAY_CARPET, DatagenBlocks.INSTANCE.getGLOW_LIGHT_GRAY_CARPET().get())
                          .put(Blocks.CYAN_CARPET, DatagenBlocks.INSTANCE.getGLOW_CYAN_CARPET().get())
                          .put(Blocks.PURPLE_CARPET, DatagenBlocks.INSTANCE.getGLOW_PURPLE_CARPET().get())
                          .put(Blocks.BLUE_CARPET, DatagenBlocks.INSTANCE.getGLOW_BLUE_CARPET().get())
                          .put(Blocks.BROWN_CARPET, DatagenBlocks.INSTANCE.getGLOW_BROWN_CARPET().get())
                          .put(Blocks.GREEN_CARPET, DatagenBlocks.INSTANCE.getGLOW_GREEN_CARPET().get())
                          .put(Blocks.RED_CARPET, DatagenBlocks.INSTANCE.getGLOW_RED_CARPET().get())
                          .put(Blocks.BLACK_CARPET, DatagenBlocks.INSTANCE.getGLOW_BLACK_CARPET().get())
                          .build());

  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    BlockPos blockpos = context.getClickedPos();
    BlockState currentBlock = level.getBlockState(blockpos);

    return getGlowable(currentBlock).map((blockState) -> {
      Player player = context.getPlayer();
      ItemStack itemstack = context.getItemInHand();

      if (player instanceof ServerPlayer) {
        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
      }

      itemstack.shrink(1);
      level.setBlock(blockpos, blockState, 11);
      level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockState));

      // play sound and particles
      level.playSound(player, blockpos, blockState.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
      var particleAmount = 18;
      for (int i = 0; i < particleAmount; i++) {
        // randomly spread particles around the block
        var x = blockpos.getX() + 1.2D + (level.random.nextDouble() - 1.2D) * 1.2D;
        var y = blockpos.getY() + 1.2D + (level.random.nextDouble() - 1.2D) * 1.2D;
        var z = blockpos.getZ() + 1.2D + (level.random.nextDouble() - 1.2D) * 1.2D;
        level.addParticle(ParticleTypes.GLOW, x, y, z, 0.0D, 0.0D, 0.0D);
      }
      return InteractionResult.sidedSuccess(level.isClientSide);
    }).orElse(super.useOn(context));

  }

  private static Optional<BlockState> getGlowable(BlockState currentBlock) {
    return Optional.ofNullable(
            GLOWABLES.get().get(currentBlock.getBlock())).map((block) -> block.withPropertiesOf(currentBlock));
  }


}