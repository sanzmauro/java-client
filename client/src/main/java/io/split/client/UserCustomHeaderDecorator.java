package io.split.client;

import java.util.*;

public interface UserCustomHeaderDecorator
{
    /**
     * Get the additional headers needed for all http operations
     * @return HashMap of addition headers
     */
    Map<String, String> getHeaderOverrides();
}
