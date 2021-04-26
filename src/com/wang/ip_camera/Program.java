package com.wang.ip_camera;

public class Program {

    private String outputPath = "/home/charles/";


    public static void main(String[] args) {
        System.out.println("Starting IP camera recorder.");
        run();
    }

    private static void run() {
        try {
            Runtime run = Runtime.getRuntime();
            Process FFmpeg = run.exec("ffmpeg -i \"rtsp://192.168.1.41:554/h264?username=admin&password=123456\" -an -c:v copy \"output.mkv\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}