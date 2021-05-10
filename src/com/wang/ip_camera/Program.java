package com.wang.ip_camera;

import java.util.ArrayList;
import java.util.List;

public class Program {

    private static String outputPath;
    private static String FFmpegPath;
    private static String ipAddress;
    private static String username;
    private static String password;


    public static void main(String[] args) {

        if (args[0].equals("-h") && args.length == 1) {
            System.out.println("IP camera recorder.");
            System.out.println("Usage: java -jar Recorder.jar [FFmpeg Path] [IP of camera with port] [username] [password] [Output Path]");
            System.exit(0);
        }
        if (args.length == 5) {
            System.out.println("Starting IP camera recorder.");
            FFmpegPath = args[0];
            ipAddress = args[1];
            username = args[2];
            password = args[3];
            outputPath = args[4];
            run();
        }
        System.out.println("End of Program.");
    }

    private static void run() {
        List<String> FFmpegArgs = new ArrayList<String>();
        FFmpegArgs.add(FFmpegPath);
        FFmpegArgs.add("-i");
        FFmpegArgs.add("rtsp://" + ipAddress + "/h264?username=" + username + "&password=" + password);
        FFmpegArgs.add("-an");
        FFmpegArgs.add("-c:v");
        FFmpegArgs.add("copy");
        FFmpegArgs.add(outputPath);


        try {
            ProcessBuilder FFmpegPB = new ProcessBuilder(FFmpegArgs);
            //FFmpegPB.inheritIO(); //Used for debug
            FFmpegPB.start();

            while(true) {
                System.out.println("Recording...");
                Thread.sleep(5000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}