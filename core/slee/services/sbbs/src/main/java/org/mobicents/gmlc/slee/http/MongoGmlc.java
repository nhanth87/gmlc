package org.mobicents.gmlc.slee.http;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class MongoGmlc {

    private final Logger logger = Logger.getLogger(MongoGmlc.class);

    DB mongoGmlc;
    MongoClient mongoClient;
    String curlUser;

    public MongoGmlc(String mongoHost, int mongoPort, String mongoDatabase) {
        try {
            if (this.logger.isDebugEnabled()) {
                logger.debug("MongoGmlc Initialized for MongoDB {"  + mongoHost + ":" + mongoPort + "/" + mongoDatabase + " }");
            }
            mongoClient = new MongoClient(mongoHost, mongoPort);
            mongoGmlc = mongoClient.getDB(mongoDatabase);
        } catch (Exception e) {
            logger.error("Exception on MongoGmlc(String mongoHost, int mongoPort, String mongoDatabase) constructor : ", e);
        }
    }

    public String queryCurlUser(String token) {
        if (mongoGmlc != null) {
            DBObject dbObject = mongoGmlc.getCollection("curl-account").findOne(new BasicDBObject("token", token));
            if (dbObject != null) {
                if (this.logger.isDebugEnabled()) {
                    logger.debug("Token : \"" + token + "\", provided via curl has a match in MongoDB " + mongoGmlc);
                }
                String gmlcUser = (String) dbObject.get("username");
                if (gmlcUser != null) {
                    curlUser = gmlcUser;
                } else {
                    logger.warn("Token : \"" + token + "\" provided via curl has a match for the username : \"" + curlUser + "\" in MongoDB " + mongoGmlc);
                }
            } else {
                logger.warn("token \"" + token + "\" does not match any record in MongoDB " + mongoGmlc);
            }
        }
        return curlUser;
    }

    public void closeMongo() {
        try {
            mongoGmlc.getMongo().close();
        } catch (Exception e) {
            logger.error("Exception when closing connection to MongoDB");
        }
    }
}
