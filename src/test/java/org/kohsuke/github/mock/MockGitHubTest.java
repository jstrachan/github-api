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
package org.kohsuke.github.mock;

import org.junit.Before;
import org.junit.Test;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHPersonSet;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.MockGitHub;

import java.util.Map;

/**
 */
public class MockGitHubTest {
    protected MockGitHub gitHub;
    protected String userName = "someUser";
    protected String repositoryName = "someRepository";

    @Before
    public void setUp() throws Exception {
        gitHub = new MockGitHub();

        GHUser someUser = gitHub.addUser(userName);
    }

    @Test
    public void testUser() throws Exception {
        System.out.println("Looking up user name: " + userName);
        GHUser user = gitHub.getUser(userName);

        System.out.println("Results: " + user);

        GHPersonSet<GHUser> followers = user.getFollowers();
        System.out.println("followers: " + followers);

        Map<String, GHRepository> repositories = user.getRepositories();
        System.out.println("repositories " + repositories);

        GHRepository repository = gitHub.createRepository(repositoryName).
                homepage("http://acme.com/").description("my description").autoInit(true).create();
        System.out.println("repository " + repository);

        repositories = user.getRepositories();
        System.out.println("repositories " + repositories);

    }

}
