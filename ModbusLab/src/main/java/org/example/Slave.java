package org.example;
// імпорт бібліотек
import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveTCP;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import com.intelligt.modbus.jlibmodbus.utils.DataUtils;
import com.intelligt.modbus.jlibmodbus.utils.FrameEvent;
import com.intelligt.modbus.jlibmodbus.utils.FrameEventListener;
import com.intelligt.modbus.jlibmodbus.utils.ModbusSlaveTcpObserver;
import com.intelligt.modbus.jlibmodbus.utils.TcpClientInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observer;

// базовий клас серверу
public class Slave {
    // головний метод классу (точка входу в програму)
    public static void main(String[] argv) {
        try {
            // базові налаштування сервера
            Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
            TcpParameters tcpParameters = new TcpParameters();
            tcpParameters.setHost(InetAddress.getLocalHost());
            tcpParameters.setPort(Modbus.TCP_PORT);
            tcpParameters.setKeepAlive(true);

            ModbusSlaveTCP slave = (ModbusSlaveTCP) ModbusSlaveFactory.createModbusSlaveTCP(tcpParameters);

            slave.setServerAddress(Modbus.TCP_DEFAULT_ID);
            slave.setBroadcastEnabled(true);
            slave.setReadTimeout(1000);     // таймаут очікування в мілісеундах

            // обробник подій відправки та отримання даних
            FrameEventListener listener = new FrameEventListener() {
                @Override
                public void frameSentEvent(FrameEvent event) {
                    System.out.println("frame sent " + DataUtils.toAscii(event.getBytes()));
                }

                @Override
                public void frameReceivedEvent(FrameEvent event) {
                    System.out.println("frame recv " + DataUtils.toAscii(event.getBytes()));
                }
            };

            slave.addListener(listener);
            // обробник подій підключення клієнта до серверу
            Observer o = new ModbusSlaveTcpObserver() {
                @Override
                public void clientAccepted(TcpClientInfo info) {
                    System.out.println("Client connected " + info.getTcpParameters().getHost());
                }

                @Override
                public void clientDisconnected(TcpClientInfo info) {
                    System.out.println("Client disconnected " + info.getTcpParameters().getHost());
                }
            };
            slave.addObserver(o);

            // створення сховища даних
            ModbusHoldingRegisters holdingRegisters = new ModbusHoldingRegisters(2000);

            for (int i = 0; i < holdingRegisters.getQuantity(); i++) {
                //fill
                holdingRegisters.set(i, i + 1);
            }

            // задання базових значень регістрів 1 та 6
            holdingRegisters.setFloat64At(1, Math.PI);
            holdingRegisters.setFloat64At(6, 1.1);

            slave.getDataHolder().setHoldingRegisters(holdingRegisters);

            Modbus.setAutoIncrementTransactionId(true);

            // запуск сервера
            slave.listen();

            //slave.shutdown();
        } catch (UnknownHostException | ModbusProtocolException | ModbusIOException e) {
            e.printStackTrace();
        }
    }
}
