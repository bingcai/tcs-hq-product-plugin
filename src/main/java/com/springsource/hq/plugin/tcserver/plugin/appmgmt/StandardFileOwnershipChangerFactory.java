
package com.springsource.hq.plugin.tcserver.plugin.appmgmt;
/*
        Copyright (C) 2010-2014 Pivotal Software, Inc.


        All rights reserved. This program and the accompanying materials
        are made available under the terms of the under the Apache License,
        Version 2.0 (the "License”); you may not use this file except in compliance
        with the License. You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
 */

import org.hyperic.hq.product.PluginException;

import com.springsource.hq.plugin.tcserver.plugin.Utils;

/**
 * Standard implementations of <code>FileOwnershipChangerFactory</code>
 * <p />
 * 
 * <strong>Concurrent Semantics</strong><br />
 * 
 * Thread-safe
 * 
 */
public final class StandardFileOwnershipChangerFactory implements FileOwnershipChangerFactory {

    private final FileOwnershipChanger fileOwnershipChanger;

    public StandardFileOwnershipChangerFactory() throws PluginException {
        if ((!Utils.isWindows()) && Utils.isRoot(Utils.getAgentUser())) {
            this.fileOwnershipChanger = new ChownFileOwnershipChanger();
        } else {
            this.fileOwnershipChanger = new NoOpFileOwnershipChanger();
        }
    }

    public FileOwnershipChanger getFileOwnershipChanger() {
        return this.fileOwnershipChanger;
    }
}
