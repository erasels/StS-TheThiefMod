package thiefmod.powers.Unique;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnCardDrawPower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thiefmod.ThiefMod;
import thiefmod.util.TextureLoader;

// Empty Base

public class VoidboundPower extends AbstractPower implements OnCardDrawPower {
    public AbstractCreature source;

    public static final String POWER_ID = ThiefMod.makeID("VoidboundPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theThiefAssets/images/powers/84/VoidboundPower.png");
    private static final Texture tex32 = TextureLoader.getTexture("theThiefAssets/images/powers/32/VoidboundPower.png");


    public VoidboundPower(AbstractPlayer owner, AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        type = PowerType.BUFF;
        isTurnBased = false;

        this.owner = owner;
        this.source = source;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {

    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.cardID.equals(VoidCard.ID)) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
        }
    }


    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}


