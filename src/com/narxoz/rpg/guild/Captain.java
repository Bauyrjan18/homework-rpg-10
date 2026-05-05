package com.narxoz.rpg.guild;

public class Captain extends GuildMember {

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        System.out.println("[OUT] " + getName() + " (Captain) issues order: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String senderName = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[IN] " + getName() + " (Captain) heard [" + topic + "] from " + senderName + ": Preparing troops for -> " + payload);
    }
}
