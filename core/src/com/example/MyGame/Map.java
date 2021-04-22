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
    Actor jet;
    Stage stage;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    public ArrayList<Integer> getRouteX() {
        return routeX;
    }

    public ArrayList<Integer> getRouteY() {
        return routeY;
    }

    public Map(String path){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        jet = new Actor();
        stage = new Stage();
        stage.addActor(jet);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, (float) (w/2.2), (float) (h/1.8));
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
                routeY.add(630);
                routeY.add(630);
                break;
        }
    }
}
