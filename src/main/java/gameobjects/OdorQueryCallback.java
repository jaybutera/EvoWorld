package gameobjects;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.dynamics.Fixture;

import java.util.ArrayList;

public class OdorQueryCallback implements QueryCallback {
    public ArrayList<ChemicalComposition> foundChems;

    public OdorQueryCallback () {
        foundChems = new ArrayList<>();
    }

    @Override
    public boolean reportFixture (Fixture fixture) {
        foundChems.add( (ChemicalComposition)fixture.getUserData() );
        return true;
    }
}
