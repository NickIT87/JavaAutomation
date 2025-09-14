package behavioral.iterator.example.iterators;

import behavioral.iterator.example.profile.Profile;

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}