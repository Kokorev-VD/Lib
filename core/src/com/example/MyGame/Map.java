package com.example.MyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class Map {
    protected ArrayList<Integer> routeX = new ArrayList<>();
    protected ArrayList<Integer> routeY = new ArrayList<>();
    protected ArrayList<Integer> routeX1 = new ArrayList<>();
    protected ArrayList<Integer> routeY1 = new ArrayList<>();
    Actor jet;
    Stage stage;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    private int y;
    private int y1;

    public int getY() {
        return y;
    }

    public ArrayList<Integer> getRouteX() {
        return routeX;
    }

    public ArrayList<Integer> getRouteY() {
        return routeY;
    }

    public int getY1() {
        return y1;
    }

    public ArrayList<Integer> getRouteX1() {
        return routeX1;
    }

    public ArrayList<Integer> getRouteY1() {
        return routeY1;
    }

    public Map(String path){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        jet = new Actor();
        stage = new Stage();
        stage.addActor(jet);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, (float) (w/2.2), (float) (h/1.75));
        camera.update();
        tiledMap = new TmxMapLoader().load(path);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        switch (path){
            case "try.tmx":
                routeX.add(300);
                routeX.add(300);
                routeX.add(750);
                routeX.add(750);
                routeX.add(1300);
                routeX.add(1300);
                routeX.add(2160);

                routeY.add(550);
                routeY.add(880);
                routeY.add(880);
                routeY.add(430);
                routeY.add(430);
                routeY.add(620);
                routeY.add(620);

                y = 500;
                y1 = 500;
                break;
            case "map1.tmx":
                routeX.add(1600);
                routeX.add(1600);
                routeX.add(370);
                routeX.add(370);
                routeX.add(1700);
                routeX.add(1700);
                routeX.add(2160);

                routeY.add(980);
                routeY.add(780);
                routeY.add(780);
                routeY.add(370);
                routeY.add(370);
                routeY.add(600);
                routeY.add(600);

                y = 980;
                y1 = 980;
                break;
            case "map2.tmx":
                routeX.add(0);
                routeX.add(350);
                routeX.add(930);
                routeX.add(1220);
                routeX.add(1700);
                routeX.add(1700);
                routeX.add(2160);

                routeY.add(820);
                routeY.add(530);
                routeY.add(530);
                routeY.add(820);
                routeY.add(820);
                routeY.add(550);
                routeY.add(550);

                y = 820;
                y1 = 820;
                break;
            case "map3.tmx":
                routeX.add(560);
                routeX.add(560);
                routeX.add(1550);
                routeX.add(1550);
                routeX.add(2160);

                routeY.add(900);
                routeY.add(530);
                routeY.add(530);
                routeY.add(250);
                routeY.add(250);

                routeX1.add(560);
                routeX1.add(560);
                routeX1.add(1550);
                routeX1.add(1550);
                routeX1.add(2160);

                routeY1.add(120);
                routeY1.add(530);
                routeY1.add(530);
                routeY1.add(980);
                routeY1.add(980);

                y = 900;

                y1 = 120;
                break;
        }
    }
}
