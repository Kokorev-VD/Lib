package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture bg;
	/*TiledMap tiledMap;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;*/
	LinkedList<Enemy> enemies;
	LinkedList<Tower> twrs;
	Sprite btn;
	Sprite btn1;
	Sprite btn2;
	ArrayList<Integer> routeX;
	ArrayList<Integer> routeY;
	Double velX;
	Double velY;
	Bombing boom = new Bombing();
	static int money = 100;
	BitmapFont font;
	int enal = 10;
	int abilityCost = 25;
	public ShapeRenderer srend;
	boolean canPlace = false;
	boolean canPlace1 = false;
	/*public void PlayScreen(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();

		tiledMap = new TmxMapLoader().load("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiledMap);

		camera.position.set(0, 3200, 0);
	}*/
	@Override
	public void create() {
		//PlayScreen();
		font = new BitmapFont();
		font.getData().setScale(3);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.setColor(Color.CYAN);
		batch = new SpriteBatch();
		twrs = new LinkedList<>();
		Tower tower = new Laser();
		tower.setPosX(3000);
		tower.setPosY(3000);
		twrs.add(tower);

		routeX = new ArrayList<>();
		routeY = new ArrayList<>();

		routeX.add(300);
		routeX.add(300);
		routeX.add(750);
		routeX.add(750);
		routeX.add(1300);
		routeX.add(1300);
		routeX.add(2160);

		routeY.add(450);
		routeY.add(800);
		routeY.add(800);
		routeY.add(350);
		routeY.add(350);
		routeY.add(580);
		routeY.add(580);

		enemies = new LinkedList<>();

		velX = 0.8;
		velY = 0.8;
		for (int i = 0; i < 10; i++) {
			if (Math.random() < 0.5) {
				Helicopter h = new Helicopter(-250 * i, 450);
				enemies.add(h);
			} else {
				Tank t = new Tank(-250 * i, 450);
				enemies.add(t);
			}
		}
		srend = new ShapeRenderer();
		srend.setColor(Color.RED);
		btn = new Sprite(new Texture("laser.png"));
		btn.setX(1005);
		btn.setY(125);
		btn.setSize(150f, 150f);
		btn1 = new Sprite(new Texture("rocketTower.png"));
		btn1.setX(1305);
		btn1.setY(125);
		btn1.setSize(150f, 150f);
		btn2 = new Sprite(new Texture("explosion.png"));
		btn2.setX(1605);
		btn2.setY(125);
		btn2.setSize(150f, 150f);
		Gdx.input.setInputProcessor(new InputAdapter() {
			public boolean touchUp(int x, int y, int pointer, int button) {
				if (canPlace && money >=10) {
					Tower tower = new Laser();
					tower.setPosX(x);
					tower.setPosY(y * (-1) + 1000);
					tower.s();
					twrs.addFirst(tower);
					velX /= 1.16;
					velY /= 1.16;
					money-=10;
					canPlace = false;
					System.out.println(money);
				}
				if (canPlace1 && money>=50) {
					Tower tower = new RocketTower();
					tower.setPosX(x);
					tower.setPosY(y * (-1) + 1000);
					tower.s();
					twrs.addFirst(tower);
					velX /= 1.16;
					velY /= 1.16;
					money-=50;
					canPlace1 = false;
					System.out.println(money);
				}
				if (x >= 1005 & x <= 1155 & y * (-1) + 1100 >= 125 & y * (-1) + 1100 <= 275) {
					canPlace = true;
				}
				if (x >= 1305 & x <= 1455 & y * (-1) + 1100 >= 125 & y * (-1) + 1100 <= 275) {
					canPlace1 = true;
				}
				if (x >= 1605 & x <= 1755 & y * (-1) + 1100 >= 125 & y * (-1) + 1100 <= 275 && money>=abilityCost) {
					boom.ability(enemies);
					money-=abilityCost;
					System.out.println(money);
					abilityCost+=5;
				}

				return true;
			}
		});

	}

	@Override
	public void render() {
		try {
			Thread.sleep(1000/100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		batch.begin();

		batch.draw(new Texture("background.png"), 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font.draw(batch, "Money:" + String.valueOf(money), 75, 100);
		font.draw(batch, "Cost:10", 1005, 100);
		font.draw(batch, "Cost:50", 1305, 100);
		font.draw(batch, "Cost:" + String.valueOf(abilityCost), 1605, 100);
		srend.begin(ShapeRenderer.ShapeType.Filled);
		btn.draw(batch);
		btn1.draw(batch);
		btn2.draw(batch);
		if (enemies.isEmpty()) {
			Rocket.a = 1;
			enal += 5;
			Enemy.setA((int) (Enemy.getA() + 50));
			for (int i = 0; i < enal; i++) {
				if (Math.random() < 0.5) {
					Helicopter h = new Helicopter(-250 * i, 450);
					enemies.add(h);
				} else {
					Tank t = new Tank(-250 * i, 450);
					enemies.add(t);
				}
			}
		}
		for (Enemy en : enemies) {
			for (Tower tower : twrs) {
				try {
					if(tower instanceof RocketTower) tower.attack(enemies.getFirst());
					else tower.attack(en, srend);
				} catch (Throwable throwable) {
					throwable.printStackTrace();
				}
			}
		}
		for (Enemy en : enemies) {
			en.move(routeX, routeY);
			en.enemy.draw(batch);
			en.blade.draw(batch);
		}
		try {
			for (Enemy en : enemies) {
				if (!en.isAlive()) {
					enemies.remove(en);
				}
				en.drawHealth(srend);
			}
		} catch (Exception ignored) {
		}
		for (Tower tower : twrs) {
			tower.sprite.draw(batch);
			tower.drawHealth(srend, twrs);
			if (tower instanceof RocketTower) {
				if (!((RocketTower) tower).rockets.isEmpty()) {
					((RocketTower) tower).rockets.get(0).getSprite().draw(batch);
				}
			}
		}
		srend.end();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		bg.dispose();
	}

}