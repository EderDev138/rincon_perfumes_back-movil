package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.dto.request.LoginRequest;
import cl.perfumes.rincon_perfumes_back.dto.response.LoginResponse;

public interface AuthServicio {
    LoginResponse login(LoginRequest request);
}
