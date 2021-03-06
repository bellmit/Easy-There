package com.nexorel.et.content.skills;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.nexorel.et.Reference.MODID;

public class SkillScreen extends Screen {

    public static final int WIDTH = 252;
    public static final int HEIGHT = 139;
    SkillTabGUI skillTabGUI = new SkillTabGUI(this);
    private boolean isScrolling;
    private final ResourceLocation GUI = new ResourceLocation(MODID, "textures/gui/skill_window.png");
    public static ResourceLocation SKILL_ASSETS_LOC = new ResourceLocation(MODID, "textures/gui/skill_window.png");
    private final Player player;

    public SkillScreen() {
        super(new TranslatableComponent("screen.et.skills"));
        this.player = Minecraft.getInstance().player;
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new SkillScreen());
    }

    @Override
    protected void init() {
        this.skillTabGUI.init();
    }

    @Override
    public void tick() {
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        this.renderBackground(matrixStack);
        renderInside(matrixStack, mouseX, mouseY, relX, relY);
        renderWindow(matrixStack, relX, relY);
        renderTooltips(matrixStack, relX, relY);
    }

    private void renderInside(PoseStack matrixStack, int mouseX, int mouseY, int x, int y) {
        SkillTabGUI skillTabGUI = this.skillTabGUI;
        if (skillTabGUI == null) {
            fill(matrixStack, x + 9, y + 18, x + 9 + 234, y + 18 + 113, -16777216);
        } else {
            matrixStack.pushPose();
            matrixStack.translate((float) (x + 9), (float) (y + 18), 0.0F);
            skillTabGUI.drawContents(matrixStack, mouseX, mouseY);
            matrixStack.popPose();
            RenderSystem.depthFunc(515);
            RenderSystem.disableDepthTest();
        }
    }

    public void renderWindow(PoseStack matrixStack, int relX, int relY) {
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, GUI);
        this.blit(matrixStack, relX, relY, 0, 0, 252, 140);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();

        this.font.draw(matrixStack, "Skills", (float) (relX + 8), (float) (relY + 6), 4210752);
    }

    public void renderTooltips(PoseStack matrixStack, int x, int y) {
        matrixStack.translate((float) (x + 9), (float) (y + 18), 0.0F);
        this.skillTabGUI.drawTooltips(matrixStack, x, y);
    }

    @Override
    public boolean mouseDragged(double p_231045_1_, double p_231045_3_, int p_231045_5_, double p_231045_6_, double p_231045_8_) {
        if (p_231045_5_ != 0) {
            this.isScrolling = false;
            return false;
        } else {
            if (!this.isScrolling) {
                this.isScrolling = true;
            } else {
                this.skillTabGUI.scroll(p_231045_6_, p_231045_8_);
            }

            return true;
        }
    }

    public enum Skills {
        COMBAT("skill.combat", new ItemStack(Items.DIAMOND_SWORD)),
        MINING("skill.mining", new ItemStack(Items.GOLDEN_PICKAXE)),
        FORAGING("skill.foraging", new ItemStack(Items.STONE_AXE)),
        FARMING("skill.farming", new ItemStack(Items.CARROT));
        protected static final SkillScreen.Skills[] VALUES = values();
        final String name;
        final ItemStack renderStack;

        Skills(String name, ItemStack renderStack) {
            this.name = name;
            this.renderStack = renderStack;
        }

        public String getName() {
            return this.name;
        }

        public ItemStack getRenderStack() {
            return this.renderStack;
        }

        public void drawIcon(ItemRenderer itemRenderer, int x, int y) {
            itemRenderer.renderAndDecorateFakeItem(this.renderStack, x, y);
        }
    }
}
