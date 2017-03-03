package gameobjects;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

public class MouthSensorCallback implements ContactListener {
    private void creatureEatFood (Creature creature, Food food) {
        food.die();
        creature.energy += 15f;
    }

    @Override
    public void beginContact (Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // XOR may provide slight speed up
        if ( fixA.isSensor() ^ fixB.isSensor() ) { // XOR - if one is a sensor

            if (fixA.isSensor()) {
                UserDataContainer udContainer = (UserDataContainer) fixA.getUserData();

                // Fixture A is a creature mouth sensor
                if (udContainer.entityType == EntityType.CreatureMouthSensor) {
                    UserDataContainer udB = (UserDataContainer) fixB.getUserData();

                    if (udB.entityType == EntityType.Food) {
                        creatureEatFood((Creature)((UserDataContainer) fixA.getUserData()).entity, (Food)udB.entity);
                    }
                }
            }

            else if (fixB.isSensor()) {
                UserDataContainer udContainer = (UserDataContainer) fixB.getUserData();

                // Fixture A is a creature mouth sensor
                if (udContainer.entityType == EntityType.CreatureMouthSensor) {
                    UserDataContainer udA = (UserDataContainer) fixA.getUserData();

                    if (udA.entityType == EntityType.Food) {
                        creatureEatFood((Creature) ((UserDataContainer) fixB.getUserData()).entity, (Food)udA.entity);
                    }
                }
            }
        }
    }

    @Override
    public void endContact (Contact contact) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }
}
