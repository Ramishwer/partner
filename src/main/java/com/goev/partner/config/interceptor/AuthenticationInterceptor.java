package com.goev.partner.config.interceptor;


import com.goev.lib.dto.ResponseDto;
import com.goev.lib.exceptions.ResponseException;
import com.goev.lib.services.RestClient;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.detail.PartnerSessionDao;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.repository.partner.detail.PartnerSessionRepository;
import com.goev.partner.utilities.RequestContext;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
@AllArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final PartnerSessionRepository partnerSessionRepository;
    private final RestClient restClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("Authorization");

        if (token == null) {
            throw new ResponseException(401, "Invalid Access Token");
        }
        /** Code to Authenticate */

        PartnerSessionDao partnerSessionDao = partnerSessionRepository.findByUUID(RequestContext.getSessionUUID());

        if (partnerSessionDao == null)
            throw new ResponseException(401, "Invalid Session UUID");

        try {
            String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/" + partnerSessionDao.getAuthSessionUuid();
            HttpHeaders header = new HttpHeaders();
            header.set("Authorization", RequestContext.getAccessToken());
            String responseStr = restClient.get(url, header, String.class, true);
            ResponseDto<SessionDetailsDto> session = ApplicationConstants.GSON.fromJson(responseStr, new TypeToken<ResponseDto<SessionDetailsDto>>() {
            }.getType());
            SessionDetailsDto sessionDto = session.getData();
            if (sessionDto == null || sessionDto.getDetails() == null) {
                throw new ResponseException(401, "Token Expired");
            }
            request.setAttribute("authSessionUUID", sessionDto.getDetails().getUuid());
            request.setAttribute("authUUID", sessionDto.getDetails().getAuthUUID());
            request.setAttribute("organizationUUID", sessionDto.getDetails().getOrganizationUUID());
            request.setAttribute("partnerSession", partnerSessionDao);
        } catch (Exception e) {
            log.error("Error in checking token :", e);
            throw new ResponseException(401, "Invalid Token");
        }
        return true;
    }

}
