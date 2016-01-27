package ru.dimall.implementations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dimall.interfaces.IFirmList;
import ru.dimall.interfaces.IGisDirectory;
import ru.dimall.interfaces.IHttpUrlConnection;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 *
 * Kind of gis directory
 * @author  franco
 * @see IGisDirectory
 * @see Firmobject
 */

public class GisDirectory implements IGisDirectory<Firmobject> {

    private IHttpUrlConnection connection;

    /**
     *
     * @return
     */
    @Override
    public IHttpUrlConnection getConnection() {
        return connection;
    }

    /**
     *
     * @param connection
     */
    @Autowired
    @Override
    public void setConnection(IHttpUrlConnection connection) {
        this.connection = connection;
    }

    /**
     * getting organization list from 2gis service
     * @return list of organizations
     */
    @Override
    public IFirmList<Firmobject> handle(Map<String,String> parameters) {

        IFirmList<Firmobject> result = new Firmlist();

        if (this.getConnection() != null && parameters != null && parameters.get("industry") != null && parameters.get("city") != null && parameters.get("page") != null && parameters.get("pagesize") != null) {

            try {

                String city = parameters.get("city");

                this.getConnection().setParameters("key=" + this.getConnection().getUserKey() + "&version=1.3&what=" + URLEncoder.encode(parameters.get("industry"), "UTF-8") + "&where=" + URLEncoder.encode(city, "UTF-8") + "&sort=name&page=" + URLEncoder.encode(parameters.get("page"), "UTF-8") + "&pagesize=" + URLEncoder.encode(parameters.get("pagesize"), "UTF-8") + "&output=json");

                String jsonResponse = this.getConnection().sendGet();

                if (jsonResponse != null) {

                    JSONParser parser = new JSONParser();

                    Object object = parser.parse(jsonResponse);

                    JSONObject jsonObject = (JSONObject) object;

                    JSONArray jsonFirms = (JSONArray) jsonObject.get("result");

                    if (jsonFirms != null)

                        for (Object firm : jsonFirms) {
                            JSONObject jsonFirm = (JSONObject) firm;
                            result.getFirms().add(new Firmobject(Long.parseLong((String)jsonFirm.get("id")),String.valueOf(jsonFirm.get("name")),city.toUpperCase() + ", " + String.valueOf(jsonFirm.get("address")), 0,String.valueOf(jsonFirm.get("hash"))));
                        }

                }

            } catch (ParseException e) {
                result.getFirms().clear();
            } catch (UnsupportedEncodingException e) {
                result.getFirms().clear();
            }

        }

        return result;

    }

}
