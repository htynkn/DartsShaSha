package com.huangyunkun.extension;

public interface IStatisticsService {
    void onResume();

    void onPause();

    void onEvent(String eventId, String label);

    void onEvent(String eventId, String label, int count);
}
