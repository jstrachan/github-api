/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kohsuke.github;

import java.io.IOException;

/**
 * Represents a mock GitHub service that is useful for testing
 */
public class MockGitHub extends GitHub {

    public MockGitHub() throws IOException {
        this(GitHub.GITHUB_URL, "dummyuser", null, null, HttpConnector.DEFAULT, RateLimitHandler.WAIT, AbuseLimitHandler.WAIT);
    }

    /**
     * Creates a mock github API for testing of libraries that work with github
     */
    public MockGitHub(String apiUrl, String login, String oauthAccessToken, String password, HttpConnector connector, RateLimitHandler rateLimitHandler, AbuseLimitHandler abuseLimitHandler) throws IOException {
        super(apiUrl, login, oauthAccessToken, password, connector, rateLimitHandler, abuseLimitHandler);
    }

    public GHUser addUser(GHUser user) {
        users.put(user.getLogin(), user);
        return user;
    }

    public GHOrganization addOrganisation(GHOrganization org) {
        orgs.put(org.getLogin(), org);
        return org;
    }

    public GHUser addUser(String login) {
        return addUser(new MockUser(this, login));
    }

    @Override
    Requester requester() {
        return new MockRequester(this);
    }

    @Override
    public GHCreateRepositoryBuilder createRepository(String name) {
        // TODO
        return super.createRepository(name);

    }
}
