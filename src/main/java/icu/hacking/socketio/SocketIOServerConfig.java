package icu.hacking.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
@AllArgsConstructor
public class SocketIOServerConfig {

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(9092);

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        configuration.setSocketConfig(socketConfig);

        return new SocketIOServer(configuration);
    }

}