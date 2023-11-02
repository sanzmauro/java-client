package io.split.engine.common;

import java.util.Objects;

public class FetchOptions {

    public static final Long DEFAULT_TARGET_CHANGENUMBER = -1L;

    public static class Builder {

        public Builder() {}

        public Builder(FetchOptions opts) {
            _targetCN = opts._targetCN;
            _cacheControlHeaders = opts._cacheControlHeaders;
            _fastlyDebugHeader = opts._fastlyDebugHeader;
            _flagSetsFilter = opts._flagSetsFilter;
        }

        public Builder cacheControlHeaders(boolean on) {
            _cacheControlHeaders = on;
            return this;
        }

        public Builder fastlyDebugHeader(boolean on) {
            _fastlyDebugHeader = on;
            return this;
        }

        public Builder targetChangeNumber(long targetCN) {
            _targetCN = targetCN;
            return this;
        }

        public Builder flagSetsFilter(String flagSetsFilter) {
            _flagSetsFilter = flagSetsFilter;
            return this;
        }

        public FetchOptions build() {
            return new FetchOptions(_cacheControlHeaders, _targetCN, _fastlyDebugHeader, _flagSetsFilter);
        }

        private long _targetCN = DEFAULT_TARGET_CHANGENUMBER;
        private boolean _cacheControlHeaders = false;
        private boolean _fastlyDebugHeader = false;
        private String _flagSetsFilter = "";
    }

    public boolean cacheControlHeadersEnabled() {
        return _cacheControlHeaders;
    }

    public boolean fastlyDebugHeaderEnabled() {
        return _fastlyDebugHeader;
    }

    public long targetCN() { return _targetCN; }

    public boolean hasCustomCN() { return _targetCN != DEFAULT_TARGET_CHANGENUMBER; }

    public String flagSetsFilter() {
        return _flagSetsFilter;
    }

    private FetchOptions(boolean cacheControlHeaders,
                         long targetCN,
                         boolean fastlyDebugHeader,
                         String flagSetsFilter) {
        _cacheControlHeaders = cacheControlHeaders;
        _targetCN = targetCN;
        _fastlyDebugHeader = fastlyDebugHeader;
        _flagSetsFilter = flagSetsFilter;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (!(obj instanceof FetchOptions)) return false;

        FetchOptions other = (FetchOptions) obj;

        return Objects.equals(_cacheControlHeaders, other._cacheControlHeaders)
                && Objects.equals(_fastlyDebugHeader, other._fastlyDebugHeader)
                && Objects.equals(_targetCN, other._targetCN)
                && Objects.equals(_flagSetsFilter, other._flagSetsFilter);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(_cacheControlHeaders, _fastlyDebugHeader,
                _targetCN, _flagSetsFilter);
    }

    private final boolean _cacheControlHeaders;
    private final boolean _fastlyDebugHeader;
    private final long _targetCN;
    private final String _flagSetsFilter;
}