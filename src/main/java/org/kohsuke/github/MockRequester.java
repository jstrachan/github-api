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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 */
public class MockRequester extends Requester {
    private final MockGitHub root;

    public MockRequester(MockGitHub root) {
        super(root);
        this.root = root;
    }

    @Override
    public <T> T to(String tailApiUrl, Class<T> type) throws IOException {
        if (type == null) {
            return null;
        }
        //return super.to(tailApiUrl, type);
        System.out.println("Creating a new instance of type " + type.getSimpleName());
        try {
            T value = type.newInstance();
            if (value instanceof GHRepository) {
                GHRepository repository = (GHRepository) value;
                repository.wrap(root);
            }

            populateArgs(value, getArgs());
            return value;
        } catch (Exception e) {
            throw new IOException("Failed to create " + type.getSimpleName() + ". " + e, e);
        }
    }

    protected  <T> void populateArgs(T value, List<Entry> args) {
        for (Entry arg : args) {
            System.out.println("Has argument " + arg.key + " = " + arg.value);
        }
    }

    @Override
    public <T> T to(String tailApiUrl, T existingInstance) throws IOException {
        // return super.to(tailApiUrl, existingInstance);
        return null;
    }



    @Override
    <T> Iterator<T> asIterator(String tailApiUrl, Class<T> type, int pageSize) {
        //return super.asIterator(tailApiUrl, type, pageSize);
        return Collections.EMPTY_LIST.iterator();
    }
}
