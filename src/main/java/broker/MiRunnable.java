package broker;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MiRunnable implements Runnable{

    @Override
    public void run() {
        try {
            ComunicadorMqtt brokerPublicador=new ComunicadorMqtt("tcp://broker.hivemq.com:1883","id-cliente");
            brokerPublicador.publicar("un-topic","un mensaje",1);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }
}
