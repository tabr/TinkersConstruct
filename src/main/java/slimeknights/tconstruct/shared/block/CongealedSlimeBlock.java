package slimeknights.tconstruct.shared.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CongealedSlimeBlock extends Block {

  private static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 10, 16);
  public CongealedSlimeBlock(Properties properties) {
    super(properties);
  }

  @Deprecated
  @Override
  public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }

  @Override
  public void onLanded(IBlockReader worldIn, Entity entityIn) {
    if (!(entityIn instanceof LivingEntity) && !(entityIn instanceof ItemEntity)) {
      super.onLanded(worldIn, entityIn);
      // this is mostly needed to prevent XP orbs from bouncing. which completely breaks the game.
      return;
    }

    Vector3d vec3d = entityIn.getMotion();

    if (vec3d.y < -0.25D) {
      entityIn.setMotion(vec3d.x, -vec3d.y * -1.2D, vec3d.z);
      entityIn.fallDistance = 0;
      if (entityIn instanceof ItemEntity) {
        entityIn.setOnGround(false);
      }
    } else {
      super.onLanded(worldIn, entityIn);
    }
  }

  @Override
  public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
    // no fall damage on congealed slime
    entityIn.onLivingFall(fallDistance, 0.0F);
  }
}
