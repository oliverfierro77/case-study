package debug;//package debug;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//import javax.jms.Connection;
//import javax.jms.ExceptionListener;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.jms.Topic;
//
//class JmsConnectivityTest {
//
//    public static void main(String[] args) throws JMSException {
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        connection.setExceptionListener(new ExceptionListener() {
//            @Override
//            public void onException(JMSException e) {
//                e.printStackTrace();
//            }
//        });
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Topic destination = session.createTopic("topic1");
//        MessageProducer producer = session.createProducer(destination);
//        MessageConsumer consumer = session.createConsumer(destination);
//        producer.send(session.createTextMessage("Shoes"));
//        System.out.println("Sent message");
//        Message message = consumer.receive(1000);
//        if (message instanceof TextMessage) {
//            TextMessage textMessage = (TextMessage) message;
//            String text = textMessage.getText();
//            System.out.println("Received: " + text);
//        } else {
//            System.out.println("Received: " + message);
//        }
//        consumer.close();
//        session.close();
//        connection.close();
//
//    }
//
//}
