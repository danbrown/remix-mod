package mixin;

import com.dannbrown.remix.init.DatagenBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(PotionItem.class)
public class PotionItemMixin extends Item {

  PotionItemMixin(Properties properties) {
    super(properties);
  }

  private static final Supplier<BiMap<Block, Block>> WASHABLES =
          Suppliers.memoize(() ->
                  ImmutableBiMap.<Block, Block>builder()
                          // wool blocks
                          .put(DatagenBlocks.INSTANCE.getGLOW_WOOL().get(), Blocks.WHITE_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_ORANGE_WOOL().get(), Blocks.ORANGE_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_MAGENTA_WOOL().get(), Blocks.MAGENTA_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_LIGHT_BLUE_WOOL().get(), Blocks.LIGHT_BLUE_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_YELLOW_WOOL().get(), Blocks.YELLOW_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_LIME_WOOL().get(), Blocks.LIME_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_PINK_WOOL().get(), Blocks.PINK_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_GRAY_WOOL().get(), Blocks.GRAY_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_LIGHT_GRAY_WOOL().get(), Blocks.LIGHT_GRAY_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_CYAN_WOOL().get(), Blocks.CYAN_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_PURPLE_WOOL().get(), Blocks.PURPLE_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_BLUE_WOOL().get(), Blocks.BLUE_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_BROWN_WOOL().get(), Blocks.BROWN_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_GREEN_WOOL().get(), Blocks.GREEN_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_RED_WOOL().get(), Blocks.RED_WOOL)
                          .put(DatagenBlocks.INSTANCE.getGLOW_BLACK_WOOL().get(), Blocks.BLACK_WOOL)
                          // carpet blocks
                          .put(DatagenBlocks.INSTANCE.getGLOW_CARPET().get(), Blocks.WHITE_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_ORANGE_CARPET().get(), Blocks.ORANGE_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_MAGENTA_CARPET().get(), Blocks.MAGENTA_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_LIGHT_BLUE_CARPET().get(), Blocks.LIGHT_BLUE_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_YELLOW_CARPET().get(), Blocks.YELLOW_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_LIME_CARPET().get(), Blocks.LIME_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_PINK_CARPET().get(), Blocks.PINK_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_GRAY_CARPET().get(), Blocks.GRAY_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_LIGHT_GRAY_CARPET().get(), Blocks.LIGHT_GRAY_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_CYAN_CARPET().get(), Blocks.CYAN_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_PURPLE_CARPET().get(), Blocks.PURPLE_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_BLUE_CARPET().get(), Blocks.BLUE_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_BROWN_CARPET().get(), Blocks.BROWN_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_GREEN_CARPET().get(), Blocks.GREEN_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_RED_CARPET().get(), Blocks.RED_CARPET)
                          .put(DatagenBlocks.INSTANCE.getGLOW_BLACK_CARPET().get(), Blocks.BLACK_CARPET)
                          .build());

  @Inject(method = {"useOn"}, at = {@At("TAIL")}, cancellable = true, require = 1)
  public void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
    ItemStack itemstack = context.getItemInHand();
    Level level = context.getLevel();
    BlockPos blockpos = context.getClickedPos();
    BlockState currentBlock = level.getBlockState(blockpos);
    Player player = context.getPlayer();

    if (PotionUtils.getPotion(itemstack) == Potions.WATER) {
      getWashable(currentBlock).map((blockState) -> {
        if (player instanceof ServerPlayer) {
          CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
        }

        player.setItemInHand(context.getHand(), ItemUtils.createFilledResult(itemstack, player, new ItemStack(Items.GLASS_BOTTLE)));
        level.setBlock(blockpos, blockState, 11);
        level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockState));

        // play sound and particles
        level.playSound(player, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        var particleAmount = 18;
        for (int i = 0; i < particleAmount; i++) {
          // randomly spread particles around the block
          var x = blockpos.getX() + 1.2D + (level.random.nextDouble() - 1.2D) * 1.2D;
          var y = blockpos.getY() + 1.2D + (level.random.nextDouble() - 1.2D) * 1.2D;
          var z = blockpos.getZ() + 1.2D + (level.random.nextDouble() - 1.2D) * 1.2D;
          level.addParticle(ParticleTypes.SPLASH, x, y, z, 0.0D, 0.0D, 0.0D);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
      }).orElse(super.useOn(context));
    }
  }

  private static Optional<BlockState> getWashable(BlockState currentBlock) {
    return Optional.ofNullable(
            WASHABLES.get().get(currentBlock.getBlock())).map((block) -> block.withPropertiesOf(currentBlock));
  }
}
