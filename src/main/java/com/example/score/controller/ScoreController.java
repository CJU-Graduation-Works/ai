package com.example.score.controller;

import com.example.score.dto.ScoreResponse;
import com.example.score.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ScoreController {

    @Autowired
    private PythonService pythonService;

    @PostMapping(value = "/score", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ScoreResponse getScore(
            @RequestPart("image") MultipartFile image,
            @RequestPart("audio") MultipartFile audio,
            @RequestPart("quizScore") int quizScore) throws IOException, InterruptedException {

        // 이미지와 오디오를 임시 파일로 저장
        File tempImage = File.createTempFile("face_", ".jpg");
        image.transferTo(tempImage);

        File tempAudio = File.createTempFile("voice_", ".wav");
        audio.transferTo(tempAudio);

        // 각각의 점수 계산
        double faceScore = pythonService.getFaceScore(tempImage.getAbsolutePath());
        double voiceScore = pythonService.getVoiceScore(tempAudio.getAbsolutePath());
        double qnaScore = quizScore;

        // 가중치 적용 후 최종 점수 계산
        double finalScore = faceScore * 0.4 + voiceScore * 0.4 + qnaScore * 0.2;

        return new ScoreResponse(faceScore, voiceScore, qnaScore, finalScore);
    }
}
