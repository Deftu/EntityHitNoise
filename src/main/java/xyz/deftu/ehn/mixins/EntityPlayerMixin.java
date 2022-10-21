package xyz.deftu.ehn.mixins;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.ehn.AttackHandler;

@Mixin({EntityPlayer.class})
public class EntityPlayerMixin {
    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;getEntityAttribute(Lnet/minecraft/entity/ai/attributes/IAttribute;)Lnet/minecraft/entity/ai/attributes/IAttributeInstance;", shift = At.Shift.BEFORE))
    private void ehn$onEntityAttacked(Entity target, CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (!(player instanceof EntityPlayerSP))
            return;

        AttackHandler.handle(target);
    }
}
