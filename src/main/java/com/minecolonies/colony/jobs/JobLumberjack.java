package com.minecolonies.colony.jobs;

import com.minecolonies.client.render.RenderBipedCitizen;
import com.minecolonies.colony.CitizenData;
import com.minecolonies.entity.ai.EntityAIWorkLumberjack;
import com.minecolonies.entity.ai.Tree;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.nbt.NBTTagCompound;

/**
 * The Lumberjack job class
 */
public class JobLumberjack extends Job
{
    private static final String TAG_TREE = "Tree";
    /**
     * The tree this lumberjack is currently working on
     */
    public Tree tree;

    /**
     * Create a lumberjack job
     *
     * @param entity the lumberjack
     */
    public JobLumberjack(CitizenData entity)
    {
        super(entity);
    }

    /**
     * Return a Localization label for the Job
     *
     * @return localization label String
     */
    @Override
    public String getName()
    {
        return "com.minecolonies.job.Lumberjack";
    }

    /**
     * Get the RenderBipedCitizen.Model to use when the Citizen performs this job role.
     *
     * @return Model of the citizen
     */
    @Override
    public RenderBipedCitizen.Model getModel()
    {
        return RenderBipedCitizen.Model.LUMBERJACK;
    }

    /**
     * Save the Job to an NBTTagCompound
     *
     * @param compound NBTTagCompound to save the Job to
     */
    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        NBTTagCompound treeTag = new NBTTagCompound();

        if (tree != null)
        {
            tree.writeToNBT(treeTag);
        }
    }

    /**
     * Restore the Job from an NBTTagCompound
     *
     * @param compound NBTTagCompound containing saved Job data
     */
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if (compound.hasKey(TAG_TREE))
        {
            tree = Tree.readFromNBT(compound.getCompoundTag(TAG_TREE));
        }
    }

    /**
     * Override to add Job-specific AI tasks to the given EntityAITask list
     *
     * @param tasks EntityAITasks list to add tasks to
     */
    @Override
    public void addTasks(EntityAITasks tasks)
    {
        tasks.addTask(3, new EntityAIWorkLumberjack(this));
    }

}
