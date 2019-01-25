package thiefmod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.SmilingMask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thiefmod.ThiefMod;

public class ShadowMask extends CustomRelic {
    // ID, images, stats.
    public static final String ID = ThiefMod.makeID("ThievesMask");
    public static final String IMG = "thiefmodAssets/images/relics/ShadowMask.png";
    public static final String OUTLINE ="thiefmodAssets/images/relics/outline/ShadowMask.png";
    public static final int DISCOUNT = 100;
    public static final Logger logger = LogManager.getLogger(ThiefMod.class.getName());

    public ShadowMask() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.STARTER, LandingSound.MAGICAL);
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip("Synergy",
                DESCRIPTIONS[1] + FontHelper.colorString(new SmilingMask().name, "y") + DESCRIPTIONS[2]));
        initializeTips();
    }

    // Flash at the start of Battle.
    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, 1), 1));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + new SmilingMask().name + ".";
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new ShadowMask();
    }
}
