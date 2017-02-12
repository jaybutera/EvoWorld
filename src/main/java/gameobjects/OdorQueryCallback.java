package gameobjects;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.dynamics.Fixture;

import java.util.ArrayList;

public class OdorQueryCallback implements QueryCallback {
    public ArrayList<Fixture> foundFixtures;

    public OdorQueryCallback () {
        foundFixtures = new ArrayList<>();
    }

    @Override
    public boolean reportFixture (Fixture fixture) {
        foundFixtures.add( fixture );
        return true;
    }
}
