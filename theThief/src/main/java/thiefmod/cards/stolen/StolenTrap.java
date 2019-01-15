package thiefmod.cards.stolen;

import com.megacrit.cardcrawl.actions.unique.SetupAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import thiefmod.ThiefMod;

public class StolenTrap extends CustomCard {

	
	
// TEXT DECLARATION 
	
	public static final String ID = thiefmod.ThiefMod.makeID("StolenTrap");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = ThiefMod.makePath(ThiefMod.DEFAULT_COMMON_ATTACK);

	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = CardColor.COLORLESS;

	private static final int COST = 1;	
	private static final int UPGRADE_COST = 0;	

	
// /STAT DECLARATION/
	
	public StolenTrap() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.exhaust = true;
	}
	
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SetupAction());
    }
		
	
    @Override
    public AbstractCard makeCopy() {
        return new StolenTrap();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        	this.upgradeBaseCost(UPGRADE_COST);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}