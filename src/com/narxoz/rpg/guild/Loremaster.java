package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {
    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        System.out.println("[OUT] " + getName() + " (Loremaster) shares wisdom: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String senderName = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[IN] " + getName() + " (Loremaster) archives [" + topic + "] from " + senderName + ": Searching ancient texts for -> " + payload);
    }
}