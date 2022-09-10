package de.gml;

import java.util.ArrayList;
import java.util.List;

public abstract class State implements Base {

    private final List<Base> members = new ArrayList<>();

    public void add(Base member) {
        members.add(member);
    }

    public void remove(Base member) {
        members.remove(member);
    }

    @Override
    public void update(float delta) {
        for (Base member : members) {
            member.update(delta);
        }
    }

    @Override
    public void destroy() {}

    public List<Base> getMembers() {
        return members;
    }

}
