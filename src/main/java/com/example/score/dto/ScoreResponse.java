package com.example.score.dto;

public class ScoreResponse {
    private double faceScore;
    private double voiceScore;
    private double qnaScore;
    private double finalScore;

    public ScoreResponse(double faceScore, double voiceScore, double qnaScore, double finalScore) {
        this.faceScore = faceScore;
        this.voiceScore = voiceScore;
        this.qnaScore = qnaScore;
        this.finalScore = finalScore;
    }

    public double getFaceScore() {
        return faceScore;
    }

    public double getVoiceScore() {
        return voiceScore;
    }

    public double getQnaScore() {
        return qnaScore;
    }

    public double getFinalScore() {
        return finalScore;
    }
}