package org.example;


import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.msg.request.ReadHoldingRegistersRequest;
import com.intelligt.modbus.jlibmodbus.msg.response.ReadHoldingRegistersResponse;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveTCP;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import com.intelligt.modbus.jlibmodbus.utils.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observer;


public class LabTCP {
    static public void main(String[] argv) {

        //
        try {
            Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
            TcpParameters tcpParameters = new TcpParameters();
            //listening on localhost
            tcpParameters.setHost(InetAddress.getLocalHost());
            tcpParameters.setPort(Modbus.TCP_PORT);
            tcpParameters.setKeepAlive(true);

            ModbusSlaveTCP slave = (ModbusSlaveTCP) ModbusSlaveFactory.createModbusSlaveTCP(tcpParameters);
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);


            master.setResponseTimeout(1000);
            slave.setServerAddress(Modbus.TCP_DEFAULT_ID);
            slave.setBroadcastEnabled(true);
            slave.setReadTimeout(1000);

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
            slave.addListener(listener);
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

            ModbusHoldingRegisters holdingRegisters = new ModbusHoldingRegisters(1000);

            for (int i = 0; i < holdingRegisters.getQuantity(); i++) {
                //fill
                holdingRegisters.set(i, i + 1);
            }

            //place the number PI at offset 6
            holdingRegisters.setFloat64At(1, Math.PI);
            holdingRegisters.setFloat64At(6, 1.1);

            slave.getDataHolder().setHoldingRegisters(holdingRegisters);

            Modbus.setAutoIncrementTransactionId(true);

            slave.listen();

            master.connect();

            //prepare request
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
            //get float
            //System.out.println("PI is approximately equal to " + registers.getFloat64At(0));
            System.out.println("Custom float value " + registers.getFloat64At(1));
            System.out.println("Custom float value " + registers.getFloat64At(6));
            //System.out.println("Custom float value " + registers.getFloat64At(8699));
            System.out.println();

            master.disconnect();

            slave.shutdown();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ModbusProtocolException e) {
            e.printStackTrace();
        } catch (ModbusIOException e) {
            e.printStackTrace();
        } catch (ModbusNumberException e) {
            e.printStackTrace();
        }
    }
}