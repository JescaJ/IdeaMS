//package com.flyhub.ideamanagementsystem;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.security.core.Authentication;
//
//import com.flyhub.ideamanagementsystem.entities.CustomUserDetails;
//
//public class CustomAccessTokenEnhancer implements TokenEnhancer{
//
//	@Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        Authentication userAuthentication = authentication.getUserAuthentication();
//        if (userAuthentication != null) {
//            Object principal = authentication.getUserAuthentication().getPrincipal();
//            if (principal instanceof CustomUserDetails) {
//                Map<String, Object> additionalInfo = new HashMap<>();
//                additionalInfo.put("userDetails", principal);
//                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//            }
//        }
//        return accessToken;
//    }
//}