import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.concurrent.CompletableFuture.runAsync;

public class Asenkron {

    public static void main(String[] args) throws IOException, InterruptedException {
        new Asenkron().three();
    }

    private void one() {
        CompletableFuture<Void> cf1 = runAsync(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                System.out.println("Thread Calisiyor : " + Thread.currentThread().getName());
            }
            long finish = System.currentTimeMillis();
            System.out.println(finish - start);
        });

        CompletableFuture<Void> cf2 = runAsync(() -> {
            long start = System.currentTimeMillis();
            for (long i = 0; i < 1000L; i++) {
                System.out.println("Thread Calisiyor : " + Thread.currentThread().getName());
            }
            long finish = System.currentTimeMillis();
            System.out.println(finish - start);
        });
        cf1.join();
        cf2.join();
        System.out.println("Main Thread Calisiyor : " + Thread.currentThread().getName());
    }

    private void two() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Path path = Path.of("/home/kaan/Desktop/ltedit.sh");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(16384);
        asynchronousFileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                buffer.flip();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                try {
                    asynchronousFileChannel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                for (byte one : data) {
                    System.out.print(one);
                }
                latch.countDown();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
        for (int i = 0; i < 3; i++) {
            System.out.println("kasldjsakdjasldjkas");
        }
        latch.await();
    }

    private void three() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        File file = new File("/home/kaan/Desktop/asenkronyazma.txt");
        file.createNewFile();
        Path path = Path.of("/home/kaan/Desktop/asenkronyazma.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 1000; i++) {
                buffer.put((byte) 'a');
            }
            latch.countDown();
        });
        System.out.println("xxx");
        latch.await();
        buffer.flip();
        channel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("Writed");
                try {
                    channel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                latch.countDown();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
        System.out.println("Process From Main Thread");
        latch.await();
    }

}
