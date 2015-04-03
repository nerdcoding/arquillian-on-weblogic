/*
 * AbstractArquillianIT.java
 *
 * Copyright (c) 2015, Tobias Koltsch. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package org.nerdcoding.sample.arquillian.weblogic;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

/**
 * Abstract super class for all Arquillian based integration test.
 * <p/>
 * <ul>
 *  <li>
 *      Packages the deployment artifact (WAR).
 *  </li>
 *  <li>
 *      Defines transaction handling for integration tests. Before every test a transaction is started and after every test the transaction
 *      is rolled back.
 *  </li>
 * </ul>
 *
 * @author Tobias Koltsch
 */
@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public abstract class AbstractArquillianIT {

    private static final String DEPLOYMENT_PACKAGE = "org.nerdcoding.sample.arquillian.weblogic";

    @Deployment
    public static WebArchive createDeployment() throws Exception {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, DEPLOYMENT_PACKAGE)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
    }

}


