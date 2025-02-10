package com.ProgettoISS;

public class Camera {
    private int cameraX, cameraY; // Posizione della telecamera
    private int mapWidth, mapHeight; // Dimensioni della mappa
    private int screenWidth, screenHeight; // Dimensioni della finestra
    private double zoom; // Fattore di zoom

    private static final int CAMERA_MARGIN = 200; // Margine per il movimento della camera

    public Camera(int mapWidth, int mapHeight, int screenWidth, int screenHeight, double zoom) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.cameraX = 0;
        this.cameraY = 0;
        this.zoom = zoom;
    }

    public void update(int playerX, int playerY) {
        int zoomedWidth = (int) (screenWidth / zoom);
        int zoomedHeight = (int) (screenHeight / zoom);

        if (playerX - cameraX > zoomedWidth - CAMERA_MARGIN) {
            cameraX = playerX - (zoomedWidth - CAMERA_MARGIN);
        } else if (playerX - cameraX < CAMERA_MARGIN) {
            cameraX = playerX - CAMERA_MARGIN;
        }

        if (playerY - cameraY > zoomedHeight - CAMERA_MARGIN) {
            cameraY = playerY - (zoomedHeight - CAMERA_MARGIN);
        } else if (playerY - cameraY < CAMERA_MARGIN) {
            cameraY = playerY - CAMERA_MARGIN;
        }

        cameraX = Math.max(0, Math.min(cameraX, mapWidth - zoomedWidth));
        cameraY = Math.max(0, Math.min(cameraY, mapHeight - zoomedHeight));
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
}