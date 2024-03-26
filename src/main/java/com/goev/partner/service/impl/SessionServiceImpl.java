package com.goev.partner.service.impl;


import com.goev.lib.dto.PasswordCredentialsDto;
import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {
    private String uuid= UUID.randomUUID().toString();
    private String savedRefreshToken = "Z29ldi1hZG1pbjoxNzEyNjc0MjQ5MTI1OjFkNjVjMTA0YTZjYTRhYTk3MGQ3ZjEwMDEwZmUyZmQ0NTQ2MDdjZjQzZGRkZjMxNTQ4NjY4ZDcxODBlYTEzODM";
    @Override
    public SessionDto createSession(PasswordCredentialsDto credentials) {
        return SessionDto.builder()
                .accessToken("eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJjYjc0ODI3YS1lMzcxLTRlNDUtYjZmZi03YTAzNWYzY2MyOGIifQ.eyJleHAiOjE3MTE1MDUwODEsImlhdCI6MTcxMTQ2OTA4MSwianRpIjoiZDdmNWYzM2QtZmI5OS00YjI4LTgzODctZmVmNjM3ZDdhMzVjIiwiaXNzIjoiaHR0cHM6Ly9zaWduaW4uc3RhZ2luZy5nby1ldm1vYmlsaXR5Lm5ldC9yZWFsbXMvbWFzdGVyIiwic3ViIjoiNGI4MTQxM2EtYzZiOC00NGE5LTgyY2QtN2ZlOTQ3ZjgyMWI1IiwidHlwIjoiU2VyaWFsaXplZC1JRCIsInNlc3Npb25fc3RhdGUiOiJmNDVkNmExZC1lNDhmLTQzMzktYTQ0Yi05Mjk0NWVhOGNmYmIiLCJzaWQiOiJmNDVkNmExZC1lNDhmLTQzMzktYTQ0Yi05Mjk0NWVhOGNmYmIiLCJzdGF0ZV9jaGVja2VyIjoicS1XZTR1YjdUM1Z1WkpXLWdpaG5XeURzN2tKQXNzTXdJNE50NkFTMHlROCJ9.brCQBvDtXfymNDHmkwTBrH3iIg8Q8k0Jd9Y6O73zr0w")
                .refreshToken(savedRefreshToken)
                .expiresIn(360000L)
                .uuid(uuid).build();
    }

    @Override
    public SessionDto refreshSessionForSessionUUID(String sessionUUID, String refreshToken) {

        if(!savedRefreshToken.equals(refreshToken))
            throw new ResponseException("Invalid Refresh Token");
        if(!uuid.equals(sessionUUID))
            throw new ResponseException("Invalid Session UUID");
        return  SessionDto.builder()
                .accessToken("eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJjYjc0ODI3YS1lMzcxLTRlNDUtYjZmZi03YTAzNWYzY2MyOGIifQ.eyJleHAiOjE3MTE1MDUxNzMsImlhdCI6MTcxMTQ2OTE3MywianRpIjoiYWQ5NTlmMTQtY2NjYS00OWI4LTkwMGUtN2RjZWNiNmJmZjA2IiwiaXNzIjoiaHR0cHM6Ly9zaWduaW4uc3RhZ2luZy5nby1ldm1vYmlsaXR5Lm5ldC9yZWFsbXMvbWFzdGVyIiwic3ViIjoiNGI4MTQxM2EtYzZiOC00NGE5LTgyY2QtN2ZlOTQ3ZjgyMWI1IiwidHlwIjoiU2VyaWFsaXplZC1JRCIsInNlc3Npb25fc3RhdGUiOiJiN2E4YzBmMy0yNmJjLTQ2M2ItYmZiMi1hNTQ0Y2QxZGIxMGUiLCJzaWQiOiJiN2E4YzBmMy0yNmJjLTQ2M2ItYmZiMi1hNTQ0Y2QxZGIxMGUiLCJzdGF0ZV9jaGVja2VyIjoiUEZNSTg2aTVsT09IYmRSWVlUTlVmRlpEb2hpR0t6V3h0aGc1UnlmT3BCUSJ9.7OZgvKWWXTc6tVAtit390ltHVEUjwgjraNDojbgYw7E")
                .refreshToken(savedRefreshToken)
                .expiresIn(360000L)
                .uuid(uuid).build();
    }

    @Override
    public SessionDetailsDto getSessionDetails(String sessionUUID) {
        if(!uuid.equals(sessionUUID))
            throw new ResponseException("Invalid Session UUID");
        return SessionDetailsDto.builder()
                .details(SessionDto.builder()
                        .uuid(uuid)
                        .email("test.partner@go-evmobility.com")
                        .phone("0000000001")
                        .firstName("Test")
                        .state("ACTIVE")
                        .build())
                .uuid(uuid)
                .build();
    }

    @Override
    public Boolean deleteSession(String sessionUUID) {
        if(!uuid.equals(sessionUUID))
            throw new ResponseException("Invalid Session UUID");
        return true;
    }

    @Override
    public List<SessionDto> getSessions() {
        return new ArrayList<>();
    }
}
