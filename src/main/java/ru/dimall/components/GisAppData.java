package ru.dimall.components;

import org.springframework.stereotype.Component;
import ru.dimall.implementations.Firmobject;
import ru.dimall.interfaces.IFirmList;
import ru.dimall.interfaces.IGisAppData;
import java.util.Map;

/**
 * Store of application parameters
 * @author  franco
 * @see IGisAppData
 */
@Component
public class GisAppData implements IGisAppData {

    private Map<String,String> data;

    /**
     * get application parameters
     * @return map of application parameters
     */
    @Override
    public Map<String, String> getData() {
        return data;
    }

    /**
     * set application parameters
     * @param data map of application parameters
     */
    @Override
    public void setData(Map<String, String> data) {
        this.data = data;
    }
}