package easemob.server.comm;

import io.swagger.client.ApiException;

/**
 * Created by easemob on 2017/3/16.
 */
public interface EasemobAPI {
    Object invokeEasemobAPI() throws ApiException;
}