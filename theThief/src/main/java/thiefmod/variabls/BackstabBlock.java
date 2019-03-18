package thiefmod.variabls;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thiefmod.cards.abstracts.AbstractBackstabCard;
import thiefmod.cards.abstracts.AbstractThiefCard;

public class BackstabBlock extends DynamicVariable {

    @Override
    public String key() {
        return "theThief:BackstabTimesBlock";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return card.isBlockModified;
//        return ((AbstractThiefCard) card).isBackstabNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractThiefCard) card).backstabNumber * card.block;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractThiefCard) card).baseBackstabNumber * card.baseBlock;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return card.upgradedBlock;
//      return ((AbstractThiefCard) card).upgradedBackstabNumber;
    }
}