package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    private int totalRouted = 0;
    private int totalNotified = 0;

    @Override
    public void register(GuildMember member) {
        String role = member.getClass().getSimpleName();

        switch (role) {
            case "Captain":
                addSubscriber("orders", member);
                addSubscriber("urgent", member);
                break;
            case "Quartermaster":
                addSubscriber("supplies", member);
                addSubscriber("rewards", member);
                break;
            case "Scout":
                addSubscriber("scouting", member);
                break;
            case "Healer":
                addSubscriber("healing", member);
                addSubscriber("urgent", member);
                break;
            case "Loremaster": // Part 4 Extension
                addSubscriber("lore", member);
                addSubscriber("curse", member);
                addSubscriber("history", member);
                break;
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        totalRouted++;
        List<GuildMember> subs = subscribersFor(topic);

        for (GuildMember sub : subs) {
            if (sub != from) {
                sub.receive(topic, from, payload);
                totalNotified++;
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
    public int getTotalRouted() { return totalRouted; }
    public int getTotalNotified() { return totalNotified; }
}
