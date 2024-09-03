package broker;

import com.google.gson.Gson;
import domain.accesorios.SolicitudApertura;
import domain.objetos.Heladera;
import domain.personas.Humano;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Date;


public class ComunicadorMqtt {
    private String brokerUrl= "tcp://broker.hivemq.com:1883";
    private String clientId;
    private MqttClient client;

    public ComunicadorMqtt(String brokerUrl, String clientId) throws MqttException {
        this.brokerUrl = brokerUrl;
        this.clientId = clientId;
        this.client = new MqttClient(brokerUrl, clientId);
    }

    public void conectar() throws MqttException {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        client.connect(connOpts);
        System.out.println("Conectado al broker: " + brokerUrl);
    }

    public void desconectar() throws MqttException {
        client.disconnect();
        System.out.println("Desconectado del broker");
    }

    public void suscribir(String topic, IMqttMessageListener messageListener) throws MqttException {
        client.subscribe(topic, messageListener);
        System.out.println("Suscrito al topic: " + topic);
    }

    public void publicar(String topic, String mensaje, int qos) throws MqttException {
        MqttMessage message = new MqttMessage(mensaje.getBytes());
        message.setQos(qos);
        client.publish(topic, message);
        System.out.println("Mensaje publicado al topic: " + topic);
    }
    public void publicarSolicitudAperturaDeHeladera(Heladera heladera, int qos, Humano humano) throws MqttException {
        //FIXME terminar de definir si es la ubicacion lo que utilizare para identificar a las heladeras
        String topic="heladeras/"+heladera.getUbicacion()+"/solicitud-apertura";
        Date fechaHora=new Date();
        DatosSolicitudAperturaMqtt datos = new DatosSolicitudAperturaMqtt(humano.getTarjetaColaborador().getCodigo(), fechaHora);
        String mensaje = new Gson().toJson(datos);
        this.publicar(topic,mensaje,qos);
        humano.getTarjetaColaborador().getSolicitudes().add(new SolicitudApertura(heladera,fechaHora));

    }
}