package ru.coutvv.vkliker.core.api.action;

import ru.coutvv.vkliker.core.api.support.raw.Json;

public interface Like {
    void add(Json objectIdentity) throws Exception;
    void remove(Json objectIdentity) throws Exception;
    boolean liked(Json objectIdentity) throws Exception;
}
