package ru.coutvv.vkliker.core.api.support.raw;

import java.util.List;

public interface Json {

    Json objField(String fieldName);
    List<Json> arrField(String fieldName);
    String stringField(String fieldName);
    Long longField(String fieldName);

}
