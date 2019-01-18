package thiefmod.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thiefmod.ThiefMod;
import thiefmod.patches.Character.AbstractCardEnum;

import java.util.ArrayList;
import java.util.List;

public class HuntersInstinct extends AbstractBackstabCard {
//implements StartupCard
//implements ModalChoice.Callback

// TEXT DECLARATION

    public static final String ID = ThiefMod.makeID("HuntersInstinct");
    public static final String IMG = ThiefMod.makePath(ThiefMod.DEFAULT_UNCOMMON_ATTACK);
    public static final CardColor COLOR = AbstractCardEnum.THIEF_GRAY;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;


// /TEXT DECLARATION/

    // STAT DECLARATION
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    private static final int MAGIC = 3;

// /STAT DECLARATION/

    public HuntersInstinct() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.exhaust = true;

        this.magicNumber = this.baseMagicNumber = MAGIC;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int maximumHand = BaseMod.MAX_HAND_SIZE;
        int currentHand = p.hand.group.size();

        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDrawPileAction(new VoidCard(), this.magicNumber, true, true, false));

        do {
            if (!AbstractDungeon.player.drawPile.isEmpty() || !AbstractDungeon.player.discardPile.isEmpty()) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
                currentHand++;
            }
            else return;
        }
        while (currentHand < maximumHand);

    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo("Flavor Text", EXTENDED_DESCRIPTION[0]));
        // tips.addAll(modal.generateTooltips());
        return tips;
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new HuntersInstinct();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
//          this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}