/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.accumulo.test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.apache.accumulo.cluster.ClusterUser;
import org.apache.accumulo.core.client.Accumulo;
import org.apache.accumulo.core.client.AccumuloClient;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.admin.SecurityOperations;
import org.apache.accumulo.core.client.security.SecurityErrorCode;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.apache.accumulo.harness.AccumuloClusterHarness;
import org.junit.Test;

public class UsersIT extends AccumuloClusterHarness {

  @Test
  public void testCreateExistingUser() throws Exception {
    ClusterUser user0 = getUser(0);
    try (AccumuloClient client = Accumulo.newClient().from(getClientProps()).build()) {
      SecurityOperations securityOperations = client.securityOperations();
      Set<String> currentUsers = securityOperations.listLocalUsers();
      final String user0Principal = user0.getPrincipal();

      // Ensure that the user exists
      if (!currentUsers.contains(user0Principal)) {
        PasswordToken token = null;
        if (!saslEnabled()) {
          token = new PasswordToken(user0.getPassword());
        }
        securityOperations.createLocalUser(user0Principal, token);
      }

      final PasswordToken badToken = new PasswordToken("better_fail");
      var ase = assertThrows("Creating a user that already exists should throw an exception",
          AccumuloSecurityException.class,
          () -> securityOperations.createLocalUser(user0Principal, badToken));
      assertSame("Expected USER_EXISTS error", SecurityErrorCode.USER_EXISTS,
          ase.getSecurityErrorCode());
      String msg = ase.getMessage();
      assertTrue("Error message didn't contain principal: '" + msg + "'",
          msg.contains(user0Principal));
    }
  }

}
