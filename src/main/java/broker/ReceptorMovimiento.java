package broker;

import domain.objetos.Heladera;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import repositorio.IRepoHeladera;

public class ReceptorMovimiento implements IMqttMessageListener {
    private IRepoHeladera repoHeladera;
    @Override
    public void messageArrived(String topic, MqttMessage mensajeMovimiento) throws Exception {
// Extraer el ID de la heladera del topic
        String[] topicParts = topic.split("/");
        String heladeraId = topicParts[1]; // "heladeras/{id}/alertas/{tipo}"
        Heladera heladera=repoHeladera.getById(heladeraId);
        heladera.getSensorMovimiento().procesarAlerta();
        System.out.println("Mensaje recibido de topic: " + topic );

    }
}
