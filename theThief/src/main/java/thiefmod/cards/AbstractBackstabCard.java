package thiefmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thiefmod.CardIgnore;
import thiefmod.powers.Common.BackstabPower;

@CardIgnore
public abstract class AbstractBackstabCard extends CustomCard {

    public int backstabNumber;
    public int baseBackstabNumber;
    public boolean upgradedBackstabNumber;
    public boolean isBackstabNumberModified;

    public AbstractBackstabCard(final String id, final String name, final String img, final int cost,
                                final String rawDescription,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardColor color,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        isCostModified = false;
        isCostModifiedForTurn = false;

        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isBackstabNumberModified = false;
    }

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedBackstabNumber) {
            backstabNumber = baseBackstabNumber;
            isBackstabNumberModified = true;
        }
    }


    public void upgradeBackstabNumber(int amount) {
        super.displayUpgrades();
        baseBackstabNumber += amount;
        backstabNumber = baseBackstabNumber;
        upgradedBackstabNumber = true;
    }

    public static boolean canBackstab() {
        if (AbstractDungeon.player.cardsPlayedThisTurn < 2) {
            return true;
        } else if (AbstractDungeon.player.hasPower(BackstabPower.POWER_ID)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getAssets() {
        return "theThiefAssets/images/cards/";
    }

    public static String getAssetsBeta() {
        return "theThiefAssets/images/cards/beta";
    }
}