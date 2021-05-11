package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

public class MyGame extends ApplicationAdapter implements InputProcessor {
	Batch batch;
	Texture bg;
	Map map;
	LinkedList<Enemy> enemies;
	LinkedList<Tower> twrs;
	Sprite btn;
	Sprite btn1;
	Sprite btn2;
	Double velX;
	Double velY;
	String path;
	Bombing boom = new Bombing();
	static int money = 100;
	BitmapFont font;
	int enal = 10;
	int abilityCost = 25;
	public int wave = 1;
	float heliChance;
	public ShapeRenderer srend;
	boolean canPlace = false;
	boolean canPlace1 = false;
	public void PlayScreen(){
		Gdx.input.setInputProcessor(this);
	}

	public MyGame(String path) {
		this.path = path;
		switch (path){
			case "map0.tmx":
				heliChance = 0;
				break;
			case "map1.tmx":
				heliChance = 1;
				break;
			case "map2.tmx":
				heliChance = 0.3f;
				break;
			case "map3.tmx":
				heliChance = 0.5f;
				break;
		}
	}

	@Override
	public void create() {
		map = new Map(path);
		PlayScreen();
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

		enemies = new LinkedList<>();

		velX = 0.8;
		velY = 0.8;
		for (int i = 0; i < 10; i++) {
			if (Math.random() < heliChance) {
				Helicopter h;
				if(i%2 == 0) {
					h = new Helicopter(-250 * i, map.getY(), i % 2);
				}
				else {
					h = new Helicopter(-250 * i, map.getY1(), i % 2);
				}
				enemies.add(h);
			} else {
				Tank t;
				if(i%2 == 0){
					t = new Tank(-250 * i, map.getY(), i%2);
				}
				else{
					t = new Tank(-250 * i, map.getY1(), i%2);
				}
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
				if (canPlace1 && money>=10) {
					Tower tower = new RocketTower();
					tower.setPosX(x);
					tower.setPosY(y * (-1) + 1000);
					tower.s();
					twrs.addFirst(tower);
					velX /= 1.16;
					velY /= 1.16;
					money-=10;
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
				}

				return true;
			}
		});

	}

	@Override
	public void render() {
		if(wave == 10){
			System.exit(1);
		}
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.camera.update();

		map.tiledMapRenderer.setView(map.camera.combined,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		map.tiledMapRenderer.render();
		map.stage.draw();
		try {
			Thread.sleep(1000/100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		batch.begin();

		font.draw(batch, "Money:" + money, 75, 100);
		font.draw(batch, "Wave:" + wave, 75, 160);
		font.draw(batch, "Cost:10", 1005, 100);
		font.draw(batch, "Cost:10", 1305, 100);
		font.draw(batch, "Cost:" + abilityCost, 1605, 100);
		srend.begin(ShapeRenderer.ShapeType.Filled);
		btn.draw(batch);
		btn1.draw(batch);
		btn2.draw(batch);
		if (enemies.isEmpty()) {
			Rocket.a = 1;
			enal += 2;
			wave++;
			Enemy.setA((int) (Enemy.getA() + 50));
			for (int i = 0; i < enal; i++) {
				if (Math.random() < heliChance) {
					Helicopter h;
					if (i % 2 == 0) {
						h = new Helicopter(-250 * i, map.getY(), i % 2);
					} else {
						h = new Helicopter(-250 * i, map.getY1(), i % 2);
					}
					enemies.add(h);
				} else {
					Tank t;
					if (i % 2 == 0) {
						t = new Tank(-250 * i, map.getY(), i % 2);
					} else {
						t = new Tank(-250 * i, map.getY1(), i % 2);
					}
					enemies.add(t);
				}
			}
		}
		for (Enemy en : enemies) {
			for (Tower tower : twrs) {
				try {
					if(tower instanceof RocketTower && en instanceof Helicopter) tower.attack(en);
					else tower.attack(en, srend);
				} catch (Throwable throwable) {
					throwable.printStackTrace();
				}
			}
		}
		for (Enemy en : enemies) {
			if(en.i == 0 || !path.equals("map3.tmx")) {
				en.move(map.getRouteX(), map.getRouteY(), path, enemies);
			}
			else{
				en.move(map.getRouteX1(), map.getRouteY1(), path, enemies);
			}
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

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}