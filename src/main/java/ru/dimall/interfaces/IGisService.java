package ru.dimall.interfaces;

import java.util.Map;

public interface IGisService {

    IGisAppData getAppData();

    /*String*/IFirmList<?> getInfo(Map<String,String> requestParameters);

}
