package org.example;
// імпорт бібліотек
import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.msg.request.ReadHoldingRegistersRequest;
import com.intelligt.modbus.jlibmodbus.msg.request.WriteSingleRegisterRequest;
import com.intelligt.modbus.jlibmodbus.msg.response.ReadHoldingRegistersResponse;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import com.intelligt.modbus.jlibmodbus.utils.DataUtils;
import com.intelligt.modbus.jlibmodbus.utils.FrameEvent;
import com.intelligt.modbus.jlibmodbus.utils.FrameEventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

// базовий клас клієнта
public class Master {
    // головний метод классу (точка входу в програму)
    public static void main(String[] argv) {
        try {
            // базові налаштування клієнта
            Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
            TcpParameters tcpParameters = new TcpParameters();
            tcpParameters.setHost(InetAddress.getLocalHost());
            tcpParameters.setPort(Modbus.TCP_PORT);
            tcpParameters.setKeepAlive(true);

            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            // таймаут очікування в мілісеундах
            master.setResponseTimeout(1000);

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

            master.addListener(listener);

            // під'єднання до серверу
            master.connect();

            // створення запиту на читання регістрів
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest();
            request.setServerAddress(Modbus.TCP_DEFAULT_ID);
            request.setStartAddress(0);
            request.setQuantity(10);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) request.getResponse();

            master.processRequest(request);
            ModbusHoldingRegisters registers = response.getHoldingRegisters();
            for (int r : registers) {
                System.out.println("HoldingRegister: " + r);
            }

            System.out.println("Custom float value " + registers.getFloat64At(1));
            System.out.println("Custom float value " + registers.getFloat64At(6));

            // Запис значення в окремий регістр
            int registerAddressToWrite = 12; // Specify the address to write to
            short valueToWrite = 12345; // Specify the value to write

            // створення запиту на запис значення регістру
            WriteSingleRegisterRequest writeRequest = new WriteSingleRegisterRequest();
            writeRequest.setServerAddress(Modbus.TCP_DEFAULT_ID);
            writeRequest.setStartAddress(registerAddressToWrite);
            writeRequest.setValue(valueToWrite);

            master.processRequest(writeRequest);

            // вивід значення яке має бути записано до регістра в консоль
            System.out.println("Written Register: " + registerAddressToWrite);
            System.out.println("Value Written: " + valueToWrite);

            // Створення запиту на читання записаного значення в регістрі
            ReadHoldingRegistersRequest readUpdatedRequest = new ReadHoldingRegistersRequest();
            readUpdatedRequest.setServerAddress(Modbus.TCP_DEFAULT_ID);
            readUpdatedRequest.setStartAddress(registerAddressToWrite);
            readUpdatedRequest.setQuantity(1);
            ReadHoldingRegistersResponse readUpdatedResponse = (ReadHoldingRegistersResponse) readUpdatedRequest.getResponse();

            master.processRequest(readUpdatedRequest);
            ModbusHoldingRegisters readUpdatedRegisters = readUpdatedResponse.getHoldingRegisters();

            // вивід значення регістра в консоль
            System.out.println("Updated Value in Register " + registerAddressToWrite + ": " + readUpdatedRegisters.getInt16At(0));

            // завершення роботи клієнта
            master.disconnect();

        } catch (UnknownHostException | ModbusProtocolException | ModbusIOException | ModbusNumberException e) {
            e.printStackTrace();
        }
    }
}
