package icu.hacking.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import icu.hacking.socketio.params.request.UserLoginRequestBody;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@Component
@AllArgsConstructor
public class SocketIOServerInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final SocketIOServer server;

    private final ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        server.addConnectListener(client -> System.out.println("New Client connected: " + client.getSessionId()));
        addEventListeners();
        server.addDisconnectListener(client -> System.out.println("Client disconnected: " + client.getSessionId()));
        server.start();
    }

    @SneakyThrows
    private void addEventListeners() {
        final Map<String, Object> beans = applicationContext.getBeansWithAnnotation(SocketIOListener.class);
        for (final Object bean : beans.values()) {
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                SocketIOEventMapping annotation = method.getAnnotation(SocketIOEventMapping.class);
                if (annotation != null) {
                    String eventName = annotation.event();
                    server.addEventListener(eventName, String.class, (client, data, ackSender) -> {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        Class<?> protoClazz = parameterTypes[0];
                        Method parseFromMethod = protoClazz.getMethod("parseFrom", byte[].class);
                        Object protoBody = parseFromMethod.invoke(null, (Object) binaryStringToByteArray(data));
                        method.invoke(bean, protoBody);
                    });
                }
            }
        }
    }

    private byte[] binaryStringToByteArray(String s) {
        int count = s.length() / 8;
        byte[] b = new byte[count];
        for (int i = 1; i < count; ++i) {
            String t = s.substring((i - 1) * 8, i * 8);
            b[i - 1] = (byte) Integer.parseInt(t, 2);
        }
        return b;
    }
}
