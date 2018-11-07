package easemob.server.api.impl;

import easemob.server.api.AuthTokenAPI;
import easemob.server.comm.TokenUtil;


public class EasemobAuthToken implements AuthTokenAPI {

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
