package ru.dimall.implementations;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.dimall.implementations.Firmobject;
import ru.dimall.interfaces.IFirmList;
import ru.dimall.interfaces.IGisDirectory;
import ru.dimall.interfaces.IGisFlamp;
import ru.dimall.interfaces.IHttpUrlConnection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @author  franco
 * @see IGisFlamp
 * @see Firmobject
 */
public class GisFlamp implements IGisFlamp<Firmobject> {

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
     * @param firms
     * @return
     */
    @Override
    public void fillRating(IFirmList<Firmobject> firms) {

        if (firms != null && firms.getFirms() != null && this.getConnection() != null) {

            for (Firmobject firm : firms.getFirms()) {

                try {
                    this.getConnection().setParameters("key=" + this.getConnection().getUserKey() + "&version=1.3&id=" + URLEncoder.encode(String.valueOf(firm.getId()), "UTF-8") + "&hash=" + URLEncoder.encode(firm.getHash(), "UTF-8") + "&output=json");
                    if (this.getConnection().getParameters() != null && this.getConnection().getParameters() != "") {
                        firm.setRating(this.getRatingFromResponse(this.getConnection().sendGet()));
                    }
                } catch (UnsupportedEncodingException ex) {
                    firm.setRating(0);
                } catch (Exception ex) {
                    firm.setRating(0);
                }

            }

        }

    }

    /**
     *
     * @param jsonResponse
     * @return
     */
    private int getRatingFromResponse(String jsonResponse) {

        if (jsonResponse != null) {

            JSONParser parser = new JSONParser();

            try {
                Object object = parser.parse(jsonResponse);
                return Integer.parseInt(((JSONObject) object).get("rating").toString());
            } catch (ParseException e) {
                return 0;
            }

        }

        return 0;

    }

}
