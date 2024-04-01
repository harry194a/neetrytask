package com.neetry.platform.iam.application.oauth;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OauthToken {

    private final String accessToken;
    private final String refreshToken;

    private OauthToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static OauthToken bearerTokenOf(String accessToken, String refreshToken) {
        return new OauthToken(accessToken, refreshToken);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof OauthToken)) return false;

        final OauthToken that = (OauthToken) o;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accessToken", accessToken)
                .append("refreshToken", refreshToken)
                .toString();
    }
}
