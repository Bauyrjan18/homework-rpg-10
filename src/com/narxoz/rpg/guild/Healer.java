package com.narxoz.rpg.guild;

public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        System.out.println("[OUT] " + getName() + " (Healer) readies aid: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String senderName = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[IN] " + getName() + " (Healer) responds to [" + topic + "] from " + senderName + ": Gathering bandages for -> " + payload);
    }
}
