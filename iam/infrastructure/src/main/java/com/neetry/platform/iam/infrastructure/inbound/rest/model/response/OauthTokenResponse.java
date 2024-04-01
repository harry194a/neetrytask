package com.neetry.platform.iam.infrastructure.inbound.rest.model.response;

import com.neetry.platform.iam.application.oauth.OauthToken;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OauthTokenResponse {

    private final String accessToken;
    private final String refreshToken;

    public OauthTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static OauthTokenResponse from(OauthToken oauthToken) {
        return new OauthTokenResponse(oauthToken.getAccessToken(), oauthToken.getRefreshToken());
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accessToken", accessToken)
                .append("refreshToken", refreshToken)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof OauthTokenResponse)) return false;

        final OauthTokenResponse that = (OauthTokenResponse) o;

        return new EqualsBuilder()
                .append(accessToken, that.accessToken)
                .append(refreshToken, that.refreshToken)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(accessToken)
                .append(refreshToken)
                .toHashCode();
    }
}
