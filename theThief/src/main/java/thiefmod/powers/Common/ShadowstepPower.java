package thiefmod.powers.Common;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thiefmod.ThiefMod;
import thiefmod.patches.ThiefCardTags;
import thiefmod.powers.Common.BackstabPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

// Empty Base

public class ShadowstepPower extends AbstractPower {

    public AbstractCreature source;

    public static final String POWER_ID = ThiefMod.makeID("ShadowstepPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = ThiefMod.makePath(ThiefMod.COMMON_POWER);


    public ShadowstepPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.img = new Texture(IMG);
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
        return (damageAmount / 10) * (10 - this.amount);
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
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }


}

