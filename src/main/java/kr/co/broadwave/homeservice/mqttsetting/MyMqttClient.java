package kr.co.broadwave.homeservice.mqttsetting;

import org.eclipse.paho.client.mqttv3.*;

public class MyMqttClient implements MqttCallback {

    private MqttClient client;
    private MqttConnectOptions option;

    public MyMqttClient init(String userName, String password, String serverURI, String clientId){

        option = new MqttConnectOptions();
        option.setCleanSession(true);
        option.setKeepAliveInterval(30);
        option.setUserName(userName);
        option.setPassword(password.toCharArray());  //옵션 객체에 접속하기위한 세팅끝!
        try {
            client = new MqttClient(serverURI, clientId);
            client.setCallback(this);
            client.connect(option);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    //구독 대상 전달
    public boolean subscribe(String... topics){
        try {
            if(topics != null){
                for(String topic : topics){
                    client.subscribe(topic,0);  //구독할 주제, 숫자는 품질 값
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //전송
    public boolean sender(String topic, String msg) throws MqttException{
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());  //보낼 메시지
        client.publish(topic, message);  //토픽과 함께 보낸다.
        return false;
    }

    @Override
    public void connectionLost(Throwable arg0) {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {

    }


    @Override
    public void messageArrived(String arg0, MqttMessage arg1) throws Exception {

    }

}