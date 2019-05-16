package thiefmod.variabls;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thiefmod.cards.abstracts.AbstractBackstabCard;
import thiefmod.cards.abstracts.AbstractThiefCard;

public class BackstabDamage extends DynamicVariable {

    @Override
    public String key() {
        return "theThief:BackstabTimesDamage";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return card.isDamageModified;
//        return ((AbstractThiefCard) card).isBackstabNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractThiefCard) card).backstabNumber * card.damage;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractThiefCard) card).baseBackstabNumber * card.baseDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return card.upgradedDamage;
//      return ((AbstractThiefCard) card).upgradedBackstabNumber;
    }
}