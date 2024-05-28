package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Window extends PApplet {

  private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
  private ArrayList<AbstractCharacter> characters = new ArrayList<AbstractCharacter>();
  private PlayerCharacter player;

  private EnemyPower enemyChecker = new EnemyPower();
  private EnemyCollection enemyCollection = new EnemyCollection();

  private ArrayList<EnemyCharacter> enemyList = new ArrayList<>();
  private int numberEnemy = 20;

  private int maxEnemy = 50;
  private int delay = 3000;
  private int period = 5000;
  Timer timer = new Timer();
  /**
   * Runs before applet starts.
   */

  public void setup() {
    PVector playerDirection  = new PVector(1, 1).normalize();
    PVector playerPosition = new PVector(width / 2f, height / 2f);

    PlayerCharacter player = PlayerCharacter.getInstance(playerPosition, playerDirection, this);
    addPlayer(player);

    for (int i = 0; i < numberEnemy; i++) {
      PVector enemyDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector enemyPosition = new PVector(random(width), random(height));
      EnemyCharacter enemy = new EnemyCharacter(enemyPosition, enemyDirection, this);
      addThreadEnemy(enemy);
      enemyList.add(enemy);
    }


    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {

        for (EnemyCharacter e : enemyCollection) {

          PVector currentPosition = e.getPosition();
          PVector currentPlayer = player.getPosition();
          float distance = PVector.dist(currentPosition, currentPlayer);
          e.setDistance(distance);
        }
        enemyCollection.collectionSort();
        Iterator itr = enemyCollection.iterator();
        int indexItr = 0;

        while (itr.hasNext()) {

          EnemyCharacter thisTest = (EnemyCharacter) itr.next();
          thisTest.getPower().incrementCount(indexItr);
          indexItr++;
        }

        for (EnemyCharacter e : enemyCollection) {
          System.out.println("EnemyPower: " + e.getPower().getCount());
        }
      }
    }, delay, period);




  }


  @Override
  public void keyPressed(KeyEvent event) {
    super.keyPressed(event);
    if (player == null) {
      return;
    }
    switch (event.getKeyCode()) {
      case RIGHT:
        player.rotate((float) Math.PI / 8f);
        break;
      case LEFT:
        player.rotate((float) -Math.PI / 8f);
        break;
      default:
        break;
    }
  }



  /**
   * Runs on each frame.
   */
  public void draw() {
    background(10);

    for (EnemyCharacter en : enemyCollection) {
     addThreadEnemy(en);
     enemyList.add(en);
    }

    for (IDrawable d : drawables) {
      d.draw(this);
    }

    player.move(this);

    for (EnemyCharacter e : enemyCollection) {
      e.move(this);

      if (player.inRange(e)) {
        player.registerObserver(e);
      } else {
        player.unregisterObserver(e);
        e.move(this);

      }
    }
  }

  public void settings() {
    size(640, 360);
  }

  public void addPlayer(PlayerCharacter player) {
    this.player = player;
    this.drawables.add(player);
    this.characters.add(player);
  }

  public void removeThreadEnemy(EnemyCharacter enemy) {
   if (this.numberEnemy > 0) {
    this.numberEnemy -= 1;
    this.enemyCollection.remove(enemy);
    this.drawables.remove(enemy);
    this.characters.remove(enemy);
   }
  }

  public void addThreadEnemy(EnemyCharacter enemy) {
   if (this.numberEnemy < maxEnemy) {
    this.numberEnemy += 1;
    this.enemyCollection.add(enemy);
    this.drawables.add(enemy);
    this.characters.add(enemy);
   }

  }

 /**
  * Add to ArrayList EnemyCollection
  * @param enemy
  */
  public void addEnemy(EnemyCharacter enemy) {
    this.enemyCollection.add(enemy);
  }

 /**
  * Remove from ArrayList EnemyCollection
  * @param enemy
  */
  public void removeEnemy(EnemyCharacter enemy) {
   enemyCollection.remove(enemy);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"window"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);
  }
}