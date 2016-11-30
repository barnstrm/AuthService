package com.cognetyx.AuthorizeService.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
