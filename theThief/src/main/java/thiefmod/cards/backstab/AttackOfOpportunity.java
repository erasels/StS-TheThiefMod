package thiefmod.cards.backstab;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thiefmod.ThiefMod;
import thiefmod.cards.AbstractBackstabCard;
import thiefmod.patches.Character.AbstractCardEnum;
import thiefmod.patches.Character.ThiefCardTags;
import thiefmod.powers.Common.BackstabPower;

import java.util.ArrayList;
import java.util.List;

public class AttackOfOpportunity extends AbstractBackstabCard {


    // TEXT DECLARATION

    public static final String ID = ThiefMod.makeID("AttackOfOpportunity");
    public static final String IMG = ThiefMod.makePath(ThiefMod.DEFAULT_UNCOMMON_ATTACK);
    public static final CardColor COLOR = AbstractCardEnum.THIEF_GRAY;

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("theThief:FlavorText");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String FLAVOR_STRINGS[] = uiStrings.TEXT;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;


    // /TEXT DECLARATION/


    // STAT DECLARATION
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;

    private static final int DAMAGE = 16;

    private static final int MAGIC = 1; // Card draw
    private static final int UPGRADED_PLUS_MAGIC = 1;

    private static final int BACKSTAB = 2; // Voids
    private static final int UPGRADED_PLUS_BACKSTAB = -1;


    // /STAT DECLARATION/


    public AttackOfOpportunity() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        backstabNumber = baseBackstabNumber = BACKSTAB;

        tags.add(ThiefCardTags.BACKSTAB);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        final int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();

        // Deal 16 Damage
        AbstractDungeon.actionManager.addToBottom(new DamageAction(
                m, new DamageInfo(p, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        // Add voids to your draw pile
        if (backstabNumber != 0) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), backstabNumber, true, true));
        }

        // Backstab - Draw 1 card.
        if (count <= 1) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
        }

    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (backstabNumber == 1) {
            if (magicNumber == 1) {
                if (AbstractDungeon.player.cardsPlayedThisTurn == 0 || AbstractDungeon.player.hasPower(BackstabPower.POWER_ID)) {
                    rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
                } else {
                    rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
                }
            } else {
                if (AbstractDungeon.player.cardsPlayedThisTurn == 0 || AbstractDungeon.player.hasPower(BackstabPower.POWER_ID)) {
                    rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
                } else {
                    rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[6];
                }
            }
        } else {
            if (magicNumber == 1) {
                if (AbstractDungeon.player.cardsPlayedThisTurn == 0 || AbstractDungeon.player.hasPower(BackstabPower.POWER_ID)) {
                    rawDescription = EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3];
                } else {
                    rawDescription = EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[4];
                }
            } else {
                if (AbstractDungeon.player.cardsPlayedThisTurn == 0 || AbstractDungeon.player.hasPower(BackstabPower.POWER_ID)) {
                    rawDescription = EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[5];
                } else {
                    rawDescription = EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[6];
                }
            }
        }
        initializeDescription();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(FLAVOR_STRINGS[0], EXTENDED_DESCRIPTION[0]));
        return tips;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_PLUS_MAGIC);
            upgradeBackstabNumber(UPGRADED_PLUS_BACKSTAB);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}