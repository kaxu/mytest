package com.kaxudodo;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by aaron on 16/8/2.
 */
public class NioTest {

    public static void main(String[] args) throws IOException {
        NioTest nioTest = new NioTest();
        nioTest.socketChannel();
    }

    private void baseTest() throws IOException {
        InputStream input = new FileInputStream(new File("src/com/kaxudodo/test.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String nameLine   = reader.readLine();
        String ageLine    = reader.readLine();
        String emailLine  = reader.readLine();
        String phoneLine  = reader.readLine();

        RandomAccessFile aFile = new RandomAccessFile("src/com/kaxudodo/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(47);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            int i = 0;
            while(buf.hasRemaining() && i<5){
                char a = (char) buf.get();
                if('5' == a){
                    buf.mark();
                }
                if('@' == a){
                    buf.reset();
                    i++;
                }
                System.out.print(a);
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    private void selector() throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
        socketChannel.configureBlocking(false);
        SelectionKey selectionKey = socketChannel.register(selector,SelectionKey.OP_CONNECT);
        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }

    private void fileChanel() throws IOException {
        //从通道中读
        RandomAccessFile aFile = new RandomAccessFile("src/com/kaxudodo/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);

        //写入通道
        String newData = "New String to write to file..." + System.currentTimeMillis();
        buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while(buf.hasRemaining()) {
            inChannel.write(buf);
        }
        //用完FileChannel后必须将其关闭
        inChannel.close();
    }

    private void socketChannel() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("https://www.zhongkecaifu.cn/api/product/home/list", 80));
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                char a = (char) buf.get();
                System.out.print(a);
            }

            buf.clear();
        }
    }
}

