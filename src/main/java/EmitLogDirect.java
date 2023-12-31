import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String severity = getSeverity(argv);
            String message = getMessage(argv);

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }

    private static String getMessage(String[] argv) {
        return "Rodrigo f silva  ";
    }

    private static String getSeverity(String[] argv) {
        String severity;
         severity = getRandomSeverity(); // Obtém a gravidade aleatoriamente
        return severity;
    }
    private static String getRandomSeverity() {
    String[] severities = {"info", "warning", "error"};
    Random random = new Random();
    int index = random.nextInt(severities.length);
    return severities[index];
}


}
