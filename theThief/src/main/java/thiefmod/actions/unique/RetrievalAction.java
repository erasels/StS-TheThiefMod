package thiefmod.actions.unique;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thiefmod.ThiefMod;
import thiefmod.actions.common.DrawPileToHandAction;
import thiefmod.actions.common.ExhaustToHandAction;
import thiefmod.actions.common.LimboToHandAction;

import java.util.ArrayList;

public class RetrievalAction extends AbstractGameAction {

    private static final Logger logger = LogManager.getLogger(ThiefMod.class.getName());

    private ArrayList<AbstractCard> cardsToReturn;
    private int returnAmount;
    private static int check = 0;

    public RetrievalAction(final ArrayList<AbstractCard> cardsToReturn, final int returnAmount) {
        this.cardsToReturn = cardsToReturn;
    }

    @Override
    public void update() {

        logger.info("Update start");
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
        logger.info("Waited out");

        logger.info("cardsToRetun inside the action is " + cardsToReturn);

        for (AbstractCard iterateCard : cardsToReturn) {
            logger.info("For loop started");

            check++;
            // I was now told StSlib has a MoveCardsAction. It is too late. I made all these actions. I don't wanna redo em.
            stopCheck();
            AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(iterateCard));
            logger.info("Discard to hand added.");
            stopCheck();
            AbstractDungeon.actionManager.addToBottom(new DrawPileToHandAction(iterateCard));
            logger.info("Draw to hand added.");
            stopCheck();
            AbstractDungeon.actionManager.addToBottom(new ExhaustToHandAction(iterateCard));
            logger.info("Exhaust to hand added.");
            stopCheck();
            AbstractDungeon.actionManager.addToBottom(new LimboToHandAction(iterateCard));
            logger.info("Limbo to hand added.");

            if (check >= returnAmount) {
                isDone = true;
            }

        }
        logger.info("RetrievalAction is done.");

        isDone = true;
    }

    private void stopCheck() {
        if (AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE) {
            AbstractDungeon.player.createHandIsFullDialog();
            isDone = true;
        }
    }
}
