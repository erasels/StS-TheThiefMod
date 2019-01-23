package thiefmod.powers.Common;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import thiefmod.ThiefMod;
import thiefmod.patches.Unique.ThiefCardTags;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;

// Empty Base

public class ShadowstepPower extends AbstractPower {

    public AbstractCreature source;
    private int focused = this.owner.getPower(FocusPower.POWER_ID).amount;

    public static final String POWER_ID = ThiefMod.makeID("ShadowstepPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = ThiefMod.makePath(ThiefMod.COMMON_POWER);


    public ShadowstepPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.img = ImageMaster.loadImage(IMG);
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        this.owner = owner;
        this.source = source;

        this.amount = amount;

        this.updateDescription();
    }

    /*
     * Play a shadowstep card.
     * Apply backstab power. Apply elusive power. (This one, rename it to elusive)
     *
     */
    @Override
    public void onInitialApplication() {
        actionManager.addToBottom(new ApplyPowerAction(this.owner, this.source,
                new BackstabPower(this.owner, this.source, this.amount), this.amount));

    }

    @Override
    public int onLoseHp(int damageAmount) {
        return (damageAmount / 10) * (10 - this.amount * (focused + 1));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(ThiefCardTags.BACKSTAB) || card.hasTag(ThiefCardTags.SHADOWSTEP)) {
            return;
        }
        actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.source, this.ID));
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount <= 0) {
            actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.source, this.ID));
        }
    }


    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            if (this.owner.hasPower(FocusPower.POWER_ID)) {
                this.description = DESCRIPTIONS[0] + this.amount * (focused + 1) + DESCRIPTIONS[2];
            } else {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
            }
        } else if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount * (focused + 1) + DESCRIPTIONS[2];
        }
    }


}


