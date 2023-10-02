package com.ydmsama.entitydistance.cmd;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandEntityListAdd extends CommandBase {

    @Override
    public String getName() {
        return "entitylistadd";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/entitylistadd <entityID>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            sender.sendMessage(new TextComponentString("Invalid arguments! Usage: " + this.getUsage(sender)));
            return;
        }

        String entityId = args[0];
        List<String> entities = new ArrayList<>(Arrays.asList(ModConfig.entityList));
        if (!entities.contains(entityId)) {
            entities.add(entityId);
            ModConfig.entityList = entities.toArray(new String[0]);
            sender.sendMessage(new TextComponentString(entityId + " added to entity list."));
        } else {
            sender.sendMessage(new TextComponentString(entityId + " already exists in the entity list."));
        }
        ConfigChangedEvent.OnConfigChangedEvent event = new ConfigChangedEvent.OnConfigChangedEvent(EntityDistance.MOD_ID, null, false, false);
        MinecraftForge.EVENT_BUS.post(event);
    }
}
