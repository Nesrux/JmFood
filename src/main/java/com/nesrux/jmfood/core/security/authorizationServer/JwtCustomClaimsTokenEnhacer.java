package com.nesrux.jmfood.core.security.authorizationServer;

import java.util.HashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class JwtCustomClaimsTokenEnhacer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if(authentication.getPrincipal() instanceof AuthUser) {
			var authUser = (AuthUser) authentication.getPrincipal();
	
			var info = new HashMap<String, Object>();
			info.put("nome_completo", authUser.getNome());
			info.put("usuario_id", authUser.getId());
	
			var oAuth2AcessToken = (DefaultOAuth2AccessToken) accessToken;
			oAuth2AcessToken.setAdditionalInformation(info);
		}
		
		return accessToken;
	}

}
