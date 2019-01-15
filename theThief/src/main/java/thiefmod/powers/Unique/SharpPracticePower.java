package thiefmod.powers.Unique;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thiefmod.ThiefMod;

// Empty Base

public class SharpPracticePower extends AbstractPower {
    private boolean isUpgraded;
    private boolean ADD_UPGRADED;
    private boolean ADD_RANDOM;
    private String ADD_LOCATION;
    private AbstractPlayer source;

    public static final String POWER_ID = ThiefMod.makeID("SharpPracticePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = ThiefMod.makePath(ThiefMod.COMMON_POWER);


    public SharpPracticePower(final AbstractCreature owner, final AbstractPlayer source, boolean isUpgraded, final int amount, boolean ADD_RANDOM, final String ADD_LOCATION, boolean ADD_UPGRADED) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.img = new Texture(IMG);
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        this.owner = owner;
        this.source = source;

        this.isUpgraded = isUpgraded;

        this.amount = amount;
        this.ADD_UPGRADED = ADD_UPGRADED;
        this.ADD_RANDOM = ADD_RANDOM;
        this.ADD_LOCATION = ADD_LOCATION;

        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new thiefmod.actions.common.StealCardAction(
                this.source, this.amount, 1, this.ADD_RANDOM, true, this.ADD_LOCATION, this.ADD_UPGRADED));
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

