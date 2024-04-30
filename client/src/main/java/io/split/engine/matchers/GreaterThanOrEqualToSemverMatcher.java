package io.split.engine.matchers;

import io.split.engine.evaluator.EvaluationContext;

import java.util.Map;

public class GreaterThanOrEqualToSemverMatcher implements Matcher {

    private final Semver _semVer;

    public GreaterThanOrEqualToSemverMatcher(String semVer) {
        _semVer = Semver.build(semVer);
    }

    @Override
    public boolean match(Object matchValue, String bucketingKey, Map<String, Object> attributes, EvaluationContext evaluationContext) {
        if (matchValue == null) {
            return false;
        }
        Semver matchSemver = Semver.build(matchValue.toString());
        if (matchSemver == null) {
            return false;
        }

        return _semVer != null && matchSemver.Compare(_semVer) >= 0;
    }

    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr.append(">= semver ");
        bldr.append(_semVer.Version());
        return bldr.toString();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + _semVer.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof GreaterThanOrEqualToSemverMatcher)) return false;

        GreaterThanOrEqualToSemverMatcher other = (GreaterThanOrEqualToSemverMatcher) obj;

        return _semVer == other._semVer;
    }

}
