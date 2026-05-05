package com.narxoz.rpg.guild;

public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        System.out.println("[OUT] " + getName() + " (Quartermaster) requests: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String senderName = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[IN] " + getName() + " (Quartermaster) logs [" + topic + "] from " + senderName + ": Checking inventory for -> " + payload);
    }
}
