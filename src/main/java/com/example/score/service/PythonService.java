package com.example.score.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class PythonService {

    // 윈도우에서는 보통 "python"으로 실행 (리눅스/mac은 "python3")
    private final String python = "python";

    // 프로젝트 루트에 있는 스크립트 이름
    private final String faceScriptPath = "face_predict.py";
    private final String voiceScriptPath = "voice_predict.py";

    public double getFaceScore(String imagePath) throws java.io.IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(python, faceScriptPath, imagePath);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String result = reader.readLine();
            System.out.println("🧪 Python 얼굴 점수 출력: " + result);
            process.waitFor();
            return Double.parseDouble(result.trim());
        }
    }

    public double getVoiceScore(String audioPath) throws java.io.IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(python, voiceScriptPath, audioPath);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String result = reader.readLine();
            System.out.println("🧪 Python 음성 점수 출력: " + result);
            process.waitFor();
            return Double.parseDouble(result.trim());
        }
    }
}
