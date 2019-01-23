package com.team.dream.sleepsafe.hebergerinformation;

import org.json.JSONException;

public interface IHebergerInformationActivityPresenter {
    void sendHost(String edtSipCode, String edtCity, String edtRoad, String edtPlaces, String edtMaxDistance, String chbTakeEm) throws JSONException;
}
