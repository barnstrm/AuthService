package com.cognetyx.AuthorizeService.jwt.extractor;

/*
 *  Implementations of this interface should always return raw base-64 encoded JWT
 */
public interface TokenExtractor {
	public String extract(String payload);
}
