package shop.consumer;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Subscriber {

    @Autowired
    public LogRepository repository;

    @RabbitListener(queues="${jsa.rabbitmq.queue.creation_logs}")
    public void receivedCreationLog(String message) {
        SaveLog(message);
    }

    @RabbitListener(queues="${jsa.rabbitmq.queue.update_logs}")
    public void receivedUpdateLog(String message) {
        SaveLog(message);
    }

    @RabbitListener(queues="${jsa.rabbitmq.queue.deletion_logs}")
    public void receivedDeletionLog(String message) {
        SaveLog(message);
    }

    private Log Deserialize(String logItem)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Log LogObject = mapper.readValue(logItem, Log.class);
            return  LogObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private void SaveLog(String message) {
        System.out.println("Received Message: " + message);

        Log record = Deserialize(message);
        repository.save(record);
    }

}