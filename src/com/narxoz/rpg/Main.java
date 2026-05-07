package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.ArrayList;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");
        List<Hero> party = new ArrayList<>();
        party.add(new Hero("Arthur", 100, 20, 10));
        party.add(new Hero("Merlin", 60, 100, 5, 2, 50));

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Clear Rat Cellar", QuestPriority.LOW, 15, false));
        questLog.add(new Quest("Escort the Merchant", QuestPriority.NORMAL, 50, false));
        questLog.add(new Quest("Slay the Dragon", QuestPriority.URGENT, 1000, true));
        questLog.add(new Quest("Cleanse Cursed Ruins", QuestPriority.HIGH, 400, false));
        questLog.add(new Quest("Find Lost Kitten", QuestPriority.LOW, 5, false));

        GuildHall hall = new GuildHall();
        Captain captain = new Captain("Valeria", hall);
        Quartermaster quartermaster = new Quartermaster("Thorne", hall);
        Scout scout = new Scout("Elara", hall);
        Healer healer = new Healer("Gideon", hall);
        Loremaster loremaster = new Loremaster("Balthazar", hall);

        System.out.println("--- Pre-Council Guild Chatter (Mediator Demonstration) ---");
        scout.reportRoute("urgent", "The Dragon has been spotted near the northern pass!");
        captain.issueOrder("supplies", "We need fire-resistant gear ASAP.");
        quartermaster.requestSupplies("healing", "Restocking burn salves for the expedition.");
        healer.prepareAid("lore", "Seeking past medical records on dragon fire.");
        loremaster.shareLore("orders", "Ancient scrolls say dragon scales are weakest at the underbelly.");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println("--- Open/Closed Proof: Sorting Quests by Reward (Iterator Demonstration) ---");
        QuestIterator rewardIt = new RewardSortedQuestIterator(questLog);
        while (rewardIt.hasNext()) {
            Quest q = rewardIt.next();
            System.out.println(String.format("Reward: %4d gold | Priority: %-6s | %s",
                    q.getRewardGold(), q.getPriority(), q.getTitle()));
        }
        System.out.println();

        System.out.println("--- Final Metrics ---");
        System.out.println(result);
    }
}
