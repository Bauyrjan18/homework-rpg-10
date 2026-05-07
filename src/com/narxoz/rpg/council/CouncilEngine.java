package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;

        GuildHall guildHall = (GuildHall) hall;
        int initialRouted = guildHall.getTotalRouted();
        int initialNotified = guildHall.getTotalNotified();

        System.out.println("\n=== CouncilEngine: War Council Session Begins ===");
        System.out.println("Assessing party capability with " + party.size() + " heroes.");

        System.out.println("\n--- Pass 1: Reviewing HIGH/URGENT Priority Quests ---");
        QuestIterator priorityIt = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priorityIt.hasNext()) {
            Quest q = priorityIt.next();
            questsTraversed++;
            System.out.println("Focus on: " + q.getTitle());
            hall.dispatch("orders", null, "Commanders, plan strategy for " + q.getTitle());

            if (q.isUrgent()) {
                hall.dispatch("urgent", null, "Emergency prep for " + q.getTitle());
            }
        }

        System.out.println("\n--- Pass 2: Checking standard backlog & supplies ---");
        QuestIterator orderedIt = questLog.ordered();
        while (orderedIt.hasNext()) {
            Quest q = orderedIt.next();
            questsTraversed++;

            if (q.getTitle().toLowerCase().contains("curse")) {
                hall.dispatch("curse", null, "Investigate magical anomaly in " + q.getTitle());
            } else {
                hall.dispatch("supplies", null, "Standard gear check for " + q.getTitle());
            }
        }

        int messagesRouted = guildHall.getTotalRouted() - initialRouted;
        int membersNotified = guildHall.getTotalNotified() - initialNotified;

        System.out.println("=== CouncilEngine: War Council Session Ends ===\n");

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}
