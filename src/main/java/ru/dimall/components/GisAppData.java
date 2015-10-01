package ru.dimall.components;

import org.springframework.stereotype.Component;
import ru.dimall.interfaces.IGisAppData;
import java.util.Map;

@Component
public class GisAppData implements IGisAppData {

    private Map<String,String> data;

    @Override
    public Map<String, String> getData() {
        return data;
    }

    @Override
    public void setData(Map<String, String> data) {
        this.data = data;
    }
}