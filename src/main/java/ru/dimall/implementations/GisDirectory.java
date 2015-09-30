package ru.dimall.implementations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.dimall.implementations.Firmlist;
import ru.dimall.implementations.Firmobject;
import ru.dimall.interfaces.IFirmList;
import ru.dimall.interfaces.IGisDirectory;
import ru.dimall.interfaces.IHttpUrlConnection;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

/**
 *
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
    @Override
    public void setConnection(IHttpUrlConnection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return
     */
    @Override
    public IFirmList<Firmobject> handle(Map<String,String> parameters) {

        IFirmList<Firmobject> result = new Firmlist();

        if (this.getConnection() != null && parameters != null) {

            try {

                this.getConnection().setParameters("key=" + this.getConnection().getUserKey() + "&version=1.3&what=" + URLEncoder.encode(parameters.get("industry"), "UTF-8") + "&where=" + URLEncoder.encode(parameters.get("city"), "UTF-8") + "&sort=name&page=1&pagesize=50&output=json");

                String jsonResponse = this.getConnection().sendGet();

                if (jsonResponse != null) {

                    JSONParser parser = new JSONParser();

                    Object object = parser.parse(jsonResponse);

                    JSONObject jsonObject = (JSONObject) object;

                    JSONArray jsonFirms = (JSONArray) jsonObject.get("result");

                    if (jsonFirms != null)

                        for (Object firm : jsonFirms) {
                            JSONObject jsonFirm = (JSONObject) firm;
                            result.getFirms().add(new Firmobject(Long.parseLong((String)jsonFirm.get("id")),String.valueOf(jsonFirm.get("name")),String.valueOf(jsonFirm.get("address")), 0,String.valueOf(jsonFirm.get("hash"))));
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
