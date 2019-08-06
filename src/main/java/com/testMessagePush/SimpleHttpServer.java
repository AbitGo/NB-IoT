/*
 * File Name: com.huawei.m2m.nsse.j808.client.SimpleHttpServer.java
 *
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.testMessagePush;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.utils.ExceptionUtil;

public class SimpleHttpServer {
    /**
     * Log Tool
     */

    private int port;
    private ServerSocketChannel serverSocketChannel = null;
    private ExecutorService executorService;
    private static final int POOL_MULTIPLE = 4;

    private int msgCnt = 0;

    private static String recvString = null;

    public static String getRecvString() {
        return recvString;
    }

    public SimpleHttpServer(int port) throws IOException {
        this.port = port;
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.socket().bind(new InetSocketAddress(this.port));

    }

    public void service() {
        while (true) {
            SocketChannel socketChannel = null;
            try {
                socketChannel = serverSocketChannel.accept();
                Handler handler = new Handler(socketChannel);
                executorService.execute(handler);
            } catch (IOException e) {
                System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
            }
        }
    }

    public static void startServer(final int port) {
        System.out.println("Enabling the server...， Server port = " + port);
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    new SimpleHttpServer(port).service();
                } catch (Exception e) {
                    System.out.println("Failed to enable the server. Server port = " + port);
                    System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
                    System.out.println();
                }
            }
        });
        t.start();
    }

    public static String recvBuf;

    public static String getRecvBuf() {
        return recvBuf;
    }

    public static void setRecvBuf(String recvBuf) {
        SimpleHttpServer.recvBuf = recvBuf;
    }

    class Handler implements Runnable {
        private SocketChannel socketChannel;

        public Handler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        public void run() {
            handle(socketChannel);
        }
        public void destory()
        {
            try{
                if (socketChannel != null)
                    socketChannel.close();
            }
            catch (IOException e) {
                System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
            }
        }

        private void handle(SocketChannel socketChannel) {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(2048);
                socketChannel.read(buffer);
                buffer.flip();
                recvString = new String(buffer.array());

                // String responseHead = recvString.substring(0, recvString.lastIndexOf("\n"));
                String responseJsonBody = "";

                if (recvString != null) {

                    try {
                        responseJsonBody = recvString.substring(recvString.lastIndexOf("\n"));
                    } catch (Exception e) {
                        System.out.println("get responseJsonBody fail.");
                    }
                }
                System.out.print("Received the " + msgCnt + "th message pushed by the platform: ");
                System.out.println(responseJsonBody);

                msgCnt++;
                System.out.println("***************");
            } catch (IOException e) {
                System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
            } finally {
                try {
                    if (socketChannel != null)
                        socketChannel.close();
                } catch (IOException e) {
                    System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
                }
            }
        }

    }

}
