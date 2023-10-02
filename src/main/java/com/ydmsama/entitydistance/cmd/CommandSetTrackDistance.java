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

public class CommandSetTrackDistance extends CommandBase {

    @Override
    public String getName() {
        return "settrackdistance";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/settrackdistance <value>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            sender.sendMessage(new TextComponentString("Invalid arguments! Usage: " + this.getUsage(sender)));
            return;
        }

        double value = Double.parseDouble(args[0]);
        ModConfig.trackDistanceMultiplier = value;
        ConfigChangedEvent.OnConfigChangedEvent event = new ConfigChangedEvent.OnConfigChangedEvent(EntityDistance.MOD_ID, null, false, false);
        MinecraftForge.EVENT_BUS.post(event);
        sender.sendMessage(new TextComponentString("Track Distance Multiplier set to " + value));
    }
}