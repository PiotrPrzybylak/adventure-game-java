package adventuregame.logic.functional;

import java.util.ArrayList;
import java.util.List;

class ImmutableList<T> {

    private final List<T> list;

    public ImmutableList(List<T> list) {
        this.list = List.copyOf(list);
    }

    public int size() {
        return list.size();
    }

    public T get(Integer index) {
        return list.get(index);
    }

    public ImmutableList<T> replaceElementAt(Integer index, T newElement) {
        List<T> result = new ArrayList<>(list);
        result.set(index, newElement);
        return new ImmutableList<>(result);
    }
}
