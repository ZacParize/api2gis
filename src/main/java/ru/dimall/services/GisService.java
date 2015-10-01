package ru.dimall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dimall.implementations.Firmobject;
import ru.dimall.interfaces.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class GisService implements IGisService {

    private IGisAppData appData;

    private IHttpUrlConnection httpUrlConnection;

    private IGisDirectory gisDirectory;

    private IGisFlamp gisFlamp;


    public IGisAppData getAppData() {
        return appData;
    }

    @Autowired
    public void setConfiguration(IGisAppData appData) {
        this.appData = appData;
    }

    public IHttpUrlConnection getHttpUrlConnection() {
        return httpUrlConnection;
    }

    @Autowired
    public void setHttpUrlConnection(IHttpUrlConnection httpUrlConnection) {
        this.httpUrlConnection = httpUrlConnection;
    }

    public IGisDirectory getGisDirectory() {
        return gisDirectory;
    }

    @Autowired
    public void setGisDirectory(IGisDirectory gisDirectory) {
        this.gisDirectory = gisDirectory;
    }

    public IGisFlamp getGisFlamp() {
        return gisFlamp;
    }

    @Autowired
    public void setGisFlamp(IGisFlamp gisFlamp) {
        this.gisFlamp = gisFlamp;
    }

    @Override
    public String getInfo(Map<String,String> requestParameters) {

        this.getHttpUrlConnection().setServiceMethod(this.getAppData().getData().get("searchingMethod"));
        this.getGisDirectory().setConnection(this.getHttpUrlConnection());
        IFirmList<Firmobject> firms = this.getGisDirectory().handle(requestParameters);
        this.getHttpUrlConnection().setServiceMethod(this.getAppData().getData().get("ratingMethod"));
        this.getGisFlamp().setConnection(this.getHttpUrlConnection());
        this.getGisFlamp().fillRating(firms);

        return firms.toString();

    }

}
