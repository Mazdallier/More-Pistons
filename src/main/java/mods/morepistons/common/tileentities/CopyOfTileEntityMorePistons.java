package mods.morepistons.common.tileentities;

import static mods.morepistons.ModMorePistons.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.morepistons.common.block.BlockMorePistonsExtension;
import mods.morepistons.common.block.BlockMorePistonsRod;
import mods.morepistons.inits.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CopyOfTileEntityMorePistons extends TileEntity {
	
	public Block storedBlock = null;
	private int storedMetadata = 0;
	public int storedOrientation = 0;
	private boolean extending = false;
	private boolean shouldHeadBeRendered = false;
	public int distance = 0;
	public boolean isBlockPiston = false;
	
	private float progress = 0;
	private float lastProgress = 0;
	
	private List pushedObjects = new ArrayList();
	
	/**
	 * Consstructeur vide obligatoire pour le reload de l'entité au chargement du terrain
	 */
	public CopyOfTileEntityMorePistons () {
	}
	
	public CopyOfTileEntityMorePistons(Block block, int metadata, int orientation, boolean extending, boolean shouldHeadBeRendered, int distance, boolean isBlockPiston) {
		if (block == null) {
			log.severe ("Block strorage in null in creation TileEntiry");
		}
		
		this.storedBlock          = block;
		this.storedMetadata       = metadata;
		this.storedOrientation    = orientation;
		this.extending            = extending;
		this.shouldHeadBeRendered = shouldHeadBeRendered;
		this.distance             = distance;
		this.isBlockPiston        = isBlockPiston;
	}
	
	public int getOpened () {
		return 0;
	}
	
	public float getProgress (float par1) {
		if (par1 > 1.0F) {
			par1 = 1.0F;
		}
		return this.lastProgress + (this.progress - this.lastProgress) * par1;
	}

	/**
	 * Affiche les pistons rod
	 * 
	 * @param nb
	 */
	public void displayPistonRod(int nb) {
		this.displayPistonRod(nb, nb);
	}
	
	/**
	 * Affiche les pistons rod
	 * 
	 * @param nb
	 */
	public void displayPistonRod(int nb, int lenght) {
		
		int x = this.xCoord - (Facing.offsetsXForSide[this.storedOrientation] * lenght);
		int y = this.yCoord - (Facing.offsetsYForSide[this.storedOrientation] * lenght);
		int z = this.zCoord - (Facing.offsetsZForSide[this.storedOrientation] * lenght);
		
		log.debug("displayPistonRod : "+x+", "+y+", "+z+" nb="+nb+" lenght="+lenght);
		
		for (int i = 0; i < nb; i++) {
			x += Facing.offsetsXForSide[this.storedOrientation];
			y += Facing.offsetsYForSide[this.storedOrientation];
			z += Facing.offsetsZForSide[this.storedOrientation];
			Block block = this.worldObj.getBlock(x, y, z);
			if (
				block == null || 
				block instanceof BlockAir || 
				(
					!(block instanceof BlockPistonBase) && 
					!(block instanceof BlockPistonMoving)
				)
			) {
				this.worldObj.setBlock (x, y, z, ModBlocks.blockPistonRod, this.storedOrientation, 2);
			}
		}
	}
	

	/**
	 * Remove les pistons rod
	 * 
	 * @param nb
	 */
	public void removePistonRod(int nb) {
		this.removePistonRod(nb, nb);
	}
	
	/**
	 * Remove les pistons rod
	 * 
	 * @param nb
	 */
	public void removePistonRod(int nb, int lenght) {
		
		int x = this.xCoord + Facing.offsetsXForSide[this.storedOrientation] * (lenght + 1);
		int y = this.yCoord + Facing.offsetsYForSide[this.storedOrientation] * (lenght + 1);
		int z = this.zCoord + Facing.offsetsZForSide[this.storedOrientation] * (lenght + 1);
		
		for (int i = 0; i <= nb; i++) {
			x -= Facing.offsetsXForSide[this.storedOrientation];
			y -= Facing.offsetsYForSide[this.storedOrientation];
			z -= Facing.offsetsZForSide[this.storedOrientation];
			
			Block block = this.worldObj.getBlock(x, y, z);
			if (
				block instanceof BlockMorePistonsRod ||
				block instanceof BlockMorePistonsExtension
			) {
				this.worldObj.setBlockToAir (x, y, z);
				this.worldObj.setBlockMetadataWithNotify(x, y, z, 0, 2);
			}
		}
	}
	

	@SideOnly(Side.CLIENT)
	public float getOffsetX(float par1) {
		return this.extending ? (getProgress(par1) * this.distance - (float) this.distance) * Facing.offsetsXForSide[this.storedOrientation] : ((float) this.distance - getProgress(par1) * this.distance) * Facing.offsetsXForSide[this.storedOrientation];
	}

	@SideOnly(Side.CLIENT)
	public float getOffsetY(float par1) {
		return this.extending ? (getProgress(par1) * this.distance - (float) this.distance) * Facing.offsetsYForSide[this.storedOrientation] : ((float) this.distance - getProgress(par1) * this.distance) * Facing.offsetsYForSide[this.storedOrientation];
	}

	@SideOnly(Side.CLIENT)
	public float getOffsetZ(float par1) {
		return this.extending ? (getProgress(par1) * this.distance - (float) this.distance) * Facing.offsetsZForSide[this.storedOrientation] : ((float) this.distance - getProgress(par1) * this.distance) * Facing.offsetsZForSide[this.storedOrientation];
	}
	@SideOnly(Side.CLIENT)
		public boolean shouldRenderHead () {
		return this.shouldHeadBeRendered;
	}
	
	public boolean isExtending () {
		return this.extending;
	}
	
	public void updateEntity() {
		
		this.lastProgress = this.progress;
		
		if (this.lastProgress >= 1.0F) {
			
			updatePushedObjects(1.0F, 0.25F);
			// Fin du mouvement
			
			this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
			invalidate ();
			
			if (this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof  BlockPistonMoving) {
				

				if (this.distance < 0 || this.isBlockPiston && !this.extending) {
					this.removePistonRod(Math.abs(this.distance));
				} else if (this.isBlockPiston && this.extending) {
					this.displayPistonRod(this.distance + 1);
				}
				
				this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.storedBlock);
				this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.storedMetadata, 2);
				
				if (this.storedBlock != null) {
					if (this.storedBlock instanceof BlockMorePistonsExtension ||
						this.storedBlock instanceof BlockPistonBase
					) {
						this.storedBlock.onNeighborBlockChange(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.storedBlock);
					}
				}
			}
		} else {

			this.progress += 0.05F;

			if (this.progress >= 1.0F) {
				this.progress = 1.0F;
			}
			
			if (this.extending) {
				updatePushedObjects(this.progress, this.progress - this.lastProgress + 0.0625F);
			}
		}
	}
	
	private void updatePushedObjects(float par1, float par2) {
		if (this.extending) {
			par1 = 1.0F - par1;
		} else {
			par1 -= 1.0F;
		}

		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		
		if (this.storedBlock == null) {
			
			log.severe("Block Stored in TilEntity is null");
			
		} else {
			for (int i = 0; i <= this.distance; i++) {
				
				this.testCollisionWithOtherEntity(x, y, z, par1, par2);
				
				x -= Facing.offsetsXForSide[this.storedOrientation];
				y -= Facing.offsetsYForSide[this.storedOrientation];
				z -= Facing.offsetsZForSide[this.storedOrientation];
				
			}
		}
	}
	
	/**
	 * Test la collision au coordonnée envoyer et deplace l'entité au besoin
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	private void testCollisionWithOtherEntity(int x, int y, int z, float par1, float par2) {
		
		if (this.storedBlock == null) {
			log.severe ("Block strorage in null in creation TileEntiry");
			return;
		}
		
		AxisAlignedBB var3 = Blocks.piston_extension.func_149964_a(this.worldObj, x, y, z, this.storedBlock, par1, this.storedOrientation);

		if (var3 != null) {
			List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity) null, var3);
			
			if (!var4.isEmpty() && this.extending) {
				this.pushedObjects.addAll(var4);
				Iterator i = this.pushedObjects.iterator();

				int fX = Facing.offsetsXForSide[this.storedOrientation];
				int fY = Facing.offsetsYForSide[this.storedOrientation];
				int fZ = Facing.offsetsZForSide[this.storedOrientation];

				while (i.hasNext()) {
					Entity entity = (Entity) i.next();

					double xE = entity.posX;
					double yE = entity.posY;
					double zE = entity.posZ;
					
					switch (this.storedOrientation) {
						case 0:
						case 1:
							yE = this.yCoord + fY + par2 * fY; break;
						case 2:
						case 3:
							zE = this.zCoord + fZ + par2 * fZ; break;
						case 4:
						case 5:
							xE = this.xCoord + fX + par2 * fX; break;
					}
					
					if (!(this.worldObj.getBlock(this.xCoord + fX, this.yCoord + fY, this.zCoord + fZ) instanceof BlockPistonMoving)) {
						entity.setPosition(xE, yE, zE);
					}
				}
				
				this.pushedObjects.clear();
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		try {
			
			this.storedBlock       = Block.getBlockById(par1NBTTagCompound.getInteger("blockId"));
			this.storedMetadata    = par1NBTTagCompound.getInteger("blockData");
			this.storedOrientation = par1NBTTagCompound.getInteger("facing");
			this.extending         = par1NBTTagCompound.getBoolean("extending");
			this.distance          = par1NBTTagCompound.getInteger("distance");
			this.isBlockPiston     = par1NBTTagCompound.getBoolean("isBlockPiston");
			
			this.lastProgress = (this.progress = par1NBTTagCompound.getFloat("progress"));
		
		} catch (Exception e) {
			log.warning("Not stored tile entity : "+this.xCoord+", "+this.yCoord+", "+this.zCoord);
		}
		
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		
		if (this.storedBlock == null) {
			log.severe ("Strored Block strored in null in TileEntiry");
			par1NBTTagCompound.setInteger("blockId"  , Block.getIdFromBlock(Blocks.air));
		} else {
			par1NBTTagCompound.setInteger("blockId"  , Block.getIdFromBlock(this.storedBlock));
		}
		
		par1NBTTagCompound.setInteger("blockData"    , this.storedMetadata);
		par1NBTTagCompound.setInteger("facing"       , this.storedOrientation);
		par1NBTTagCompound.setBoolean("extending"    , this.extending);
		par1NBTTagCompound.setInteger("distance"     , this.distance);
		par1NBTTagCompound.setBoolean("isBlockPiston", this.isBlockPiston);
		par1NBTTagCompound.setFloat  ("progress"     , this.lastProgress);
		
	}

	public BlockPistonBase getBlockPiston() {
		
		BlockPistonBase piston = null;
		Block block = this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);
		
		if (piston instanceof BlockPistonBase) {
			piston = (BlockPistonBase)block;
		}
		
		return piston;
	}
}
