/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package com.minaclient.util;

import android.util.Log;

import com.google.gson.Gson;
import com.mina.object.User2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Sends its memory usage to the MemoryMonitor server.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class MemMonClient extends IoHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(MemMonClient.class);
    
    private static final String DEBUG_TAG = "MemMonClient";

    private IoSession session;

    private IoConnector connector;

    /**
     * Default constructor.
     */
    public MemMonClient() {

        LOGGER.debug("UDPClient::UDPClient");
        LOGGER.debug("Created a datagram connector");
        connector = new NioDatagramConnector();

        LOGGER.debug("Setting the handler");
        connector.setHandler(this);
        
        //创建接收数据的过滤器
        //DefaultIoFilterChainBuilder chain=connector.getFilterChain();
        //chain.addLast("myChin", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));     


        LOGGER.debug("About to connect to the server...");
        ConnectFuture connFuture = connector.connect(new InetSocketAddress(
                "10.1.0.24", 18080));

        LOGGER.debug("About to wait.");
        connFuture.awaitUninterruptibly();

        LOGGER.debug("Adding a future listener.");
        connFuture.addListener(new IoFutureListener<ConnectFuture>() {
            public void operationComplete(ConnectFuture future) {
                if (future.isConnected()) {
                    LOGGER.debug("...connected");
                    session = future.getSession();
                    try {
                        sendData();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    LOGGER.error("Not connected...exiting");
                }
            }
        });
    }

    
    private void sendData() throws InterruptedException {
    	/*******************方式1：传递字符串    start***********************/
    	/*String value = (String) "sendData";  
        IoBuffer buf = IoBuffer.allocate(value.getBytes().length);  
        buf.setAutoExpand(true);  
        
        buf.putInt(value.getBytes().length);
        
        if (value != null)  
            buf.put(value.trim().getBytes());  
        buf.flip();  
        session.write(buf);  */
        /*******************方式1：传递字符串    end***********************/
        
        /*******************方式2：使用json传递字符串    start***********************/
    	User2 user = new User2();
    	user.setCompanyName("mycompany");
    	user.setUserName("myusername");
    	user.setPassword("myPassword");
    	
    	Gson gson = new Gson();
    	String jsonStr = gson.toJson(user);
    	String value = (String) jsonStr;  
        IoBuffer buf = IoBuffer.allocate(value.getBytes().length);  
        buf.setAutoExpand(true);  
        
        buf.putInt(value.getBytes().length);
        
        if (value != null)  
            buf.put(value.trim().getBytes());  
        buf.flip();  
        session.write(buf);  
        /*******************方式2：使用json传递字符串    end***********************/
        
        
        /*******************方式3：直接传递对象    start***********************/
    	/*User2 user = new User2();
    	user.setCompanyName("mycompany");
    	user.setUserName("myusername");
    	user.setPassword("myPassword");
    	session.write(user);*/
        /*******************方式3：直接传递对象    end***********************/
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        LOGGER.debug("Session recv...");
        /*******************方式1：解析返回字符串    start ***********************/
        if (message instanceof IoBuffer) {
            IoBuffer buffer = (IoBuffer) message;
            int len = buffer.getInt();
            System.out.println("len:"+len);
            byte [] bytes = new byte[len];
            buffer.get(bytes);
            String str = new String(bytes) ;
            
            Log.d(DEBUG_TAG, "Session recv : "+str);

        }
        /*******************方式1：解析返回字符串    end ***********************/
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        LOGGER.debug("Message sent...");
        Log.d(DEBUG_TAG, "Session sent...");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        LOGGER.debug("Session ...");
        Log.d(DEBUG_TAG, "Session closed...");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        LOGGER.debug("Session created...");
        Log.d(DEBUG_TAG, "Session created...");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        LOGGER.debug("Session idle...");
        Log.d(DEBUG_TAG, "Session idle...");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        LOGGER.debug("Session opened...");
        Log.d(DEBUG_TAG, "Session opened...");
    }

}
