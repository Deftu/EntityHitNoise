package xyz.deftu.ehn

import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler

object EntityHitNoiseCommand : Command(EntityHitNoise.ID) {
    @DefaultHandler fun execute() =
        EssentialAPI.getGuiUtil().openScreen(EntityHitNoiseConfig.gui())
}
