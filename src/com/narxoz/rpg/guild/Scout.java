package com.narxoz.rpg.guild;

public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        System.out.println("[OUT] " + getName() + " (Scout) reports: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String senderName = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[IN] " + getName() + " (Scout) notes [" + topic + "] from " + senderName + ": Updating maps for -> " + payload);
    }
}
